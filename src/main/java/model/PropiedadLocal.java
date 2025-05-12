package model;

public class PropiedadLocal extends Propiedad {
    private String nombreComercial;
    private String actividad;
    
    public PropiedadLocal() {}
    
    public PropiedadLocal(String codigo, int metrosCuadrados, String propietarioId, 
                         String nombreComercial, String actividad) {
        super(codigo, metrosCuadrados, propietarioId);
        this.nombreComercial = nombreComercial;
        this.actividad = actividad;
    }
    
    // Getters y setters
    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }
    
    public String getActividad() { return actividad; }
    public void setActividad(String actividad) { this.actividad = actividad; }
    
    @Override
    public String getTipo() {
        return "Local";
    }
    
    @Override
    public String getInfoAdicional() {
        return nombreComercial + " - " + actividad;
    }
}