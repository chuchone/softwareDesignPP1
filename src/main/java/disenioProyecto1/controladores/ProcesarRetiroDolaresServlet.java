/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCFisico.obtenerListaClientesFisicos;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCJuridico.obtenerListaClientesJuridicos;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.obtenerCuentasBancarias;
import static disenioProyecto1.capaDatos.validaciones.ValidacionesCuentas.validarSiExisteCuentaYPin;
import disenioProyecto1.gestorBanco.CuentaBancaria;
import disenioProyecto1.gestorBanco.ResultadoCuenta;
import static disenioProyecto1.gestorBanco.ResultadoCuenta.existeCuentaBancariaRet;
import static disenioProyecto1.integracion.CifradorDES.encriptarPIN;
import static disenioProyecto1.integracion.ConexionBCCR.obtenerTipoCambio;
import static disenioProyecto1.integracion.SMSSender.mandarMensaje;
import static disenioProyecto1.integracion.ConexionBCCR.convertirDolaresAColones;
import disenioProyecto1.usuarios.CFisico;
import disenioProyecto1.usuarios.CJuridico;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Nelson
 */
@WebServlet("/ProcesarRetiroDolaresServlet")
public class ProcesarRetiroDolaresServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String numeroCuenta = request.getParameter("numeroCuenta");
        String pin = request.getParameter("pin");
        String montoDolaresStr = request.getParameter("monto");

        try {
            double montoRetiro = Double.parseDouble(montoDolaresStr);
            double tipoCambio =  obtenerTipoCambio("318");   
            double montoRetiroAlCambio = convertirDolaresAColones( montoRetiro, tipoCambio);
            String pinEncriptado = encriptarPIN(pin);
            boolean esValido = validarSiExisteCuentaYPin(numeroCuenta, pinEncriptado);

            if (esValido) {
                long telefono = obtenerTelefono(numeroCuenta);
                String telefonoString = String.valueOf(telefono);

                String codigoSMS = mandarMensaje(telefonoString); 

                // Guardar el código SMS, el PIN encriptado y el número de cuenta en la sesión
                HttpSession session = request.getSession();
                session.setAttribute("codigoSMS", codigoSMS);
                session.setAttribute("pinEncriptado", pinEncriptado);
                session.setAttribute("numeroCuenta", numeroCuenta);
                session.setAttribute("montoRetiro", montoRetiroAlCambio);
                // Redireccionar al JSP de ingresar SMS
                response.sendRedirect("ingresarSMSRetiDolares.jsp");
            } else {
                request.setAttribute("mensajeDeError", "PIN inválido.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("mensajeDeError", "Monto inválido.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProcesarRetiroColonesServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensajeDeError", "Ocurrió un problema.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    private static long obtenerTelefono (String numeroCuenta) throws SQLException{
        List<CuentaBancaria> listaCuentas = obtenerCuentasBancarias();
        ArrayList<CFisico> listaClientesFisicos = obtenerListaClientesFisicos();
        ArrayList<CJuridico> listaClientesJuridicos = obtenerListaClientesJuridicos();
        
        long identificion = obtenerIdentidadDeCuenta(listaCuentas, numeroCuenta);
        int numeroTelefonoCFisico = obtenerTelefonoCFisico(listaClientesFisicos, identificion);
        int numeroTelefonoCJuridico = obtenerTelefonoCJuridico(listaClientesJuridicos, identificion);
        
        if (numeroTelefonoCFisico != 0){
        
            return numeroTelefonoCFisico;
        }else{
            return numeroTelefonoCJuridico;
        }
        
    
    }
    
    
    private static int obtenerTelefonoCFisico (ArrayList<CFisico> listaClientesFisicos, long identificion){
        for(CFisico usuario: listaClientesFisicos){
            if(usuario.identificacion == identificion){
                return usuario.telefono;
            }
            
        }
        return 0;
    
    
    }
    private static int obtenerTelefonoCJuridico (ArrayList<CJuridico> listaClientesJuridicos, long identificion){
        for (CJuridico usuario: listaClientesJuridicos){
            if(usuario.identificacion == identificion){
                return usuario.telefono;
            }
        }
        return 0;
    
    }
    private static long obtenerIdentidadDeCuenta(List<CuentaBancaria> listaCuentas, String numeroCuenta){
    
        for (CuentaBancaria cuenta: listaCuentas){
            if(cuenta.numeroCuenta.equals(numeroCuenta)){
                return cuenta.cedulaPersonaAsociada;
                        
            }
        }
        return 0;
    
    }
}
