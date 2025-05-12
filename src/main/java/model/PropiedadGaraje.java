package model;

public class PropiedadGaraje extends Propiedad {
    private boolean abierta; // O: true, T: false
    private boolean conTraster; // S: true, N: false
    
    public PropiedadGaraje() {}
    
    public PropiedadGaraje(String codigo, int metrosCuadrados, String propietarioId, 
                           boolean abierta, boolean conTraster) {
        super(codigo, metrosCuadrados, propietarioId);
        this.abierta = abierta;
        this.conTraster = conTraster;
    }
    
    // Getters y setters
    public boolean isAbierta() { return abierta; }
    public void setAbierta(boolean abierta) { this.abierta = abierta; }
    
    public boolean isConTraster() { return conTraster; }
    public void setConTraster(boolean conTraster) { this.conTraster = conTraster; }
    
    @Override
    public String getTipo() {
        return "Garaje";
    }
    
    @Override
    public String getInfoAdicional() {
        return (abierta ? "Oberta" : "Tancada") + 
               ", " + (conTraster ? "amb Traster" : "sense Traster");
    }
}