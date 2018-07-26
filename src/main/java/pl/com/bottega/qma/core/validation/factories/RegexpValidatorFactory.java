package pl.com.bottega.qma.core.validation.factories;

import pl.com.bottega.qma.core.validation.validators.RegexpNumberValidator;
import pl.com.bottega.qma.core.validation.ValidateRegexp;
import pl.com.bottega.qma.core.validation.validators.Validator;

import java.lang.reflect.Field;
import java.util.Optional;

public class RegexpValidatorFactory implements ValidatorFactory {
  @Override
  public Optional<Validator> create(Field field) {
    ValidateRegexp validateRegexp = field.getAnnotation(ValidateRegexp.class);
    if(validateRegexp == null) {
      return Optional.empty();
    }
    if (!field.getType().equals(String.class)) {
      throw new IllegalArgumentException("ValidateRegexp allowed only on String");
    }
    return Optional.of(new RegexpNumberValidator(field, validateRegexp));
  }
}
