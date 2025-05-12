package model;

public class Propietario {
    private String id;
    private String nombre;
    private String poblacion;
    private String email;
    
    public Propietario() {}
    
    public Propietario(String id, String nombre, String poblacion, String email) {
        this.id = id;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.email = email;
    }
    
    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getPoblacion() { return poblacion; }
    public void setPoblacion(String poblacion) { this.poblacion = poblacion; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}