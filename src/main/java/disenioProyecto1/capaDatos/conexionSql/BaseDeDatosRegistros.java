/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.conexionSql;

import static disenioProyecto1.capaDatos.conexionSql.BasesDatos.conectarBasesDeDatos;
import disenioProyecto1.modelo.gestorBanco.Transaccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nelson
 */
public class BaseDeDatosRegistros {
    public static void insertarDatosCBancaria(Transaccion obj) throws SQLException{
    
        BasesDatos baseDeDatos = conectarBasesDeDatos();
        Connection con = baseDeDatos.getConnection();
        String sql = "INSERT INTO Registros VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        
        try {
            statement = con.prepareStatement(sql); // Prepara la sentencia

            // Asigna los valores del objeto cliente jurídico a los parámetros
            statement.setString(1, obj.tipoTransaccion);
            statement.setDouble(2, obj.monto);
            statement.setDouble(3, obj.dineroEnCuenta);
            statement.setString(4, obj.fecha);
            statement.setDouble(5, obj.comision);
            statement.setString(6, obj.numCuentaQuePertenese);

            // Ejecuta la sentencia de inserción
            int rowsInserted = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    public static List<Transaccion> obtenerTransacciones() throws SQLException {
        List<Transaccion> transacciones = new ArrayList<>();
        BasesDatos baseDeDatos = conectarBasesDeDatos(); // Método que maneja la conexión
        Connection con = baseDeDatos.getConnection();
        String sql = "SELECT * FROM Registros"; // Ajusta esto según tu esquema

        try (PreparedStatement statement = con.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Asumiendo que el orden de las columnas es correcto
                double monto = resultSet.getDouble("monto");
                String tipoTransaccion = resultSet.getString("tipoTransaccion");
                String fecha = resultSet.getString("fecha");
                double comision = resultSet.getDouble("comision");
                double dineroEnCuenta = resultSet.getDouble("dineroEnCuenta");
                String numCuenta = resultSet.getString("numCuentaQuePertenece");

                // Crear un objeto Transaccion y agregarlo a la lista
                Transaccion transaccion = new Transaccion(monto, tipoTransaccion, fecha, comision, dineroEnCuenta, numCuenta);
                transacciones.add(transaccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Propagar la excepción
        } finally {
            if (con != null && !con.isClosed()) {
                con.close(); // Cerrar la conexión
            }
        }
        
        return transacciones;
        
       
    }
    public static void eliminarTablaRegistros() throws SQLException {
        BasesDatos baseDeDatos = conectarBasesDeDatos(); // Método que maneja la conexión
        Connection con = baseDeDatos.getConnection();
        String sql = "DROP TABLE IF EXISTS Registros"; // Sentencia para eliminar la tabla

        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(sql); // Prepara la sentencia
            statement.executeUpdate(); // Ejecuta la sentencia de eliminación
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close(); // Cerrar el statement
            }
            if (con != null && !con.isClosed()) {
                con.close(); // Cerrar la conexión
            }
        }
    }
    
}
