package pl.com.bottega.qma.core.validation.validators;

import pl.com.bottega.qma.core.validation.ValidateNumber;
import pl.com.bottega.qma.core.validation.ValidationErrors;

import java.lang.reflect.Field;

public class IntegerNumberValidator extends FieldValidator<Integer> {

  private final ValidateNumber config;

  public IntegerNumberValidator(Field field, ValidateNumber config) {
    super(field);
    this.config = config;
  }

  @Override
  protected void validateValue(Integer value, ValidationErrors errors) {
    if(value == null) {
      return;
    }
    if(value > config.max() || value < config.min()) {
      addError(errors, String.format("must be between %d and %d", config.min(), config.max()));
    }
  }

}
