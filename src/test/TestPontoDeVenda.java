package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.PontoDeVenda;

import org.junit.Test;

import controller.PontoDeVendaController;

public class TestPontoDeVenda {

    @Test
    public void cadastrarPontoDeVenda() {
        int id = 0;
        try {
            id =
                    PontoDeVendaController.cadastrarPontoDeVenda("aehooo", "rua nanana",
                            "12345678", "whoknows", "38255238000130");
        } catch (Exception e) {
            fail();
        } finally {
            if (id > 0) {
                PontoDeVendaController.deletarPontoDeVenda(id);
            }
        }
    }

    @Test
    public void listarPontosDeVenda() {
        int id =
                PontoDeVendaController.cadastrarPontoDeVenda("aehooo", "rua nanana", "12345678",
                        "whoknows", "38255238000130");
        ArrayList<PontoDeVenda> l =
                PontoDeVendaController
                        .listarPontosDeVenda("aehooo", "rua nanana", "38255238000130");
        PontoDeVendaController.deletarPontoDeVenda(id);
        assertNotNull(l);
        assertEquals(l.size(), 1);
    }

    @Test
    public void alterarPontoDeVenda() {
        int id = 0;
        try {
            id =
                    PontoDeVendaController.cadastrarPontoDeVenda("aehooo", "rua nanana",
                            "12345678", "whoknows", "38255238000130");
            PontoDeVendaController.alterarPontoDeVenda(id, "hehehe", "rua nanana", "");
        } catch (Exception e) {
            fail();
        } finally {
            if (id > 0) {
                PontoDeVendaController.deletarPontoDeVenda(id);
            }
        }
    }

    @Test
    public void deletarPontoDeVenda() {
        try {
            int id =
                    PontoDeVendaController.cadastrarPontoDeVenda("aehooo", "rua nanana",
                            "12345678", "whoknows", "38255238000130");
            PontoDeVendaController.deletarPontoDeVenda(id);
        } catch (Exception e) {
            fail();
        }
    }

}
