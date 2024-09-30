/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.conexionSql;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCFisico.insertarDatosCFisico;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCFisico.obtenerListaClientesFisicos;
import disenioProyecto1.usuarios.CFisico;
import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nelson
 */
public class BasesDatos {

    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String USUARIO = "nechavarria";
    private static String PASSWORD = "disenio#1";
    private static String URL = "jdbc:mysql://nechchavarria-bd.c3iesa4wevxn.us-east-2.rds.amazonaws.com:3306/pp1_disenio_bd?useSSL=false"; // Incluye la base de datos

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR EN EL DRIVER: " + e);
        }
    }
    public static boolean delegarCrearCFisico(CFisico obj) {
        try {
            BasesDatos base_disenio = conectarBasesDeDatos(); 
            insertarDatosCFisico(base_disenio, obj); 
            return true; 
        } catch (SQLException e) {           
            e.printStackTrace(); // mostrar el error en la consola
            return false;
        }
    }
    

    
    public static int numClientesCreados(){
        BasesDatos base_disenio = conectarBasesDeDatos();
        String sqlSelect = "SELECT * FROM informacionGeneral";
        String sqlInsert = "INSERT INTO informacionGeneral (cantCuentas, cantUsuarios) VALUES (?, ?)";
        String sqlUpdate = "UPDATE informacionGeneral SET cantUsuarios = ?";

        int nuevoTotalUsuarios = 0;

        try (Connection con = base_disenio.getConnection();
             PreparedStatement statementSelect = con.prepareStatement(sqlSelect);
             ResultSet resultSet = statementSelect.executeQuery()) {

            if (resultSet.next()) { // Verifica si hay resultados
                int cantUsuarios = resultSet.getInt("cantUsuarios");

                // Si no hay usuarios, se inserta un nuevo registro
                if (cantUsuarios == 0) {
                    try (PreparedStatement statementInsert = con.prepareStatement(sqlInsert)) {
                        statementInsert.setInt(1, 0); // 0 cuentas
                        statementInsert.setInt(2, 1); // 1 usuario
                        statementInsert.executeUpdate();
                        nuevoTotalUsuarios = 1; // Retorna 1 porque ahora hay un usuario
                    }
                } else {
                    // Si ya hay usuarios, se actualiza la cantidad de usuarios
                    nuevoTotalUsuarios = cantUsuarios + 1; // Calcula el nuevo total
                    try (PreparedStatement statementUpdate = con.prepareStatement(sqlUpdate)) {
                        statementUpdate.setInt(1, nuevoTotalUsuarios);
                        statementUpdate.executeUpdate();
                    }
                }
            } else {
                // Si no hay registros en la tabla, insertamos el primer registro
                try (PreparedStatement statementInsert = con.prepareStatement(sqlInsert)) {
                    statementInsert.setInt(1, 0); // 0 cuentas
                    statementInsert.setInt(2, 1); // 1 usuario
                    statementInsert.executeUpdate();
                    nuevoTotalUsuarios = 1; // Retorna 1 porque ahora hay un usuario
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nuevoTotalUsuarios; // Retorna el nuevo total de usuarios
    }
    
    public static int numCuentasCreadas(){
    
        BasesDatos base_disenio = conectarBasesDeDatos();
        String sqlSelect = "SELECT * FROM informacionGeneral";
        String sqlUpdate = "UPDATE informacionGeneral SET cantCuentas = ?";

        int nuevoTotalCuentas = 0;

        try (Connection con = base_disenio.getConnection();
             PreparedStatement statementSelect = con.prepareStatement(sqlSelect);
             ResultSet resultSet = statementSelect.executeQuery()) {

            if (resultSet.next()) { // Verifica si hay resultados
                int cantCuentas = resultSet.getInt("cantCuentas");

                // Incrementa la cantidad de cuentas
                nuevoTotalCuentas = cantCuentas + 1; // Calcula el nuevo total
                try (PreparedStatement statementUpdate = con.prepareStatement(sqlUpdate)) {
                    statementUpdate.setInt(1, nuevoTotalCuentas);
                    statementUpdate.executeUpdate();
                }
            } else {
                // Si no hay registros en la tabla, insertamos el primer registro
                try (PreparedStatement statementInsert = con.prepareStatement("INSERT INTO informacionGeneral (cantCuentas, cantUsuarios) VALUES (?, ?)")) {
                    statementInsert.setInt(1, 1); // 1 cuenta
                    statementInsert.setInt(2, 0); // 0 usuarios
                    statementInsert.executeUpdate();
                    nuevoTotalCuentas = 1; // Retorna 1 porque ahora hay una cuenta
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nuevoTotalCuentas; // Retorna el nuevo total de cuentas
    }    
    
    
    
    
    public static BasesDatos conectarBasesDeDatos (){
        BasesDatos baseDeDatos = new BasesDatos();
        baseDeDatos.getConnection();
        return baseDeDatos;        
    }
    

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR EN LA CONEXION: " + e);
        }
        return con;
    }
    
    public PreparedStatement prepareStatement(String query) throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            return con.prepareStatement(query);
        } else {
            throw new SQLException("No se pudo establecer la conexión");
        }
    }
        
            
}