package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controller.PessoaController;
import controller.PassageiroController;
import dao.PessoaDAO;
import model.Passageiro;
import model.Pessoa;

public class TestPassageiro {

    PessoaDAO pessoa_dao;
    String date;

    @Before
    public void init() {
        pessoa_dao = new PessoaDAO();
        date = "2016-01-11";
    }

    @Test
    public void cadastrarPassageiroSemResponsavel() {
        int id = 0;
        try {
            id =
                    PassageiroController.cadastrarPassageiro("test", "test", "test", "test",
                            "test", "test", date, null);
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
    public void cadastrarPassageiroComResponsavel() {
        int id = 0, id_responsavel = 0;
        try {
            id_responsavel =
                    PassageiroController.cadastrarPassageiro("test", "test", "test", "test",
                            "test", "test", date, null);

            id =
                    PassageiroController.cadastrarPassageiro("asdf", "asdf", "asdf", "asdf",
                            "asdf", "asdf", date, Integer.toString(id_responsavel));
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
    public void listarPassageiros() {
        PassageiroController.cadastrarPassageiro("test", "test", "test", "test", "test", "test",
                date, null);
        ArrayList<Passageiro> l =
                PassageiroController.listarPassageiros("test", "test", "test", date);
        Pessoa p = pessoa_dao.getPessoaByCodigo("test");
        PessoaController.deletarPessoa(p.getId());
        assertNotNull(l);
        assertEquals(l.size(), 1);
    }

    @Test
    public void alterarPassageiro() {
        int id = 0;
        try {
            id =
                    PassageiroController.cadastrarPassageiro("test", "test", "test", "test",
                            "test", "test", date, null);
            PassageiroController.alterarPassageiro(id, "asdf", "asdf", "asdf", "asdf", null);
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
    public void deletarPassageiro() {
        int id = 0;
        try {
            id =
                    PassageiroController.cadastrarPassageiro("test", "test", "test", "test",
                            "test", "test", date, null);
            PassageiroController.deletarPassageiro(id);
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
