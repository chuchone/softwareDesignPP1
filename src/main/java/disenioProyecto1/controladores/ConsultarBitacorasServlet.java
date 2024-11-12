package disenioProyecto1.controladores;

import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosBitacoras.obtenerListaBitacoras;
import disenioProyecto1.modelo.bitacoras.Bitacoras;
import static disenioProyecto1.modelo.gestorBanco.GestionBanco.analisisSentimientos;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.SynthesizeSpeechRequest;
import software.amazon.awssdk.services.polly.model.SynthesizeSpeechResponse;
import software.amazon.awssdk.core.ResponseInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ConsultarBitacorasServlet", urlPatterns = {"/ConsultarBitacorasServlet"})
public class ConsultarBitacorasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtiene la lista de bitácoras
            List<Bitacoras> bitacoras = obtenerListaBitacoras();

            // Realiza el análisis de sentimientos
            String analisisSentimientos = analisisSentimientos(bitacoras);

            // Genera el archivo de audio con AWS Polly
            String audioFilePath = generarAudio(analisisSentimientos);

            // Configura los atributos para el JSP
            request.setAttribute("bitacoras", bitacoras);
            request.setAttribute("analisisSentimientos", analisisSentimientos);
            request.setAttribute("audioFilePath", audioFilePath);

            // Redirige al JSP para mostrar la información
            request.getRequestDispatcher("mostrarBitacoras.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error al procesar la solicitud.");
        }
    }
    private String generarAudio(String text) throws Exception {
        // Ruta específica para guardar el archivo
        String audioDirectoryPath = "C:\\Users\\Usuario\\Desktop\\Nueva carpeta\\audios";
        String audioFilePath = audioDirectoryPath + File.separator + "analisis.mp3";

        // Verifica si la carpeta existe, si no, la crea
        File directory = new File(audioDirectoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Crea todas las carpetas necesarias
        }

        File audioFile = new File(audioFilePath);

        try (PollyClient pollyClient = PollyClient.create()) {
            // Configuración de AWS Polly
            SynthesizeSpeechRequest synthesizeSpeechRequest = SynthesizeSpeechRequest.builder()
                    .text(text)
                    .voiceId("Lucia") // Cambia según el idioma
                    .outputFormat(software.amazon.awssdk.services.polly.model.OutputFormat.MP3)
                    .build();

            // Genera el flujo de audio
            ResponseInputStream<SynthesizeSpeechResponse> synthesizeSpeechResponse = pollyClient.synthesizeSpeech(synthesizeSpeechRequest);

            // Escribe el flujo de audio en un archivo
            try (FileOutputStream outputStream = new FileOutputStream(audioFile)) {
                byte[] buffer = synthesizeSpeechResponse.readAllBytes();
                outputStream.write(buffer);
            }
        }

        return audioFilePath; // Devuelve la ruta completa del archivo generado
    }

}
