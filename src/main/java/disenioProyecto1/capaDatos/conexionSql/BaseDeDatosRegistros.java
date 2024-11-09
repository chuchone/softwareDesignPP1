/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.conexionSql;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosSingleton.conectarBasesDeDatos;
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
    
        BasesDatos baseDeDatos = BaseDeDatosSingleton.conectarBasesDeDatos();

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
        BasesDatos baseDeDatos = BaseDeDatosSingleton.conectarBasesDeDatos();

        Connection con = baseDeDatos.getConnection();
        String sql = "SELECT * FROM Registros"; 

        try (PreparedStatement statement = con.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                double monto = resultSet.getDouble("monto");
                String tipoTransaccion = resultSet.getString("tipoTransaccion");
                String fecha = resultSet.getString("fecha");
                double comision = resultSet.getDouble("comision");
                double dineroEnCuenta = resultSet.getDouble("dineroEnCuenta");
                String numCuenta = resultSet.getString("numCuentaQuePertenece");

                Transaccion transaccion = new Transaccion(monto, tipoTransaccion, fecha, comision, dineroEnCuenta, numCuenta);
                transacciones.add(transaccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        } finally {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
        
        return transacciones;
        
       
    }
    public static void eliminarTablaRegistros() throws SQLException {
        BasesDatos baseDeDatos = BaseDeDatosSingleton.conectarBasesDeDatos();

        Connection con = baseDeDatos.getConnection();
        String sql = "DROP TABLE IF EXISTS Registros";

        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(sql); 
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close(); 
            }
            if (con != null && !con.isClosed()) {
                con.close(); 
            }
        }
    }
    
}
