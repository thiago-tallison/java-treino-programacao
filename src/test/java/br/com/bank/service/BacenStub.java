package br.com.bank.service;

import br.com.bank.errors.BancoNaoCadastradoException;
import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

public class BacenStub extends Bacen {

  @Override
  public long cadastrarBanco(Banco banco) {
    if (banco.getNumeroRegistro().equals(1L)) {
      return banco.getNumeroRegistro();
    } else {
      throw new BancoNaoCadastradoException("Erro ao cadastrar banco");
    }
  }
}
