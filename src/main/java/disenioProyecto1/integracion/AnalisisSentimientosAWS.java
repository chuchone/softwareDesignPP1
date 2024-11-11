/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.integracion;

import software.amazon.awssdk.services.comprehend.ComprehendClient;
import software.amazon.awssdk.services.comprehend.model.*;
/**
 *
 * @author Nelson
 */
public class AnalisisSentimientosAWS {
    public String analyzeSentiment(String text) {
        // Resultado del análisis de sentimientos
        String result = "";
        
        // Configura el cliente de Amazon Comprehend
        try (ComprehendClient comprehendClient = ComprehendClient.create()) {
            // Solicitud de análisis de sentimientos
            DetectSentimentRequest sentimentRequest = DetectSentimentRequest.builder()
                    .text(text)
                    .languageCode("es") // Código de idioma: "en" para inglés, "es" para español
                    .build();

            DetectSentimentResponse sentimentResponse = comprehendClient.detectSentiment(sentimentRequest);

            // Construir el resultado
            result += "Sentimiento dominante: " + sentimentResponse.sentimentAsString() + "\n";
            result += "Detalles del sentimiento:\n";
            result += "Positivo: " + sentimentResponse.sentimentScore().positive() + "\n";
            result += "Negativo: " + sentimentResponse.sentimentScore().negative() + "\n";
            result += "Neutral: " + sentimentResponse.sentimentScore().neutral() + "\n";
            result += "Mixto: " + sentimentResponse.sentimentScore().mixed() + "\n";
        } catch (Exception e) {
            result = "Error al analizar sentimientos: " + e.getMessage();
        }
        
        return result;
    }
}
