<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestión de Comunidad de Propietarios</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Gestión de Comunidad de Propietarios</h1>
        
        <% if (request.getAttribute("error") != null) { %>
            <div class="error">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        
        <form action="${pageContext.request.contextPath}/procesar-comunidad" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="comunidadFile">Archivo de comunidad:</label>
                <input type="file" id="comunidadFile" name="comunidadFile" required>
            </div>
            
            <div class="form-group">
                <label for="gastosFile">Archivo de gastos:</label>
                <input type="file" id="gastosFile" name="gastosFile" required>
            </div>
            
            <button type="submit" class="btn">Procesar</button>
        </form>
    </div>
</body>
</html>