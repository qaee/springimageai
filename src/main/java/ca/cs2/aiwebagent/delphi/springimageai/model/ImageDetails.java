package ca.cs2.aiwebagent.delphi.springimageai.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ImageDetails {
    private String role;
    private String content;
    private List<String> images;
}
