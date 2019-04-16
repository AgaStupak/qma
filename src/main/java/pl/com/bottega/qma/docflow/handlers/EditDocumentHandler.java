package pl.com.bottega.qma.docflow.handlers;

import pl.com.bottega.qma.core.Handler;
import pl.com.bottega.qma.docflow.commands.EditDocumentCommand;
import pl.com.bottega.qma.docflow.domain.Document;
import pl.com.bottega.qma.docflow.domain.DocumentRepository;

public class EditDocumentHandler implements Handler<EditDocumentCommand> {

  private final DocumentRepository repository;

  public EditDocumentHandler(DocumentRepository repository) {
    this.repository = repository;
  }

  @Override
  public void handle(EditDocumentCommand editDocumentCommand) {
    Document document = repository.get(editDocumentCommand.documentNumber);
    document.edit(editDocumentCommand);
    repository.save(document);
  }
}
