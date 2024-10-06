<%-- 
    Document   : errorCambiarPin
    Created on : 1 oct. 2024, 04:34:38
    Author     : Nelson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error al Cambiar PIN</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f4f4f4;
            margin-top: 50px;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            display: inline-block;
        }
        .error-message {
            color: #e74c3c;
            font-size: 24px;
            margin-bottom: 20px;
        }
        button {
            background-color: #3498db;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="error-message">Error al cambiar el PIN</h1>
        <p>Ha ocurrido un problema al intentar cambiar su PIN. Por favor, inténtelo de nuevo.</p>
        <form action="cambiarPin.jsp" method="get">
            <button type="submit">Volver a intentar</button>
            <a href="index.jsp" class="consult-option">Volver al Inicio</a>
        </form>
    </div>
</body>
</html>