package util;

import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileParser {

    public Comunidad parseComunidad(InputStream inputStream) throws IOException {
        Comunidad comunidad = new Comunidad();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String currentSection = null;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith(".")) {
                continue; // Saltar líneas vacías o comentarios
            }

            if (line.startsWith("#")) {
                currentSection = line.substring(1).trim();
                continue;
            }

            switch (currentSection) {
                case "Comunitat":
                    parseComunidadLine(comunidad, line);
                    break;
                case "Zona":
                    parseZonaLine(comunidad, line);
                    break;
                case "Propietat":
                    parsePropiedadLine(comunidad, line);
                    break;
                case "Propietari":
                    parsePropietarioLine(comunidad, line);
                    break;
            }
        }

        reader.close();
        return comunidad;
    }

    public List<Gasto> parseGastos(InputStream inputStream) throws IOException {
        List<Gasto> gastos = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        boolean inDespesesSection = false;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith(".")) {
                continue;
            }

            if (line.startsWith("#Despeses") || line.startsWith("#Pressupost")) {
                inDespesesSection = true;
                continue;
            }

            if (inDespesesSection) {
                String[] parts = line.split(";");
                if (parts.length >= 4) {
                    String id = parts[0].trim();
                    String descripcion = parts[1].trim();
                    BigDecimal importe = new BigDecimal(parts[2].trim());
                    String zonaId = parts[3].trim();

                    gastos.add(new Gasto(id, descripcion, importe, zonaId));
                }
            }
        }

        reader.close();
        return gastos;
    }

    private void parseComunidadLine(Comunidad comunidad, String line) {
        String[] parts = line.split(";");
        if (parts.length >= 3) {
            comunidad.setId(parts[0].trim());
            comunidad.setNombre(parts[1].trim());
            comunidad.setPoblacion(parts[2].trim());
        }
    }

    private void parseZonaLine(Comunidad comunidad, String line) {
        String[] parts = line.split(";");
        if (parts.length >= 3) {
            String id = parts[0].trim();
            String nombre = parts[1].trim();
            char tipoReparto = parts[2].trim().charAt(0);

            comunidad.getZonas().add(new Zona(id, nombre, tipoReparto));
        }
    }

    private void parsePropiedadLine(Comunidad comunidad, String line) {
        String[] parts = line.split(";");
        if (parts.length < 5) return;

        String tipo = parts[0].trim();
        String codigo = parts[1].trim();
        int metros = Integer.parseInt(parts[2].trim());
        String propietarioId = parts[3].trim();
        String porcentajesStr = parts[4].trim();

        // Parsear porcentajes por zona
        Map<String, Integer> porcentajes = new HashMap<>();
        String[] porcentajesArray = porcentajesStr.split(",");
        for (String porcentaje : porcentajesArray) {
            String[] zonaPorcentaje = porcentaje.split("-");
            if (zonaPorcentaje.length == 2) {
                String zonaId = zonaPorcentaje[0].trim();
                int valorPorcentaje = Integer.parseInt(zonaPorcentaje[1].trim());
                porcentajes.put(zonaId, valorPorcentaje);
            }
        }

        // Crear propiedad según el tipo
        Propiedad propiedad = null;
        switch (tipo) {
            case "P": // Piso
                if (parts.length >= 7) {
                    boolean habitual = parts[5].trim().equals("HH");
                    int numDormitorios = Integer.parseInt(parts[6].trim());
                    propiedad = new PropiedadPiso(codigo, metros, propietarioId, habitual, numDormitorios);
                }
                break;
            case "L": // Local
                if (parts.length >= 6) {
                    String nombreComercial = parts[5].trim();
                    String actividad = parts.length > 6 ? parts[6].trim() : "";
                    propiedad = new PropiedadLocal(codigo, metros, propietarioId, nombreComercial, actividad);
                }
                break;
            case "G": // Garaje
                if (parts.length >= 6) {
                    boolean abierta = parts[5].trim().equals("O");
                    boolean conTraster = parts[6].trim().equals("S");
                    propiedad = new PropiedadGaraje(codigo, metros, propietarioId, abierta, conTraster);
                }
                break;
        }

        if (propiedad != null) {
            propiedad.setPorcentajesPorZona(porcentajes);
            comunidad.getPropiedades().add(propiedad);
        }
    }

    private void parsePropietarioLine(Comunidad comunidad, String line) {
        String[] parts = line.split(";");
        if (parts.length >= 4) {
            String id = parts[0].trim();
            String nombre = parts[1].trim();
            String poblacion = parts[2].trim();
            String email = parts[3].trim();

            comunidad.getPropietarios().add(new Propietario(id, nombre, poblacion, email));
        }
    }
}