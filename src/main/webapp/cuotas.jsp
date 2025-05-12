<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reparto de Cuotas</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Reparto de Cuotas</h1>
        
        <% Comunidad comunidad = (Comunidad) session.getAttribute("comunidad"); %>
        
        <h2>Cuotas por Propiedad</h2>
        <table>
            <thead>
                <tr>
                    <th>Propiedad</th>
                    <th>Propietario</th>
                    <% for (Zona zona : comunidad.getZonas()) { %>
                        <th>% <%= zona.getNombre() %></th>
                        <th>Importe <%= zona.getNombre() %></th>
                    <% } %>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <% 
                Map<String, BigDecimal> totalesPorZona = new HashMap<>();
                BigDecimal granTotal = BigDecimal.ZERO;
                
                for (Propiedad propiedad : comunidad.getPropiedades()) { 
                    Propietario propietario = comunidad.getPropietarioById(propiedad.getPropietarioId());
                    List<Cuota> cuotas = comunidad.getCuotasPorPropiedad().get(propiedad.getCodigo());
                    
                    BigDecimal totalPropiedad = BigDecimal.ZERO;
                %>
                    <tr>
                        <td><%= propiedad.getCodigo() %></td>
                        <td><%= propietario != null ? propietario.getNombre() : "Desconocido" %></td>
                        
                        <% for (Zona zona : comunidad.getZonas()) { 
                            BigDecimal porcentaje = BigDecimal.ZERO;
                            BigDecimal importe = BigDecimal.ZERO;
                            
                            if (cuotas != null) {
                                for (Cuota cuota : cuotas) {
                                    if (cuota.getZonaId().equals(zona.getId())) {
                                        porcentaje = cuota.getPorcentaje();
                                        importe = cuota.getImporte();
                                        totalPropiedad = totalPropiedad.add(importe);
                                        
                                        // Acumular totales por zona
                                        BigDecimal totalZona = totalesPorZona.getOrDefault(zona.getId(), BigDecimal.ZERO);
                                        totalesPorZona.put(zona.getId(), totalZona.add(importe));
                                        break;
                                    }
                                }
                            }
                        %>
                            <td><%= porcentaje.compareTo(BigDecimal.ZERO) > 0 ? porcentaje.intValue() + "%" : "-" %></td>
                            <td><%= importe.compareTo(BigDecimal.ZERO) > 0 ? String.format("%.2f", importe) : "-" %></td>
                        <% } %>
                        
                        <td><%= String.format("%.2f", totalPropiedad) %></td>
                    </tr>
                    <% granTotal = granTotal.add(totalPropiedad); %>
                <% } %>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="2">Totales</td>
                    <% for (Zona zona : comunidad.getZonas()) { %>
                        <td></td>
                        <td><%= String.format("%.2f", totalesPorZona.getOrDefault(zona.getId(), BigDecimal.ZERO)) %></td>
                    <% } %>
                    <td><%= String.format("%.2f", granTotal) %></td>
                </tr>
            </tfoot>
        </table>
        
        <div class="footer">
            <%= comunidad.getPropiedades().size() %> propiedades total
        </div>
        
        <h2>Cuotas por Propietario</h2>
        <table>
            <thead>
                <tr>
                    <th>Propietario</th>
                    <% for (Zona zona : comunidad.getZonas()) { %>
                        <th>% <%= zona.getNombre() %></th>
                        <th>Importe <%= zona.getNombre() %></th>
                    <% } %>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <% 
                totalesPorZona.clear();
                granTotal = BigDecimal.ZERO;
                
                for (Propietario propietario : comunidad.getPropietarios()) { 
                    List<Cuota> cuotas = comunidad.getCuotasPorPropietario().get(propietario.getId());
                    
                    BigDecimal totalPropietario = BigDecimal.ZERO;
                %>
                    <tr>
                        <td><%= propietario.getNombre() %></td>
                        
                        <% for (Zona zona : comunidad.getZonas()) { 
                            BigDecimal porcentaje = BigDecimal.ZERO;
                            BigDecimal importe = BigDecimal.ZERO;
                            
                            if (cuotas != null) {
                                for (Cuota cuota : cuotas) {
                                    if (cuota.getZonaId().equals(zona.getId())) {
                                        porcentaje = cuota.getPorcentaje();
                                        importe = cuota.getImporte();
                                        totalPropietario = totalPropietario.add(importe);
                                        
                                        // Acumular totales por zona
                                        BigDecimal totalZona = totalesPorZona.getOrDefault(zona.getId(), BigDecimal.ZERO);
                                        totalesPorZona.put(zona.getId(), totalZona.add(importe));
                                        break;
                                    }
                                }
                            }
                        %>
                            <td><%= porcentaje.compareTo(BigDecimal.ZERO) > 0 ? porcentaje.intValue() + "%" : "-" %></td>
                            <td><%= importe.compareTo(BigDecimal.ZERO) > 0 ? String.format("%.2f", importe) : "-" %></td>
                        <% } %>
                        
                        <td><%= String.format("%.2f", totalPropietario) %></td>
                    </tr>
                    <% granTotal = granTotal.add(totalPropietario); %>
                <% } %>
            </tbody>
            <tfoot>
                <tr>
                    <td>Totales</td>
                    <% for (Zona zona : comunidad.getZonas()) { %>
                        <td></td>
                        <td><%= String.format("%.2f", totalesPorZona.getOrDefault(zona.getId(), BigDecimal.ZERO)) %></td>
                    <% } %>
                    <td><%= String.format("%.2f", granTotal) %></td>
                </tr>
            </tfoot>
        </table>
        
        <div class="footer">
            <%= comunidad.getPropietarios().size() %> propietarios total
        </div>
        
        <div class="navigation">
            <a href="resumen.jsp" class="btn">Volver al Resumen</a>
            <a href="index.jsp" class="btn">Inicio</a>
        </div>
    </div>
</body>
</html>