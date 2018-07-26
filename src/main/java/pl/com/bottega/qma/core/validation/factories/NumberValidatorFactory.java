package pl.com.bottega.qma.core.validation.factories;

import pl.com.bottega.qma.core.validation.*;
import pl.com.bottega.qma.core.validation.validators.BigDecimalNumberValidator;
import pl.com.bottega.qma.core.validation.validators.IntegerNumberValidator;
import pl.com.bottega.qma.core.validation.validators.LongNumberValidator;
import pl.com.bottega.qma.core.validation.validators.Validator;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Optional;

public class NumberValidatorFactory implements ValidatorFactory {
  @Override
  public Optional<Validator> create(Field field) {
    ValidateNumber validateNumber = field.getDeclaredAnnotation(ValidateNumber.class);
    if(validateNumber == null) {
      return Optional.empty();
    }
    if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
      return Optional.of(new IntegerNumberValidator(field, validateNumber));
    } else if (field.getType().equals(BigDecimal.class)) {
      return Optional.of( new BigDecimalNumberValidator(field, validateNumber));
    } else if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
      return Optional.of(new LongNumberValidator(field, validateNumber));
    } else {
      throw new IllegalArgumentException("Unsupported type for number validator");
    }
  }
}
