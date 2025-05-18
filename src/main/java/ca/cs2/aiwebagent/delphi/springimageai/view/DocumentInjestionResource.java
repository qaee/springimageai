package ca.cs2.aiwebagent.delphi.springimageai.view;

import ca.cs2.aiwebagent.delphi.springimageai.service.DocumentInjestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/document")
public class DocumentInjestionResource {

    private DocumentInjestionService documentInjestionService;

    public DocumentInjestionResource(DocumentInjestionService documentInjestionService) {
        this.documentInjestionService = documentInjestionService;
    }
    @GetMapping("import")
    public String importDocument() {
        return documentInjestionService.injestDocument();
    }
}
