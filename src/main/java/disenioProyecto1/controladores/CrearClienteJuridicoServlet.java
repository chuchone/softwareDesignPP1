/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import disenioProyecto1.capaDatos.validaciones.ValidacionesFormularios;
import static disenioProyecto1.gestorBanco.GestionBanco.agregarAListaCJuridico;
import disenioProyecto1.usuarios.CJuridico;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Nelson
 */
@WebServlet("/CrearClienteJuridicoServlet")
public class CrearClienteJuridicoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre").trim();
        String categoria = request.getParameter("categoria").trim();
        String telefono = request.getParameter("telefono").trim();
        String email = request.getParameter("email").trim();
        String tipoNegocio = request.getParameter("tipoNegocio").trim();
        String cedulaJuridica = request.getParameter("cedulaJuridica").trim();
        String razonSocial = request.getParameter("razonSocial").trim();

        try {
            realizarValidaciones(nombre, categoria, telefono, email, tipoNegocio, cedulaJuridica, razonSocial);
            // Si todas las validaciones pasan, redirigir a la página de confirmación
            int telefonoInt = Integer.parseInt(telefono);
            int cedulaJuridicaInt = Integer.parseInt(cedulaJuridica);
           
            
            CJuridico obj = new CJuridico(nombre, telefonoInt, email, tipoNegocio, razonSocial, cedulaJuridicaInt);
            agregarAListaCJuridico(obj);
            
            request.getRequestDispatcher("confirmacionClienteJuridio.jsp").forward(request, response);

        } catch (ValidationException e) {
            request.setAttribute("mensajeDeError", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void realizarValidaciones(String nombre, String categoria, String telefono, String email, 
                                      String tipoNegocio, String cedulaJuridica, String razonSocial) throws ValidationException {
        if (!ValidacionesFormularios.validarCamposRequeridos(nombre, categoria, telefono, email, tipoNegocio, cedulaJuridica, razonSocial)) {
            throw new ValidationException("Todos los campos son obligatorios.");
        }
        validar(ValidacionesFormularios.validarTelefonoCJuridico(telefono), "El número de teléfono debe tener 10 dígitos.");
        validar(ValidacionesFormularios.validarEmail(email), "Formato de correo electrónico incorrecto.");
        validar(ValidacionesFormularios.validarCedulaJuridica(cedulaJuridica), "La cédula jurídica debe tener 10 dígitos.");
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