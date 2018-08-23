package pl.com.bottega.qma.core;

import pl.com.bottega.qma.core.validation.ValidationEngine;
import pl.com.bottega.qma.core.validation.ValidationErrors;

public class ValidatingHandler<CommandT extends Command, ReturnT>
    extends HandlerDecorator<CommandT, ReturnT> {

  private final ValidationEngine validationEngine;

  public ValidatingHandler(Handler decorated, ValidationEngine validationEngine) {
    super(decorated);
    this.validationEngine = validationEngine;
  }

  @Override
  public ReturnT handle(CommandT command) {
    ValidationErrors errors = validationEngine.validate(command);
    if(errors.isInvalid()) {
      throw new IllegalArgumentException();
    }
    return decorated.handle(command);
  }

}
