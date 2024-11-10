
package disenioProyecto1.modelo.bitacoras;

import static disenioProyecto1.modelo.gestorBanco.GestionBanco.generarCodigoBitacora;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosBitacoras.*;
import static disenioProyecto1.modelo.bitacoras.NotificationObserver.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Nelson
 */
public class Bitacoras implements Observer {
    public String usuario;
    public String accion;
    public String fechaHoraAccion;
    public String ipAcceso;
    public String sistemaOperativo;
    public String paisAcceso;
    public String numeroBitacora;
            
    public Bitacoras(String accion, String usuario, String ipAcceso, String sistemaOperativo, String paisAcceso, String fechaHoraAccion, String numBitacora) {
        this.fechaHoraAccion = fechaHoraAccion;
        this.accion = accion;
        this.usuario = usuario;
        this.ipAcceso = ipAcceso;
        this.sistemaOperativo = sistemaOperativo; 
        this.paisAcceso= paisAcceso;
        this.numeroBitacora = numBitacora;
    }

    @Override
    public void updateBitacora(String accion, String usuario, String ipAcceso, String sistemaOperativo, String paisAcceso) throws SQLException {
        Bitacoras bitacora = new Bitacoras(accion, usuario, ipAcceso,  sistemaOperativo,  paisAcceso,  obtenerFechaHoraActual(), generarCodigoBitacora());
        insertarBitacora(bitacora);
    }
    
}
