/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.integracion;

/**
 *
 * @author Nelson
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class SMSSender {
    private static final String API_TOKEN = "FUzfaqPjT7Wh-3OKwpsNpAAxO_onn2AbOl1QvUHFiFs7ON_Bod-n9tVK33SIWAUC";
    private static final String API_ENDPOINT = "https://gatewayapi.com/rest/mtsms";

    public static String mandarMensaje (String numero){
        String palabra = generarMensajeAleatorio(4);
        try {
            String sender = "ExampleSMS";
            String message = "Ingrese el codigo: " + palabra;
            String recipient = numero; 
            sendSMS(sender, message, recipient);
            return palabra;
        } catch (Exception e) {
            e.printStackTrace();
            return palabra;
        }
    }

    private static void sendSMS(String sender, String message, String recipient) throws Exception {
        URL url = new URL(API_ENDPOINT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        String postData = String.format("token=%s&sender=%s&message=%s&recipients.0.msisdn=%s",
                API_TOKEN,
                URLEncoder.encode(sender, StandardCharsets.UTF_8.name()),
                URLEncoder.encode(message, StandardCharsets.UTF_8.name()),
                recipient);

        byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);

        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));

        
        try (OutputStream os = conn.getOutputStream()) {
            os.write(postDataBytes);
        }

        int responseCode = conn.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
        } else {
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                String inputLine;
                StringBuilder errorResponse = new StringBuilder();
                while ((inputLine = errorReader.readLine()) != null) {
                    errorResponse.append(inputLine);
                }
                throw new RuntimeException("HTTP error code : " + responseCode + " Error message : " + errorResponse.toString());
            }
        }
    }    
    private static String generarMensajeAleatorio(int longitud) {
        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder mensajeAleatorio = new StringBuilder(longitud);
        Random random = new Random();

        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(alfabeto.length());
                mensajeAleatorio.append(alfabeto.charAt(index));
        }

        return mensajeAleatorio.toString();
    }
  
    
}