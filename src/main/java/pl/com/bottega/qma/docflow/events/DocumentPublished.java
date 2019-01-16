package pl.com.bottega.qma.docflow.events;

import pl.com.bottega.qma.docflow.DocumentStatus;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class DocumentPublished extends DocumentEvent {

  @ElementCollection
  public Set<String> departments;

  DocumentPublished() {}

  public DocumentPublished(String number, Long employeeId, LocalDateTime timestamp, Set<String> departments) {
    super(number, employeeId, timestamp);
    this.departments = departments;
  }

  @Override
  public DocumentStatus targetStatus() {
    return DocumentStatus.PUBLISHED;
  }
}
