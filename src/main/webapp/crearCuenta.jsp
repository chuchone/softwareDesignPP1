<%-- 
    Document   : crearCuenta
    Created on : 24 sep. 2024, 23:33:04
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear Cuenta - Sistema Bancario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #3399ff;
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
            background-color: #3399ff;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 1.2em;
        }
        input[type="submit"]:hover {
            background-color: #287ac2;
        }
        .error {
            color: red;
            font-size: 0.9em;
            margin-bottom: 20px;
        }
    </style>
    <script>
        function validarFormulario() {
            var identidad = document.getElementById("identidad").value.trim();
            var pin = document.getElementById("pin").value.trim();
            var montoInicial = document.getElementById("montoInicial").value.trim();

            // Validar que todos los campos requeridos estén llenos
            if (identidad === "" || pin === "" || montoInicial === "") {
                alert("Todos los campos son obligatorios.");
                return false;
            }

            // Validar formato del PIN (4 dígitos numéricos)
            if (pin.length !== 4 || isNaN(pin)) {
                alert("El PIN debe tener 4 dígitos.");
                return false;
            }

            // Validar monto inicial (solo números sin decimales)
            if (isNaN(montoInicial) || parseInt(montoInicial) <= 0) {
                alert("El monto inicial debe ser un número positivo sin decimales.");
                return false;
            }

            // Aquí puedes agregar la validación de la identidad llamando a la base de datos u otro sistema
            return true;
        }
    </script>
</head>
<body>
    <header>
        <h1>Crear Cuenta</h1>
    </header>

    <form action="CrearCuentasBancariasServlet" method="post">
        <label for="identidad">Identidad del Cliente (Cédula Física o Jurídica):</label>
        <input type="text" id="identidad" name="identidad" required>

        <label for="pin">PIN de la Cuenta:</label>
        <input type="password" id="pin" name="pin" maxlength="4" required>

        <label for="montoInicial">Monto de Depósito Inicial:</label>
        <input type="text" id="montoInicial" name="montoInicial" required>

        <input type="submit" value="Crear Cuenta">
        <a href="index.jsp" class="consult-option">Volver al Inicio</a>
    </form>
</body>
</html>
