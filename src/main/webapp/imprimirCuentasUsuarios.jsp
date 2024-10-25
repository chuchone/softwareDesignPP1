<%-- 
    Document   : imprimirCuentasUsuarios
    Created on : 6 oct. 2024, 21:56:48
    Author     : Nelson
--%>

<%@ page import="disenioProyecto1.modelo.gestorBanco.CuentaBancaria"%>
<%@ page import="disenioProyecto1.modelo.gestorBanco.Transaccion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Imprimir Cuentas de Usuario</title>
    <link rel="stylesheet" href="styles.css"> 
</head>
<body>
    <h1>Cuentas de Usuario</h1>
    
    <%
        List<CuentaBancaria> listaCuentasDeUsuario = (List<CuentaBancaria>) request.getAttribute("listaCuentasDeUsuario");
        Map<String, List<Transaccion>> transaccionesPorCuenta = (Map<String, List<Transaccion>>) request.getAttribute("transaccionesPorCuenta");
        
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
                        DecimalFormat df = new DecimalFormat("#.00");

                        for (CuentaBancaria cuenta : listaCuentasDeUsuario) {
                    %>
                    <tr>
                        <td><%= cuenta.numeroCuenta %></td>
                        <td><%= df.format(cuenta.dineroEnLaCuenta) %></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <!-- Verificar si hay transacciones para la cuenta -->
                            <%
                                List<Transaccion> transacciones = transaccionesPorCuenta.get(cuenta.numeroCuenta);
                                
                                if (transacciones != null && !transacciones.isEmpty()) {
                            %>
                                <table border="1" style="width: 100%;">
                                    <thead>
                                        <tr>
                                            <th>Fecha</th>
                                            <th>Tipo de Transacción</th>
                                            <th>Monto</th>
                                            <th>Comisión</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            for (Transaccion transaccion : transacciones) {
                                        %>
                                        <tr>
                                            <td><%= transaccion.fecha %></td>
                                            <td><%= transaccion.tipoTransaccion %></td>
                                            <td><%= df.format(transaccion.monto) %></td>
                                            <td><%= df.format(transaccion.comision) %></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            <%
                                } else {
                            %>
                                <p>No hay transacciones para esta cuenta.</p>
                            <%
                                }
                            %>
                        </td>
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