package disenioProyecto1.controladores;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.insertarDatosCBancaria;
import disenioProyecto1.gestorBanco.CuentaBancaria;
import static disenioProyecto1.gestorBanco.CuentaBancaria.obtenerFechaActual;
import static disenioProyecto1.gestorBanco.GestionBanco.generarCodigoCuentaBancaria;
import static disenioProyecto1.integracion.CifradorDES.encriptarPIN;
import static disenioProyecto1.capaDatos.validaciones.ValidacionesCuentas.*;
import static disenioProyecto1.capaDatos.validaciones.ValidacionesCuentas.validarSiExisteCliente;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/CrearCuentasBancariasServlet")
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


    private void procesarRegistroCuenta(String cedula, String pin, String montoInicial, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int montoInt = Integer.parseInt(montoInicial);
            var pinEncriptado = encriptarPIN(pin);
            
            System.out.println("antes de llamar a listas");
            String nombreCliente = validarSiExisteCliente(cedula);
            System.out.println("desúes de llamar a listas");

            if (!nombreCliente.equals("noHay")) {
                String numeroCuenta = generarCodigoCuentaBancaria();
                CuentaBancaria obj = new CuentaBancaria(convertirStringALong(cedula), pinEncriptado, montoInt, 0, 0, true, obtenerFechaActual(), numeroCuenta, nombreCliente);
                if (insertarDatosCBancaria(obj)){
                    request.setAttribute("cuentaBancaria", obj);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("confirmacionCuenta.jsp");
                    dispatcher.forward(request, response); 
                    }else{ 
                    request.setAttribute("mensajeDeError", "Hubo un error");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("errorCrearCuenta.jsp");
                    dispatcher.forward(request, response); 
              
                }
            } else {
                request.setAttribute("mensajeDeError", "No existe usuario para asignarle la cuenta");
                RequestDispatcher dispatcher = request.getRequestDispatcher("errorCrearCuenta.jsp");
                dispatcher.forward(request, response);                        
            }
        } catch (NumberFormatException e) {
            Logger.getLogger(CrearCuentasBancariasServlet.class.getName()).log(Level.SEVERE, "Error al parsear el monto inicial", e);
            request.setAttribute("errorMessage", "El monto inicial debe ser un número válido");
            request.getRequestDispatcher("crearCuenta.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CrearCuentasBancariasServlet.class.getName()).log(Level.SEVERE, "Hubieron problemas de conexión", ex);
            request.setAttribute("errorMessage", "Hubieron problemas de conexión");
            request.getRequestDispatcher("crearCuenta.jsp").forward(request, response);
        }
    }
    public long convertirStringALong(String cadena) {
        try {
            return Long.parseLong(cadena);
        } catch (NumberFormatException e) {
            System.out.println("Error: La cadena no es un número válido.");
            e.printStackTrace(); // O puedes manejar el error de otra manera
            return -1; // Devuelve un valor por defecto en caso de error
        }
}

}
