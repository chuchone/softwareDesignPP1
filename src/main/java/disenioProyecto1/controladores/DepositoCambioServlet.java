/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;


import disenioProyecto1.modelo.gestorBanco.ResultadoCuenta;
import static disenioProyecto1.modelo.gestorBanco.ResultadoCuenta.existeCuentaBancariaDep;
import static disenioProyecto1.integracion.ConexionBCCR.convertirDolaresAColones;
import static disenioProyecto1.integracion.ConexionBCCR.obtenerTipoCambio;
import static disenioProyecto1.integracion.IPIntegracion.obtenerPaisDesdeIP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Nelson
 */
@WebServlet("/DepositoCambioServlet")
public class DepositoCambioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener parámetros del formulario
        String numeroCuenta = request.getParameter("numeroCuenta");
        String montoDepositoStr = request.getParameter("montoDeposito");
        double monto = convertirStringADouble(montoDepositoStr);        
        double tipoCambio = obtenerTipoCambio("318");
        double montoEnColones = convertirDolaresAColones(monto, tipoCambio);
        // Obtener la IP del cliente
        
        
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        // Obtener el sistema operativo del cliente (User-Agent)
        String userAgent = request.getHeader("User-Agent");

        // Obtener el país de origen a partir de la IP usando ipstack API
        //String country = obtenerPaisDesdeIP(ipAddress);
        try {
            ResultadoCuenta informacionCuenta = existeCuentaBancariaDep(numeroCuenta, montoEnColones, ipAddress, userAgent, "Costa Rica");
            if (informacionCuenta.isExisteCuenta()) {
                // Pasar los valores como atributos al JSP
                request.setAttribute("numeroCuenta", numeroCuenta);
                request.setAttribute("montoDepositado", montoEnColones);
                request.setAttribute("montoComision", informacionCuenta.getRegisComisiones());
                
                // Redirigir a la página de confirmación
                request.getRequestDispatcher("confirmacionDepositoDolares.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
            Logger.getLogger(DepositoCambioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static double convertirStringADouble(String str) throws NumberFormatException {
        return Double.parseDouble(str);
    }
        // Método para obtener el país a partir de la IP usando la API de ipstack


}