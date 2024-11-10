/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.conexionSql;

import disenioProyecto1.capaDatos.conexionSql.conectar.BaseDeDatosSingleton;
import disenioProyecto1.capaDatos.conexionSql.conectar.BasesDatos;
import disenioProyecto1.modelo.bitacoras.Bitacoras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nelson
 */
public class BaseDeDatosBitacoras {
    public static void insertarBitacora(Bitacoras obj) throws SQLException{
    
        BasesDatos baseDeDatos = BaseDeDatosSingleton.conectarBasesDeDatos();
        Connection con = baseDeDatos.getConnection();
        String sql = "INSERT INTO bitacora VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement insertador = null;
        try{
            insertador = con.prepareStatement(sql); 
            insertador.setString(1, obj.usuario);
            insertador.setString(2, obj.fechaHoraAccion);
            insertador.setString(3, obj.ipAcceso);
            insertador.setString(4, obj.sistemaOperativo);
            insertador.setString(5, obj.paisAcceso);
            insertador.setString(6, obj.numeroBitacora);
            insertador.setString(7, obj.accion);
            
            int filasInsertadas = insertador.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("¡Datos insertados correctamente!");
            }
        
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (insertador != null) {
                insertador.close();
            }
        }    
    }
    
    public static ArrayList<Bitacoras> obtenerListaBitacoras() throws SQLException{
        BasesDatos baseDeDatos = BaseDeDatosSingleton.conectarBasesDeDatos();
        Connection con = baseDeDatos.getConnection();
        String sql = "SELECT * FROM bitacora"; 
        
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Bitacoras> listaBitacoras = new ArrayList<>();
        
        try{
            statement = con.prepareStatement(sql); 
            resultSet = statement.executeQuery(); 

            while (resultSet.next()) {
                String usuario = resultSet.getString("usuario"); 
                String fechaYHora = resultSet.getString("fechaYHora"); 
                String ipAcceso = resultSet.getString("ipAcceso");
                String sistemaOperativo = resultSet.getString("sistemaOperativo"); 
                String paisAcceso = resultSet.getString("paisAcceso"); 
                String numBitacora = resultSet.getString("indice"); 
                String AccionesEnElSistema = resultSet.getString("AccionesEnElSistema");
                
                Bitacoras obj = new Bitacoras(AccionesEnElSistema, usuario, ipAcceso, sistemaOperativo, paisAcceso, fechaYHora,  numBitacora);
                listaBitacoras.add(obj);
            }        
        
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return listaBitacoras;
    
    }
   
    
}
