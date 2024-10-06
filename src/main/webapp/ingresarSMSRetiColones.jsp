<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Verificación de SMS</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .form-container {
            max-width: 400px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .form-container h2 {
            text-align: center;
        }
        .form-container input[type="text"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-container input[type="submit"],
        .form-container input[type="button"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .form-container input[type="submit"] {
            background-color: #4CAF50;
            color: white;
        }
        .form-container input[type="button"] {
            background-color: #f44336;
            color: white;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Verificación de SMS</h2>
        <form action="ProcesarRetiroColonesDespSMS" method="post">
            <label for="sms">Ingrese el SMS que se le envió a su teléfono:</label>
            <input type="text" id="sms" name="sms" required>
            <!-- Campos ocultos para enviar el código SMS, el PIN encriptado y el número de cuenta -->
            <input type="hidden" name="codigoSMS" value="${sessionScope.codigoSMS}">
            <input type="hidden" name="pinEncriptado" value="${sessionScope.pinEncriptado}">
            <input type="hidden" name="numeroCuenta" value="${sessionScope.numeroCuenta}">
            <input type="hidden" name="montoRetiro" value="${sessionScope.montoRetiro}">
            <a href="index.jsp" class="consult-option">Volver al Inicio</a>
            <input type="submit" value="Enviar">
        </form>
        <form action="operacionesBancarias.jsp" method="get">
            <input type="button" value="Volver a Operaciones Bancarias" onclick="window.location.href='operacionesBancarias.jsp'">
        </form>
    </div>
</body>
</html>
