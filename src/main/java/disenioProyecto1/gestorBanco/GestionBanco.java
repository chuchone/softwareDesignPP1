/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.gestorBanco;

/**
 *
 * @author Nelson
 */

import java.util.ArrayList;
import java.util.List;

import disenioProyecto1.usuarios.CJuridico;
import disenioProyecto1.usuarios.CFisico;

public class GestionBanco {
    public static int contadorCliente = 0;
    public static int contadorCBancaria = 0;
    private static ArrayList<CJuridico> J = new ArrayList<>();
    private static ArrayList<CFisico> F = new ArrayList<>();
    private static ArrayList<CuentaBancaria> cuentasBancarias = new ArrayList<>();


    public static String generarCodigoCliente() {
        contadorCliente++;
        return "CTE" + contadorCliente;
    }
    public static String generarCodigoCuentaBancaria() {
        contadorCBancaria++;
        return "CTA" + contadorCBancaria;
    }

    public static void agregarAListaCJuridico(CJuridico obj){
        J.add(obj);
    }

    public static void agregarAListaCFisico(CFisico obj){
        F.add(obj);
    }
    public static void agregarAListaCuentasBancarias(CuentaBancaria obj){
        cuentasBancarias.add(obj);
    }

    public static ArrayList<CFisico> getF() {
        return F;
    }

    public static ArrayList<CJuridico> getJ() {
        return J;
    }
}
