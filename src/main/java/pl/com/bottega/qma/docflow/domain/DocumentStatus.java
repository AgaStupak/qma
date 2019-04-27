package pl.com.bottega.qma.docflow.domain;

import java.util.List;

import static pl.com.bottega.qma.docflow.domain.DocumentOperation.*;

public enum DocumentStatus {

  DRAFT(EDIT, PASS_TO_VERIFICATION, ARCHIVE),
  WAITING_VERIFICATION(VERIFY, REJECT, ARCHIVE, EDIT),
  VERIFIED(EDIT, PUBLISH, ARCHIVE),
  PUBLISHED(ARCHIVE, CREATE_NEW_VERSION),
  ARCHIVED(CREATE_NEW_VERSION);

  private List<DocumentOperation> allowedOperations;

  DocumentStatus(DocumentOperation... allowedOperations) {
    this.allowedOperations = List.of(allowedOperations);
  }

}
