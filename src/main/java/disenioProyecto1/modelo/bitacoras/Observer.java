package disenioProyecto1.modelo.bitacoras;

import java.sql.SQLException;

/**
 *
 * @author Nelson
 */
public interface Observer {
    public void updateBitacora(String accion, String usuario, String ipAcceso, String sistemaOperativo, String paisAcceso) throws SQLException;
}
