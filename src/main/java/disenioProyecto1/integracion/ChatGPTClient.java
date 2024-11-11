package disenioProyecto1.integracion;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionChoice;

import java.util.List;

public class ChatGPTClient {

    private static ChatGPTClient instance; 
    private final OpenAiService openAiService;

    private ChatGPTClient() { 
        String apiKey = "sk-QypuSc0_p_04sVYaRHci_zMsmJ2NrLdlHPY8pn-SFuT3BlbkFJN_PsshXnJ44QnQY_iF21QYMm6DEFfkyz2dyeS8NU4A";
        this.openAiService = new OpenAiService(apiKey);
    }

    public static ChatGPTClient getInstance() { 
        if (instance == null) {
            instance = new ChatGPTClient();
        }
        return instance;
    }

    public String obtenerRespuesta(String pregunta) {
        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt(pregunta)
                .maxTokens(125)
                .build();

        List<CompletionChoice> choices = openAiService.createCompletion(request).getChoices();
        return choices.isEmpty() ? "No se recibió respuesta" : choices.get(0).getText().trim();
    }
}
