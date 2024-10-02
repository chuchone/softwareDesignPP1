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
    public static ResultadoCuenta existeCuentaBancariaRet(String numCuenta, double cantidadRetirar, String pin) throws SQLException {
        List<CuentaBancaria> listaCuentas = obtenerCuentasBancarias();
        for (CuentaBancaria cuenta : listaCuentas) {
            if (cuenta.numeroCuenta.equals(numCuenta) && cuenta.PIN_Asociado.equals(pin)) {
                cuenta.retirar(cantidadRetirar);
                limpiarTablaCuentas();
                boolean seInserto = insertarListaCuentasBancarias(listaCuentas);
                return new ResultadoCuenta(cuenta.dineroEnLaCuenta, cuenta.regisComisiones, true);
            }
        }

        // Si no se encuentra la cuenta
        return new ResultadoCuenta(0.0, 0.0, false);
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

