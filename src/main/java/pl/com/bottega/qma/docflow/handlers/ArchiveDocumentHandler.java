package pl.com.bottega.qma.docflow.handlers;

import pl.com.bottega.qma.core.Handler;
import pl.com.bottega.qma.docflow.commands.ArchiveDocumentCommand;
import reactor.core.publisher.Mono;

public class ArchiveDocumentHandler implements Handler<ArchiveDocumentCommand> {
  @Override
  public Mono<Void> handle(ArchiveDocumentCommand createDocumentCommand) {

    return null;
  }
}
