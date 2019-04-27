package pl.com.bottega.qma.core;

import reactor.core.publisher.Mono;

public interface Handler<CommandT extends Command> {

  Mono<Void> handle(CommandT command);

}
