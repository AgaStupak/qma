package pl.com.bottega.qma.docflow.handlers;

import pl.com.bottega.qma.core.Handler;
import pl.com.bottega.qma.docflow.commands.EditDocumentCommand;
import pl.com.bottega.qma.docflow.domain.DocumentRepository;
import reactor.core.publisher.Mono;

public class EditDocumentHandler implements Handler<EditDocumentCommand> {

  private final DocumentRepository repository;

  public EditDocumentHandler(DocumentRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<Void> handle(EditDocumentCommand editDocumentCommand) {
    return repository.get(editDocumentCommand.documentNumber)
        .flatMap(document -> {
          document.edit(editDocumentCommand);
          return repository.save(document);
        });
  }
}
