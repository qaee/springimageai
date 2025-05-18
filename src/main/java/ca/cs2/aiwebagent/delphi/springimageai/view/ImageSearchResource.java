package ca.cs2.aiwebagent.delphi.springimageai.view;

import ca.cs2.aiwebagent.delphi.springimageai.service.ImageSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
public class ImageSearchResource {

    private final ImageSearchService  imageSearchService;

    public ImageSearchResource(ImageSearchService imageSearchService) {
        this.imageSearchService = imageSearchService;
    }

    /*public ImageSearchResource(  Optional<ImageModel> imageModel) {
            imageModel.ifPresent(model -> this.imageModel = model);
            this.images = List.of(
                    Media.builder().id("fruits").mimeType(MimeTypeUtils.IMAGE_PNG).data(new ClassPathResource("images/fruits.png")).build(),
                    Media.builder().id("fruits-2").mimeType(MimeTypeUtils.IMAGE_PNG).data(new ClassPathResource("images/fruits-2.png")).build(),
                    Media.builder().id("fruits-3").mimeType(MimeTypeUtils.IMAGE_PNG).data(new ClassPathResource("images/fruits-3.png")).build(),
                    Media.builder().id("fruits-4").mimeType(MimeTypeUtils.IMAGE_PNG).data(new ClassPathResource("images/fruits-4.png")).build(),
                    Media.builder().id("fruits-5").mimeType(MimeTypeUtils.IMAGE_PNG).data(new ClassPathResource("images/fruits-5.png")).build(),
                    Media.builder().id("animals").mimeType(MimeTypeUtils.IMAGE_PNG).data(new ClassPathResource("images/animals.png")).build(),
                    Media.builder().id("animals-2").mimeType(MimeTypeUtils.IMAGE_PNG).data(new ClassPathResource("images/animals-2.png")).build(),
                    Media.builder().id("animals-3").mimeType(MimeTypeUtils.IMAGE_PNG).data(new ClassPathResource("images/animals-3.png")).build(),
                    Media.builder().id("animals-4").mimeType(MimeTypeUtils.IMAGE_PNG).data(new ClassPathResource("images/animals-4.png")).build(),
                    Media.builder().id("animals-5").mimeType(MimeTypeUtils.IMAGE_PNG).data(new ClassPathResource("images/animals-5.png")).build()
            );
        }*/
    @GetMapping
    public String imageSearch() throws IOException {
        return imageSearchService.imageSearch("","what is in this image");
    }
}
