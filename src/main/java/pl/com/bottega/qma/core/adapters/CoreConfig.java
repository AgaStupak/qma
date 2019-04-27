package pl.com.bottega.qma.core.adapters;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.bottega.qma.core.CommandGateway;

@Configuration
public class CoreConfig {

  @Bean
  public CommandGateway commandGateway(ApplicationContext ctx) {
    return new SpringCommandGateway(ctx);
  }

}
