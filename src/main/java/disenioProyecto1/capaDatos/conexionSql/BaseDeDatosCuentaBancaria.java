package disenioProyecto1.capaDatos.conexionSql;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosSingleton.conectarBasesDeDatos;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCJuridico.obtenerNumeroTelefono;
import disenioProyecto1.modelo.gestorBanco.CuentaBancaria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nelson
 */
public class BaseDeDatosCuentaBancaria {
    public static boolean insertarDatosCBancaria(CuentaBancaria obj) throws SQLException {
        
        BasesDatos baseDeDatos = BaseDeDatosSingleton.conectarBasesDeDatos();
        Connection con = baseDeDatos.getConnection();
        
        // Verifica si la conexión está cerrada
        if (con == null || con.isClosed()) {
            throw new SQLException("No se pudo establecer la conexión a la base de datos.");
        }

        String sql = "INSERT INTO cuentaBancaria VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        
        try {
            statement = con.prepareStatement(sql); // Prepara la sentencia

            // Asigna los valores a los parámetros de la consulta
            statement.setLong(1, obj.cedulaPersonaAsociada);
            statement.setString(2, obj.numeroCuenta);
            statement.setDouble(3, obj.dineroEnLaCuenta);
            statement.setString(4, obj.PIN_Asociado);
            statement.setDouble(5, obj.regisComisiones);
            statement.setDouble(6, obj.cantidadRetiros);
            statement.setBoolean(7, obj.statusCuentaActiva);
            statement.setString(8, obj.fechaCreacion); 
            statement.setString(9, obj.nombreDuenio);

            // Ejecuta la sentencia de inserción
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // Retorna true solo si se insertó una fila

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null) {
                statement.close();
            }
            // No cerramos `con` aquí, ya que es una conexión persistente
        }
    }



    public static List<CuentaBancaria> obtenerCuentasBancarias() throws SQLException {
        List<CuentaBancaria> listaCuentas = new ArrayList<>();
        BasesDatos baseDeDatos = BaseDeDatosSingleton.conectarBasesDeDatos();
        Connection con = baseDeDatos.getConnection();

        if (con == null || con.isClosed()) {
            throw new SQLException("No se pudo establecer la conexión a la base de datos.");
        }

        String sql = "SELECT * FROM cuentaBancaria";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = con.prepareStatement(sql); // Prepara la sentencia
            resultSet = statement.executeQuery(); // Ejecuta la consulta

            while (resultSet.next()) {
                CuentaBancaria cuenta = new CuentaBancaria(
                    resultSet.getLong("identiCliente"),
                    resultSet.getString("pinAsociado"),
                    resultSet.getDouble("saldo"), 
                    resultSet.getDouble("cantComisiones"),
                    resultSet.getDouble("canRetiros"),
                    resultSet.getBoolean("status"),
                    resultSet.getString("fechaCreacion"),
                    resultSet.getString("numCuenta"),
                    resultSet.getString("nombreDuenio")
                );

                listaCuentas.add(cuenta); 
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

        return listaCuentas; 
    }

    public static boolean limpiarTablaCuentas() throws SQLException {
        BasesDatos baseDeDatos = BaseDeDatosSingleton.conectarBasesDeDatos();
        Connection con = baseDeDatos.getConnection();
        String sql = "DELETE FROM cuentaBancaria"; 
        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(sql); 

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    public static boolean insertarListaCuentasBancarias(List<CuentaBancaria> cuentas) throws SQLException {
        boolean todasInsertadas = true; 
        for (CuentaBancaria cuenta : cuentas) {
            boolean resultado = insertarDatosCBancaria(cuenta); 
            if (!resultado) {
                todasInsertadas = false; 
            }
        }

        return todasInsertadas; 
    }
    public static boolean actualizarPinCuenta(String nuevoPin, String numeroCuenta, String pinActual) throws SQLException {
        List<CuentaBancaria> listaCuentas = obtenerCuentasBancarias();
        boolean cuentaActualizada = verificarYActualizarPin(listaCuentas, nuevoPin, numeroCuenta, pinActual);
        if (!cuentaActualizada) {
            System.out.println("Cuenta no encontrada o el PIN actual es incorrecto.");
            return false;
        }

        // 3. Limpiar la tabla e insertar las cuentas actualizadas
        if (!limpiarTablaYGuardarCuentas(listaCuentas)) {
            System.out.println("Error al limpiar o insertar cuentas.");
            return false;
        }
        
        listaCuentas.clear();

        return true;
    }

    private static boolean verificarYActualizarPin(List<CuentaBancaria> listaCuentas, String nuevoPin, String numeroCuenta, String pinActual) {
        for (CuentaBancaria cuenta : listaCuentas) {
            if (cuenta.numeroCuenta.equals(numeroCuenta) && cuenta.PIN_Asociado.equals(pinActual)) {
                cuenta.PIN_Asociado = nuevoPin;
                return true; 
            }
        }
        return false; 
    }

    private static boolean limpiarTablaYGuardarCuentas(List<CuentaBancaria> listaCuentas) throws SQLException {
        // Limpiar la tabla
        boolean tablaLimpiada = limpiarTablaCuentas();
        if (!tablaLimpiada) {
            return false;
        }

        return insertarListaCuentasBancarias(listaCuentas);
    }
       
}
