/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.gestorBanco;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.insertarListaCuentasBancarias;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.limpiarTablaCuentas;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.obtenerCuentasBancarias;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nelson
 */
public class ResultadoCuenta {
    private double dineroEnLaCuenta;
    private double regisComisiones;
    private boolean existeCuenta;

    public ResultadoCuenta(double dineroEnLaCuenta, double regisComisiones, boolean existeCuenta) {
        this.dineroEnLaCuenta = dineroEnLaCuenta;
        this.regisComisiones = regisComisiones;
        this.existeCuenta = existeCuenta;
    }

    public double getDineroEnLaCuenta() {
        return dineroEnLaCuenta;
    }

    public double getRegisComisiones() {
        return regisComisiones;
    }

    public boolean isExisteCuenta() {
        return existeCuenta;
    }
    
    public static ResultadoCuenta existeCuentaBancariaDep(String numCuenta, double cantidadDepositar) throws SQLException {
        List<CuentaBancaria> listaCuentas = obtenerCuentasBancarias();
        for (CuentaBancaria cuenta : listaCuentas) {
            if (cuenta.numeroCuenta.equals(numCuenta)) {
                cuenta.depositar(cantidadDepositar);
                limpiarTablaCuentas();
                boolean seInserto = insertarListaCuentasBancarias(listaCuentas);
                return new ResultadoCuenta(cuenta.dineroEnLaCuenta, cuenta.regisComisiones, true);
            }
        }


        // Si no se encuentra la cuenta
        return new ResultadoCuenta(0.0, 0.0, false);
    }
    
    
    public static boolean existeCuentaBancariaRet(String numCuenta, double cantidadRetirar, String pin) throws SQLException {
        List<CuentaBancaria> listaCuentas = obtenerCuentasBancarias();

        boolean existe = buscarCuentaPorNumeroYPin(listaCuentas, numCuenta, pin);
        if (!existe) {
            return false;
        }

        if (!cantidadEnCuenta(listaCuentas, cantidadRetirar)) {
            return false;
        }

        return procesarRetiro(cantidadRetirar, listaCuentas, numCuenta);
    }
    
    private static boolean cantidadEnCuenta(List<CuentaBancaria> listaCuentas, double cantidadRetirar){
        
        for(CuentaBancaria cuenta: listaCuentas){
        
            if (cuenta.dineroEnLaCuenta >= cantidadRetirar){
                return true;
            }
        }
        return false;
    }

    // Método para buscar la cuenta por número y PIN
    private static boolean buscarCuentaPorNumeroYPin(List<CuentaBancaria> listaCuentas, String numCuenta, String pin) {
        for (CuentaBancaria cuenta : listaCuentas) {
            if (cuenta.numeroCuenta.equals(numCuenta) && cuenta.PIN_Asociado.equals(pin)) {
                return true;
            }
        }
        return false;
    }

    // Método para verificar si tiene fondos suficientes
    private static boolean tieneFondosSuficientes(CuentaBancaria cuenta, double cantidadRetirar) {
        return cuenta.dineroEnLaCuenta >= cantidadRetirar;
    }

    // Método para procesar el retiro y guardar cambios en la base de datos
    private static boolean  procesarRetiro(double cantidadRetirar, List<CuentaBancaria> listaCuentas, String numCuenta) throws SQLException {
        for(CuentaBancaria cuenta: listaCuentas){
            if (cuenta.numeroCuenta.equals(numCuenta)){
                cuenta.retirar(cantidadRetirar);
            }

        }
        limpiarTablaCuentas();
        boolean seInserto = insertarListaCuentasBancarias(listaCuentas);
        return seInserto;

    }
    public static boolean existeCuentaYPin(String numCuenta,String pinCif) throws SQLException{
        List<CuentaBancaria> listaCuentas = obtenerCuentasBancarias();
        for (CuentaBancaria cuenta : listaCuentas) {
            if(cuenta.numeroCuenta.equals(cuenta) && cuenta.PIN_Asociado.equals(pinCif)){
                return true;
            }
        }
        return false;
    
    }
    public static long existeCuenta(String numCuenta) throws SQLException{
        List<CuentaBancaria> listaCuentas = obtenerCuentasBancarias();
        for (CuentaBancaria cuenta : listaCuentas) {
            if(cuenta.numeroCuenta.equals(cuenta)){
                return cuenta.cedulaPersonaAsociada;
            }        
        }
        return 0;
    
    }
}

