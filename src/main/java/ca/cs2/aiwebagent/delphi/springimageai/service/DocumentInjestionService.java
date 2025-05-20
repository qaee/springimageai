package ca.cs2.aiwebagent.delphi.springimageai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class DocumentInjestionService {

    private static final Logger log = LoggerFactory.getLogger(DocumentInjestionService.class);

    @Value("classpath:/pdf/release_notes_delphi-mobile.pdf")
    private Resource resource1;
    private final VectorStore vectorStore;
    public DocumentInjestionService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public String  injestDocument() {

        TikaDocumentReader reader = new TikaDocumentReader(resource1);
        TextSplitter textSplitter = new TokenTextSplitter();
        log.info("Ingesting PDF file");
        vectorStore.accept(textSplitter.split(reader.read()));

        log.info("Completed Ingesting PDF file");
        return "Ingested PDF file";
    }
}
