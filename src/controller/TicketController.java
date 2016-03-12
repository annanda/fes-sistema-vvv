package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Ticket;
import dao.TicketDAO;

public class TicketController {
    public static int cadastrarTicket(int id_reserva, int id_percurso, int numero,
            String id_localizador) {
        int id = 0;
        TicketDAO ticket_dao = new TicketDAO();
        Ticket novo_ticket = getTicketByIdLocalizador(id_localizador);

        if (novo_ticket == null) {
            novo_ticket =
                    new Ticket(numero, id_localizador,
                            ReservaController.getReservaById(id_reserva),
                            PercursoController.getPercursoById(id_percurso));
            id = ticket_dao.cadastrarTicket(novo_ticket);
        } else {
            System.out
                    .println("Id Localizador ja cadastrado. Ticket pode ja ter sido cadastrado no sistema");
        }

        return id;
    }

    public static ArrayList<Ticket> listarTickets(int id_reserva, int id_percurso, int numero) {
        TicketDAO ticket_dao = new TicketDAO();
        HashMap<String, String> conditions = new HashMap<String, String>();

        conditions.put("id_reserva", "" + id_reserva);
        conditions.put("id_percurso", "" + id_percurso);
        conditions.put("numero", "" + numero);

        return ticket_dao.listarTickets(conditions);
    }

    public static Ticket getTicketById(int id) {
        TicketDAO ticket_dao = new TicketDAO();

        return ticket_dao.getTicketById(id);
    }

    public static Ticket getTicketByIdLocalizador(String id_localizador) {
        TicketDAO ticket_dao = new TicketDAO();

        return ticket_dao.getTicketByIdLocalizador(id_localizador);
    }

    // Tickets nao podem ser alterados

    // Tickets nao podem ser deletados
}
