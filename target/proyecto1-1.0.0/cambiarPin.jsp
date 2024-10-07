<%-- 
    Document   : cambiarPin
    Created on : 24 sep. 2024, 23:36:55
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cambiar PIN - Sistema Bancario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #ff9966;
            color: white;
            padding: 20px;
            text-align: center;
        }
        h1 {
            margin: 0;
            font-size: 2.5em;
        }
        form {
            max-width: 600px;
            margin: 40px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #ff9966;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 1.2em;
        }
        input[type="submit"]:hover {
            background-color: #e68a5d;
        }
        .error {
            color: red;
            font-size: 0.9em;
            margin-bottom: 20px;
        }
    </style>

</head>
<body>
    <header>
        <h1>Cambiar PIN</h1>
    </header>

    <form action="cambiarPINServlet" method="post">
        <label for="cuenta">Número de Cuenta:</label>
        <input type="text" id="cuenta" name="cuenta" maxlength="7" required>

        <label for="pinActual">PIN Actual:</label>
        <input type="password" id="pinActual" name="pinActual" maxlength="7" required>

        <label for="nuevoPin">Nuevo PIN:</label>
        <input type="password" id="nuevoPin" name="nuevoPin" maxlength="7" required>

        <input type="submit" value="Cambiar PIN">
        <a href="index.jsp" class="account-option">Volver al Inicio</a>

    </form>
</body>
</html>

