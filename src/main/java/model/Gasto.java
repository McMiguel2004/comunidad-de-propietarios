package model;

import java.math.BigDecimal;

public class Gasto {
    private String id;
    private String descripcion;
    private BigDecimal importe;
    private String zonaId;
    
    public Gasto() {}
    
    public Gasto(String id, String descripcion, BigDecimal importe, String zonaId) {
        this.id = id;
        this.descripcion = descripcion;
        this.importe = importe;
        this.zonaId = zonaId;
    }
    
    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public BigDecimal getImporte() { return importe; }
    public void setImporte(BigDecimal importe) { this.importe = importe; }
    
    public String getZonaId() { return zonaId; }
    public void setZonaId(String zonaId) { this.zonaId = zonaId; }
}