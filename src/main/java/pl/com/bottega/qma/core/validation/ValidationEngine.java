package pl.com.bottega.qma.core.validation;

import pl.com.bottega.qma.core.validation.factories.*;
import pl.com.bottega.qma.core.validation.validators.Validator;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ValidationEngine {

  private final Map<Class, List<Validator>> validatorsCache = new HashMap<>();

  private final List<ValidatorFactory> factories = new LinkedList<>();

  public ValidationEngine() {
    factories.add(new PresenceValidatorFactory());
    factories.add(new NumberValidatorFactory());
    factories.add(new RegexpValidatorFactory());
    factories.add(new SizeValidatorFactory());
  }

  public ValidationErrors validate(Object object) {
    var chain = validatorsChain(object.getClass());
    ValidationErrors errors = new ValidationErrors();
    chain.forEach(validator -> validator.validate(object, errors));
    return errors;
  }

  private List<Validator> validatorsChain(Class clazz) {
    if(validatorsCache.containsKey(clazz)) {
      return validatorsCache.get(clazz);
    }
    var chain = createChain(clazz);
    validatorsCache.put(clazz, chain);
    return chain;
  }

  private List<Validator> createChain(Class clazz) {
    var chain = new LinkedList<Validator>();
    for(Field field : clazz.getDeclaredFields()) {
      for(ValidatorFactory factory : factories) {
        factory.create(field).ifPresent((v) -> chain.add(v));
      }
    }
    return chain;
  }
}
