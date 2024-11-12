
package disenioProyecto1.integracion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.google.gson.Gson;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosBitacoras.obtenerListaBitacoras;
import disenioProyecto1.modelo.bitacoras.Bitacoras;


@WebServlet(name = "ExportarDatosServlet", urlPatterns = {"/ExportarDatosServlet"})
public class ExportarDatosJSONXMLCSV extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtiene el formato desde los parámetros de la solicitud
        String formato = request.getParameter("formato");


        try {

            List<Bitacoras> bitacoras = obtenerListaBitacoras();


            if (bitacoras == null || bitacoras.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No hay datos para exportar.");
                return;
            }            
            // Selecciona el formato solicitado
            switch (formato.toLowerCase()) {
                case "json":
                    exportarJSON(bitacoras, response);
                    break;
                case "xml":
                    exportarXML(bitacoras, response);
                    break;
                case "csv":
                    exportarCSV(bitacoras, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato no soportado.");
            }
        } catch (Exception e) {
            // Manejo de errores genérico
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error al procesar la solicitud.");
            e.printStackTrace();
        }
    }

    private void exportarJSON(List<Bitacoras> bitacoras, HttpServletResponse response) throws IOException {
        // Configuración de respuesta para JSON
        response.setContentType("application/json");
        response.setHeader("Content-Disposition", "attachment; filename=bitacoras.json");

        Gson gson = new Gson();
        String json = gson.toJson(bitacoras);

        try (PrintWriter writer = response.getWriter()) {
            writer.write(json);
        }
    }

    private void exportarCSV(List<Bitacoras> bitacoras, HttpServletResponse response) throws IOException {
        // Configuración de respuesta para CSV
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=bitacoras.csv");

        try (PrintWriter writer = response.getWriter()) {
            // Encabezados CSV
            writer.println("Número,Usuario,Acción,Fecha y Hora,IP de Acceso,Sistema Operativo,País");

            // Datos
            for (Bitacoras bitacora : bitacoras) {
                writer.printf("%s,%s,%s,%s,%s,%s,%s%n",
                        bitacora.numeroBitacora,
                        bitacora.usuario,
                        bitacora.accion,
                        bitacora.fechaHoraAccion,
                        bitacora.ipAcceso,
                        bitacora.sistemaOperativo,
                        bitacora.paisAcceso);
            }
        }
    }

    private void exportarXML(List<Bitacoras> bitacoras, HttpServletResponse response) throws IOException {
        // Configuración de respuesta para XML
        response.setContentType("application/xml");
        response.setHeader("Content-Disposition", "attachment; filename=bitacoras.xml");

        try (PrintWriter writer = response.getWriter()) {
            // Construcción manual del XML
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<bitacoras>");
            for (Bitacoras bitacora : bitacoras) {
                writer.println("  <bitacora>");
                writer.printf("    <numero>%s</numero>%n", bitacora.numeroBitacora);
                writer.printf("    <usuario>%s</usuario>%n", bitacora.usuario);
                writer.printf("    <accion>%s</accion>%n", bitacora.accion);
                writer.printf("    <fechaHora>%s</fechaHora>%n", bitacora.fechaHoraAccion);
                writer.printf("    <ipAcceso>%s</ipAcceso>%n", bitacora.ipAcceso);
                writer.printf("    <sistemaOperativo>%s</sistemaOperativo>%n", bitacora.sistemaOperativo);
                writer.printf("    <pais>%s</pais>%n", bitacora.paisAcceso);
                writer.println("  </bitacora>");
            }
            writer.println("</bitacoras>");
        }
    }
}