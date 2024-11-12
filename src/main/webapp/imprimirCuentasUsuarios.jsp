<%-- 
    Document   : imprimirCuentasUsuarios
    Created on : 6 oct. 2024, 21:56:48
    Author     : Nelson
--%>
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
    <style>
      body {
        font-family: Arial, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        background-color: #f4f4f9;
        margin: 0;
        padding: 0;
      }

      h1 {
        text-align: center;
        color: #333;
        margin-bottom: 20px;
      }

      .container {
        width: 80%;
        max-width: 900px;
        margin: auto;
        text-align: center;
      }

      table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
        border: 2px solid #007ACC; 
      }

      th, td {
        padding: 10px;
        text-align: center;
        border: 1px solid #007ACC; 
      }

      th {
        background-color: #007ACC; 
        color: white;
      }

      tr:nth-child(even) {
        background-color: #d0e7f9; 
      }

      .bold-text {
        font-weight: bold;
      }

      .no-transactions {
        color: #666;
        font-style: italic;
      }

      .back-link {
        display: inline-block;
        margin-top: 20px;
        text-decoration: none;
        color: #007ACC;
        font-weight: bold;
      }

      .back-link:hover {
        text-decoration: underline;
        color: #005B99; 
      }
    </style>
  </head>
  <body>
    <div class="container">
      <h1>Cuentas y transacciones asociadas al usuario</h1>

      <%
        List<CuentaBancaria> listaCuentasDeUsuario = (List<CuentaBancaria>) request.getAttribute("listaCuentasDeUsuario");
        Map<String, List<Transaccion>> transaccionesPorCuenta = (Map<String, List<Transaccion>>) request.getAttribute("transaccionesPorCuenta");

        if (listaCuentasDeUsuario != null && !listaCuentasDeUsuario.isEmpty()) {
      %>
      <table>
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
            <td class="bold-text"><%= cuenta.numeroCuenta%></td>
            <td class="bold-text"><%= df.format(cuenta.dineroEnLaCuenta)%></td>
          </tr>
          <tr>
            <td colspan="2">
              <%
                List<Transaccion> transacciones = transaccionesPorCuenta.get(cuenta.numeroCuenta);

                if (transacciones != null && !transacciones.isEmpty()) {
              %>
              <table>
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
                    <td><%= transaccion.fecha%></td>
                    <td><%= transaccion.tipoTransaccion%></td>
                    <td><%= df.format(transaccion.monto)%></td>
                    <td><%= df.format(transaccion.comision)%></td>
                  </tr>
                  <%
                    }
                  %>
                </tbody>
              </table>
              <%
              } else {
              %>
              <p class="no-transactions">No hay transacciones para esta cuenta.</p>
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

      <a href="index.jsp" class="back-link">Regresar al menú</a>
    </div>
  </body>
</html>
