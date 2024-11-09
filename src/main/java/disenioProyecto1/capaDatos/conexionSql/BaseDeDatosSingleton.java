/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.conexionSql;

/**
 *
 * @author Nelson
 */
public class BaseDeDatosSingleton {
    
    private static BasesDatos baseDeDatosInstance = null; // Instancia única

    public static BasesDatos conectarBasesDeDatos() {
        if (baseDeDatosInstance == null) { 
            baseDeDatosInstance = new BasesDatos();
        }
        return baseDeDatosInstance;
    }
}
    

