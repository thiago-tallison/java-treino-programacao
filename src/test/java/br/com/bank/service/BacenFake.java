package br.com.bank.service;

import java.util.List;

import br.com.bank.errors.BancoNaoCadastradoException;
import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

public class BacenFake extends Bacen {
  public static final Long SIMULATE_SUCCESS = 1L;
  public static final Long SIMULATE_ERROR = 2L;

  @Override
  public long cadastrarBanco(Banco banco) {

    System.out.println("acessando de banco de dados");
    System.out.println("chamada de rede");

    if (banco.getNumeroRegistro().equals(SIMULATE_SUCCESS)) {
      bancos.add(banco);
      return banco.getNumeroRegistro();
    } else {
      throw new BancoNaoCadastradoException("Erro ao cadastrar banco");
    }
  }

  public List<Banco> getBancos() {
    return bancos;
  }

}
