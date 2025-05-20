package ca.cs2.aiwebagent.delphi.springimageai.service;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.content.Media;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;

@Service
public class ImageSearchService {


    public String imageSearch(Resource resource, String content) {
          return conductImageSearch(content, resource);
    }

    public String conductImageSearch(String content, Resource resource) {
        SystemMessage systemMessage = new SystemMessage("use language German");
        SystemMessage systemMessage1 = new SystemMessage("Objective recognise images");
        SystemMessage systemMessage2 = new SystemMessage("related to fiber optic cable");

        OllamaApi ollamaApi =
                OllamaApi.builder()
                .build();
        Media media = Media.builder()
                .data(resource)
                .mimeType(MimeType.valueOf("image/png"))
                .build();
        UserMessage userMessage = UserMessage.builder()
                .media(media)
                .text(content)
                .build();
        String whatIsInThisImage = OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .defaultOptions(OllamaOptions.builder()
                        .model("qwen2.5vl")
                        .build())
                .build()
                .call(userMessage, systemMessage
                , systemMessage1, systemMessage2
                );
        return whatIsInThisImage;
    }
}
