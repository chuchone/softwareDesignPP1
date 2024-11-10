<%-- 
    Document   : verBitacoras
    Created on : 9 nov. 2024, 19:39:07
    Author     : Nelson
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="disenioProyecto1.modelo.bitacoras.Bitacoras" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ver Bitácoras</title>
</head>
<body>
    <h1>Registro de Bitácoras</h1>

    <!-- Botón para mostrar la tabla de bitácoras -->
    <form action="AccesoBitacorasServlet" method="post">
        <button type="submit">Mostrar Tabla de Bitácoras</button>
    </form>

    <!-- Tabla de Bitácoras -->
    <c:if test="${not empty bitacoras}">
        <table border="1">
            <tr>
                <th>Número</th>
                <th>Usuario</th>
                <th>Acción</th>
                <th>Fecha y Hora</th>
                <th>IP de Acceso</th>
                <th>Sistema Operativo</th>
                <th>País de Acceso</th>
            </tr>
            <c:forEach var="bitacora" items="${bitacoras}">
                <tr>
                    <td>${bitacora.numeroBitacora}</td>
                    <td>${bitacora.usuario}</td>
                    <td>${bitacora.accion}</td>
                    <td>${bitacora.fechaHoraAccion}</td>
                    <td>${bitacora.ipAcceso}</td>
                    <td>${bitacora.sistemaOperativo}</td>
                    <td>${bitacora.paisAcceso}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <!-- Botones para descargar archivos en diferentes formatos -->
    <form action="ExportarBitacorasServlet" method="get">
        <button type="submit" name="formato" value="json">Descargar en JSON</button>
        <button type="submit" name="formato" value="xml">Descargar en XML</button>
        <button type="submit" name="formato" value="csv">Descargar en CSV</button>
    </form>
</body>
</html>
