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

    @Value("classpath:/pdf/PM_Absichtserklaerung_OXG_20250429.pdf")
    private Resource resource1;
    @Value("classpath:/pdf/202505_Pressemitteilung_Ankuendigung_Muenster.pdf")
    private Resource resource2;
    @Value("classpath:/pdf/202505_Pressemitteilung_Ankuendigung_Parchim.pdf")
    private Resource resource3;
    @Value("classpath:/pdf/202505_Pressemitteilung_Baustart_Zittau.pdf")
    private Resource resource4;
    private final VectorStore vectorStore;
    public DocumentInjestionService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public String  injestDocument() {

        TikaDocumentReader reader = new TikaDocumentReader(resource1);
        TextSplitter textSplitter = new TokenTextSplitter();
        log.info("Ingesting PDF file");
        vectorStore.accept(textSplitter.split(reader.read()));

        reader = new TikaDocumentReader(resource2);
        textSplitter = new TokenTextSplitter();
        log.info("Ingesting PDF file");
        vectorStore.accept(textSplitter.split(reader.read()));
        reader = new TikaDocumentReader(resource3);
        textSplitter = new TokenTextSplitter();
        log.info("Ingesting PDF file");
        vectorStore.accept(textSplitter.split(reader.read()));
        reader = new TikaDocumentReader(resource4);
        textSplitter = new TokenTextSplitter();
        log.info("Ingesting PDF file");
        vectorStore.accept(textSplitter.split(reader.read()));



        log.info("Completed Ingesting PDF file");
        return "Ingested PDF file";
    }
}
