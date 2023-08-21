package br.com.bank.errors;

public class BancoNaoCadastradoException extends RuntimeException {
  public BancoNaoCadastradoException(String message) {
    super(message);
  }
}
