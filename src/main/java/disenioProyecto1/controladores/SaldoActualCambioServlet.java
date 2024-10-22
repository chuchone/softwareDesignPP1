/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.obtenerCuentasBancarias;
import disenioProyecto1.modelo.gestorBanco.CuentaBancaria;
import static disenioProyecto1.integracion.CifradorDES.encriptarPIN;
import static disenioProyecto1.integracion.ConexionBCCR.obtenerTipoCambio;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nelson
 */
@WebServlet("/SaldoActualCambioServlet")
public class SaldoActualCambioServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String numeroCuenta = request.getParameter("numeroCuenta");
        String pin = request.getParameter("pin");
        
        try {
            // Cifrado del PIN
            String pinCifrado = encriptarPIN(pin);
            
            // Obtener lista de cuentas
            List<CuentaBancaria> listaDeCuentas = obtenerCuentasBancarias();
            
            if (existeCuenta(listaDeCuentas, pinCifrado, numeroCuenta)) {
                String nombreCliente = obtenerNombre(listaDeCuentas, numeroCuenta);
                Double saldo = obtenerSaldo(listaDeCuentas, numeroCuenta);
                double tipoCambio = obtenerTipoCambio("318");
                double saldoAlCambio = saldo/tipoCambio;
                request.setAttribute("nombreCliente", nombreCliente);
                request.setAttribute("saldo", saldoAlCambio);
                
                // Redirigir a la página de confirmación
                request.getRequestDispatcher("enseniarSaldoCambio.jsp").forward(request, response);
            } else {
                // En caso de que no se encuentre la cuenta o el PIN no coincida
                response.sendRedirect("error.jsp");
            }

        } catch (Exception e) {
            Logger.getLogger(SaldoActualServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("error.jsp"); // Redirigir a una página de error si ocurre una excepción
        }
    }

    private boolean existeCuenta(List<CuentaBancaria> listaDeCuentas, String pinCifrado, String numeroCuenta) {
        for (CuentaBancaria cuenta : listaDeCuentas) {
            if (cuenta.PIN_Asociado.equals(pinCifrado) && cuenta.numeroCuenta.equals(numeroCuenta)) {
                return true;
            }
        }
        return false;
    }

    private String obtenerNombre(List<CuentaBancaria> listaDeCuentas, String numeroCuenta) {
        for (CuentaBancaria cuenta : listaDeCuentas) {
            if (cuenta.numeroCuenta.equals(numeroCuenta)) {
                return cuenta.nombreDuenio;
            }
        }
        return "No disponible";
    }

    private double obtenerSaldo(List<CuentaBancaria> listaDeCuentas, String numeroCuenta) {
        for (CuentaBancaria cuenta : listaDeCuentas) {
            if (cuenta.numeroCuenta.equals(numeroCuenta)) {
                return cuenta.dineroEnLaCuenta;
            }
        }
        return 0.0;
    }
}
