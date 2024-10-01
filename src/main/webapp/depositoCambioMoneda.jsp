<%-- 
    Document   : depositoDolares
    Created on : 1 oct. 2024, 08:54:02
    Author     : Nelson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Depósito de Honor</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            text-align: center;
        }
        h1 {
            color: #4CAF50;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .client-option {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px 5px;
            background-color: #4CAF50;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .client-option:hover {
            background-color: #45a049;
        }
        .error {
            color: red;
            margin-top: 10px;
        }
    </style>
    <script>
        function validateForm() {
            var numeroCuenta = document.getElementById("numeroCuenta").value;
            var montoDeposito = document.getElementById("montoDeposito").value;
            var errorMessage = "";

            if (numeroCuenta === "") {
                errorMessage += "Por favor, ingrese el número de cuenta.\\n";
            }
            if (montoDeposito === "" || isNaN(montoDeposito) || montoDeposito % 1 !== 0) {
                errorMessage += "Por favor, ingrese un monto válido (sin decimales).\\n";
            }

            if (errorMessage !== "") {
                document.getElementById("error").innerText = errorMessage;
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Depósito con cambio al Dollar</h1>
        <form action="DepositoCambioServlet" method="post"">
            <label for="numeroCuenta">Número de Cuenta:</label>
            <input type="text" id="numeroCuenta" name="numeroCuenta">
            
            <label for="montoDeposito">Monto del Depósito (USD):</label>
            <input type="number" id="montoDeposito" name="montoDeposito">
            
            <input type="submit" value="Realizar Depósito">
            <a href="operacionesBancarias.jsp" class="client-option">Volver al Inicio</a>
            <div id="error" class="error"></div>
        </form>
    </div>
</body>
</html>

