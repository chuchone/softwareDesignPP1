<%-- 
    Document   : depocitoColones
    Created on : 24 sep. 2024, 23:41:51
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Realizar Depósito en Colones</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #4caf50;
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
            background-color: #4caf50;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 1.2em;
        }
        input[type="submit"]:hover {
            background-color: #43a047;
        }
        .error {
            color: red;
            font-size: 0.9em;
            margin-bottom: 20px;
        }
    </style>
    <script>
        function validarFormulario() {
            var cuenta = document.getElementById("cuenta").value.trim();
            var montoDeposito = document.getElementById("montoDeposito").value.trim();

            // Validar que todos los campos requeridos estén llenos
            if (cuenta === "" || montoDeposito === "") {
                alert("Todos los campos son obligatorios.");
                return false;
            }

            // Validar que el número de cuenta tiene un formato adecuado
            if (cuenta.length !== 7 || !/^[A-Za-z0-9]+$/.test(cuenta)) {
                alert("El número de cuenta debe ser alfanumérico de 7 caracteres.");
                return false;
            }

            // Validar que el monto del depósito sea un número sin decimales
            if (isNaN(montoDeposito) || parseInt(montoDeposito) <= 0 || montoDeposito.includes('.')) {
                alert("El monto del depósito debe ser un número entero positivo sin decimales.");
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
    <header>
        <h1>Realizar Depósito en Colones</h1>
    </header>

    <form action="DepositoEnColonesServlet" method="post">
        <label for="cuenta">Número de Cuenta:</label>
        <input type="text" id="cuenta" name="cuenta" maxlength="7" required>

        <label for="montoDeposito">Monto del Depósito (Colones):</label>
        <input type="text" id="montoDeposito" name="montoDeposito" required>

        <input type="submit" value="Realizar Depósito">
        <a href="index.jsp" class="consult-option">Volver al Inicio</a>
    </form>
</body>
</html>
