package service;

import model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComunidadService {

    public void calcularCuotas(Comunidad comunidad) {
        // Calcular cuotas por propiedad
        calcularCuotasPorPropiedad(comunidad);
        
        // Calcular cuotas por propietario
        calcularCuotasPorPropietario(comunidad);
    }

    private void calcularCuotasPorPropiedad(Comunidad comunidad) {
        Map<String, List<Cuota>> cuotasPorPropiedad = new HashMap<>();
        
        for (Propiedad propiedad : comunidad.getPropiedades()) {
            List<Cuota> cuotas = new ArrayList<>();
            
            for (Gasto gasto : comunidad.getGastos()) {
                Zona zona = comunidad.getZonaById(gasto.getZonaId());
                if (zona == null) continue;
                
                BigDecimal porcentaje = calcularPorcentaje(comunidad, propiedad, zona);
                if (porcentaje.compareTo(BigDecimal.ZERO) <= 0) continue;
                
                BigDecimal importe = gasto.getImporte()
                                        .multiply(porcentaje)
                                        .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                
                Cuota cuota = new Cuota(zona.getId(), porcentaje, importe);
                cuotas.add(cuota);
            }
            
            cuotasPorPropiedad.put(propiedad.getCodigo(), cuotas);
        }
        
        comunidad.setCuotasPorPropiedad(cuotasPorPropiedad);
    }

    private void calcularCuotasPorPropietario(Comunidad comunidad) {
        Map<String, List<Cuota>> cuotasPorPropietario = new HashMap<>();
        
        for (Propietario propietario : comunidad.getPropietarios()) {
            List<Cuota> cuotasPropietario = new ArrayList<>();
            Map<String, Cuota> cuotasPorZona = new HashMap<>();
            
            // Obtener todas las propiedades del propietario
            List<Propiedad> propiedades = comunidad.getPropiedadesByPropietario(propietario.getId());
            
            // Sumar cuotas de todas sus propiedades
            for (Propiedad propiedad : propiedades) {
                List<Cuota> cuotasPropiedad = comunidad.getCuotasPorPropiedad().get(propiedad.getCodigo());
                if (cuotasPropiedad == null) continue;
                
                for (Cuota cuota : cuotasPropiedad) {
                    Cuota cuotaExistente = cuotasPorZona.get(cuota.getZonaId());
                    if (cuotaExistente == null) {
                        cuotasPorZona.put(cuota.getZonaId(), 
                            new Cuota(cuota.getZonaId(), cuota.getPorcentaje(), cuota.getImporte()));
                    } else {
                        cuotaExistente.setPorcentaje(cuotaExistente.getPorcentaje().add(cuota.getPorcentaje()));
                        cuotaExistente.setImporte(cuotaExistente.getImporte().add(cuota.getImporte()));
                    }
                }
            }
            
            cuotasPropietario.addAll(cuotasPorZona.values());
            cuotasPorPropietario.put(propietario.getId(), cuotasPropietario);
        }
        
        comunidad.setCuotasPorPropietario(cuotasPorPropietario);
    }

    private BigDecimal calcularPorcentaje(Comunidad comunidad, Propiedad propiedad, Zona zona) {
        if (zona.getTipoReparto() == 'P') {
            // Reparto proporcional - usar el porcentaje definido en la propiedad
            Integer porcentaje = propiedad.getPorcentajesPorZona().get(zona.getId());
            return porcentaje != null ? new BigDecimal(porcentaje) : BigDecimal.ZERO;
        } else {
            // Reparto igualitario - dividir entre todas las propiedades de la zona
            long numPropiedadesEnZona = comunidad.getPropiedades().stream()
                .filter(p -> p.getPorcentajesPorZona().containsKey(zona.getId()))
                .count();
            
            if (numPropiedadesEnZona == 0) return BigDecimal.ZERO;
            
            // Calcular porcentaje (100% / numPropiedades) redondeado hacia arriba
            BigDecimal porcentaje = new BigDecimal("100")
                .divide(new BigDecimal(numPropiedadesEnZona), 0, RoundingMode.UP);
            
            // Verificar si la propiedad pertenece a esta zona
            return propiedad.getPorcentajesPorZona().containsKey(zona.getId()) ? porcentaje : BigDecimal.ZERO;
        }
    }
}