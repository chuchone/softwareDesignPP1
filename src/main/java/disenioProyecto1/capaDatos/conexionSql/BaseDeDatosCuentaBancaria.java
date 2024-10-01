package disenioProyecto1.capaDatos.conexionSql;

import static disenioProyecto1.capaDatos.conexionSql.BasesDatos.conectarBasesDeDatos;
import disenioProyecto1.gestorBanco.CuentaBancaria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            System.out.println("jala lindo");

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


}
