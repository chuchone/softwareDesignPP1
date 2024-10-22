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
@WebServlet("/CambiarCorreoCliente")
public class CambiarCorreoCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String identificacion = request.getParameter("identidad");
        String nuevoCorreo = request.getParameter("nuevoCorreo");

        long cedula = Long.parseLong(identificacion);

        try {
            boolean esFisico = existeCFisico(cedula);
            boolean esJuridico = existeCJuridico(cedula);
            String correoAnterior = "";

            if (esFisico) {
                String nombre = "Desconocido";
                List<CFisico> listaCFisicos = obtenerListaClientesFisicos();
                for (CFisico cliente : listaCFisicos) {
                    if (cliente.identificacion == cedula) {
                        nombre = cliente.nombre;
                        correoAnterior = cliente.correo;
                        cliente.correo = nuevoCorreo;  // Cambiar el correo electrónico
                    }
                }
                limpiarTablaCfisico();
                for (CFisico cliente : listaCFisicos) {
                    delegarCrearCFisico(cliente);
                }
                request.setAttribute("nombre", nombre);
                request.setAttribute("correoAnterior", correoAnterior);
                request.setAttribute("correo", nuevoCorreo);
                request.getRequestDispatcher("resultadoCambioCorreo.jsp").forward(request, response);
            } else if (esJuridico) {
                String nombre = "Desconocido";
                List<CJuridico> listaCJuridico = obtenerListaClientesJuridicos();
                for (CJuridico cliente : listaCJuridico) {
                    if (cliente.identificacion == cedula) {
                        correoAnterior = cliente.correo;
                        nombre = cliente.nombre;
                        cliente.correo = nuevoCorreo;  // Cambiar el correo electrónico
                    }
                }
                limpiarTablaCJuridico();
                for (CJuridico cliente : listaCJuridico) {
                    insertarClienteJuridico(cliente);
                }
                request.setAttribute("nombre", nombre);
                request.setAttribute("correoAnterior", correoAnterior);
                request.setAttribute("correo", nuevoCorreo);
                request.getRequestDispatcher("resultadoCambioCorreo.jsp").forward(request, response);

            } else {
                request.setAttribute("mensajeDeError", "Cliente no encontrado.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("mensajeDeError", "Ocurrió un error al acceder a la base de datos.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("mensajeDeError", "El formato de la identificación es inválido.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensajeDeError", "Ocurrió un error inesperado.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

}