package pl.com.bottega.qma.core;

import reactor.core.publisher.Mono;

public interface CommandGateway {

  Mono<Void> execute(Command command);

}
