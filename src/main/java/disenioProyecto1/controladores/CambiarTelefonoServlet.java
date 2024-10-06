/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

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
@WebServlet("/CambiarTelefonoServlet")
public class CambiarTelefonoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String identificacion = request.getParameter("identidad");
        String nuevoTelefono = request.getParameter("nuevoTelefono");
            

        // Redireccionar a la página de resultados
        request.getRequestDispatcher("resultadoCambioTelefono.jsp").forward(request, response);
    }
}