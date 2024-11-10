
package disenioProyecto1.integracion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author Nelson
 */
public class IPIntegracion {
    public static String obtenerPaisDesdeIP(String ipAddress) throws IOException {
        String apiKey = "53157f572ab827b4ebb1479e386a5980"; 
        String apiUrl = "http://api.ipstack.com/" + ipAddress + "?access_key=" + apiKey;
        
        // Realizar la solicitud a la API de ipstack
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer responseJson = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            responseJson.append(inputLine);
        }
        in.close();

        // Parsear la respuesta JSON de ipstack
        JSONObject myResponse = new JSONObject(responseJson.toString());
        return myResponse.getString("country_name");  // Extraer el país
    }
}
