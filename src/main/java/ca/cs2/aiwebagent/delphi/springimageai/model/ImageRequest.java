package ca.cs2.aiwebagent.delphi.springimageai.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ImageRequest {
    private String model;
    private List<ImageDetails> messages;
}
