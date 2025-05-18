package ca.cs2.aiwebagent.delphi.springimageai.service;

import ca.cs2.aiwebagent.delphi.springimageai.model.ImageDetails;
import ca.cs2.aiwebagent.delphi.springimageai.model.ImageRequest;
import ca.cs2.aiwebagent.delphi.springimageai.model.Root;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.Token;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.ollama.management.ModelManagementOptions;
import org.springframework.ai.reader.JsonReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MimeType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class ImageSearchService {

    @Value("${spring.ai.ollama.base-url}")
    private String baseUrl;
    private final OllamaChatModel ollamaChatModel;
    public ImageSearchService(OllamaChatModel ollamaChatModel) {
        this.ollamaChatModel = ollamaChatModel;
    }
    public String imageSearch(String image, String content) {

          return doRestCall(getImageBase64(), content);
      //    return conductImageSearch();
    }

    private String doRestCall(String image, String content)  {
        String url = baseUrl + "/api/chat";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> string = restTemplate
                .postForEntity(url, createImageRequest(image, content), String.class);
        string.getHeaders().forEach((k, v) -> System.out.println(k + ":" + v));
        String body = string.getBody();
        return body;
    }

    private ImageRequest createImageRequest(String image, String prompt) {
        ImageDetails imageDetails = ImageDetails.builder()
                .role("user")
                .content(prompt)
                .images(Arrays.asList(image))
                .build();
        return ImageRequest.builder()
                .model("llama3.2-vision")
                .messages(Arrays.asList(imageDetails))
                .build();
    }


    public String conductImageSearch() {
        OllamaApi ollamaApi =
                OllamaApi.builder()
                .build();
        Media media = Media.builder()
                .data(getResource())
                .mimeType(MimeType.valueOf("image/png"))
                .build();
        UserMessage userMessage = UserMessage.builder()
                .media(media)
                .text("What is in this image?")
                .build();
        String whatIsInThisImage = OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .defaultOptions(OllamaOptions.builder()
                        .model("llama3.2-vision")
                        .build())
                .build()
                .call(userMessage);
        return whatIsInThisImage;
    }

    private String getImageBase64() {
        Resource resource = getResource();
        try {
            return Base64.getEncoder().encodeToString(resource.getInputStream().readAllBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
        return "" ;
    }
    private Resource getResource() {
        return new ClassPathResource("images/fruits-5.png");
    }
}
