<%-- 
    Document   : imprimirCuentasUsuarios
    Created on : 6 oct. 2024, 21:56:48
    Author     : Nelson
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="disenioProyecto1.gestorBanco.CuentaBancaria" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Imprimir Cuentas de Usuario</title>
    <link rel="stylesheet" href="styles.css"> <!-- Si deseas incluir estilos -->
</head>
<body>
    <h1>Cuentas de Usuario</h1>
    
    <%
        // Obtener la lista de cuentas de usuario desde el request
        List<CuentaBancaria> listaCuentasDeUsuario = (List<CuentaBancaria>) request.getAttribute("listaCuentasDeUsuario");
        
        // Verificar si la lista no es nula ni está vacía
        if (listaCuentasDeUsuario != null && !listaCuentasDeUsuario.isEmpty()) {
    %>
            <table border="1">
                <thead>
                    <tr>
                        <th>Número de Cuenta</th>
                        <th>Dinero en la Cuenta</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        // Crear una instancia de DecimalFormat para formatear el dinero
                        DecimalFormat df = new DecimalFormat("#.00");

                        // Recorrer la lista de cuentas y mostrarlas en la tabla
                        for (CuentaBancaria cuenta : listaCuentasDeUsuario) {
                    %>
                    <tr>
                        <td><%= cuenta.numeroCuenta %></td>
                        <td><%= df.format(cuenta.dineroEnLaCuenta) %></td> <!-- Formatear a dos decimales -->
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
    <%
        } else {
    %>
            <p>No se encontraron cuentas para el usuario.</p>
    <%
        }
    %>
    
    <a href="index.jsp">Regresar al menú</a> <!-- Enlace para regresar al menú principal -->
</body>
</html>
