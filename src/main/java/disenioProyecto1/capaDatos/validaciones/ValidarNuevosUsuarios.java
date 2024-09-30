/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.capaDatos.validaciones;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCFisico.obtenerListaClientesFisicos;
import disenioProyecto1.usuarios.CFisico;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nelson
 */
public class ValidarNuevosUsuarios {

    public static boolean validarNuevoCFisico(int identificacion)throws SQLException {
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
}
