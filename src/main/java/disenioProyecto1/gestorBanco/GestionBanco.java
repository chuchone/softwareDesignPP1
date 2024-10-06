/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.gestorBanco;

/**
 *
 * @author Nelson
 */


import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosRegistros.obtenerTransacciones;
import static disenioProyecto1.capaDatos.conexionSql.BasesDatos.numClientesCreados;
import static disenioProyecto1.capaDatos.conexionSql.BasesDatos.numCuentasCreadas;
import java.util.ArrayList;
import java.util.List;
import disenioProyecto1.usuarios.CJuridico;
import disenioProyecto1.usuarios.CFisico;
import java.sql.SQLException;

public class GestionBanco {



    public static String generarCodigoCliente() {      
        return "CTE" + numClientesCreados();
    }
    public static String generarCodigoCuentaBancaria() {
        return "CTA" + numCuentasCreadas();
    }


    public static double obtenerComisiones(String numeroCuenta) throws SQLException{
    
        List<Transaccion> listaTransacciones = obtenerTransacciones();
        double cantidad = 0;
        for(Transaccion transaccion: listaTransacciones){
        
            if(transaccion.numCuentaQuePertenese.equals(numeroCuenta)){
                cantidad = cantidad + transaccion.comision;
            
            }
        
        }
        listaTransacciones.clear();
        return cantidad;
    }


}
  