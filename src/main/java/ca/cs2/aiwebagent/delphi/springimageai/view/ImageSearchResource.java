package ca.cs2.aiwebagent.delphi.springimageai.view;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageSearchResource {
    @GetMapping
    public String imageSearch() {
        return "Hello ImageSearch!";
    }
}
