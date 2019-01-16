package pl.com.bottega.qma.docflow;

import pl.com.bottega.qma.docflow.events.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DocumentStatus {

  DRAFT(Arrays.asList(DocumentEdited.class, DocumentVerified.class, DocumentArchived.class)),
  VERIFIED(Arrays.asList(DocumentPublished.class, DocumentEdited.class)),
  PUBLISHED(Arrays.asList(DocumentPublished.class, DocumentArchived.class)),
  ARCHIVED(Collections.emptyList());

  private Collection<Class<? extends DocumentEvent>> possibleEvents;

  DocumentStatus(Collection<Class<? extends DocumentEvent>> possibleEvents) {
    this.possibleEvents = possibleEvents;
  }

  <EventT extends DocumentEvent> void checkOperationPermited(Class<EventT> eventT) {
    if(possibleEvents.contains(eventT)) {
      return;
    }
    throw new IllegalDocumentOperation(String.format("only %s documents can be %s", allowedStates(eventT), operationName(eventT)));
  }

  private <EventT extends DocumentEvent> String allowedStates(Class<EventT> eventT) {
     Stream<DocumentStatus> statesAllowedToPerformOperation = Arrays.stream(DocumentStatus.values()).
        filter(documentStatus -> documentStatus.possibleEvents.contains(eventT));
    return String.join(" and ",
        statesAllowedToPerformOperation.map(documentStatus -> documentStatus.name().toLowerCase()).collect(Collectors.toList()));
  }

  private <EventT extends DocumentEvent> String operationName(Class<EventT> eventT) {
    Pattern pattern = Pattern.compile("^.*Document(.*)$");
    Matcher matcher = pattern.matcher(eventT.getName());
    matcher.find();
    return matcher.group(1).toLowerCase();
  }

}
