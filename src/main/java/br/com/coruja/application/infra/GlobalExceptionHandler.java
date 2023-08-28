package br.com.coruja.application.infra;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.coruja.application.errors.EmailJaCadastradoException;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(EmailJaCadastradoException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ResponseBody
  public ErrorResponse handleEmailJaCadastradoException(EmailJaCadastradoException e) {

    return new ErrorResponse(e.getMessage());
  }
}
