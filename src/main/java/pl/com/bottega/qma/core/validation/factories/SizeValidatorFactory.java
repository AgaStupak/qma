package pl.com.bottega.qma.core.validation.factories;

import pl.com.bottega.qma.core.validation.validators.SizeValidator;
import pl.com.bottega.qma.core.validation.ValidateSize;
import pl.com.bottega.qma.core.validation.validators.Validator;

import java.lang.reflect.Field;
import java.util.Optional;

public class SizeValidatorFactory implements ValidatorFactory {

  @Override
  public Optional<Validator> create(Field field) {
    ValidateSize validateSize = field.getAnnotation(ValidateSize.class);
    if (validateSize == null) {
      return Optional.empty();
    }
    return Optional.of(new SizeValidator(field, validateSize));
  }
}
