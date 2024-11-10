
package disenioProyecto1.capaDatos.conexionSql.conectar;

import disenioProyecto1.capaDatos.conexionSql.conectar.BasesDatos;

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
    

