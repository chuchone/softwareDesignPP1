/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.usuarios;
import disenioProyecto1.gestorBanco.CuentaBancaria;
/**
 *
 * @author Nelson
 */
import java.util.ArrayList;

public abstract class Cliente {
    public ArrayList<CuentaBancaria>cuentas = new ArrayList<CuentaBancaria>();
    public int telefono;
    public String cuenta; // todo antes del @
    public String nombre;
    public String codigoCliente;
    public String correo;
    public long identificacion; // para juridico o para fisico
    public String tipo;
    public int maxCuentas;
    public void darseDeAlta(){}
    public void agregarCuenta(CuentaBancaria c){
        cuentas.add(c);
    }
    public ArrayList<CuentaBancaria> getListaCuentas(){return cuentas;}
    public void enviarCorreoInactivacion(String mensaje){}
}
