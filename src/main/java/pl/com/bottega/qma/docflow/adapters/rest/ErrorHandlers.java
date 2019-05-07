package pl.com.bottega.qma.docflow.adapters.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.com.bottega.qma.docflow.DocumentRepository;

@ControllerAdvice
public class ErrorHandlers {

    @ExceptionHandler(value = DocumentRepository.DocumentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleDocumentNotFound() { }

}
