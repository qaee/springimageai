package ca.cs2.aiwebagent.delphi.springimageai.view;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.ChatModelCallAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final OllamaChatModel ollamaChatModel;
    private final VectorStore vectorStore;

    public ChatController(OllamaChatModel ollamaChatModel, VectorStore vectorStore) {
        this.ollamaChatModel = ollamaChatModel;
        this.vectorStore = vectorStore;
    }

    @PostMapping
    public String chat(@RequestBody String message) {
       // ChatModelCallAdvisor modelCallAdvisor = new ChatModelCallAdvisor(ollamaChatModel)
     //   new Advisor(ollamaChatModel, vectorStore).advise(message);
        return ChatClient.builder(ollamaChatModel)
                .build().prompt("Please answer in German")
      //          .advisors(new QuestionAnswerAdvisor(vectorStore))
                .advisors(
                )
                .user(message)
                .call()
                .content();
    }
}