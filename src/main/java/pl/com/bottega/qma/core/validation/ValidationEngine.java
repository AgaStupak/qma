package pl.com.bottega.qma.core.validation;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ValidationEngine {

  private List<ValidatorFactory> factories = new LinkedList<>();

  public ValidationEngine() {
    factories.add(new ValidatePresenceFactory());

  }

  public ValidationErrors validate(Object object) {
    List<Validator> validators = validatorsFor(object);
    ValidationErrors errors = new ValidationErrors();
    for(Validator validator : validators) {
      validator.validate(object, errors);
    }
    return errors;
  }

  private List<Validator> validatorsFor(Object object) {
    List<Validator> validators = new LinkedList<>();
    for(Field field : object.getClass().getDeclaredFields()) {
      for(ValidatorFactory factory : factories) {
        Optional<Validator> optionalValidator = factory.createValidator(field);
        if(optionalValidator.isPresent()) {
          validators.add(optionalValidator.get());
        }
      }
    }
    return validators;
  }

}
