<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listado de Propietarios</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Listado de Propietarios</h1>
        
        <% Comunidad comunidad = (Comunidad) session.getAttribute("comunidad"); %>
        
        <table>
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Propiedades</th>
                </tr>
            </thead>
            <tbody>
                <% for (Propietario propietario : comunidad.getPropietarios()) { 
                    List<Propiedad> propiedades = comunidad.getPropiedadesByPropietario(propietario.getId());
                %>
                    <tr>
                        <td><%= propietario.getId() %></td>
                        <td><%= propietario.getNombre() %></td>
                        <td><%= propietario.getEmail() %></td>
                        <td>
                            <% for (int i = 0; i < propiedades.size(); i++) { %>
                                <%= propiedades.get(i).getCodigo() %>
                                <% if (i < propiedades.size() - 1) { %>, <% } %>
                            <% } %>
                        </td>
                    </tr>
                <% } %>
            </tbody>
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