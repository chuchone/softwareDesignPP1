<%-- 
    Document   : verBitacoras
    Created on : 9 nov. 2024, 19:39:07
    Author     : Nelson
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="disenioProyecto1.modelo.bitacoras.Bitacoras" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ver Bitácoras</title>
</head>
<body>
    <h1>Registro de Bitácoras</h1>
    <!-- Botones para descargar archivos en diferentes formatos -->
    <form action="AccesoBitacorasServlet" method="get">
        <button type="submit" name="formato" value="json">Descargar en JSON</button>
        <button type="submit" name="formato" value="xml">Descargar en XML</button>
        <button type="submit" name="formato" value="csv">Descargar en CSV</button>
    </form>
</body>
</html>
