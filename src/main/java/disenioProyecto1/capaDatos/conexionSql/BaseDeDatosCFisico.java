/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.conexionSql;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosSingleton.conectarBasesDeDatos;
import disenioProyecto1.modelo.usuarios.CFisico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nelson
 */
public class BaseDeDatosCFisico {
    
    
    public static void insertarDatosCFisico(CFisico obj) throws SQLException {
        BasesDatos baseDeDatos = BaseDeDatosSingleton.conectarBasesDeDatos();

        Connection con = baseDeDatos.getConnection();

        // Sentencia SQL para insertar datos
        String sql = "INSERT INTO clientesFisicos VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement insertador = null;

        try {
            insertador = con.prepareStatement(sql); 
            insertador.setInt(1, obj.telefono);
            insertador.setString(2, obj.cuenta);
            insertador.setString(3, obj.correo);
            insertador.setLong(4, obj.identificacion);
            insertador.setString(5, obj.tipo); 
            insertador.setInt(6, obj.maxCuentas);
            insertador.setString(7, obj.fechaNacimiento);
            insertador.setString(8, obj.nombre);
            insertador.setString(9, obj.codigoCliente);

            // Ejecuta la sentencia
            int filasInsertadas = insertador.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("¡Datos insertados correctamente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (insertador != null) {
                insertador.close();
            }
        }
    }
    public static boolean delegarCrearCFisico(CFisico obj) {
        try {
            insertarDatosCFisico(obj); 
            return true; 
        } catch (SQLException e) {           
            e.printStackTrace(); // mostrar el error en la consola
            return false;
        }
    }
    
    
    public static long recorrerFisicosRCedula(ArrayList<CFisico> listaFisico,long cedula){
        for (CFisico cliente : listaFisico){
            if (cedula == cliente.identificacion){
                return cliente.telefono;
            }            
        }
        return 0;
    }
    
    
    
    public static ArrayList<CFisico> obtenerListaClientesFisicos() throws SQLException {
        
        BasesDatos baseDeDatos = BaseDeDatosSingleton.conectarBasesDeDatos();

        Connection con = baseDeDatos.getConnection();

        String sql = "SELECT * FROM clientesFisicos";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<CFisico> listaClientesFisicos = new ArrayList<>();

        try {
            statement = con.prepareStatement(sql); 
            resultSet = statement.executeQuery(); 

            while (resultSet.next()) {
                int telefono = resultSet.getInt("telefono");
                String cuenta = resultSet.getString("nombreCuenta"); 
                String correo = resultSet.getString("correoElectronico");
                int identificacion = resultSet.getInt("identificacion");
                String tipo = resultSet.getString("tipoCliente");
                int maxCuentas = resultSet.getInt("maxCuentas");
                String fechaNacimiento = resultSet.getString("fechaNacimiento");
                String nombre = resultSet.getString("nombreCompleto");
                String codigoCliente  = resultSet.getString("codigoCliente");
                
                CFisico obj = new CFisico(telefono, correo,  nombre, identificacion, fechaNacimiento,  maxCuentas, codigoCliente);
                listaClientesFisicos.add(obj);
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return listaClientesFisicos;
        
    }
    
    
    public static void eliminarCliente(int identificacion) throws SQLException {
        BasesDatos baseDeDatos = BaseDeDatosSingleton.conectarBasesDeDatos();

        Connection con = baseDeDatos.getConnection();

        // Sentencia SQL para eliminar datos
        String sql = "DELETE FROM clientesFisicos WHERE identificacion = ?";

        PreparedStatement eliminador = null;

        try {
            eliminador = con.prepareStatement(sql); 
            eliminador.setInt(1, identificacion); 

            // Ejecuta la sentencia
            int filasEliminadas = eliminador.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("¡Cliente eliminado correctamente!");
            } else {
                System.out.println("No se encontró un cliente con esa identificación.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (eliminador != null) {
                eliminador.close();
            }
        }
    }
    public static void limpiarTablaCfisico() throws SQLException {
        BasesDatos baseDeDatos = BaseDeDatosSingleton.conectarBasesDeDatos();

        Connection con = baseDeDatos.getConnection();

        // Sentencia SQL para limpiar la tabla
        String sql = "DELETE FROM clientesFisicos";

        PreparedStatement limpiador = null;

        try {
            limpiador = con.prepareStatement(sql); 

            // Ejecuta la sentencia
            limpiador.executeUpdate();
            System.out.println("¡Tabla clientesFisicos limpiada correctamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (limpiador != null) {
                limpiador.close();
            }
        }
    }
    

    
}
