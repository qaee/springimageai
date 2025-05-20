package ca.cs2.aiwebagent.delphi.springimageai.view;

import ca.cs2.aiwebagent.delphi.springimageai.service.ImageSearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
public class ImageSearchResource {

    private final ImageSearchService  imageSearchService;
    public ImageSearchResource(ImageSearchService imageSearchService) {
        this.imageSearchService = imageSearchService;
    }
    @PostMapping
    public String imageSearch(@RequestParam("file") MultipartFile file, @RequestParam String message) throws IOException {
        return imageSearchService.imageSearch(file.getResource(), message);
    }
}
