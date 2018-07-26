package pl.com.bottega.qma.docflow;

import com.google.common.base.Predicate;
import pl.com.bottega.qma.docflow.commands.*;
import pl.com.bottega.qma.docflow.events.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class Document {

  private DocumentStatus status;
  private final Long creatorId;
  private Long editorId, archiverId;

  private List<DocumentEvent> events = new LinkedList<>();

  public Document(String number, CreateDocumentCommand createDocumentCommand) {
    status = DocumentStatus.DRAFT;
    creatorId = createDocumentCommand.creatorId;
    events.add(new DocumentCreated(number, createDocumentCommand.creatorId, LocalDateTime.now()));
  }

  public void edit(EditDocumentCommand editDocumentCommand) {
    checkState(status == DocumentStatus.DRAFT || status == DocumentStatus.VERIFIED,
        "only draft and verified documents can be edited");
    status = DocumentStatus.DRAFT;
    editorId = editDocumentCommand.editorId;
    events.add(new DocumentEdited(
        editDocumentCommand.documentNumber,
        editDocumentCommand.editorId,
        LocalDateTime.now(),
        editDocumentCommand.title,
        editDocumentCommand.content
    ));
  }

  public void verify(VerifyDocumentCommand verifyDocumentCommand) {
    checkState(status == DocumentStatus.DRAFT,
        "only draft documents can be verified");
    checkArgument(!creatorId.equals(verifyDocumentCommand.verifierId), "document creator can't verify it");
    checkArgument(!editorId.equals(verifyDocumentCommand.verifierId), "document editor can't verify it");
    status = DocumentStatus.VERIFIED;
    events.add(new DocumentVerified(
        verifyDocumentCommand.documentNumber,
        verifyDocumentCommand.verifierId,
        LocalDateTime.now()
    ));
  }

  public void publish(PublishDocumentCommand publishDocumentCommand) {
    checkState(status == DocumentStatus.VERIFIED || status == DocumentStatus.PUBLISHED,
        "only verified and published documents can be published");
    status = DocumentStatus.PUBLISHED;
    events.add(new DocumentPublished(
        publishDocumentCommand.documentNumber,
        publishDocumentCommand.publisherId,
        LocalDateTime.now(),
        publishDocumentCommand.departmentCodes
    ));
  }

  public void archive(ArchiveDocumentCommand archiveDocumentCommand) {
    status = DocumentStatus.ARCHIVED;
    archiverId = archiveDocumentCommand.archiverId;
  }

  public String number() {
    return events.get(0).number;
  }

  public Long creatorId() {
    return creatorId;
  }

  public String title() {
    return eventsOfType(DocumentEdited.class).
        filter((event) -> event.title.isPresent()).
        reduce((first, second) -> second).
        map((event) -> event.title.get()).
        orElse("");
  }

  private <T extends DocumentEvent> Stream<T> eventsOfType(Class<T> klass) {
    return events.stream().
        filter((event) -> event.getClass().equals(klass)).
        map((event) -> (T) event);
  }

  public String content() {
    return eventsOfType(DocumentEdited.class).
        filter((event) -> event.content.isPresent()).
        map((event) -> event.content.get()).
        reduce((first, second) -> second).
        orElse("");
  }

  public Long editorId() {
    return editorId;
  }

  public DocumentStatus status() {
    return status;
  }

  public Long verifierId() {
    return eventsOfType(DocumentVerified.class).
        reduce((first, second) -> second).
        map((event) -> event.employeeId).
        orElse(null);
  }

  public Long publisherId() {
    return eventsOfType(DocumentPublished.class).
        reduce((first, second) -> second).
        map((event) -> event.employeeId).
        orElse(null);
  }

  public Set<String> publishedFor() {

    return new HashSet<>();
  }

  public Long archiverId() {
    return archiverId;
  }
}
