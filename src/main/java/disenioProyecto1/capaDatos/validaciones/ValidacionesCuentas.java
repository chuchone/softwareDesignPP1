/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.validaciones;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCFisico.obtenerListaClientesFisicos;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCJuridico.obtenerListaClientesJuridicos;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.obtenerCuentasBancarias;
import disenioProyecto1.modelo.gestorBanco.CuentaBancaria;
import disenioProyecto1.modelo.usuarios.CFisico;
import disenioProyecto1.modelo.usuarios.CJuridico;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nelson
 */
public class ValidacionesCuentas {
    public static String validarCamposObligatorios(String identidad, String pin, String montoInicial) {
        if (identidad.isEmpty() || pin.isEmpty() || montoInicial.isEmpty()) {
            return "Todos los campos son obligatorios.";
        }
        return null; // Sin errores
    }
    

    public static String validarPin(String pin) {
        if (pin.length() != 7) {
            return "El PIN debe tener 7 dígitos.";
        }
        return null; // Sin errores
    }

    public static String validarMontoInicial(String montoInicial) {
        if (!isNumeric(montoInicial) || Integer.parseInt(montoInicial) <= 0) {
            return "El monto inicial debe ser un número positivo sin decimales.";
        }
        return null; // Sin errores
    }
        // Método auxiliar para verificar si una cadena es numérica
    public  static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    public static String validarSiExisteCliente(String cedula) throws SQLException {
        String nombreCFisico = existeCFisico(cedula);
        String nombreCJuridico = existeCJuridico(cedula);  // Cambiado a nombreCJuridico

        if (nombreCFisico != "noHay") {  // Corregido el operador
            return nombreCFisico;
        } else if (nombreCJuridico != "noHay") {  // Corregido el operador
            return nombreCJuridico;  // Cambiado a nombreCJuridico
        }
        return "noHay";
    }

    private static String existeCJuridico(String cedula) throws SQLException {
        try {
            long cedulaJuridicaLong = Long.parseLong(cedula);
            ArrayList<CJuridico> listaClienteJuridico = obtenerListaClientesJuridicos();
            for (CJuridico cliente : listaClienteJuridico) {            
                if (cliente.identificacion == cedulaJuridicaLong) {
                    return cliente.nombre;
                }
            }
        } catch (NumberFormatException e) {
            // Maneja el caso en que la cédula no sea un número válido
            return "noHay";
        }
        return "noHay";        
    }

    private static String existeCFisico(String cedula) throws SQLException {
        try {
            long cedulaFisicaLong = Long.parseLong(cedula.trim());
            ArrayList<CFisico> listaClienteFisico = obtenerListaClientesFisicos();  
            for (CFisico cliente : listaClienteFisico) {
                if (cliente.identificacion == cedulaFisicaLong) {               
                    return cliente.nombre;  
                }
            }
        } catch (NumberFormatException e) {
            // Maneja el caso en que la cédula no sea un número válido
            return "noHay";
        }
        return "noHay";   
    }
    
    public static boolean validarSiExisteCuentaYPin(String numCuenta, String pin) throws SQLException{
        List<CuentaBancaria> listaCuentas = obtenerCuentasBancarias();
        for(CuentaBancaria cuenta : listaCuentas){
            if (cuenta.PIN_Asociado.equals(pin) && cuenta.numeroCuenta.equals(numCuenta)){
                listaCuentas.clear();
                return true;
            }        
        }
        listaCuentas.clear();
        return false;
    
    }
    

}
