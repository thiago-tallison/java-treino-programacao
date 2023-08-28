package br.com.bank.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import br.com.bank.errors.BancoNaoCadastradoException;
import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SistemaBancarioTest {
  @Mock
  private Bacen bacenMock;

  @Mock
  private Banco banco;

  @InjectMocks
  private SistemaBancario sut;

  @Test
  @RepeatedTest(5)
  public void testRegistrarBanco() {
    System.out.println(banco);
    System.out.println(bacenMock);

    when(bacenMock.cadastrarBanco(banco)).thenReturn(123L);

    long result = sut.registrarBanco(banco);

    assertEquals(123L, result);
  }

  @Test
  public void testFailRegistrarBanco() {
    when(bacenMock.cadastrarBanco(banco)).thenReturn(456L);

    long result = sut.registrarBanco(banco);
     
    assertNotEquals(123L, result);

    verify(bacenMock).cadastrarBanco(banco);
  }

@Test
  public void testExceptionRegistrarBanco() {
    when(bacenMock.cadastrarBanco(banco)).thenThrow(BancoNaoCadastradoException.class);
    assertThrows(BancoNaoCadastradoException.class, () ->  sut.registrarBanco(banco));
  }

  @Test
  public void registry_a_bank_success_case_with_stub() {
    Banco banco = new Banco("Banco fake", 1L);

    BacenStub bacenStub = new BacenStub();
    SistemaBancario sistemaBancario = new SistemaBancario(bacenStub);

    assertDoesNotThrow(() -> sistemaBancario.registrarBanco(banco));
  }

  @Test
  public void registry_a_bank_error_case_with_stub() {
    Banco banco = new Banco("Banco fake", 2L);
    BacenStub bacenStub = new BacenStub();
    SistemaBancario sistemaBancario = new SistemaBancario(bacenStub);

    Throwable throwable = assertThrows(BancoNaoCadastradoException.class, () -> sistemaBancario.registrarBanco(banco));

    assertEquals(throwable.getMessage(), "Erro ao cadastrar banco");
  }

  @Test
  public void registry_a_bank_success_case_with_fake() {
    Banco banco = new Banco("Banco fake", BacenFake.SIMULATE_SUCCESS);
    BacenFake bacenFake = new BacenFake();
    SistemaBancario sistemaBancario = new SistemaBancario(bacenFake);

    assertDoesNotThrow(() -> sistemaBancario.registrarBanco(banco));
    assertEquals(sistemaBancario.registrarBanco(banco), 1L);
    assertTrue(bacenFake.getBancos().contains(banco));
  }

  @Test
  public void registry_a_bank_error_case_with_fake() {
    Banco banco = new Banco("Banco fake", BacenFake.SIMULATE_ERROR);
    BacenFake bacenFake = new BacenFake();
    SistemaBancario sistemaBancario = new SistemaBancario(bacenFake);

    Throwable throwable = assertThrows(BancoNaoCadastradoException.class, () -> sistemaBancario.registrarBanco(banco));

    assertEquals(throwable.getMessage(), "Erro ao cadastrar banco");
    assertFalse(bacenFake.getBancos().contains(banco));
  }

}