/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.conexionSql;

import static disenioProyecto1.capaDatos.conexionSql.BasesDatos.conectarBasesDeDatos;
import disenioProyecto1.usuarios.CJuridico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nelson
 */
public class BaseDeDatosCJuridico {
    public static ArrayList<CJuridico> obtenerListaClientesJuridicos() throws SQLException {

        BasesDatos baseDeDatos = conectarBasesDeDatos();
        Connection con = baseDeDatos.getConnection();

        // Sentencia SQL para obtener todos los clientes jurídicos
        String sql = "SELECT * FROM clientesJuridicos";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<CJuridico> listaClientesJuridicos = new ArrayList<>();

        try {
            statement = con.prepareStatement(sql); // Prepara la sentencia
            resultSet = statement.executeQuery(); // Ejecuta la consulta

            // Procesa los resultados
            while (resultSet.next()) {
                int telefono = resultSet.getInt("telefono");
                String cuenta = resultSet.getString("cuenta");
                String nombre = resultSet.getString("nombre");
                String correo = resultSet.getString("correo");
                long identificacion = resultSet.getInt("identificacion"); // Cedula jurídica para clientes jurídicos
                String tipoUsuario = resultSet.getString("tipoUsuario");
                int maxCuentas = resultSet.getInt("maxCuentas");
                String razonSocial = resultSet.getString("razonSocial"); // Razón social en lugar de nombre
                String codigoCliente  = resultSet.getString("codigoCliente");

                // Crea el objeto cliente jurídico con los datos obtenidos
                CJuridico obj = new CJuridico(nombre, telefono, correo, tipoUsuario, razonSocial, identificacion);
                listaClientesJuridicos.add(obj);
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
        return listaClientesJuridicos;
    }
    public static void insertarClienteJuridico(CJuridico obj) throws SQLException {

        // Conectar a la base de datos
        BasesDatos baseDeDatos = conectarBasesDeDatos();
        Connection con = baseDeDatos.getConnection();

        // Sentencia SQL para insertar un nuevo cliente jurídico
        String sql = "INSERT INTO clientesJuridicos (telefono, cuenta, nombre, codigoCliente, correo, identificacion, tipoNegocio, razonSocial, tipoUsuario, maxCuentas) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(sql); // Prepara la sentencia

            // Asigna los valores del objeto cliente jurídico a los parámetros
            statement.setInt(1, obj.telefono);
            statement.setString(2, obj.cuenta);
            statement.setString(3, obj.nombre);
            statement.setString(4, obj.codigoCliente);
            statement.setString(5, obj.correo); // Cedula jurídica
            statement.setLong(6, obj.identificacion);
            statement.setString(7, obj.tipoNegocio);
            statement.setString(8, obj.razonSocial); // Razón social
            statement.setString(9, obj.tipo);
            statement.setInt(10, obj.maxCuentas);

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
}
