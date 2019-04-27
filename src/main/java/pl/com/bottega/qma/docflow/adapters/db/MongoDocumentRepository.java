package pl.com.bottega.qma.docflow.adapters.db;

import pl.com.bottega.qma.docflow.domain.Document;
import pl.com.bottega.qma.docflow.domain.DocumentRepository;
import reactor.core.publisher.Mono;

public class MongoDocumentRepository implements DocumentRepository {

  private final SpringDataDocumentRepository springDataDocumentRepository;

  public MongoDocumentRepository(SpringDataDocumentRepository springDataDocumentRepository) {
    this.springDataDocumentRepository = springDataDocumentRepository;
  }

  @Override
  public Mono<Void> save(Document document) {
    return springDataDocumentRepository.insert(document).then();
  }

  @Override
  public Mono<Document> get(String documentNumber) {
    return springDataDocumentRepository.findById(documentNumber);
  }
}

