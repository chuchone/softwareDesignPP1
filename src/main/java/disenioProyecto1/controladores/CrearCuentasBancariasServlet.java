/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import disenioProyecto1.gestorBanco.CuentaBancaria;
import static disenioProyecto1.gestorBanco.GestionBanco.agregarAListaCuentasBancarias;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Nelson
 */
@WebServlet(name = "CrearCuentasBancariasServlet", urlPatterns = {"/CrearCuentasBancariasServlet"})
public class CrearCuentasBancariasServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String identidad = request.getParameter("identidad").trim();
        String pin = request.getParameter("pin").trim();
        String montoInicial = request.getParameter("montoInicial").trim();
        
        // Inicializar mensajes de error
        String errorMessage = null;

        // Validaciones
        if (identidad.isEmpty() || pin.isEmpty() || montoInicial.isEmpty()) {
            errorMessage = "Todos los campos son obligatorios.";
        } else if (pin.length() != 4 || !pin.matches("\\d{4}")) {
            errorMessage = "El PIN debe tener 4 dígitos.";
        } else if (!isNumeric(montoInicial) || Integer.parseInt(montoInicial) <= 0) {
            errorMessage = "El monto inicial debe ser un número positivo sin decimales.";
        }
        
        // Verificar si hay errores
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("crearCuenta.jsp").forward(request, response);
        } else {
            int montoInt = Integer.parseInt(montoInicial);

            CuentaBancaria obj = new CuentaBancaria(identidad,  pin , montoInt);
            agregarAListaCuentasBancarias(obj);
            
            // Redirigir a la página de confirmación
            response.sendRedirect("confirmacionCuenta.jsp");   // Redirigir a la página de confirmación

        }
    }

    // Método auxiliar para verificar si una cadena es numérica
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}