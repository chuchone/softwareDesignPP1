/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCJuridico.insertarClienteJuridico;
import disenioProyecto1.capaDatos.validaciones.ValidacionesFormularios;
import static disenioProyecto1.capaDatos.validaciones.ValidarNuevosUsuarios.validarNuevoCJuridico;
import disenioProyecto1.usuarios.CJuridico;
import java.io.IOException;
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
@WebServlet("/CrearClienteJuridicoServlet")
public class CrearClienteJuridicoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre") != null ? request.getParameter("nombre").trim() : "";
        String categoria = request.getParameter("categoria") != null ? request.getParameter("categoria").trim() : "";
        String telefono = request.getParameter("telefono") != null ? request.getParameter("telefono").trim() : "";
        String email = request.getParameter("email") != null ? request.getParameter("email").trim() : "";
        String tipoNegocio = request.getParameter("tipoNegocio") != null ? request.getParameter("tipoNegocio").trim() : "";
        String cedulaJuridica = request.getParameter("cedulaJuridica") != null ? request.getParameter("cedulaJuridica").trim() : "";
        String razonSocial = request.getParameter("razonSocial") != null ? request.getParameter("razonSocial").trim() : "";
        
        
        try {
            realizarValidaciones(nombre, categoria, telefono, email, tipoNegocio, cedulaJuridica, razonSocial);
            int telefonoInt = Integer.parseInt(telefono.trim());
            long cedulaJuridicaLong = Long.parseLong(cedulaJuridica.trim());


            CJuridico obj = new CJuridico(nombre, telefonoInt, email, tipoNegocio, razonSocial, cedulaJuridicaLong);
            if(validarNuevoCJuridico(obj.identificacion)){
                insertarClienteJuridico(obj);
                request.getRequestDispatcher("confirmacionClienteJuridico.jsp").forward(request, response);
            }else{
                request.setAttribute("mensajeDeError", "ClienteJuridico ya existe.");
                request.getRequestDispatcher("errorCJuridico.jsp").forward(request, response);
            }
            

        } catch (NumberFormatException e) {
            request.setAttribute("mensajeDeError", "Teléfono o cédula jurídica contienen caracteres no válidos.");
            request.getRequestDispatcher("errorCJuridico.jsp").forward(request, response);
        } catch (ValidationException e) {
            request.setAttribute("mensajeDeError", e.getMessage());
            request.getRequestDispatcher("errorCJuridico.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CrearClienteJuridicoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void realizarValidaciones(String nombre, String categoria, String telefono, String email, 
                                      String tipoNegocio, String cedulaJuridica, String razonSocial) throws ValidationException {
        if (!ValidacionesFormularios.validarCamposRequeridos(nombre, categoria, telefono, email, tipoNegocio, cedulaJuridica, razonSocial)) {
            throw new ValidationException("Todos los campos son obligatorios.");
        }

        validar(ValidacionesFormularios.validarTelefonoCJuridico(telefono), "El número de teléfono debe tener 8 dígitos.");
        validar(ValidacionesFormularios.validarEmail(email), "Formato de correo electrónico incorrecto.");
        validar(ValidacionesFormularios.validarCedulaJuridica(cedulaJuridica), "La cédula jurídica debe tener 11 dígitos.");

        // Validar si los valores son numéricos
        validar(telefono.matches("\\d+"), "El número de teléfono debe contener solo dígitos.");
        validar(cedulaJuridica.matches("\\d+"), "La cédula jurídica debe contener solo dígitos.");
    }

    private void validar(boolean condicion, String mensajeError) throws ValidationException {
        if (!condicion) {
            throw new ValidationException(mensajeError);
        }
    }

    private class ValidationException extends Exception {
        public ValidationException(String mensaje) {
            super(mensaje);
        }
    }
}