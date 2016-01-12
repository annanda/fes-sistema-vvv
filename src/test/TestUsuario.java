package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controller.PessoaController;
import controller.UsuarioController;
import dao.PessoaDAO;
import model.Pessoa;
import model.Usuario;

public class TestUsuario {

  PessoaDAO pessoa_dao;

  @Before
  public void init() {
    pessoa_dao = new PessoaDAO();
  }

  @Test
  public void cadastrarUsuario() {
    int id = 0;
    try {
      id = UsuarioController.cadastrarUsuario("test", "test", "test", "test@test.com", "test", "0");
    } catch (Exception e) {
      fail();
    } finally {
      if (id > 0) {
        Pessoa p = pessoa_dao.getPessoaByCodigo("test");
        PessoaController.deletarPessoa(p.getId());
      }
    }
  }

  @Test
  public void listarUsuarios() {
    UsuarioController.cadastrarUsuario("test", "test", "test", "test@test.com", "test", "0");
    ArrayList<Usuario> l =
        UsuarioController.listarUsuarios("test", "test", "test", "test@test.com");
    Pessoa p = pessoa_dao.getPessoaByCodigo("test");
    PessoaController.deletarPessoa(p.getId());
    assertNotNull(l);
    assertEquals(l.size(), 1);
  }

  @Test
  public void alterarUsuario() {
    int id = 0;
    try {
      id = UsuarioController.cadastrarUsuario("test", "test", "test", "test@test.com", "test", "0");
      UsuarioController.alterarUsuario(id, "asdf", "asdf", "asdf", "asdf@asdf.com", "asdf", "1");
    } catch (Exception e) {
      fail();
    } finally {
      if (id > 0) {
        Pessoa p = pessoa_dao.getPessoaByCodigo("asdf");
        PessoaController.deletarPessoa(p.getId());
      }
    }
  }

  @Test
  public void deletarUsuario() {
    int id = 0;
    try {
      id = UsuarioController.cadastrarUsuario("test", "test", "test", "test@test.com", "test", "0");
      UsuarioController.deletarUsuario(id);
    } catch (Exception e) {
      fail();
    } finally {
      if (id > 0) {
        Pessoa p = pessoa_dao.getPessoaByCodigo("test");
        PessoaController.deletarPessoa(p.getId());
      }
    }
  }

}
