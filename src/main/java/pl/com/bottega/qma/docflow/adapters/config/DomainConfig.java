package pl.com.bottega.qma.docflow.adapters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.bottega.qma.docflow.domain.DocumentFactory;

@Configuration
public class DomainConfig {

  @Bean
  public DocumentFactory documentFactory() {
    return new DocumentFactory();
  }

}
