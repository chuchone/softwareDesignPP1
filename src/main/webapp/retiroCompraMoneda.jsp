<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="disenioProyecto1.integracion.ConexionBCCR" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Retiro en Dólares - Sistema Bancario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e6f7ff;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #66b3ff;
            color: #4d4d4d;
            padding: 20px;
            text-align: center;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        h1 {
            margin: 0;
            font-size: 2.5em;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin: 40px auto;
            max-width: 600px;
        }
        form {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="number"], input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
        }
        .submit-button {
            background-color: #66b3ff;
            color: white;
            border: none;
            padding: 15px 30px;
            border-radius: 5px;
            font-size: 1.2em;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .submit-button:hover {
            background-color: #0066cc;
        }
        .back-button {
            background-color: #cce6ff;
            color: #4d4d4d;
            text-align: center;
            padding: 15px 30px;
            margin-top: 20px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 1.2em;
            transition: background-color 0.3s ease;
        }
        .back-button:hover {
            background-color: #66b3ff;
        }
        .exchange-rate {
            background-color: #f0f8ff;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center;
            font-size: 1.2em;
            color: #0066cc;
        }
    </style>
</head>
<body>
    <header>
        <h1>Retiro en Dólares</h1>
    </header>
    <div class="container">
        <div class="exchange-rate">
            Tipo de Cambio (Venta): ₡ <%= ConexionBCCR.obtenerTipoCambio("317") %>
        </div>
        <form action="ProcesarRetiroDolaresServlet" method="post">
            <div class="form-group">
                <label for="numeroCuenta">Número de Cuenta:</label>
                <input type="text" id="numeroCuenta" name="numeroCuenta" required>
            </div>
            <div class="form-group">
                <label for="pin">PIN:</label>
                <input type="password" id="pin" name="pin" required>
            </div>
            <div class="form-group">
                <label for="monto">Monto a Retirar (en Dólares):</label>
                <input type="number" id="monto" name="monto" min="0" step="0.01" required>
            </div>
            <button type="submit" class="submit-button">Realizar Retiro</button>
        </form>
        <a href="index.jsp" class="back-button">Volver al inicio</a>
    </div>
</body>
</html>