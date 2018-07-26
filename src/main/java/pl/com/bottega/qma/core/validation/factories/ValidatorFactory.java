package pl.com.bottega.qma.core.validation.factories;

import pl.com.bottega.qma.core.validation.validators.Validator;

import java.lang.reflect.Field;
import java.util.Optional;

public interface ValidatorFactory {

  Optional<Validator> create(Field field);

}
