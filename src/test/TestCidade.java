package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import controller.CidadeController;
import model.Cidade;

public class TestCidade {

  @Test
  public void cadastrarCidade() {
    int id = 0;
    try {
      id = CidadeController.cadastrarCidade("test", "tes", "test");;
    } catch (Exception e) {
      fail();
    } finally {
      if (id > 0)
        CidadeController.deletarCidade(id);
    }
  }

  @Test
  public void listarCidades() {
    int id = CidadeController.cadastrarCidade("test", "tes", "test");
    ArrayList<Cidade> l = CidadeController.listarCidades("test", "tes", "test");
    CidadeController.deletarCidade(id);
    assertNotNull(l);
    assertEquals(l.size(), 1);
  }

  @Test
  public void alterarCidade() {
    int id = 0;
    try {
      id = CidadeController.cadastrarCidade("test", "tes", "test");
      CidadeController.alterarCidade(id, "asdf", "asd", "asdf");
    } catch (Exception e) {
      fail();
    } finally {
      if (id > 0)
        CidadeController.deletarCidade(id);
    }
  }

  @Test
  public void deletarCidade() {
    try {
      int id = CidadeController.cadastrarCidade("test", "tes", "test");
      CidadeController.deletarCidade(id);
    } catch (Exception e) {
      fail();
    }
  }

}
