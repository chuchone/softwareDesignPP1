package disenioProyecto1.controladores;

import disenioProyecto1.gestorBanco.CuentaBancaria;
import static disenioProyecto1.gestorBanco.CuentaBancaria.obtenerFechaActual;
import static disenioProyecto1.gestorBanco.GestionBanco.generarCodigoCuentaBancaria;
import static disenioProyecto1.integracion.CifradorDES.encriptarPIN;
import static disenioProyecto1.capaDatos.validaciones.ValidacionesCuentas.*;
import static disenioProyecto1.capaDatos.validaciones.ValidacionesCuentas.validarSiExisteCliente;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CrearCuentasBancariasServlet", urlPatterns = {"/CrearCuentasBancariasServlet"})
public class CrearCuentasBancariasServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String identidad = request.getParameter("identidad").trim();
        String pin = request.getParameter("pin").trim();
        String montoInicial = request.getParameter("montoInicial").trim();
        
        // Validar campos
        String errorMessage = validarCampos(identidad, pin, montoInicial);
        
        // Verificar si hay errores
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("crearCuenta.jsp").forward(request, response);
        } else {
            procesarRegistroCuenta(identidad, pin, montoInicial, request, response);
        }
    }

    private String validarCampos(String identidad, String pin, String montoInicial) {
        String errorMessage = validarCamposObligatorios(identidad, pin, montoInicial);
        if (errorMessage != null) {
            return errorMessage;
        }

        errorMessage = validarPin(pin);
        if (errorMessage != null) {
            return errorMessage;
        }

        errorMessage = validarMontoInicial(montoInicial);
        return errorMessage; // Retorna el mensaje de error o null si no hay errores
    }


    private void procesarRegistroCuenta(String identidad, String pin, String montoInicial, HttpServletRequest request, HttpServletResponse response) {
        try {
            int montoInt = Integer.parseInt(montoInicial);
            var pinEncriptado = encriptarPIN(pin);
            String nombreCliente = validarSiExisteCliente();
            if (nombreCliente != "noHay"){
                String numeroCuenta = generarCodigoCuentaBancaria();
                CuentaBancaria obj = new CuentaBancaria(identidad, pinEncriptado, montoInt, 0, 0, true, obtenerFechaActual(), numeroCuenta);
                // base de datos
                response.sendRedirect("confirmacionCuenta.jsp");   // Redirigir a la página de confirmación
            }else{
                request.setAttribute("errorMessage", "Hubieron problemas de conexion");                        
            }
        } catch (Exception ex) {
            Logger.getLogger(CrearCuentasBancariasServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Hubieron problemas de conexion");
            try {
                request.getRequestDispatcher("crearCuenta.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                Logger.getLogger(CrearCuentasBancariasServlet.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

}
