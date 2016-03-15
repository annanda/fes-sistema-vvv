package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Cidade;
import model.Percurso;

import org.junit.*;

import controller.CidadeController;
import controller.PercursoController;

public class TestPercurso {
    Cidade partida, chegada;

    @Before
    public void init() {
        partida =
                CidadeController.getCidadeById(CidadeController.cadastrarCidade("Rio de Janeiro",
                        "RDJ", "222"));
        chegada =
                CidadeController.getCidadeById(CidadeController.cadastrarCidade("Sao Paulo", "SPA",
                        "paradepois"));
    }

    @After
    public void finish() {
        CidadeController.deletarCidade(partida.getId());
        CidadeController.deletarCidade(chegada.getId());
    }

    @Test
    public void cadastrarPercurso() {
        int id = 0;
        try {
            id =
                    PercursoController.cadastrarPercurso("0", partida.getCodigo(),
                            chegada.getCodigo(), "2012-12-21 12:21:12", "5", "");
        } catch (Exception e) {
            fail();
        } finally {
            if (id > 0) {
                PercursoController.deletarPercurso(id);
            }
        }
    }

    @Test
    public void listarPercursos() {
        int id =
                PercursoController.cadastrarPercurso("0", partida.getCodigo(), chegada.getCodigo(),
                        "2012-12-21 12:21:12", "5", "");
        ArrayList<Percurso> l =
                PercursoController.listarPercursos("" + partida.getId(), "" + chegada.getId(),
                        "2012-12-21 12:21:12", "");
        PercursoController.deletarPercurso(id);
        assertNotNull(l);
        assertEquals(l.size(), 1);
    }

    @Test
    public void alterarPercursos() {
        int id = 0;
        try {
            id =
                    PercursoController.cadastrarPercurso("0", partida.getCodigo(),
                            chegada.getCodigo(), "2012-12-21 12:21:12", "5", "");
            PercursoController.alterarPercurso(id, "0", "1", "aeroporto123");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        } finally {
            if (id > 0) {
                PercursoController.deletarPercurso(id);
            }
        }
    }

    @Test
    public void deletarPercurso() {
        try {
            int id =
                    PercursoController.cadastrarPercurso("0", partida.getCodigo(),
                            chegada.getCodigo(), "2012-12-21 12:21:12", "5", "");
            PercursoController.deletarPercurso(id);
        } catch (Exception e) {
            fail();
        }
    }

}
