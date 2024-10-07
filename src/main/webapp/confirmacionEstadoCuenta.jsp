<%-- 
    Document   : confirmacionEstadoCuenta
    Created on : 6 oct. 2024, 20:03:14
    Author     : Nelson
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Confirmación de Envío de Estado de Cuenta</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 50%;
                margin: 100px auto;
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                text-align: center;
            }
            h1 {
                color: #4CAF50;
            }
            p {
                font-size: 18px;
            }
            a {
                display: inline-block;
                margin-top: 20px;
                padding: 10px 20px;
                background-color: #4CAF50;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            }
            a:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>¡Envío exitoso!</h1>
            <p>El estado de cuenta ha sido enviado correctamente a tu correo.</p>
            <p>Por favor revisa tu bandeja de entrada.</p>
            <a href="index.jsp">Volver al inicio</a>
        </div>
    </body>
</html>
