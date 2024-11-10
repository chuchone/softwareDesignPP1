/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import static disenioProyecto1.controladores.DepositoCambioServlet.convertirStringADouble;
import static disenioProyecto1.integracion.IPIntegracion.obtenerPaisDesdeIP;
import disenioProyecto1.modelo.gestorBanco.ResultadoCuenta;
import static disenioProyecto1.modelo.gestorBanco.ResultadoCuenta.existeCuentaBancariaDep;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nelson
 */

@WebServlet("/DepositoEnColonesServlet")
public class DepositoEnColonesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Obtener los parámetros del formulario
        String cuenta = request.getParameter("cuenta");
        String montoDepositoStr = request.getParameter("montoDeposito");
        double monto = convertirStringADouble(montoDepositoStr);        

        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        // Obtener el sistema operativo del cliente (User-Agent)
        String userAgent = request.getHeader("User-Agent");

        // Obtener el país de origen a partir de la IP usando ipstack API
        //String country = obtenerPaisDesdeIP(ipAddress);
        try {
            double montoDouble = Double.parseDouble(montoDepositoStr);
            ResultadoCuenta informacionCuenta = existeCuentaBancariaDep(cuenta, monto, ipAddress, userAgent, "Costa Rica");
            
            if (informacionCuenta.isExisteCuenta()) {
                // Pasar los valores como atributos al JSP
                request.setAttribute("numeroCuenta", cuenta);
                request.setAttribute("montoDepositado", montoDouble);
                request.setAttribute("montoComision", informacionCuenta.getRegisComisiones());
                
                // Redirigir a la página de confirmación
                request.getRequestDispatcher("confirmacionDepositoColones.jsp").forward(request, response);
            } else {
                // Redirigir a una página de error si la cuenta no existe
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DepositoEnColonesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
