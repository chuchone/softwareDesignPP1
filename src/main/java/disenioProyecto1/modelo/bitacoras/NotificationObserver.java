
package disenioProyecto1.modelo.bitacoras;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosBitacoras.insertarBitacora;
import static disenioProyecto1.modelo.gestorBanco.GestionBanco.generarCodigoBitacora;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Nelson
 */
public class NotificationObserver {
    public static void notificar(String accion, String usuario, String ipAcceso, String sistemaOperativo, String paisAcceso) throws SQLException {
        Bitacoras bitacora = new Bitacoras(accion, usuario, ipAcceso,  sistemaOperativo,  paisAcceso,  obtenerFechaHoraActual(), generarCodigoBitacora());
        insertarBitacora(bitacora);
    }
    
    public static String obtenerFechaHoraActual() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return fechaHoraActual.format(formato);
    }
}
