/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.obtenerCuentasBancarias;
import disenioProyecto1.capaDatos.conexionSql.BasesDatos;
import disenioProyecto1.gestorBanco.CuentaBancaria;
import static disenioProyecto1.gestorBanco.ResultadoCuenta.existeCuenta;
import static disenioProyecto1.gestorBanco.ResultadoCuenta.existeCuentaYPin;
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
import static disenioProyecto1.integracion.SMSSender.mandarMensaje;
import jakarta.servlet.RequestDispatcher;
/**
 *
 * @author Nelson
 */
@WebServlet("/TransferenciaLocalUnsoloDuenio")
public class TransferenciaLocalUnsoloDuenio extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cuentaOrigen = request.getParameter("numeroCuentaOrigen");
        String pin = request.getParameter("pin");
        double monto = Double.parseDouble(request.getParameter("monto"));
        String cuentaDestino = request.getParameter("numeroCuentaDestino");
        
        try {
            String pinEncriptado = encriptarPIN(pin);
            long cedula = existeCuenta(cuentaOrigen);
            long cedula2 = existeCuenta(cuentaDestino);

            if (cedula == cedula2){
                long numero = BasesDatos.obtenerNumeroTelefono(cedula);
                String numero3 = String.valueOf(numero);
                String palabra = mandarMensaje(numero3);
                
                // Redirigir a la página JSP con el mensaje
                request.setAttribute("mensaje", palabra);
                RequestDispatcher dispatcher = request.getRequestDispatcher("formularito.jsp");
                dispatcher.forward(request, response);
            } else {
                // Manejar el caso en que las cédulas no coincidan
                request.setAttribute("error", "Las cuentas no pertenecen al mismo dueño.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(TransferenciaLocalUnsoloDuenio.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "Ocurrió un error durante la transferencia.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }
}

