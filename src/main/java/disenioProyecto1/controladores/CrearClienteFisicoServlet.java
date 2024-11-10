 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;


import disenioProyecto1.capaDatos.conexionSql.conectar.BasesDatos;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCFisico.delegarCrearCFisico;
import jakarta.servlet.annotation.WebServlet;
import disenioProyecto1.capaDatos.validaciones.ValidacionesFormularios;
import static disenioProyecto1.capaDatos.validaciones.ValidacionesUsuarios.validarNuevoCFisico;
import static disenioProyecto1.modelo.gestorBanco.GestionBanco.generarCodigoCliente;

import disenioProyecto1.modelo.usuarios.CFisico;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Nelson
 */

@WebServlet("/CrearClienteFisicoServlet")
public class CrearClienteFisicoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperar los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String categoria = request.getParameter("categoria");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String identificacion = request.getParameter("identificacion");
        String maxCuentas = request.getParameter("maxCuentas");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        
        String mensajeDeError = validarDatos(nombre, email, telefono);
        if (mensajeDeError != null) {
            manejarError(request, response, mensajeDeError);
            return;
        }
        int[] valoresInt = convertirValoresEnteros(telefono, identificacion, maxCuentas, request, response);
        if (valoresInt == null) {
            return; 
        }        
        boolean ClienteNoExiste = false;
        try {
            ClienteNoExiste = validarNuevoCFisico(valoresInt[1]);
        } catch (SQLException ex) {
            Logger.getLogger(CrearClienteFisicoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ClienteNoExiste){
            String codigoCliente = generarCodigoCliente();               
            CFisico obj = new CFisico(valoresInt[0], email, nombre, valoresInt[1], fechaNacimiento, valoresInt[2], codigoCliente);
            delegarCrearCFisico(obj);
            prepararConfirmacion(request, codigoCliente, nombre, telefono, email, identificacion, maxCuentas, fechaNacimiento);
            request.getRequestDispatcher("/confirmacionClienteFisico.jsp").forward(request, response);
        }    
    }
    private String validarDatos(String nombre, String email, String telefono) {
        if (nombre == null || nombre.isEmpty()) {
            return "El nombre es obligatorio.";
        } else if (!ValidacionesFormularios.validarEmail(email)) {
            return "El correo electrónico no es válido.";
        } else if (!ValidacionesFormularios.validarTelefono(telefono)) {
            return "El número de teléfono no es válido.";
        }
        return null; // Todo es válido
    }
    private void manejarError(HttpServletRequest request, HttpServletResponse response, String mensajeDeError)
            throws ServletException, IOException {
        request.setAttribute("mensajeDeError", mensajeDeError);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
    private int[] convertirValoresEnteros(String telefono, String identificacion, String maxCuentas, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int telefonoInt, identificacionInt, maxCuentasInt;
        try {
            telefonoInt = Integer.parseInt(telefono);
            identificacionInt = Integer.parseInt(identificacion);
            maxCuentasInt = Integer.parseInt(maxCuentas);
        } catch (NumberFormatException e) {
            String mensajeDeError = "Los valores de teléfono, identificación y cantidad máxima de cuentas deben ser numéricos.";
            request.setAttribute("mensajeDeError", mensajeDeError);
            manejarError(request, response, mensajeDeError); // Reutiliza la función de manejo de errores
            return null; // Indica un error
        }
        return new int[]{telefonoInt, identificacionInt, maxCuentasInt}; // Retorna los valores como un arreglo
    }
    private void prepararConfirmacion(HttpServletRequest request,String codigoCliente, String nombre, String telefono, String email,
            String identificacion, String maxCuentas, String fechaNacimiento) {
        request.setAttribute("codigoCliente", codigoCliente);        
        request.setAttribute("nombre", nombre);
        request.setAttribute("telefono", telefono);
        request.setAttribute("email", email);
        request.setAttribute("identificacion", identificacion);
        request.setAttribute("maxCuentas", maxCuentas);
        request.setAttribute("fechaNacimiento", fechaNacimiento);
    }
}
