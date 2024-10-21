/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.conexionSql;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCFisico.insertarDatosCFisico;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCFisico.obtenerListaClientesFisicos;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCJuridico.obtenerListaClientesJuridicos;
import disenioProyecto1.usuarios.CFisico;
import disenioProyecto1.usuarios.CJuridico;
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
    

    
    public static int numClientesCreados() {
        BasesDatos base_disenio = conectarBasesDeDatos();
        String sqlSelect = "SELECT cantUsuarios FROM informacionGeneral"; // Ajusta para obtener solo la columna relevante
        String sqlUpdate = "UPDATE informacionGeneral SET cantUsuarios = ?";

        int sumaColumna2 = 0;

        try (Connection con = base_disenio.getConnection();
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
    
        BasesDatos base_disenio = conectarBasesDeDatos();
        String sqlSelect = "SELECT * FROM informacionGeneral";
        String sqlUpdate = "UPDATE informacionGeneral SET cantCuentas = ?";

        int sumaColumna1 = 0;

        try (Connection con = base_disenio.getConnection();
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
    
    
    private static long recorrerJuridicosRCedula(ArrayList<CJuridico> listaJuridicos, long cedula){
    
        for (CJuridico cliente : listaJuridicos){
            if (cedula == cliente.identificacion){
                return cliente.telefono;
            }            
        }
        return 0;    
    }
    
    private static long recorrerFisicosRCedula(ArrayList<CFisico> listaFisico,long cedula){
        for (CFisico cliente : listaFisico){
            if (cedula == cliente.identificacion){
                return cliente.telefono;
            }            
        }
        return 0;
    }
    public static long obtenerNumeroTelefono (long cedula) throws SQLException{
        ArrayList<CJuridico> listaJuridicos = obtenerListaClientesJuridicos();
        ArrayList<CFisico> listaFisico = obtenerListaClientesFisicos();
        long num = recorrerJuridicosRCedula(listaJuridicos, cedula);
        long num2 = recorrerFisicosRCedula(listaFisico, cedula);
        if (num != 0){
            return num;
        }else if(num2 != 0){
            return num2;
        }
        return 0;
    }
        
            
}