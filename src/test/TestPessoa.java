package test;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

import controller.PessoaController;
import model.Pessoa;

public class TestPessoa {

	@Test
	public void cadastrarPessoa() {
		int id = 0;
		try {
			id = PessoaController.cadastrarPessoa("test", "test", "test");
		} catch (Exception e) {
			fail();
		} finally {
			if (id > 0)
				PessoaController.deletarPessoa(id);
		}
	}

	@Test
	public void listarPessoas() {
		PessoaController.cadastrarPessoa("test", "test", "test");
		ArrayList<Pessoa> l = PessoaController.listarPessoas("test", "", "");
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void alterarPessoa() {
		int id = 0;
		try {
			id = PessoaController.cadastrarPessoa("test", "test", "test");
			PessoaController.alterarPessoa(id, "asdf", "asdf", "asdf");
		} catch (Exception e) {
			fail();
		} finally {
			if (id > 0)
				PessoaController.deletarPessoa(id);
		}
	}

	@Test
	public void deletarPessoa() {
		try {
			int id = PessoaController.cadastrarPessoa("test", "test", "test");
			PessoaController.deletarPessoa(id);
		} catch (Exception e) {
			fail();
		}
	}

}
