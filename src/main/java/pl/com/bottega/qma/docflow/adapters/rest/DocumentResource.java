package pl.com.bottega.qma.docflow.adapters.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.qma.core.CommandGateway;
import pl.com.bottega.qma.docflow.commands.CreateDocumentCommand;
import pl.com.bottega.qma.docflow.commands.EditDocumentCommand;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/documents")
public class DocumentResource {

  private final CommandGateway gateway;

  public DocumentResource(CommandGateway gateway) {
    this.gateway = gateway;
  }

  @PostMapping
  public Mono<Void> create(CreateDocumentCommand cmd) {
    return gateway.execute(cmd);
  }

  @PutMapping
  public Mono<Void> edit(EditDocumentCommand cmd) {
    return gateway.execute(cmd);
  }

}
