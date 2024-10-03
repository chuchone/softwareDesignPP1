/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.obtenerCuentasBancarias;
import disenioProyecto1.gestorBanco.CuentaBancaria;
import static disenioProyecto1.integracion.CifradorDES.encriptarPIN;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nelson
 */
@WebServlet(name = "ConsultarStatusServlet", urlPatterns = {"/ConsultarStatusServlet"})
public class ConsultarStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String numeroCuenta = request.getParameter("numeroCuenta");
        
        try {
                       
            // Obtener lista de cuentas
            List<CuentaBancaria> listaDeCuentas = obtenerCuentasBancarias();
            
            if (existeCuenta(listaDeCuentas, numeroCuenta)) {
                String nombreCliente = obtenerNombre(listaDeCuentas, numeroCuenta);
                boolean status = obtenerStatus(listaDeCuentas, numeroCuenta);
                request.setAttribute("nombreCliente", nombreCliente);
                request.setAttribute("status", status);

                request.getRequestDispatcher("enseniarStatusCuenta.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }

        } catch (Exception e) {
            Logger.getLogger(SaldoActualServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("error.jsp"); 
        }
    }

    private boolean existeCuenta(List<CuentaBancaria> listaDeCuentas,String numeroCuenta) {
        for (CuentaBancaria cuenta : listaDeCuentas) {
            if (cuenta.numeroCuenta.equals(numeroCuenta)) {
                return true;
            }
        }
        return false;
    }

    private String obtenerNombre(List<CuentaBancaria> listaDeCuentas, String numeroCuenta) {
        for (CuentaBancaria cuenta : listaDeCuentas) {
            if (cuenta.numeroCuenta.equals(numeroCuenta)) {
                return cuenta.nombreDuenio;
            }
        }
        return "No disponible";
    }

    private boolean obtenerStatus(List<CuentaBancaria> listaDeCuentas, String numeroCuenta) {
        for (CuentaBancaria cuenta : listaDeCuentas) {
            if (cuenta.numeroCuenta.equals(numeroCuenta)) {
                return cuenta.statusCuentaActiva;
            }
        }
        return false;
    }
}
