package pl.com.bottega.qma.docflow.commands;

import pl.com.bottega.qma.core.Command;
import pl.com.bottega.qma.core.validation.ValidatePresence;

import java.util.Optional;

public class EditDocumentCommand implements Command {

  @ValidatePresence
  public String documentNumber;

  @ValidatePresence
  public Long editorId;

  @ValidatePresence
  public Optional<String> title;

  @ValidatePresence
  public Optional<String> content;

}
