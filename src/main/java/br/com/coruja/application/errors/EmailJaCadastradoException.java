package br.com.coruja.application.errors;

public class EmailJaCadastradoException extends RuntimeException {
  public EmailJaCadastradoException() {
    super("E-mail já cadastrado");
  }

  public EmailJaCadastradoException(String message) {
    super(message);
  }

}
