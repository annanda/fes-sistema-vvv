package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Cidade;
import model.Passageiro;
import model.Reserva;
import model.Ticket;

import org.junit.*;

import controller.CidadeController;
import controller.ModalController;
import controller.PassageiroController;
import controller.PercursoController;
import controller.ReservaController;
import controller.TicketController;
import controller.UsuarioController;
import controller.ViagemController;

public class TestTicket {

    int id_percurso;
    Reserva reserva;
    Passageiro passageiro;
    Cidade partida, chegada;

    @Before
    public void init() {
        partida =
                CidadeController.getCidadeById(CidadeController.cadastrarCidade("Rio de Janeiro",
                        "RDJ", "222"));
        chegada =
                CidadeController.getCidadeById(CidadeController.cadastrarCidade("Sao Paulo", "SPA",
                        "paradepois"));
        id_percurso =
                PercursoController.cadastrarPercurso(
                        ""
                                + ModalController.cadastrarModal("onibus", "busao1",
                                        "delta_transportadoras", "10", "busao normal", "1803",
                                        "false", "false", "1900-01-01"), partida.getCodigo(),
                        chegada.getCodigo(), "2012-12-21 12:21:12", "6", "");
        passageiro =
                PassageiroController.getPassageiroById(PassageiroController.cadastrarPassageiro(
                        "beltrano", "Lagoa Azul", "passageirocodehu3", "00000000000", "99999999",
                        "pedreiro", "1960-12-21", ""));
        reserva =
                ReservaController.getReservaById(ReservaController.cadastrarReserva(
                        "code",
                        1,
                        "amex",
                        123.45,
                        true,
                        new String[] { "" + passageiro.getId() },
                        UsuarioController.cadastrarUsuario("fulano", "estrada do morro alto",
                                "usercode", "surfista@bol.com.br", "123", ""),
                        ViagemController.cadastrarViagem("very fun package",
                                new String[] { "" + id_percurso }).get("id_viagem")).get(
                        "id_reserva"));
    }

    @After
    public void finish() {
        CidadeController.deletarCidade(partida.getId());
        CidadeController.deletarCidade(chegada.getId());
        ModalController.deletarModal(PercursoController.getPercursoById(id_percurso).getModal()
                .getId());
        PercursoController.deletarPercurso(id_percurso);
        ViagemController.deletarViagem(reserva.getViagem().getId());
        UsuarioController.deletarUsuario(reserva.getReservante().getId());
        ReservaController.deletarReserva(reserva.getId());
        PassageiroController.deletarPassageiro(passageiro.getId());
    }

    @Test
    public void cadastrarTicket() {
        int id = 0;
        try {
            id = TicketController.cadastrarTicket(reserva.getId(), id_percurso, 1369, "LOCALIZEI");
        } catch (Exception e) {
            fail();
        } finally {
            if (id > 0) {
                TicketController.deletarTicket(id);
            }
        }
    }

    @Test
    public void listarTicket() {
        int id = TicketController.cadastrarTicket(reserva.getId(), id_percurso, 1369, "LOCALIZEI");
        ArrayList<Ticket> l = TicketController.listarTickets(reserva.getId(), id_percurso, 1369);
        TicketController.deletarTicket(id);
        assertNotNull(l);
        assertEquals(l.size(), 1);
    }

    @Test
    public void deletarTicket() {
        try {
            int id =
                    TicketController.cadastrarTicket(reserva.getId(), id_percurso, 1369,
                            "LOCALIZEI");
            TicketController.deletarTicket(id);
        } catch (Exception e) {
            fail();
        }
    }
}
