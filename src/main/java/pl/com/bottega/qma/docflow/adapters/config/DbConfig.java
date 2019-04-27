package pl.com.bottega.qma.docflow.adapters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.bottega.qma.docflow.adapters.db.MongoDocumentRepository;
import pl.com.bottega.qma.docflow.adapters.db.SpringDataDocumentRepository;
import pl.com.bottega.qma.docflow.domain.DocumentRepository;

@Configuration
public class DbConfig {

  @Bean
  public DocumentRepository documentRepository(SpringDataDocumentRepository springDataDocumentRepository) {
    return new MongoDocumentRepository(springDataDocumentRepository);
  }

}
