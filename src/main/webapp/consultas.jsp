<%-- 
    Document   : consultas
    Created on : 24 sep. 2024, 08:16:32
    Author     : Nelson
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Consultas - Sistema Bancario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0e6ff;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #cc99ff;
            color: #4d4d4d;
            padding: 20px;
            text-align: center;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        h1 {
            margin: 0;
            font-size: 2.5em;
        }
        p {
            margin: 10px 0 0;
            font-size: 1.2em;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin: 40px auto;
            max-width: 800px;
        }
        .consult-option {
            background-color: #e6ccff;
            color: #4d4d4d;
            text-align: center;
            padding: 20px;
            margin: 15px 0;
            border-radius: 10px;
            width: 100%;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            text-decoration: none;
            font-size: 1.2em;
        }
        .consult-option:hover {
            transform: translateY(-10px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
            background-color: #cc99ff;
        }
    </style>
</head>
<body>
    <header>
        <h1>Consultas</h1>
        <p>Seleccione una opción para consultar información</p>
    </header>

    <div class="container">
        <a href="consultarTransacciones.jsp" class="consult-option">Consultar Transacciones</a>
        <a href="MostrarTipoCambioCompra" class="consult-option">Consultar Tipo de Cambio (Compra)</a>
        <a href="MostrarTipoCambioVenta" class="consult-option">Consultar Tipo de Cambio (Venta)</a>
        <a href="saldoActual.jsp" class="consult-option">Consultar Saldo Actual</a>
        <a href="saldoActualCambio.jsp" class="consult-option">Consultar Saldo Actual con cambio de divisa</a>
        <a href="consultarStatus.jsp" class="consult-option">Consultar estatus de la cuenta</a>
        <a href="estadoCuentaColones.jsp" class="consult-option">Consultar estado de cuenta</a>
        <a href="estadoCuentaCambio.jsp" class="consult-option">Consultar estado de cuenta con cambio de divisa</a>

        <a href="index.jsp" class="consult-option">Volver al Inicio</a>
    </div>
</body>
</html>
