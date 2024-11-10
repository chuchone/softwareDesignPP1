/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.conexionSql;

import disenioProyecto1.capaDatos.conexionSql.conectar.BaseDeDatosSingleton;
import disenioProyecto1.capaDatos.conexionSql.conectar.BasesDatos;
import static disenioProyecto1.capaDatos.conexionSql.conectar.BaseDeDatosSingleton.conectarBasesDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nelson
 */
public class BaseDeDatosConteoUsers {
    public static int numClientesCreados() {
        BasesDatos baseDeDatos = BaseDeDatosSingleton.conectarBasesDeDatos();

        String sqlSelect = "SELECT cantUsuarios FROM informacionGeneral"; // Ajusta para obtener solo la columna relevante
        String sqlUpdate = "UPDATE informacionGeneral SET cantUsuarios = ?";

        int sumaColumna2 = 0;

        try (Connection con = baseDeDatos.getConnection();
             PreparedStatement statementSelect = con.prepareStatement(sqlSelect);
             ResultSet resultSet = statementSelect.executeQuery()) {

            if (resultSet.next()) { 
                int valorColumna2 = resultSet.getInt("cantUsuarios"); 
                sumaColumna2 = valorColumna2 + 1; // Suma 1 al valor de la columna

                // Actualiza el valor de la columna
                try (PreparedStatement statementUpdate = con.prepareStatement(sqlUpdate)) {
                    statementUpdate.setInt(1, sumaColumna2); 
                    int rowsUpdated = statementUpdate.executeUpdate();
                   
                }
            } else {
                System.out.println("No se encontró ningún registro en informacionGeneral.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return sumaColumna2; 
    }
    
    
    public static int numCuentasCreadas(){
    
        BasesDatos baseDeDatos = BaseDeDatosSingleton.conectarBasesDeDatos();
        String sqlSelect = "SELECT * FROM informacionGeneral";
        String sqlUpdate = "UPDATE informacionGeneral SET cantCuentas = ?";

        int sumaColumna1 = 0;

        try (Connection con = baseDeDatos.getConnection();
             PreparedStatement statementSelect = con.prepareStatement(sqlSelect);
             ResultSet resultSet = statementSelect.executeQuery()) {

            if (resultSet.next()) { // Verifica si hay un registro
                int valorColumna1 = resultSet.getInt(1); // Obtiene el valor de la columna 1
                sumaColumna1 = valorColumna1 + 1; // Suma 1 al valor de la columna 1

                // Actualiza el valor de la columna 1
                try (PreparedStatement statementUpdate = con.prepareStatement(sqlUpdate)) {
                    statementUpdate.setInt(1, sumaColumna1); // Establece el nuevo valor
                    statementUpdate.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sumaColumna1; // Retorna la suma total de la columna 1
    }  
        
    public static int incrementarConteoBitacoras() {
        BasesDatos baseDeDatos = BaseDeDatosSingleton.conectarBasesDeDatos();

        String sqlSelect = "SELECT conteoBitacoras FROM informacionGeneral"; // Ajusta para obtener solo la columna relevante
        String sqlUpdate = "UPDATE informacionGeneral SET conteoBitacoras = ?";

        int sumaColumna2 = 0;

        try (Connection con = baseDeDatos.getConnection();
             PreparedStatement statementSelect = con.prepareStatement(sqlSelect);
             ResultSet resultSet = statementSelect.executeQuery()) {

            if (resultSet.next()) { 
                int valorColumna2 = resultSet.getInt("conteoBitacoras"); 
                sumaColumna2 = valorColumna2 + 1; // Suma 1 al valor de la columna

                // Actualiza el valor de la columna
                try (PreparedStatement statementUpdate = con.prepareStatement(sqlUpdate)) {
                    statementUpdate.setInt(1, sumaColumna2); 
                    int rowsUpdated = statementUpdate.executeUpdate();
                   
                }
            } else {
                System.out.println("No se encontró ningún registro en informacionGeneral.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return sumaColumna2; 
    }
}
