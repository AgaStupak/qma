package pl.com.bottega.qma.core.validation.validators;

import pl.com.bottega.qma.core.validation.ValidationErrors;

public interface Validator<ValueT> {

  void validate(ValueT toValidate, ValidationErrors errors);

}
