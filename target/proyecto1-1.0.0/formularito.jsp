<%-- 
    Document   : formularito
    Created on : 2 oct. 2024, 01:58:44
    Author     : Nelson
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Verificar Mensaje</title>
</head>
<body>
    <h2>Verificación del Mensaje</h2>
    <form action="ConfirmarCodigo" method="post">
        <label for="codigo">Ingrese el código recibido:</label>
        <input type="text" id="codigo" name="codigo" required>
        <br><br>
        <input type="submit" value="Confirmar">
    </form>
</body>
</html>
