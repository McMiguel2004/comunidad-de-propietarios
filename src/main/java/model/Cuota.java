package model;

import java.math.BigDecimal;

public class Cuota {
    private String zonaId;
    private BigDecimal porcentaje;
    private BigDecimal importe;
    
    public Cuota() {}
    
    public Cuota(String zonaId, BigDecimal porcentaje, BigDecimal importe) {
        this.zonaId = zonaId;
        this.porcentaje = porcentaje;
        this.importe = importe;
    }
    
    // Getters y setters
    public String getZonaId() { return zonaId; }
    public void setZonaId(String zonaId) { this.zonaId = zonaId; }
    
    public BigDecimal getPorcentaje() { return porcentaje; }
    public void setPorcentaje(BigDecimal porcentaje) { this.porcentaje = porcentaje; }
    
    public BigDecimal getImporte() { return importe; }
    public void setImporte(BigDecimal importe) { this.importe = importe; }
}