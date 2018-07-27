package pl.com.bottega.qma.docflow;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.qma.core.CommandGateway;
import pl.com.bottega.qma.core.CommandInvalidException;
import pl.com.bottega.qma.core.validation.ValidationErrors;
import pl.com.bottega.qma.docflow.commands.CreateDocumentCommand;
import pl.com.bottega.qma.docflow.commands.EditDocumentCommand;

@RestController
public class DocumentController {

  private CommandGateway commandGateway;

  public DocumentController(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @PostMapping("/documents")
  public String create(@RequestBody CreateDocumentCommand command) {
    return commandGateway.execute(command);
  }

  @PutMapping("/documents/{number}")
  public void edit(@PathVariable String number, @RequestBody EditDocumentCommand command) {
    command.documentNumber = number;
    commandGateway.execute(command);
  }

  @ExceptionHandler(DocumentRepository.DocumentNotFoundException.class)
  @ResponseStatus(code=HttpStatus.NOT_FOUND)
  public void handleDocumentNotFound() { }

  @ExceptionHandler(CommandInvalidException.class)
  public ResponseEntity<ValidationErrors> handleInvalidCommandException(CommandInvalidException ex) {
    return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
  }

}
