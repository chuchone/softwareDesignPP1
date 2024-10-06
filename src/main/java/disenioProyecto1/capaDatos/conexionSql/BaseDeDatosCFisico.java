/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.conexionSql;

import static disenioProyecto1.capaDatos.conexionSql.BasesDatos.conectarBasesDeDatos;
import disenioProyecto1.usuarios.CFisico;
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
    
    
    public static void insertarDatosCFisico(BasesDatos baseDeDatos, CFisico obj) throws SQLException {
        Connection con = baseDeDatos.getConnection();

        // Sentencia SQL para insertar datos
        String sql = "INSERT INTO clientesFisicos VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement insertador = null;

        try {
            insertador = con.prepareStatement(sql); // Prepara la sentencia
            insertador.setInt(1, obj.telefono);
            insertador.setString(2, obj.cuenta);
            insertador.setString(3, obj.correo);
            insertador.setLong(4, obj.identificacion);
            insertador.setString(5, obj.tipo); // Asegúrate de que "fisico" está asignado correctamente
            insertador.setInt(6, obj.maxCuentas);
            insertador.setString(7, obj.getfechaNacimiento());
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
    
    
    
    public static ArrayList<CFisico> obtenerListaClientesFisicos() throws SQLException {
        
        BasesDatos baseDeDatos = conectarBasesDeDatos();

        Connection con = baseDeDatos.getConnection();

        // Sentencia SQL para obtener todos los clientes
        String sql = "SELECT * FROM clientesFisicos";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<CFisico> listaClientesFisicos = new ArrayList<>();

        try {
            statement = con.prepareStatement(sql); // Prepara la sentencia
            resultSet = statement.executeQuery(); // Ejecuta la consulta

            // Procesa los resultados
            while (resultSet.next()) {
                int telefono = resultSet.getInt("telefono");
                String cuenta = resultSet.getString("nombreCuenta"); // correo quitando @example.com
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
    
    
    public static void eliminarCliente(BasesDatos baseDeDatos, int identificacion) throws SQLException {
        
        Connection con = baseDeDatos.getConnection();

        // Sentencia SQL para eliminar datos
        String sql = "DELETE FROM clientesFisicos WHERE identificacion = ?";

        PreparedStatement eliminador = null;

        try {
            eliminador = con.prepareStatement(sql); // Prepara la sentencia
            eliminador.setInt(1, identificacion); // Establece el valor del identificador

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
        BasesDatos baseDeDatos = conectarBasesDeDatos();
        Connection con = baseDeDatos.getConnection();

        // Sentencia SQL para limpiar la tabla
        String sql = "DELETE FROM clientesFisicos";

        PreparedStatement limpiador = null;

        try {
            limpiador = con.prepareStatement(sql); // Prepara la sentencia

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
