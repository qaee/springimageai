package ca.cs2.aiwebagent.delphi.springimageai;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.ai.reader.JsonReader;

public class Test {
    public static void main(String[] args) {
        String json = "{\"model\":\"llama3.2-vision\",\"created_at\":\"2025-05-18T17:17:55.890407Z\",\"message\":{\"role\":\"assistant\",\"content\":\"The\"},\"done\":false}\n" +
                "{\"model\":\"llama3.2-vision\",\"created_at\":\"2025-05-18T17:17:55.922159Z\",\"message\":{\"role\":\"assistant\",\"content\":\" image\"},\"done\":false}\n" +
                "{\"model\":\"llama3.2-vision\",\"created_at\":\"2025-05-18T17:17:55.954592Z\",\"message\":{\"role\":\"assistant\",\"content\":\" contains\"},\"done\":false}";

        JSONPObject jsonPObject = new JSONPObject("callback", json);

    }

}
