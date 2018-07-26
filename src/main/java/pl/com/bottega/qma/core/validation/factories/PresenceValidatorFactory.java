package pl.com.bottega.qma.core.validation.factories;

import pl.com.bottega.qma.core.validation.validators.PresenceValidator;
import pl.com.bottega.qma.core.validation.ValidatePresence;
import pl.com.bottega.qma.core.validation.validators.Validator;

import java.lang.reflect.Field;
import java.util.Optional;

public class PresenceValidatorFactory implements ValidatorFactory{

  @Override
  public Optional<Validator> create(Field field) {
    ValidatePresence validatePresence = field.getAnnotation(ValidatePresence.class);
    if(validatePresence != null) {
        return Optional.of(new PresenceValidator(field, validatePresence));
    }
    return Optional.empty();
  }
}
