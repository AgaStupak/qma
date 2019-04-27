package pl.com.bottega.qma.docflow.handlers;

import pl.com.bottega.qma.core.Handler;
import pl.com.bottega.qma.docflow.commands.PublishDocumentCommand;
import reactor.core.publisher.Mono;

public class PublishDocumentHandler implements Handler<PublishDocumentCommand> {
  @Override
  public Mono<Void> handle(PublishDocumentCommand publishDocumentCommand) {

    return null;
  }
}
