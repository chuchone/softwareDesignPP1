/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.validaciones;

/**
 *
 * @author Nelson
 */

public class ValidacionesInternas {
    public static boolean validarNumeroTel(int numero){
        return false;
    }
    public static String conseguirNombreDeUsuario(String email) {
        if (email == null || !email.contains("@")) {
            return("n");
        }
        return email.substring(0, email.indexOf('@'));
    }
    public void validarMonto(int monto){

    }
    public static boolean validarRetiro(double dineroTotal, double retiro){
        return dineroTotal >= retiro;
    }
    public static boolean validarMulta(double dineroTotal, double multa) {
        return dineroTotal >= multa;
    }

}
