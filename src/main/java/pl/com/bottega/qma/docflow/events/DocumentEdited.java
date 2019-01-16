package pl.com.bottega.qma.docflow.events;

import pl.com.bottega.qma.docflow.DocumentStatus;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
public class DocumentEdited extends DocumentEvent {

  public String title, content;

  DocumentEdited() {}

  public DocumentEdited(String number, Long employeeId, LocalDateTime timestamp, Optional<String> title, Optional<String> content) {
    super(number, employeeId, timestamp);
    this.title = title.orElse(null);
    this.content = content.orElse(null);
  }

  @Override
  public DocumentStatus targetStatus() {
    return DocumentStatus.DRAFT;
  }
}
