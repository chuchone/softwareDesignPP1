<%-- 
    Document   : AccesoBitacoras
    Created on : 9 nov. 2024, 05:01:07
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Acceso a Bitácoras</title>
</head>
<body>
    <h1>Funcion de administrador. ¿Deseas acceder a las bitácoras, se tomarán imagenes/videos de su persona para corroborar su identidad?</h1>
    <form action="AccesoBitacorasServlet" method="post">
        <input type="text" name="testInput" placeholder="Prueba">
        <button type="submit">Acceder a bitácoras</button>
    </form>

</body>
</html>
