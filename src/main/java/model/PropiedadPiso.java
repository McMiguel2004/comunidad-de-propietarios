package model;

public class PropiedadPiso extends Propiedad {
    private boolean habitual; // HH: true, HNH: false
    private int numDormitorios;
    
    public PropiedadPiso() {}
    
    public PropiedadPiso(String codigo, int metrosCuadrados, String propietarioId, 
                        boolean habitual, int numDormitorios) {
        super(codigo, metrosCuadrados, propietarioId);
        this.habitual = habitual;
        this.numDormitorios = numDormitorios;
    }
    
    // Getters y setters
    public boolean isHabitual() { return habitual; }
    public void setHabitual(boolean habitual) { this.habitual = habitual; }
    
    public int getNumDormitorios() { return numDormitorios; }
    public void setNumDormitorios(int numDormitorios) { this.numDormitorios = numDormitorios; }
    
    @Override
    public String getTipo() {
        return "Piso";
    }
    
    @Override
    public String getInfoAdicional() {
        return (habitual ? "Habitatge habitual" : "Habitatge no habitual") + 
               ", " + numDormitorios + " dormitoris";
    }
}