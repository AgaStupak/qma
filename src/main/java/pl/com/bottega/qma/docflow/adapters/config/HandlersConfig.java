package pl.com.bottega.qma.docflow.adapters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.bottega.qma.docflow.domain.DocumentFactory;
import pl.com.bottega.qma.docflow.domain.DocumentRepository;
import pl.com.bottega.qma.docflow.handlers.CreateDocumentHandler;

@Configuration
public class HandlersConfig {

  @Bean
  public CreateDocumentHandler getCreateDocumentHandler(DocumentFactory documentFactory, DocumentRepository documentRepository) {
    return new CreateDocumentHandler(documentFactory, documentRepository);
  }
}
