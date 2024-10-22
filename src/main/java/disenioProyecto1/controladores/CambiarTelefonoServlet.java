/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCFisico.limpiarTablaCfisico;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCFisico.obtenerListaClientesFisicos;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCJuridico.insertarClienteJuridico;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCJuridico.limpiarTablaCJuridico;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCJuridico.obtenerListaClientesJuridicos;
import static disenioProyecto1.capaDatos.conexionSql.BasesDatos.delegarCrearCFisico;
import static disenioProyecto1.capaDatos.validaciones.ValidacionesUsuarios.*;
import disenioProyecto1.modelo.usuarios.CFisico;
import disenioProyecto1.modelo.usuarios.CJuridico;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

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

        int telefono = Integer.parseInt(nuevoTelefono);
        long cedula = Long.parseLong(identificacion);

        try {
            boolean esFisico = existeCFisico(cedula);
            boolean esJuridico = existeCJuridico(cedula);
            int telefonoAnterior = 000;
            if (esFisico) {
                String nombre = "sks";
                List<CFisico> listaCFisicos = obtenerListaClientesFisicos();
                for (CFisico cliente : listaCFisicos) {
                    if (cliente.identificacion == cedula) {
                        nombre = cliente.nombre;
                        telefonoAnterior = cliente.telefono;
                        cliente.setTelefono(telefono);
                    }
                }
                limpiarTablaCfisico();
                for (CFisico cliente : listaCFisicos) {
                    delegarCrearCFisico(cliente);
                }
                request.setAttribute("nombre", nombre);
                request.setAttribute("telefonoAnterior", telefonoAnterior);
                request.setAttribute("telefono", telefono);
                request.getRequestDispatcher("resultadoCambioTelefono.jsp").forward(request, response);
            } else if (esJuridico) {
                String nombre = "sks";
                List<CJuridico> listaCJuridico = obtenerListaClientesJuridicos();
                for (CJuridico cliente : listaCJuridico) {
                    if (cliente.identificacion == cedula) {
                        telefonoAnterior = cliente.telefono;
                        nombre = cliente.nombre;
                        cliente.setTelefono(telefono);
                    }
                }
                limpiarTablaCJuridico();
                for (CJuridico cliente : listaCJuridico) {
                    insertarClienteJuridico(cliente);
                }
                request.setAttribute("nombre", nombre);
                request.setAttribute("telefonoAnterior", telefonoAnterior);
                request.setAttribute("telefono", telefono);
                request.getRequestDispatcher("resultadoCambioTelefono.jsp").forward(request, response);

            } else {
                request.setAttribute("mensajeDeError", "Ocurrió un problema.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            // Manejo de excepciones SQL
            request.setAttribute("mensajeDeError", "Ocurrió un error al acceder a la base de datos.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Manejo de excepciones de formato de número
            request.setAttribute("mensajeDeError", "El formato del número ingresado es inválido.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            // Manejo de cualquier otra excepción
            request.setAttribute("mensajeDeError", "Ocurrió un error inesperado.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    
}