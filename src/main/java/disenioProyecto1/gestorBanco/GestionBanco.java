/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.gestorBanco;

/**
 *
 * @author Nelson
 */

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCFisico.obtenerListaClientesFisicos;
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


    public static void agregarCuentaBasesDatos(CuentaBancaria obj)
    {
    
    
    
    }


}
  