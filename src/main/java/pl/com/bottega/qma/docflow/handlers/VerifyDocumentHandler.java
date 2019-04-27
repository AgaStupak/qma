package pl.com.bottega.qma.docflow.handlers;

import pl.com.bottega.qma.core.Handler;
import pl.com.bottega.qma.docflow.commands.VerifyDocumentCommand;
import reactor.core.publisher.Mono;

public class VerifyDocumentHandler implements Handler<VerifyDocumentCommand> {
  @Override
  public Mono<Void> handle(VerifyDocumentCommand verifyDocumentCommand) {

    return null;
  }
}
