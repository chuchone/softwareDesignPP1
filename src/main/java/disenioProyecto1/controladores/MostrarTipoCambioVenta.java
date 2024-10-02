/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import static disenioProyecto1.integracion.ConexionBCCR.obtenerTipoCambio;
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
@WebServlet("/MostrarTipoCambioVenta")
public class MostrarTipoCambioVenta extends HttpServlet {
   private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Simulación de obtención de tipo de cambio desde una fuente externa
        double tipoCambioVenta = obtenerTipoCambio("318");

        // Establecer los tipos de cambio como atributos de la solicitud
        request.setAttribute("tipoCambioVenta", tipoCambioVenta);

        // Redirigir a la página JSP para mostrar los tipos de cambio
        request.getRequestDispatcher("tipoCambioVenta.jsp").forward(request, response);
    }
}
