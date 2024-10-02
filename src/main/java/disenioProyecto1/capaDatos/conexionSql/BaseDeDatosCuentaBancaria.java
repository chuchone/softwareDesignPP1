package disenioProyecto1.capaDatos.conexionSql;

import static disenioProyecto1.capaDatos.conexionSql.BasesDatos.conectarBasesDeDatos;
import disenioProyecto1.gestorBanco.CuentaBancaria;
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
    public static boolean insertarDatosCBancaria(CuentaBancaria obj) throws SQLException{
    
        BasesDatos baseDeDatos = conectarBasesDeDatos();
        Connection con = baseDeDatos.getConnection();
        String sql = "INSERT INTO cuentaBancaria VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        
        try {
            statement = con.prepareStatement(sql); // Prepara la sentencia

            // Asigna los valores del objeto cliente jurídico a los parámetros
            statement.setLong(1, obj.cedulaPersonaAsociada);
            statement.setString(2, obj.numeroCuenta);
            statement.setDouble(3, obj.dineroEnLaCuenta);
            statement.setString(4, obj.PIN_Asociado);
            statement.setDouble(5, obj.regisComisiones);
            statement.setDouble(6, obj.cantidadRetiros);
            statement.setBoolean(7,obj.statusCuentaActiva);
            statement.setString(8, obj.fechaCreacion); 
            statement.setString(9, obj.nombreDuenio);

            // Ejecuta la sentencia de inserción
            int rowsInserted = statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    public static List<CuentaBancaria> obtenerCuentasBancarias() throws SQLException {
        List<CuentaBancaria> listaCuentas = new ArrayList<>();
        BasesDatos baseDeDatos = conectarBasesDeDatos();
        Connection con = baseDeDatos.getConnection();
        String sql = "SELECT * FROM cuentaBancaria";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = con.prepareStatement(sql); // Prepara la sentencia
            resultSet = statement.executeQuery(); // Ejecuta la consulta

            // Iterar sobre el resultado y crear objetos CuentaBancaria
            while (resultSet.next()) {
                // Crear un nuevo objeto CuentaBancaria utilizando el constructor proporcionado
                CuentaBancaria cuenta = new CuentaBancaria(
                    resultSet.getLong("identiCliente"),
                    resultSet.getString("pinAsociado"),
                    resultSet.getDouble("saldo"), // Cambiar a int, según lo que necesites
                    resultSet.getDouble("cantComisiones"),
                    resultSet.getDouble("canRetiros"),
                    resultSet.getBoolean("status"),
                    resultSet.getString("fechaCreacion"),
                    resultSet.getString("numCuenta"),
                    resultSet.getString("nombreDuenio")
                );

                listaCuentas.add(cuenta); // Agregar cuenta a la lista
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

        return listaCuentas; // Retornar la lista de cuentas bancarias
    }
    public static boolean limpiarTablaCuentas() throws SQLException {
        BasesDatos baseDeDatos = conectarBasesDeDatos();
        Connection con = baseDeDatos.getConnection();
        String sql = "DELETE FROM cuentaBancaria"; // Sentencia para eliminar todos los registros
        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(sql); // Prepara la sentencia

            // Ejecuta la sentencia de eliminación
            int rowsDeleted = statement.executeUpdate();
            System.out.println("Registros eliminados: " + rowsDeleted);

            return rowsDeleted > 0; // Devuelve true si se eliminaron filas
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
        boolean todasInsertadas = true; // Bandera para verificar si todas las inserciones fueron exitosas

        for (CuentaBancaria cuenta : cuentas) {
            boolean resultado = insertarDatosCBancaria(cuenta); // Llama al método de inserción existente
            if (!resultado) {
                todasInsertadas = false; // Si alguna inserción falla, cambiar la bandera
            }
        }

        return todasInsertadas; // Devuelve true si todas las inserciones fueron exitosas
    }
    public static boolean actualizarPinCuenta(String nuevoPin, String numeroCuenta, String pinActual) throws SQLException {
        // 1. Obtener la lista de cuentas bancarias
        List<CuentaBancaria> listaCuentas = obtenerCuentasBancarias();

        // 2. Actualizar el PIN de la cuenta si existe y si el PIN actual coincide
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

    // Método que verifica que el PIN actual coincida y actualiza el PIN en la lista
    private static boolean verificarYActualizarPin(List<CuentaBancaria> listaCuentas, String nuevoPin, String numeroCuenta, String pinActual) {
        for (CuentaBancaria cuenta : listaCuentas) {
            if (cuenta.numeroCuenta.equals(numeroCuenta) && cuenta.PIN_Asociado.equals(pinActual)) {
                // Si el número de cuenta y el PIN actual coinciden, se actualiza el PIN
                cuenta.PIN_Asociado = nuevoPin;
                return true;  // Cuenta encontrada y PIN actualizado
            }
        }
        return false;  // No se encontró la cuenta o el PIN actual no coincide
    }

    // Método que limpia la tabla e inserta la lista de cuentas actualizada
    private static boolean limpiarTablaYGuardarCuentas(List<CuentaBancaria> listaCuentas) throws SQLException {
        // Limpiar la tabla
        boolean tablaLimpiada = limpiarTablaCuentas();
        if (!tablaLimpiada) {
            return false;
        }

        // Insertar la lista actualizada de cuentas
        return insertarListaCuentasBancarias(listaCuentas);
    }
       
}
