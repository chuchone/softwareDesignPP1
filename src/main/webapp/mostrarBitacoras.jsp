<%@page import="disenioProyecto1.modelo.bitacoras.Bitacoras"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Bitácoras de Uso</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eaf2f8;
            margin: 20px;
            color: #333;
        }
        h1, h2 {
            color: #333;
            text-align: center;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #5d6d7e;
            color: #fff;
        }
        td {
            background-color: #fff;
        }
        audio {
            margin: 20px auto;
            display: block;
        }
        .button-container {
            text-align: center;
            margin-top: 30px;
        }
        .download-button {
            padding: 10px 20px;
            margin: 0 10px;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }
        .download-json { background-color: #28a745; }
        .download-xml { background-color: #17a2b8; }
        .download-csv { background-color: #e67e22; }
        .home-button {
            display: inline-block;
            margin-top: 20px;
            padding: 12px 24px;
            background-color: #3498db;
            color: #fff;
            text-decoration: none;
            border-radius: 8px;
            font-weight: bold;
            font-size: 16px;
        }
        .home-button:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <h1>Bitácoras de Uso</h1>

    <!-- Obtención de los datos desde los atributos de la solicitud -->
    <%
        List<Bitacoras> bitacoras = (List<Bitacoras>) request.getAttribute("bitacoras");
        String analisisSentimientos = (String) request.getAttribute("analisisSentimientos");
        String audioFilePath = (String) request.getAttribute("audioFilePath");

        if (bitacoras == null || bitacoras.isEmpty()) {
    %>
        <p style="text-align: center;">No se encontraron registros de bitácoras.</p>
    <%
        } else {
    %>
        <table>
            <thead>
                <tr>
                    <th>Número</th>
                    <th>Usuario</th>
                    <th>Acción</th>
                    <th>Fecha y Hora</th>
                    <th>IP de Acceso</th>
                    <th>Sistema Operativo</th>
                    <th>País</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Bitacoras bitacora : bitacoras) {
                %>
                    <tr>
                        <td><%= bitacora.numeroBitacora %></td>
                        <td><%= bitacora.usuario %></td>
                        <td><%= bitacora.accion %></td>
                        <td><%= bitacora.fechaHoraAccion %></td>
                        <td><%= bitacora.ipAcceso %></td>
                        <td><%= bitacora.sistemaOperativo %></td>
                        <td><%= bitacora.paisAcceso %></td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        }
    %>

    <h2>Análisis de La Tabla de Bitacoras</h2>
    <p style="text-align: center;"><%= analisisSentimientos != null ? analisisSentimientos : "No se pudo realizar el análisis de sentimientos." %></p>

    <h2>Escuchar Análisis</h2>
    <audio controls>
        <source src="AudioServlet?file=analisis.mp3" type="audio/mpeg">
        Tu navegador no soporta el elemento de audio.
    </audio>

    <h2>Descargar información</h2>
    <div class="button-container">
        <a href="ExportarDatosServlet?formato=json" class="download-button download-json">Descargar JSON</a>
        <a href="ExportarDatosServlet?formato=xml" class="download-button download-xml">Descargar XML</a>
        <a href="ExportarDatosServlet?formato=csv" class="download-button download-csv">Descargar CSV</a>
    </div>

    <div class="button-container">
        <a href="index.jsp" class="home-button">Volver al Inicio</a>
    </div>
</body>
</html>
