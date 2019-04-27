package pl.com.bottega.qma.core.adapters;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import pl.com.bottega.qma.core.Command;
import pl.com.bottega.qma.core.CommandGateway;
import pl.com.bottega.qma.core.Handler;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SpringCommandGateway implements CommandGateway {

  private Map<Class<? extends Command>, Handler<Command>> handlers = new HashMap<>();
  private final ApplicationContext context;

  public SpringCommandGateway(ApplicationContext context) {
    this.context = context;
  }

  @Override
  public Mono<Void> execute(Command command) {
    Handler<Command> handler = handlers.get(command.getClass());
    if (handler == null) {
      throw new IllegalArgumentException(String.format("no handler for command %s", command.getClass()));
    }
    return handler.handle(command);
  }

  @EventListener
  public void handleContextRefresh(ContextRefreshedEvent event) {
    this.handlers = this.context.getBeansOfType(Handler.class).values().stream()
        .collect(Collectors.toMap(this::commandClass, Function.identity()));
  }

  private Class<? extends Command> commandClass(Handler<Command> handler) {
    return (Class<? extends Command>) ((ParameterizedType) handler.getClass().getGenericInterfaces()[0])
        .getActualTypeArguments()[0];
  }

}
