/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.modelo.gestorBanco;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosRegistros.insertarDatosCBancaria;
import static disenioProyecto1.capaDatos.validaciones.ValidacionesInternas.validarMulta;
import static disenioProyecto1.capaDatos.validaciones.ValidacionesInternas.validarRetiro;
import static disenioProyecto1.modelo.bitacoras.NotificationObserver.notificar;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Nelson
 */


public class CuentaBancaria implements IBasicosBanco {
    public long cedulaPersonaAsociada;
    public String fechaCreacion;
    public boolean statusCuentaActiva;
    public String PIN_Asociado;
    public double dineroEnLaCuenta;
    private static List<Transaccion> transacciones = new ArrayList<>();
    public double regisComisiones;
    public double cantidadRetiros;
    public String numeroCuenta;
    public String nombreDuenio;
    
    public CuentaBancaria(long cedulaPersonaAsociada, String PIN_Asociado, double depocitoInicial, double regisComisiones, double cantRetiros, boolean status, String fechaCreacion, String numeroCuenta, String nombreDuenio) { //verificar en documento PIN ASOCIADO
        this.cedulaPersonaAsociada = cedulaPersonaAsociada;
        this.PIN_Asociado = PIN_Asociado;
        this.fechaCreacion = fechaCreacion;
        this.statusCuentaActiva = status;
        this.dineroEnLaCuenta = depocitoInicial;
        this.regisComisiones = regisComisiones;
        this.cantidadRetiros = cantRetiros;
        this.numeroCuenta = numeroCuenta;
        this.nombreDuenio = nombreDuenio;
    }
    
    @Override
    public void depositar(double cant, String ipAddress, String userAgent, String country) throws SQLException{
        double comision = 0;
        dineroEnLaCuenta = dineroEnLaCuenta + cant;
        if (validarRetiro(dineroEnLaCuenta, cant)) {
            dineroEnLaCuenta = dineroEnLaCuenta + cant;
            cantidadRetiros = cantidadRetiros + 1;
            if (cantidadRetiros > 3) {
                comision = calcularCincoPorciento(dineroEnLaCuenta);
                aplicarMulta5Porciento(comision);
                crearRegistro("Deposito", dineroEnLaCuenta, cant, comision);
                notificarObservador(nombreDuenio, "El usuario realizo un depocito a su cuenta de "+ cant + "dinero en colones" ,ipAddress, userAgent, country);
                
            
            }else{
                crearRegistro("Deposito", dineroEnLaCuenta, cant, 0);
                notificarObservador(nombreDuenio, "El usuario realizo un depocito a su cuenta de "+ cant + "dinero en colones" ,ipAddress, userAgent, country);

            }
    
        }else{
            System.out.println("Fallo");

        }
    }
    
    @Override
    public void retirar(double cant, String ipAddress, String userAgent, String country) throws SQLException{ // falta mejorar diseño del metodo, implementarlo mejor
        double comision = 0;
        if (validarRetiro(dineroEnLaCuenta, cant)) {
            dineroEnLaCuenta = dineroEnLaCuenta - cant;
            cantidadRetiros = cantidadRetiros + 1;
            if (cantidadRetiros > 3) {
                comision = calcularCincoPorciento(dineroEnLaCuenta);
                aplicarMulta5Porciento(comision);
                crearRegistro("Retiro", dineroEnLaCuenta, cant, comision);
                notificarObservador(nombreDuenio, "El usuario realizo un retiro a su cuenta de "+ cant + "dinero en colones" ,ipAddress, userAgent, country);
            } else {
                crearRegistro("Retiro", dineroEnLaCuenta, cant, 0);
                notificarObservador(nombreDuenio, "El usuario realizo un retiro a su cuenta de "+ cant + "dinero en colones" ,ipAddress, userAgent, country);

            }
        }else{
            System.out.println("Fallo");}
    }
    
    public void setComision(double comision){
        regisComisiones = regisComisiones + comision;
    
    }
    
    private void crearRegistro(String tipoDeTransaccion, double dineroEnlaCuenta, double cant, double comision) throws SQLException{
        Transaccion transaccion = generarRegistro(cant, tipoDeTransaccion, comision,dineroEnlaCuenta);
        insertarDatosCBancaria(transaccion);
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
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        return formatoFecha.format(fechaActual);
    }

    private void agregarTransaccionALista(Transaccion registroTransaccion){
        transacciones.add(registroTransaccion);
    }
    private Transaccion generarRegistro(double cant, String tipo, double comision, double dineroEnLaCuenta){
        return new Transaccion(cant, tipo, obtenerFechaActual(), comision, dineroEnLaCuenta, numeroCuenta);
    }
    
    private void notificarObservador (String nombreDuenio, String accion, String ipAddress, String userAgent, String country) throws SQLException{    
        notificar(accion, nombreDuenio, ipAddress, userAgent, country);    
    }
}

