package disenioProyecto1.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.SynthesizeSpeechRequest;
import software.amazon.awssdk.services.polly.model.SynthesizeSpeechResponse;
import software.amazon.awssdk.core.ResponseInputStream;

import java.io.IOException;

@WebServlet(name = "GenerarAudioServlet", urlPatterns = {"/GenerarAudioServlet"})
public class GenerarAudioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String text = request.getParameter("text");
        System.out.println("Texto recibido: " + text);

        if (text == null || text.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se proporcionó texto para sintetizar.");
            return;
        }

        try (PollyClient pollyClient = PollyClient.create()) {
            // Configuración de AWS Polly
            SynthesizeSpeechRequest synthesizeSpeechRequest = SynthesizeSpeechRequest.builder()
                    .text(text)
                    .voiceId("Lucia") // Cambia según el idioma
                    .outputFormat(software.amazon.awssdk.services.polly.model.OutputFormat.MP3)
                    .build();

            // Genera el flujo de audio
            ResponseInputStream<SynthesizeSpeechResponse> synthesizeSpeechResponse = pollyClient.synthesizeSpeech(synthesizeSpeechRequest);

            // Configuración de la respuesta HTTP para enviar el audio
            response.setContentType("audio/mpeg");
            response.setHeader("Content-Disposition", "inline; filename=analisis.mp3");

            // Escribe el audio en la respuesta
            response.getOutputStream().write(synthesizeSpeechResponse.readAllBytes());
            response.getOutputStream().flush();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar el audio: " + e.getMessage());
        }
    }
}
