/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;


import static disenioProyecto1.modelo.gestorBanco.GestionBanco.obtenerComisiones;
import static disenioProyecto1.modelo.gestorBanco.ResultadoCuenta.existeCuentaBancariaRet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author Nelson
 */
@WebServlet("/ProcesarRetiroColonesDespSMS")
public class ProcesarRetiroColonesDespSMS extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros enviados desde el JSP
        String smsIngresado = request.getParameter("sms");
        String codigoSMS = request.getParameter("codigoSMS");
        String pinEncriptado = request.getParameter("pinEncriptado");
        String numeroCuenta = request.getParameter("numeroCuenta");
        String montoRetiro = request.getParameter("montoRetiro");

        try {
            if (!smsIngresado.equals(codigoSMS)) {
                redirigirError(request, response, "Código SMS incorrecto.");
                return;
            }
            double cantidadARetirar = Double.parseDouble(montoRetiro);
            boolean resultado = existeCuentaBancariaRet(numeroCuenta, cantidadARetirar, pinEncriptado);
            double comisionesTotales = obtenerComisiones(numeroCuenta);
            if (resultado) {
                // agregar al request comision
                request.setAttribute("montoRetirado", cantidadARetirar);
                request.setAttribute("montoComision", comisionesTotales);
                request.setAttribute("numeroCuenta", numeroCuenta);
                request.getRequestDispatcher("confirmarRetiroEnColones.jsp").forward(request, response);
            } else {
                redirigirError(request, response, "No se pudo realizar el retiro. Intente nuevamente.");
            }
        } catch (SQLException | NumberFormatException e) {
            redirigirError(request, response, "Ocurrió un error al procesar su solicitud. Intente nuevamente.");
        }
    }

    // Método para redirigir a la página de error
    private void redirigirError(HttpServletRequest request, HttpServletResponse response, String mensajeError) throws ServletException, IOException {
        request.setAttribute("mensajeDeError", mensajeError);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}