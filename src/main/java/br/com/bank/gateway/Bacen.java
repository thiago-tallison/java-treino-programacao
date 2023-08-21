package br.com.bank.gateway;

import java.util.ArrayList;
import java.util.List;

import br.com.bank.model.Banco;

public class Bacen {

	protected List<Banco> bancos = new ArrayList<>();

	public long cadastrarBanco(Banco banco) {

		System.out.println("acessando de banco de dados");
		System.out.println("chamada de rede");
		bancos.add(banco);
		return banco.getNumeroRegistro();
	}

}
