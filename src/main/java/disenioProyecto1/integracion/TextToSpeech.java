package disenioProyecto1.integracion;

/**
 *
 * @author Nelson
 */
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.SynthesizeSpeechRequest;
import software.amazon.awssdk.services.polly.model.SynthesizeSpeechResponse;
import software.amazon.awssdk.core.ResponseInputStream;
import javazoom.jl.player.Player;

import java.io.ByteArrayInputStream;

public class TextToSpeech {
    public void speak(String text) throws Exception {
        try (PollyClient pollyClient = PollyClient.create()) {
            // Solicitud a AWS Polly
            SynthesizeSpeechRequest request = SynthesizeSpeechRequest.builder()
                    .text(text)
                    .voiceId("Lucia") // Cambia según tu idioma
                    .outputFormat(software.amazon.awssdk.services.polly.model.OutputFormat.MP3) // Cambia a MP3 para mejor compatibilidad
                    .build();

            // Flujo de audio devuelto por Polly
            ResponseInputStream<SynthesizeSpeechResponse> response = pollyClient.synthesizeSpeech(request);

            // Reproducir el audio directamente desde el flujo
            Player player = new Player(new ByteArrayInputStream(response.readAllBytes()));
            player.play();
        } catch (Exception e) {
            throw new Exception("Error al reproducir el audio: " + e.getMessage(), e);
        }
    }
}
