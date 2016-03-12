package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import controller.PercursoController;
import controller.ReservaController;
import model.Constants;
import model.Ticket;

public class TicketDAO extends DAO {
    final private String tabela = "tickets";

    public int cadastrarTicket(Ticket novo_ticket) {
        final String id_ticket_label = "id_ticket";
        int id = 0;
        String id_localizador = novo_ticket.getIdLocalizador();
        String sql_query =
                insertFactory(tabela, new String[] { "" + novo_ticket.getReserva().getId(),
                        "" + novo_ticket.getPercurso().getId(), "" + novo_ticket.getNumero(),
                        novo_ticket.getIdLocalizador() });

        connect();
        try {
            statement.executeUpdate(sql_query);
            sql_query =
                    selectFactory(tabela, new String[] { id_ticket_label }, "id_localizador = '"
                            + id_localizador + Constants.SINGLE_QUOTE);
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                id = result_set.getInt(id_ticket_label);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return id;
    }

    public ArrayList<Ticket> listarTickets(HashMap<String, String> conditions) {
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, likeFactory(conditions));
        ArrayList<Ticket> tickets_encontrados = new ArrayList<Ticket>();

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            while (result_set.next()) {
                int id_ticket = result_set.getInt("id_ticket");
                tickets_encontrados.add(new Ticket(id_ticket, result_set.getInt("numero"),
                        result_set.getString("id_localizador"), ReservaController
                                .getReservaById(result_set.getInt("id_reserva")),
                        PercursoController.getPercursoById(result_set.getInt("id_percurso"))));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return tickets_encontrados;
    }

    public Ticket getTicketById(int id) {
        Ticket ticket_encontrado = null;
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "id_ticket = " + id);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                ticket_encontrado =
                        new Ticket(
                                result_set.getInt("id_ticket"),
                                result_set.getInt("numero"),
                                result_set.getString("id_localizador"),
                                ReservaController.getReservaById(result_set.getInt("id_reserva")),
                                PercursoController.getPercursoById(result_set.getInt("id_percurso")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return ticket_encontrado;
    }

    public Ticket getTicketByIdLocalizador(String id_localizador) {
        Ticket ticket_encontrado = null;
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "id_localizador = '"
                        + id_localizador + Constants.SINGLE_QUOTE);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                ticket_encontrado =
                        new Ticket(
                                result_set.getInt("id_ticket"),
                                result_set.getInt("numero"),
                                result_set.getString("id_localizador"),
                                ReservaController.getReservaById(result_set.getInt("id_reserva")),
                                PercursoController.getPercursoById(result_set.getInt("id_percurso")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return ticket_encontrado;
    }

}
