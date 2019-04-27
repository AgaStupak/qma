package pl.com.bottega.qma.confirmation.handlers;

import pl.com.bottega.qma.confirmation.commands.ConfirmDocumentOnBehalfCommand;
import pl.com.bottega.qma.core.Handler;
import reactor.core.publisher.Mono;

public class ConfirmDocumentOnBehalfHandler implements Handler<ConfirmDocumentOnBehalfCommand> {
  @Override
  public Mono<Void> handle(ConfirmDocumentOnBehalfCommand command) {

    return null;
  }
}
