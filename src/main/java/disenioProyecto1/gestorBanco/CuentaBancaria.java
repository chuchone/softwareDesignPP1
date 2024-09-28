/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.gestorBanco;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import disenioProyecto1.gestorBanco.Transaccion;
import disenioProyecto1.capaDatos.validaciones.ValidacionesInternas;
import static disenioProyecto1.capaDatos.validaciones.ValidacionesInternas.validarMulta;
import static disenioProyecto1.capaDatos.validaciones.ValidacionesInternas.validarRetiro;

/**
 *
 * @author Nelson
 */


public class CuentaBancaria {
    public String identificador;
    public String fechaCreacion;
    public boolean statusCuentaActiva;
    public String PIN_Asociado;
    public double dineroEnLaCuenta;
    private static List<Transaccion> transacciones = new ArrayList<>();
    private static double regisComisiones = 0;
    private static int cantidadRetiros = 0;
    
    public CuentaBancaria(String identificador, String PIN_Asociado, int depocitoInicial) { //verificar en documento PIN ASOCIADO

        this.identificador = identificador;
        this.PIN_Asociado = PIN_Asociado;
        this.fechaCreacion = obtenerFechaActual();
        this.statusCuentaActiva = true;
        this.dineroEnLaCuenta = depocitoInicial;
    }

    public void depositar(double cant){
        dineroEnLaCuenta = dineroEnLaCuenta + cant;
        crearRegistro("Deposito", dineroEnLaCuenta, cant, 0);
    }
    public void retirar(double cant){ // falta mejorar diseño del metodo, implementarlo mejor
        double comision = 0;
        if (validarRetiro(dineroEnLaCuenta, cant)) {
            dineroEnLaCuenta = dineroEnLaCuenta - cant;
            cantidadRetiros = cantidadRetiros + 1;
            if (cantidadRetiros > 3) {
                comision = calcularCincoPorciento(dineroEnLaCuenta);
                aplicarMulta5Porciento(comision);
                crearRegistro("Retiro", dineroEnLaCuenta, cant, comision);
            } else {
                crearRegistro("Retiro", dineroEnLaCuenta, cant, 0);
            }
        }else{
            System.out.println("Fallo");}
    }


    private void crearRegistro(String tipoDeTransaccion, double dineroEnlaCuenta, double cant, double comision){
        Transaccion transaccion = generarRegistro(cant, tipoDeTransaccion, comision,dineroEnlaCuenta);
        agregarTransaccionALista(transaccion);
    }
    private void aplicarMulta5Porciento(double comision){
        if (validarMulta(dineroEnLaCuenta, comision)){
            dineroEnLaCuenta = dineroEnLaCuenta - comision;}
        regisComisiones = regisComisiones + 1;
    }

    private static double calcularCincoPorciento(double cant) {
        return cant * 0.05;
    }
    public static String obtenerFechaActual(){
            LocalDateTime fechaActual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            return fechaActual.format(formato);
    }

    private void agregarTransaccionALista(Transaccion registroTransaccion){
        transacciones.add(registroTransaccion);
    }
    private Transaccion generarRegistro(double cant, String tipo, double comision, double dineroEnLaCuenta){
        return new Transaccion(cant, tipo, obtenerFechaActual(), comision, dineroEnLaCuenta);
    }
    public void imprimir(){
        System.out.println("Identificador de la Cuenta: " + identificador);
        System.out.println("Fecha de Creación: " + fechaCreacion);
        System.out.println("Estado de la Cuenta: " + (statusCuentaActiva ? "Activa" : "Inactiva"));
        System.out.println("PIN Asociado: " + PIN_Asociado);
        System.out.println("Dinero en la Cuenta: " + dineroEnLaCuenta);
        System.out.println("Total de comisiones registradas: " + regisComisiones);
        System.out.println("Cantidad de retiros: " + cantidadRetiros);
        System.out.println("--------------------------");
        System.out.println("Transacciones: ");
        imprimirRegistros();
        System.out.println("--------------------------");

    }
    public void imprimirRegistros(){
        for (Transaccion transacciones : transacciones){
            transacciones.imprimir();
        }
    }
}

