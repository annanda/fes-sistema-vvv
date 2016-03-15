package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Cidade;
import model.Constants;
import model.Modal;
import model.Viagem;

import org.junit.*;

import controller.CidadeController;
import controller.ModalController;
import controller.PercursoController;
import controller.ViagemController;

public class TestViagem {

    String[] ids_percursos;
    Cidade partida, chegada;

    @Before
    public void init() {
        partida =
                CidadeController.getCidadeById(CidadeController.cadastrarCidade("Rio de Janeiro",
                        "RDJ", "222"));
        chegada =
                CidadeController.getCidadeById(CidadeController.cadastrarCidade("Sao Paulo", "SPA",
                        "paradepois"));
        ids_percursos = new String[3];

        for (int i = 0; i < 3; i++) {
            String temp_codigo_modal =
                    ModalController.getModalById(
                            ModalController.cadastrarModal("onibus", "busao" + i,
                                    "delta_transportadoras", "" + (10 + i * 10), "busao normal",
                                    "180" + i, "0", "0", "1900-01-0" + (i + 1))).getCodigo();
            ids_percursos[i] =
                    ""
                            + PercursoController.cadastrarPercurso(temp_codigo_modal,
                                    partida.getCodigo(), chegada.getCodigo(), "2012-12-2" + i
                                            + " 12:21:12", "6", "");
        }
    }

    @After
    public void finish() {
        for (int i = 0; i < 3; i++) {
            int temp_id_percurso = Integer.parseInt(ids_percursos[i]);
            Modal temp_modal = PercursoController.getPercursoById(temp_id_percurso).getModal();
            if (temp_modal != null) {
                ModalController.deletarModal(temp_modal.getId());
            }
            PercursoController.deletarPercurso(temp_id_percurso);
        }
        CidadeController.deletarCidade(partida.getId());
        CidadeController.deletarCidade(chegada.getId());
    }

    @Test
    public void cadastrarViagem() {
        int id = 0;
        try {
            id = ViagemController.cadastrarViagem("pacote maneiro", ids_percursos).get("id_viagem");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        } finally {
            if (id > 0) {
                ViagemController.deletarViagem(id);
            }
        }
    }

    @Test
    public void listarViagens() {
        int id = ViagemController.cadastrarViagem("pacote maneiro", ids_percursos).get("id_viagem");
        System.out.println();
        ArrayList<Viagem> l =
                ViagemController.listarViagens("pacote maneiro", "2012-12-20 12:21:12",
                        Constants.DATETIME_FORMAT.format(ViagemController.getViagemById(id)
                                .getDataChegada()));
        System.out.println();
        ViagemController.deletarViagem(id);
        assertNotNull(l);
        assertEquals(l.size(), 1);
    }

    @Test
    public void alterarViagem() {
        int id = 0;
        try {
            id = ViagemController.cadastrarViagem("pacote maneiro", ids_percursos).get("id_viagem");
            ViagemController.alterarViagem(id, "pacote bacana", "70", ids_percursos);
        } catch (Exception e) {
            fail();
        } finally {
            ViagemController.deletarViagem(id);
        }
    }

    @Test
    public void deletarViagem() {
        try {
            int id =
                    ViagemController.cadastrarViagem("pacote maneiro", ids_percursos).get(
                            "id_viagem");
            ViagemController.deletarViagem(id);
        } catch (Exception e) {
            fail();
        }
    }
}
