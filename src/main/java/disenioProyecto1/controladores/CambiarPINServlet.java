/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.actualizarPinCuenta;
import static disenioProyecto1.integracion.CifradorDES.encriptarPIN;
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
@WebServlet("/cambiarPINServlet")
public class CambiarPINServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String cuenta = request.getParameter("cuenta");
        String pinActual = request.getParameter("pinActual");
        String nuevoPin = request.getParameter("nuevoPin");
        
        // Validación de campos vacíos
        if (cuenta == null || pinActual == null || nuevoPin == null || 
            cuenta.isEmpty() || pinActual.isEmpty() || nuevoPin.isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("cambiarPin.jsp").forward(request, response);
            return;
        }
        
        try {
            // Lógica para cambiar el PIN
            boolean cambioValido = actualizarPinCuenta(encriptarPIN(nuevoPin), cuenta, encriptarPIN(pinActual));
            if (cambioValido) {
                request.getRequestDispatcher("/confirmacionCambiarPin.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Datos incorrectos.");
                request.getRequestDispatcher("cambiarPin.jsp").forward(request, response);            
            }
        } catch (SQLException ex) {
            Logger.getLogger(CambiarPINServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/errorCambiarPin.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CambiarPINServlet.class.getName()).log(Level.SEVERE, null, ex);
            // Manejar cualquier otra excepción y redirigir a la página de error
            request.getRequestDispatcher("/errorCambiarPin.jsp").forward(request, response);
        }
    }    
}
