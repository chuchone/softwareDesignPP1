/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import disenioProyecto1.gestorBanco.ResultadoCuenta;
import static disenioProyecto1.gestorBanco.ResultadoCuenta.existeCuentaBancariaRet;
import static disenioProyecto1.integracion.CifradorDES.encriptarPIN;
import static disenioProyecto1.integracion.ConexionBCCR.convertirDolaresAColones;
import static disenioProyecto1.integracion.ConexionBCCR.obtenerTipoCambio;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        String montoStr = request.getParameter("monto");

        // Validar y convertir el monto a un número

        try {
            double monto = Double.parseDouble(montoStr);
            String pinEncriptado = encriptarPIN(pin);
            double tipoCambio = obtenerTipoCambio("318");
            double montoEnColones = convertirDolaresAColones(monto, tipoCambio);

            boolean informacionCuenta = existeCuentaBancariaRet(numeroCuenta, montoEnColones, pinEncriptado);
            if (informacionCuenta){


                request.setAttribute("numeroCuenta", numeroCuenta);
                request.setAttribute("montoDepositado", montoEnColones);
                //request.setAttribute("montoComision", informacionCuenta.getRegisComisiones());
                
                // Redirigir a la página de confirmación
                request.getRequestDispatcher("confirmarRetiroEnColones.jsp").forward(request, response);
            }else {
                request.setAttribute("error", "pin invalido.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            
            }
        } catch (NumberFormatException e) {
            // Manejar el error de conversión
            request.setAttribute("error", "Monto inválido.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
            
        } catch (Exception ex) {
            Logger.getLogger(ProcesarRetiroColonesServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "problemita.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

       
    }
}
