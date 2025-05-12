package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comunidad {
    private String id;
    private String nombre;
    private String poblacion;
    private List<Zona> zonas = new ArrayList<>();
    private List<Propiedad> propiedades = new ArrayList<>();
    private List<Propietario> propietarios = new ArrayList<>();
    private List<Gasto> gastos = new ArrayList<>();
    private Map<String, List<Cuota>> cuotasPorPropiedad = new HashMap<>();
    private Map<String, List<Cuota>> cuotasPorPropietario = new HashMap<>();

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getPoblacion() { return poblacion; }
    public void setPoblacion(String poblacion) { this.poblacion = poblacion; }
    
    public List<Zona> getZonas() { return zonas; }
    public void setZonas(List<Zona> zonas) { this.zonas = zonas; }
    
    public List<Propiedad> getPropiedades() { return propiedades; }
    public void setPropiedades(List<Propiedad> propiedades) { this.propiedades = propiedades; }
    
    public List<Propietario> getPropietarios() { return propietarios; }
    public void setPropietarios(List<Propietario> propietarios) { this.propietarios = propietarios; }
    
    public List<Gasto> getGastos() { return gastos; }
    public void setGastos(List<Gasto> gastos) { this.gastos = gastos; }
    
    public Map<String, List<Cuota>> getCuotasPorPropiedad() { return cuotasPorPropiedad; }
    public void setCuotasPorPropiedad(Map<String, List<Cuota>> cuotasPorPropiedad) { 
        this.cuotasPorPropiedad = cuotasPorPropiedad; 
    }
    
    public Map<String, List<Cuota>> getCuotasPorPropietario() { return cuotasPorPropietario; }
    public void setCuotasPorPropietario(Map<String, List<Cuota>> cuotasPorPropietario) { 
        this.cuotasPorPropietario = cuotasPorPropietario; 
    }
    
    // Métodos de ayuda
    public Zona getZonaById(String idZona) {
        return zonas.stream()
                   .filter(z -> z.getId().equals(idZona))
                   .findFirst()
                   .orElse(null);
    }
    
    public Propietario getPropietarioById(String idPropietario) {
        return propietarios.stream()
                          .filter(p -> p.getId().equals(idPropietario))
                          .findFirst()
                          .orElse(null);
    }
    
    public List<Propiedad> getPropiedadesByPropietario(String idPropietario) {
        List<Propiedad> result = new ArrayList<>();
        for (Propiedad propiedad : propiedades) {
            if (propiedad.getPropietarioId().equals(idPropietario)) {
                result.add(propiedad);
            }
        }
        return result;
    }
}