/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.conexionSql;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nelson
 */

public class BasesDatos {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USUARIO = "nechavarria";
    private static final String PASSWORD = "disenio#1";
    private static final String URL = "jdbc:mysql://nechchavarria-bd.c3iesa4wevxn.us-east-2.rds.amazonaws.com:3306/pp1_disenio_bd?useSSL=false";

    private static Connection connection = null; // Variable de conexión persistente

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR EN EL DRIVER: " + e);
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            }
        } catch (SQLException e) {
            System.out.println("ERROR EN LA CONEXION: " + e);
        }
        return connection;
    }
}
