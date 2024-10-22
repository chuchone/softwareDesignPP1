/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import static disenioProyecto1.capaDatos.validaciones.ValidacionesCuentas.validarPin;
import static disenioProyecto1.capaDatos.validaciones.ValidacionesCuentas.validarSiExisteCuentaYPin;
import static disenioProyecto1.modelo.gestorBanco.GestionBanco.prevMandarStatusDolares;
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
@WebServlet("/EnviarStatusCuentaDolaresServlet")
public class EnviarStatusCuentaDolaresServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener los parámetros del formulario
            String numeroCuenta = request.getParameter("numeroCuenta");
            String pinCuenta = request.getParameter("pinCuenta");
            
            // Validar el PIN
            String errorMsg = validarPin(pinCuenta);
            String pinCifrado = encriptarPIN(pinCuenta);
            if (errorMsg == null) {
                if (validarSiExisteCuentaYPin(numeroCuenta,pinCifrado )){

                    if(prevMandarStatusDolares(numeroCuenta)){

                        request.getRequestDispatcher("confirmacionEstadoCuentaDolares.jsp").forward(request, response);
                        return;
                    }else{
                        request.setAttribute("mensajeDeError", "Hubo un error");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                        return;
                    }
                }else{
                     request.setAttribute("mensajeDeError", "Datos incorrectos");
                     request.getRequestDispatcher("error.jsp").forward(request, response);
                     return;
                }
                     
            }else{
                 request.setAttribute("mensajeDeError", errorMsg);
                 request.getRequestDispatcher("error.jsp").forward(request, response);          
           } 
        } catch (SQLException ex ) {
            Logger.getLogger(EnviarStatusCuentaColonesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EnviarStatusCuentaColonesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}

