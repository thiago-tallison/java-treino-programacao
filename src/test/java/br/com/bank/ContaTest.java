package br.com.bank;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import br.com.bank.model.Banco;
import br.com.bank.model.Conta;

public class ContaTest {
  @Test
  public void it_sould_add_an_account() {
    Banco bancoFake = new Banco("Banco fake");
    Conta conta = new Conta("123");
    assertDoesNotThrow(() -> bancoFake.adicionarConta(conta));
  }

  @Test
  public void it_should_be_able_to_search_an_account() {
    Banco bancoFake = new Banco("Banco fake");
    Conta conta = new Conta("123");
    bancoFake.adicionarConta(conta);

    Optional<Conta> contaRecuperada = bancoFake.pesquisarContaDoCliente(conta.getCpf());

    assertTrue(contaRecuperada.isPresent());
    assertEquals(conta, contaRecuperada.get());
  }

  @Test
  public void it_should_be_able_to_list_high_income_accounts() {
    Banco bancoFake = new Banco("Banco fake");
    Conta contaAltaRenda = new Conta("123", 10000.0);
    Conta contaNaoaltarenda = new Conta("123", 9999.99);

    bancoFake.adicionarConta(contaAltaRenda);
    bancoFake.adicionarConta(contaNaoaltarenda);

    List<Conta> contasAltaRenda = bancoFake.listarContasAltaRenda();

    assertEquals(1, contasAltaRenda.size());
    assertEquals(contasAltaRenda.get(0), contaAltaRenda);
  }
}
