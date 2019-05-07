package pl.com.bottega.qma.docflow.adapters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.bottega.qma.core.events.EventPublisher;
import pl.com.bottega.qma.docflow.DocumentFactory;
import pl.com.bottega.qma.docflow.DocumentNumberGenerator;
import pl.com.bottega.qma.docflow.DocumentRepository;
import pl.com.bottega.qma.docflow.adapters.db.InMemoryDocumentRepository;
import pl.com.bottega.qma.docflow.adapters.db.MongoDocumentRepository;
import pl.com.bottega.qma.docflow.adapters.db.SpringDataMongoDocumentRepository;
import pl.com.bottega.qma.docflow.handlers.*;
import pl.com.bottega.qma.docflow.numbergenerators.ISONumberGenerator;

@Configuration
public class DocflowConfig {

  @Bean
  public DocumentFactory documentFactory(DocumentNumberGenerator documentNumberGenerator, EventPublisher eventPublisher) {
    return new DocumentFactory(documentNumberGenerator, eventPublisher);
  }

  @Bean
  public CreateDocumentHandler createDocumentHandler(DocumentRepository documentRepository, DocumentFactory documentFactory) {
    return new CreateDocumentHandler(documentRepository, documentFactory);
  }

  @Bean
  public ArchiveDocumentHandler archiveDocumentHandler(DocumentRepository documentRepository) {
    return new ArchiveDocumentHandler(documentRepository);
  }

  @Bean
  public EditDocumentHandler editDocumentHandler(DocumentRepository documentRepository) {
    return new EditDocumentHandler(documentRepository);
  }

  @Bean
  public PublishDocumentHandler publishDocumentHandler(DocumentRepository documentRepository) {
    return new PublishDocumentHandler(documentRepository);
  }

  @Bean
  public VerifyDocumentHandler verifyDocumentHandler(DocumentRepository repository) {
    return new VerifyDocumentHandler(repository);
  }

  @Bean
  public DocumentRepository documentRepository(SpringDataMongoDocumentRepository springDataMongoDocumentRepository, EventPublisher eventPublisher) {
    return new MongoDocumentRepository(springDataMongoDocumentRepository, eventPublisher);
  }

  @Bean
  public DocumentNumberGenerator documentNumberGenerator() {
    return new ISONumberGenerator();
  }

}
