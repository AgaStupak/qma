package pl.com.bottega.qma.core.validation;

import java.lang.reflect.Field;
import java.util.Optional;

public interface ValidatorFactory {

  Optional<Validator> createValidator(Field field);

}
