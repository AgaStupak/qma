package pl.com.bottega.qma.core.validation;

import java.lang.reflect.Field;
import java.util.Optional;

public class ValidatePresenceFactory implements ValidatorFactory {

  @Override
  public Optional<Validator> createValidator(Field field) {
    ValidatePresence[] annotations = field.getDeclaredAnnotationsByType(ValidatePresence.class);
    if (annotations.length == 0) {
      return Optional.empty();
    }
    return Optional.of(new PresenceValidator(field, annotations[0]));
  }

}
