package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Cidade;
import model.Reserva;

import org.junit.*;

import controller.CidadeController;
import controller.ModalController;
import controller.PassageiroController;
import controller.PercursoController;
import controller.PessoaController;
import controller.ReservaController;
import controller.UsuarioController;
import controller.ViagemController;
import dao.PessoaDAO;

public class TestReserva {

    String[] ids_passageiros;
    int id_reservante, id_viagem;
    Cidade partida, chegada;
    PessoaDAO pessoa_dao;

    @Before
    public void init() {
        partida =
                CidadeController.getCidadeById(CidadeController.cadastrarCidade("Rio de Janeiro",
                        "RDJ", "222"));
        chegada =
                CidadeController.getCidadeById(CidadeController.cadastrarCidade("Sao Paulo", "SPA",
                        "paradepois"));
        ids_passageiros = new String[3];

        for (int i = 0; i < 3; i++) {
            String cpf_responsavel =
                    ((i % 2) > 0) ? PassageiroController.getPassageiroById(
                            Integer.parseInt(ids_passageiros[i - 1])).getCpf() : "";
            ids_passageiros[i] =
                    ""
                            + PassageiroController.cadastrarPassageiro("passageiro" + i,
                                    "rua ibinoia numero " + (i + 1), "pass" + i + "code",
                                    "0000000000" + i, "9999999" + i, "ajudante" + i, "199" + i
                                            + "-1" + i + "-2" + i, cpf_responsavel);
        }

        id_reservante =
                UsuarioController.cadastrarUsuario("fulanis", "rua enderecis", "qualquerunzis",
                        "mussunds@mail.com", "cacilds", "2");

        id_viagem =
                ViagemController.cadastrarViagem(
                        "pacote muito louco vei",
                        new String[] { ""
                                + PercursoController.cadastrarPercurso(
                                        ModalController.getModalById(
                                                ModalController.cadastrarModal("onibus", "busao1",
                                                        "delta_transportadoras", "10",
                                                        "busao normal", "1803", "0", "0",
                                                        "1900-01-01")).getCodigo(), partida
                                                .getCodigo(), chegada.getCodigo(),
                                        "2012-12-21 12:21:12", "6", "") }).get("id_viagem");
    }

    @After
    public void finish() {
        pessoa_dao = new PessoaDAO();
        PessoaController.deletarPessoa(pessoa_dao.getPessoaByCodigo("qualquerunzis").getId());
        for (int i = 0; i < 3; i++) {
            PessoaController.deletarPessoa(pessoa_dao.getPessoaByCodigo("pass" + i + "code")
                    .getId());
        }
        ModalController.deletarModal(ViagemController.getViagemById(id_viagem).getPlanoDeViagem()
                .get(0).getModal().getId());
        PercursoController.deletarPercurso(ViagemController.getViagemById(id_viagem)
                .getPlanoDeViagem().get(0).getId());
        ViagemController.deletarViagem(id_viagem);
        CidadeController.deletarCidade(partida.getId());
        CidadeController.deletarCidade(chegada.getId());
    }

    @Test
    public void cadastrarReserva() {
        int id = 0;
        try {
            id =
                    ReservaController.cadastrarReserva("code", 1, "amex", 123.45, true,
                            ids_passageiros, id_reservante, id_viagem).get("id_reserva");
        } catch (Exception e) {
            fail();
        } finally {
            if (id > 0) {
                ReservaController.deletarReserva(id);
            }
        }
    }

    @Test
    public void listarReservas() {
        int id =
                ReservaController.cadastrarReserva("code", 1, "amex", 123.45, false,
                        ids_passageiros, id_reservante, id_viagem).get("id_reserva");
        ArrayList<Reserva> l =
                ReservaController.listarReservas("" + id_viagem, "" + id_reservante, ""
                        + ReservaController.getReservaById(id).getDataDaReserva(), "0", "amex");
        ReservaController.deletarReserva(id);
        assertNotNull(l);
        assertEquals(l.size(), 1);
    }

    @Test
    public void alterarReserva() {
        int id = 0;
        try {
            id =
                    ReservaController.cadastrarReserva("code", 1, "amex", 123.45, false,
                            ids_passageiros, id_reservante, id_viagem).get("id_reserva");
            ReservaController.alterarReserva(id, true, "amex", 1);
        } catch (Exception e) {
            fail();
        } finally {
            if (id > 0) {
                ReservaController.deletarReserva(id);
            }
        }
    }

    @Test
    public void deletarReserva() {
        try {
            int id =
                    ReservaController.cadastrarReserva("code", 1, "amex", 123.45, false,
                            ids_passageiros, id_reservante, id_viagem).get("id_reserva");
            ReservaController.deletarReserva(id);
        } catch (Exception e) {
            fail();
        }
    }

}
