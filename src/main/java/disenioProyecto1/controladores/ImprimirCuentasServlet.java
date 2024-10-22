/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package disenioProyecto1.controladores;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.obtenerCuentasBancarias;
import disenioProyecto1.modelo.gestorBanco.CuentaBancaria;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nelson
 */
@WebServlet("/ImprimirCuentasServlet")
public class ImprimirCuentasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String identificacion = request.getParameter("identificacion");

        if (identificacion != null && identificacion.length() <= 11) {
            try {
                long identificacionLong = Long.parseLong(identificacion);
                List<CuentaBancaria> listaCuentasDeUsuario = obtenerListaDeCuentasExistentes(identificacionLong);
                request.setAttribute("listaCuentasDeUsuario", listaCuentasDeUsuario);
                request.getRequestDispatcher("imprimirCuentasUsuarios.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ImprimirCuentasServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.setAttribute("mensajeDeError", "Identificación inválida. Debe ser de 11 caracteres o menos.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    private static List<CuentaBancaria> obtenerListaDeCuentasExistentes (long identificacion) throws SQLException{ 
        List<CuentaBancaria> listaCuentas = obtenerCuentasBancarias();
        List<CuentaBancaria> listaCuentasDeUsuario = new ArrayList<>();
        
        for(CuentaBancaria cuenta :  listaCuentas){
            if(cuenta.cedulaPersonaAsociada == identificacion){
                listaCuentasDeUsuario.add(cuenta);
            }
        }
        return listaCuentasDeUsuario;
    
    }      
        
}
