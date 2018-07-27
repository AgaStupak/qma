package pl.com.bottega.qma.core;

import pl.com.bottega.qma.core.validation.ValidationErrors;

public class CommandInvalidException extends RuntimeException {

  private final ValidationErrors errors;

  public ValidationErrors getErrors() {
    return errors;
  }

  public CommandInvalidException(ValidationErrors errors) {
    this.errors = errors;
  }
}
