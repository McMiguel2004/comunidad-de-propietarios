package model;

import java.util.HashMap;
import java.util.Map;

public abstract class Propiedad {
    protected String codigo;
    protected int metrosCuadrados;
    protected String propietarioId;
    protected Map<String, Integer> porcentajesPorZona = new HashMap<>();
    
    public Propiedad() {}
    
    public Propiedad(String codigo, int metrosCuadrados, String propietarioId) {
        this.codigo = codigo;
        this.metrosCuadrados = metrosCuadrados;
        this.propietarioId = propietarioId;
    }
    
    // Getters y setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public int getMetrosCuadrados() { return metrosCuadrados; }
    public void setMetrosCuadrados(int metrosCuadrados) { this.metrosCuadrados = metrosCuadrados; }
    
    public String getPropietarioId() { return propietarioId; }
    public void setPropietarioId(String propietarioId) { this.propietarioId = propietarioId; }
    
    public Map<String, Integer> getPorcentajesPorZona() { return porcentajesPorZona; }
    public void setPorcentajesPorZona(Map<String, Integer> porcentajesPorZona) { 
        this.porcentajesPorZona = porcentajesPorZona; 
    }
    
    public void addPorcentajeZona(String zonaId, int porcentaje) {
        porcentajesPorZona.put(zonaId, porcentaje);
    }
    
    public abstract String getTipo();
    public abstract String getInfoAdicional();
}