package pl.com.bottega.qma.core.validation;

public interface Validator<ValueT> {

  void validate(ValueT toValidate, ValidationErrors errors);

}
