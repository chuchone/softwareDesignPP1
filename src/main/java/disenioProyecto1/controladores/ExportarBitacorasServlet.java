
package disenioProyecto1.controladores;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper; // para JSON
import disenioProyecto1.modelo.bitacoras.Bitacoras;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import java.io.IOException;

@WebServlet("/ExportarBitacorasServlet")
public class ExportarBitacorasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formato = request.getParameter("formato");
        List<Bitacoras> bitacoras = (List<Bitacoras>) request.getSession().getAttribute("bitacoras");

        if (bitacoras == null || bitacoras.isEmpty()) {
            response.getWriter().println("No hay bitácoras para exportar.");
            return;
        }

        switch (formato) {
            case "json":
                exportarComoJSON(bitacoras, response);
                break;
            case "xml":
                exportarComoXML(bitacoras, response);
                break;
            case "csv":
                exportarComoCSV(bitacoras, response);
                break;
            default:
                response.getWriter().println("Formato no soportado.");
        }
    }

    private void exportarComoJSON(List<Bitacoras> bitacoras, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setHeader("Content-Disposition", "attachment;filename=bitacoras.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), bitacoras);
    }

    private void exportarComoXML(List<Bitacoras> bitacoras, HttpServletResponse response) throws IOException {
        response.setContentType("application/xml");
        response.setHeader("Content-Disposition", "attachment;filename=bitacoras.xml");

        PrintWriter writer = response.getWriter();
        writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        writer.println("<bitacoras>");
        for (Bitacoras bitacora : bitacoras) {
            writer.println("  <bitacora>");
            writer.printf("    <numero>%s</numero>%n", bitacora.numeroBitacora);
            writer.printf("    <usuario>%s</usuario>%n", bitacora.usuario);
            writer.printf("    <accion>%s</accion>%n", bitacora.accion);
            writer.printf("    <fechaHora>%s</fechaHora>%n", bitacora.fechaHoraAccion);
            writer.printf("    <ip>%s</ip>%n", bitacora.ipAcceso);
            writer.printf("    <sistemaOperativo>%s</sistemaOperativo>%n", bitacora.sistemaOperativo);
            writer.printf("    <pais>%s</pais>%n", bitacora.paisAcceso);
            writer.println("  </bitacora>");
        }
        writer.println("</bitacoras>");
    }

    private void exportarComoCSV(List<Bitacoras> bitacoras, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=bitacoras.csv");

        PrintWriter writer = response.getWriter();
        writer.println("numeroBitacora,usuario,accion,fechaHoraAccion,ipAcceso,sistemaOperativo,paisAcceso");
        for (Bitacoras bitacora : bitacoras) {
            writer.printf("%s,%s,%s,%s,%s,%s,%s%n",
                    bitacora.numeroBitacora, bitacora.usuario, bitacora.accion,
                    bitacora.fechaHoraAccion, bitacora.ipAcceso,
                    bitacora.sistemaOperativo, bitacora.paisAcceso);
        }
    }
}
