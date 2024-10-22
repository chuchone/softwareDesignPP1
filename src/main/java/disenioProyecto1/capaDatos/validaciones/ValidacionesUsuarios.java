/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.validaciones;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCFisico.obtenerListaClientesFisicos;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCJuridico.obtenerListaClientesJuridicos;

import disenioProyecto1.modelo.usuarios.CFisico;
import disenioProyecto1.modelo.usuarios.CJuridico;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nelson
 */
public class ValidacionesUsuarios {

    public static boolean validarNuevoCFisico(long identificacion)throws SQLException {
        ArrayList<CFisico> listaClienteFisico = obtenerListaClientesFisicos();  // Obtener la lista de CFisico

        // Recorrer la lista de clientes físicos
        for (CFisico cliente : listaClienteFisico) {
            // Verificar si algún cliente tiene la misma identificación
            if (cliente.identificacion == identificacion) {
                listaClienteFisico.clear();
                return false;  // Si se encuentra una coincidencia, retorna false
            }
        }
        listaClienteFisico.clear();
        return true;  // Si no se encuentra ninguna coincidencia, retorna true
    }
    
    public static boolean validarNuevoCJuridico(long identificacion)throws SQLException{
        ArrayList<CJuridico> listaClienteJuridico = obtenerListaClientesJuridicos();
                // Recorrer la lista de clientes físicos
        for (CJuridico cliente : listaClienteJuridico) {
            // Verificar si algún cliente tiene la misma identificación
            if (cliente.identificacion == identificacion) {
                listaClienteJuridico.clear();
                return false;  // Si se encuentra una coincidencia, retorna false
            }
        }
        listaClienteJuridico.clear();
        return true;  // Si no se encuentra ninguna coincidencia, retorna true
    }
    public static boolean existeCJuridico(long identificacion) throws SQLException {
        List<CJuridico> listaCJuridico = obtenerListaClientesJuridicos();
        for (CJuridico cliente : listaCJuridico) {
            if (cliente.identificacion == identificacion) {
                listaCJuridico.clear();
                return true;
            }
        }
        listaCJuridico.clear();
        return false;
    }

    public static boolean existeCFisico(long identificacion) throws SQLException {
        List<CFisico> listaCFisicos = obtenerListaClientesFisicos();
        for (CFisico cliente : listaCFisicos) {
            if (cliente.identificacion == identificacion) {
                listaCFisicos.clear();
                return true;
            }
        }
        listaCFisicos.clear();
        return false;
    }    
}
