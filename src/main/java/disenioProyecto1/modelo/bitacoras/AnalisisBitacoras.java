package disenioProyecto1.modelo.bitacoras;

import disenioProyecto1.integracion.ChatGPTClient;
import java.util.List;

/**
 *
 * @author Nelson
 */
public class AnalisisBitacoras {
    public static String generarPromptAnalisis(List<Bitacoras> bitacoras) {
        // Verifica que haya al menos 5 elementos, de lo contrario toma todos.
        
        int inicio = Math.max(0, bitacoras.size() - 5);
        List<Bitacoras> ultimasBitacoras = bitacoras.subList(inicio, bitacoras.size());

        StringBuilder prompt = new StringBuilder("Analiza los siguientes registros de bitácora, enfócate en identificar patrones, comportamientos inusuales o cualquier cambio relevante en los datos recientes. Aquí están los registros:\n");

        for (Bitacoras b : ultimasBitacoras) {
            prompt.append(String.format(
                    "- Usuario: %s, Acción: %s, Fecha y Hora: %s, IP: %s, SO: %s, País: %s, Número de Bitácora: %s\n",
                    b.usuario, b.accion, b.fechaHoraAccion, b.ipAcceso, b.sistemaOperativo, b.paisAcceso, b.numeroBitacora
            ));
        }

        prompt.append("\nEn base a estos datos, realiza el siguiente análisis:\n");
        prompt.append("1. ¿Hay acciones repetidas por el mismo usuario?\n");
        prompt.append("2. ¿Hay un cambio notable en el sistema operativo o país de acceso?\n");
        prompt.append("3. Describe cualquier observación relevante sobre los datos de IP o acciones de usuario.\n");
        prompt.append("4. Describe cualquier observación relevante para temas de moderacion o para seguimientos de usuarios\n");


        return prompt.toString();
    }

    public static String analizarUltimasBitacoras(List<Bitacoras> bitacoras) {
        ChatGPTClient cliente = ChatGPTClient.getInstance();
        String prompt = generarPromptAnalisis(bitacoras);
        return cliente.obtenerRespuesta(prompt);
    }



}
