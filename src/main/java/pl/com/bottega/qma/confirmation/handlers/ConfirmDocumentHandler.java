package pl.com.bottega.qma.confirmation.handlers;

import pl.com.bottega.qma.confirmation.commands.ConfirmDocumentCommand;
import pl.com.bottega.qma.core.Handler;
import reactor.core.publisher.Mono;

public class ConfirmDocumentHandler implements Handler<ConfirmDocumentCommand> {
  @Override
  public Mono<Void> handle(ConfirmDocumentCommand command) {

    return null;
  }
}
