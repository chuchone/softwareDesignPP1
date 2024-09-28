/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.conexionSql;

import disenioProyecto1.usuarios.CFisico;
import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Nelson
 */
public class ConexionBasesDeDatos {
    
    public static void agregarCFicoBasesDatos(CFisico obj) {
        Connection cn = null;
        PreparedStatement pat = null;
        
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectodisenio", "root", "");
            pat = cn.prepareStatement("insert into datosClienteFisico values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pat.setInt(1, obj.telefono);
            pat.setString(2, obj.cuenta);
            pat.setString(3, obj.nombre);
            pat.setString(4, obj.codigoCliente);
            pat.setString(5, obj.correo);
            pat.setInt(6, obj.identificacion);
            pat.setString(7, "fisico");
            pat.setInt(8, obj.maxCuentas);
            pat.setString(9, obj.getfechaNacimiento());
            
            pat.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (pat != null) pat.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
                 
}