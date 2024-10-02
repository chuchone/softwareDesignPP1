/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCFisico.obtenerListaClientesFisicos;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCJuridico.obtenerListaClientesJuridicos;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.insertarListaCuentasBancarias;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.limpiarTablaCuentas;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.obtenerCuentasBancarias;
import disenioProyecto1.gestorBanco.CuentaBancaria;
import static disenioProyecto1.integracion.CifradorDES.encriptarPIN;
import disenioProyecto1.usuarios.CFisico;
import disenioProyecto1.usuarios.CJuridico;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nelson
 */
@WebServlet("/EliminarCuentaServlet")
public class EliminarCuentaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String numeroCuenta = request.getParameter("numeroCuenta");
        String pin = request.getParameter("pin");    
        try {
            List<CuentaBancaria> listaCuentas = obtenerCuentasBancarias();
            boolean t = limpiarTablaCuentas();
            eliminarElemento(numeroCuenta, encriptarPIN(pin), listaCuentas);
            request.setAttribute("numeroCuenta", numeroCuenta);
            // Redirigir a la página de confirmación
            request.getRequestDispatcher("confirmacion.jsp").forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(EliminarCuentaServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("error.jsp");
        } catch (Exception ex) {
            Logger.getLogger(EliminarCuentaServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("error.jsp");
        }
    }
    private static void eliminarElemento(String numCuenta, String pin, List<CuentaBancaria> listaCuentas) throws SQLException {
        Iterator<CuentaBancaria> iterator = listaCuentas.iterator();

        while (iterator.hasNext()) {
            CuentaBancaria cuenta = iterator.next();
            if (numCuenta.equals(cuenta.numeroCuenta) && pin.equals(cuenta.PIN_Asociado)) {
                iterator.remove();
                insertarListaCuentasBancarias(listaCuentas);
                break; 
            }
        }
    }

}