<%@ page import="model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resumen de la Comunidad</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Resumen de la Comunidad</h1>
        
        <% Comunidad comunidad = (Comunidad) session.getAttribute("comunidad"); %>
        
        <div class="summary">
            <ul>
                <li>Comunidad: <%= comunidad.getId() %> - <%= comunidad.getNombre() %></li>
                <li>Población: <%= comunidad.getPoblacion() %></li>
                <li>Número de zonas: <%= comunidad.getZonas().size() %></li>
                <li>Número de propiedades: <%= comunidad.getPropiedades().size() %></li>
                <li>Número de propietarios: <%= comunidad.getPropietarios().size() %></li>
                <li>Número de gastos: <%= comunidad.getGastos().size() %></li>
            </ul>
        </div>
        
        <div class="navigation">
            <a href="propiedades.jsp" class="btn">Ver Propiedades</a>
            <a href="propietarios.jsp" class="btn">Ver Propietarios</a>
            <a href="cuotas.jsp" class="btn">Ver Cuotas</a>
            <a href="index.jsp" class="btn">Volver</a>
        </div>
    </div>
</body>
</html>