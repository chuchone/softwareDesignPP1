package disenioProyecto1.controladores;

import disenioProyecto1.gestorBanco.ResultadoCuenta;
import static disenioProyecto1.integracion.CifradorDES.encriptarPIN;
import static disenioProyecto1.gestorBanco.ResultadoCuenta.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/ProcesarRetiroColonesServlet")
public class ProcesarRetiroColonesServlet extends HttpServlet {
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
            ResultadoCuenta informacionCuenta = existeCuentaBancariaRet(numeroCuenta, monto, pinEncriptado);
            if (informacionCuenta.isExisteCuenta()){


                request.setAttribute("numeroCuenta", numeroCuenta);
                request.setAttribute("montoDepositado", monto);
                request.setAttribute("montoComision", informacionCuenta.getRegisComisiones());
                
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
