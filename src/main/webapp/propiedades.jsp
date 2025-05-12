<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listado de Propiedades</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Listado de Propiedades</h1>
        
        <% Comunidad comunidad = (Comunidad) session.getAttribute("comunidad"); %>
        
        <table>
            <thead>
                <tr>
                    <th>Código</th>
                    <th>m²</th>
                    <th>Propietario</th>
                    <th>Zonas/Quotes</th>
                    <th>Información Adicional</th>
                </tr>
            </thead>
            <tbody>
                <% for (Propiedad propiedad : comunidad.getPropiedades()) { 
                    Propietario propietario = comunidad.getPropietarioById(propiedad.getPropietarioId());
                %>
                    <tr>
                        <td><%= propiedad.getCodigo() %></td>
                        <td><%= propiedad.getMetrosCuadrados() %></td>
                        <td><%= propietario != null ? propietario.getNombre() : "Desconocido" %></td>
                        <td>
                            <% for (Map.Entry<String, Integer> entry : propiedad.getPorcentajesPorZona().entrySet()) { 
                                Zona zona = comunidad.getZonaById(entry.getKey());
                            %>
                                <%= entry.getValue() %>%<%= zona != null ? zona.getNombre() : entry.getKey() %> 
                                <% if (!entry.equals(propiedad.getPorcentajesPorZona().entrySet().toArray()[propiedad.getPorcentajesPorZona().size()-1])) { %>
                                    , 
                                <% } %>
                            <% } %>
                        </td>
                        <td><%= propiedad.getInfoAdicional() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        
        <div class="footer">
            <%= comunidad.getPropiedades().size() %> propiedades total
        </div>
        
        <div class="navigation">
            <a href="resumen.jsp" class="btn">Volver al Resumen</a>
            <a href="index.jsp" class="btn">Inicio</a>
        </div>
    </div>
</body>
</html>