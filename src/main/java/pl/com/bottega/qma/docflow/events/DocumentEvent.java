package pl.com.bottega.qma.docflow.events;

import pl.com.bottega.qma.docflow.DocumentStatus;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "event_type")
public abstract class DocumentEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public String number;
  public Long employeeId;
  public LocalDateTime timestamp;

  DocumentEvent() {}

  public DocumentEvent(String number, Long employeeId, LocalDateTime timestamp) {
    this.number = number;
    this.employeeId = employeeId;
    this.timestamp = timestamp;
  }

  public abstract DocumentStatus targetStatus();

}
