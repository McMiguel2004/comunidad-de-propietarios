package model;

public class Zona {
    private String id;
    private String nombre;
    private char tipoReparto; // 'P' para proporcional, 'I' para igualitario
    
    public Zona() {}
    
    public Zona(String id, String nombre, char tipoReparto) {
        this.id = id;
        this.nombre = nombre;
        this.tipoReparto = tipoReparto;
    }
    
    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public char getTipoReparto() { return tipoReparto; }
    public void setTipoReparto(char tipoReparto) { this.tipoReparto = tipoReparto; }
}