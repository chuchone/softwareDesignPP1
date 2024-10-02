/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

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
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/generarEstadoCuenta")
public class GenerarEstadoCuentaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Obtener parámetros (ejemplo)
        String numeroCuenta = request.getParameter("numeroCuenta");
        String nombreCliente = request.getParameter("nombreCliente");
        double saldoActual = Double.parseDouble(request.getParameter("saldoActual"));
        
        // Crear transacciones de ejemplo
//        List<generarPDF.Transaccion> transacciones = new ArrayList<>();
  //      transacciones.add(new generarPDF.Transaccion("Deposito", 1000.00, saldoActual, "01-10-2024", 0.00, numeroCuenta));
        
        // Generar PDF
    //    String pdfFile = generarPDF.crearEstadoCuenta(numeroCuenta, nombreCliente, saldoActual, transacciones);
        
        // Enviar correo
        String emailDestino = "destino@example.com"; // Reemplaza con el correo del cliente
        String asunto = "Tu Estado de Cuenta";
        String contenido = "Adjunto tu estado de cuenta en formato PDF.";
      //  CorreoElectronico.mandar(emailDestino, asunto, contenido);
        
        response.getWriter().write("Estado de cuenta generado y enviado.");
    }
}
