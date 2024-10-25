/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.obtenerCuentasBancarias;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosRegistros.obtenerTransacciones;
import disenioProyecto1.modelo.gestorBanco.CuentaBancaria;
import disenioProyecto1.modelo.gestorBanco.Transaccion;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nelson
 */
@WebServlet("/ImprimirCuentasServlet")
public class ImprimirCuentasServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String identificacion = request.getParameter("identificacion");

    if (identificacion != null && identificacion.length() <= 11) {
      try {
        long identificacionLong = Long.parseLong(identificacion);
        List<CuentaBancaria> listaCuentasDeUsuario = obtenerListaDeCuentasExistentes(identificacionLong);

        // Crear un mapa para almacenar las transacciones por número de cuenta
        Map<String, List<Transaccion>> transaccionesPorCuenta = new HashMap<>();

        // Obtener las transacciones para cada cuenta y almacenarlas en el mapa
        for (CuentaBancaria cuenta : listaCuentasDeUsuario) {
          List<Transaccion> transacciones = obtenerListaDeTransaccionesExistentes(cuenta.numeroCuenta);
          transaccionesPorCuenta.put(cuenta.numeroCuenta, transacciones); // Asociar transacciones a su número de cuenta
        }

        // Agregar las cuentas y las transacciones a la solicitud
        request.setAttribute("listaCuentasDeUsuario", listaCuentasDeUsuario);
        request.setAttribute("transaccionesPorCuenta", transaccionesPorCuenta); // Pasar el mapa de transacciones

        request.getRequestDispatcher("imprimirCuentasUsuarios.jsp").forward(request, response);
      } catch (SQLException ex) {
        Logger.getLogger(ImprimirCuentasServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
    } else {
      request.setAttribute("mensajeDeError", "Identificación inválida. Debe ser de 11 caracteres o menos.");
      request.getRequestDispatcher("error.jsp").forward(request, response);
    }
  }

  private static List<CuentaBancaria> obtenerListaDeCuentasExistentes(long identificacion) throws SQLException {
    List<CuentaBancaria> listaCuentas = obtenerCuentasBancarias();
    List<CuentaBancaria> listaCuentasDeUsuario = new ArrayList<>();

    for (CuentaBancaria cuenta : listaCuentas) {
      if (cuenta.cedulaPersonaAsociada == identificacion) {
        listaCuentasDeUsuario.add(cuenta);
      }
    }
    return listaCuentasDeUsuario;
  }

  private static List<Transaccion> obtenerListaDeTransaccionesExistentes(String numeroCuenta) throws SQLException {
    List<Transaccion> listaTransacciones = obtenerTransacciones();
    List<Transaccion> listaTransaccionesDeCuenta = new ArrayList<>();

    for (Transaccion transaccion : listaTransacciones) {
      if (transaccion.numCuentaQuePertenese.equals(numeroCuenta)) {
        listaTransaccionesDeCuenta.add(transaccion);
      }
    }
    return listaTransaccionesDeCuenta;
  }
}