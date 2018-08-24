package pl.com.bottega.qma.docflow.commands;

import pl.com.bottega.qma.core.Command;
import pl.com.bottega.qma.core.validation.ValidatePresence;

public class CreateDocumentCommand implements Command {

  @ValidatePresence
  public Long creatorId;

}
