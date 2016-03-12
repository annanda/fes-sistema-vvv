package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import model.Constants;
import model.Passageiro;
import model.Percurso;
import model.Reserva;

import org.junit.Before;
import org.junit.Test;

import controller.PassageiroController;
import controller.ReservaController;
import controller.TicketController;

public class TestTicket {

    Reserva temp_reserva;
    Percurso temp_percurso;
    String[] temp_ids_passageiros;

    @Before
    public void init() {
        int i = 0;
        temp_ids_passageiros = new String[5];
        for (String temp_id_passageiro : temp_ids_passageiros) {
            String temp_cpf = (i % 2 > 0) ? "0000000000" + (i - 1) : "";
            temp_id_passageiro =
                    ""
                            + PassageiroController.cadastrarPassageiro("fulano" + i,
                                    "endereco" + i, "codigo" + i, "0000000000" + i, "3456789" + i,
                                    "profissao" + i, "10-03-199" + (8 + i), temp_cpf);
            i++;
        }
        temp_reserva =
                ReservaController.cadastrarReserva("abc", 3, "cartao", 333.1234, true,
                        ids_passageiros);
    }

    @Test
    public void cadastrarTicket() {
        int id = 0;
        try {
            id = TicketController.cadastrarTicket(id_reserva, id_percurso, numero, id_localizador);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {

        }
    }

}
