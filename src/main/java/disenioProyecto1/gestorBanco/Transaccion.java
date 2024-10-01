/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.gestorBanco;

/**
 *
 * @author Nelson
 */
public class Transaccion {
    public double dineroEnCuenta; // adicion
    public double monto;
    public String tipoTransaccion; // Hay que hacer una lista de tipos de transaccion desde la capa de vista
    public String fecha;
    public double comision;
    public String numCuentaQuePertenese;

    public Transaccion (double monto, String tipoTransaccion, String fecha, double comision, double dineroEnCuenta, String numCuenta){
        this.dineroEnCuenta = dineroEnCuenta;
        this.fecha = fecha;
        this.monto = monto;
        this.tipoTransaccion = tipoTransaccion;
        this.comision = comision;
        this.numCuentaQuePertenese = numCuenta;
    }

    public boolean determinarSiHayCargo(){
        return false; // falta generar logica
    }

    public String determinarFechaActual(){return "DADA POR API";} // determinar api para conseguir fecha u otra solucion
    public boolean validarRetiro(CuentaBancaria cuenta){return true;} // determinar logica de validacion

}
