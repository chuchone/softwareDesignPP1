package disenioProyecto1.integracion;

/**
 *
 * @author Nelson
 */
import jakarta.servlet.http.HttpServletResponse;
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.SynthesizeSpeechRequest;
import software.amazon.awssdk.services.polly.model.SynthesizeSpeechResponse;
import software.amazon.awssdk.core.ResponseInputStream;
import javazoom.jl.player.Player;

import java.io.ByteArrayInputStream;

public class TextToSpeech {
    public void generateAudio(HttpServletResponse response, String text) throws Exception {
        try (PollyClient pollyClient = PollyClient.create()) {
            // Solicitud a AWS Polly
            SynthesizeSpeechRequest request = SynthesizeSpeechRequest.builder()
                    .text(text)
                    .voiceId("Lucia")
                    .outputFormat(software.amazon.awssdk.services.polly.model.OutputFormat.MP3)
                    .build();

            // Flujo de audio devuelto por Polly
            ResponseInputStream<SynthesizeSpeechResponse> synthesizeSpeechResponse = pollyClient.synthesizeSpeech(request);

            // Configura la respuesta para enviar el audio al cliente
            response.setContentType("audio/mpeg");
            response.setHeader("Content-Disposition", "inline; filename=\"speech.mp3\"");
            response.getOutputStream().write(synthesizeSpeechResponse.readAllBytes());
            response.getOutputStream().flush();
        } catch (Exception e) {
            throw new Exception("Error al generar el audio: " + e.getMessage(), e);
        }
    }

}
