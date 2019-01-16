package pl.com.bottega.qma.docflow.events;

import pl.com.bottega.qma.docflow.DocumentStatus;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class DocumentArchived extends DocumentEvent {
  public DocumentArchived(String number, Long employeeId, LocalDateTime timestamp) {
    super(number, employeeId, timestamp);
  }

  @Override
  public DocumentStatus targetStatus() {
    return DocumentStatus.ARCHIVED;
  }
}
