package pl.com.bottega.qma.docflow.handlers;

import pl.com.bottega.qma.core.Handler;
import pl.com.bottega.qma.docflow.commands.CreateDocumentCommand;
import pl.com.bottega.qma.docflow.domain.Document;
import pl.com.bottega.qma.docflow.domain.DocumentFactory;
import pl.com.bottega.qma.docflow.domain.DocumentRepository;
import reactor.core.publisher.Mono;

public class CreateDocumentHandler implements Handler<CreateDocumentCommand> {

  private final DocumentFactory factory;
  private final DocumentRepository repository;

  public CreateDocumentHandler(DocumentFactory factory, DocumentRepository repository) {
    this.factory = factory;
    this.repository = repository;
  }

  @Override
  public Mono<Void> handle(CreateDocumentCommand createDocumentCommand) {
    Document document = factory.create(createDocumentCommand);
    return repository.save(document);
  }
}


