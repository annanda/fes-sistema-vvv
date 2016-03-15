package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import controller.PassageiroController;
import controller.ReservaController;
import controller.UsuarioController;
import controller.ViagemController;
import model.Constants;
import model.Passageiro;
import model.Reserva;

public class ReservaDAO extends DAO {
    private String tabela = "reservas";
    private String tabela_relacionamento = "reservas_passageiros";

    public int cadastrarReserva(Reserva nova_reserva) {
        final String id_reserva_label = "id_reserva";
        int id = 0;
        String codigo = nova_reserva.getCodigo();
        String sql_query =
                insertFactory(
                        tabela,
                        new String[] { "" + nova_reserva.getReservante().getId(),
                                "" + nova_reserva.getViagem().getId(), codigo,
                                Constants.DATE_FORMAT.format(nova_reserva.getDataDaReserva()),
                                nova_reserva.getStatus() ? "1" : "0", "" + nova_reserva.getValor(),
                                nova_reserva.getTipoPagamento(), "" + nova_reserva.getQtdParcelas() });

        connect();
        try {
            statement.executeUpdate(sql_query);
            sql_query =
                    selectFactory(tabela, new String[] { id_reserva_label }, "codigo = " + codigo);
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                id = result_set.getInt(id_reserva_label);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return id;
    }

    public int cadastrarReservaPassageiro(int id_reserva, int id_passageiro) {
        int id = 0;
        String sql_query =
                insertFactory(tabela_relacionamento, new String[] { "" + id_reserva,
                        "" + id_passageiro });

        connect();
        try {
            statement.executeUpdate(sql_query);
            sql_query =
                    selectFactory(tabela_relacionamento, new String[] { "id_reserva_passageiro" },
                            "id_reserva = " + id_reserva + " AND id_passageiro = " + id_passageiro);
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                id = result_set.getInt(id);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return id;
    }

    public ArrayList<Reserva> listarReservas(HashMap<String, String> conditions) {
        ArrayList<Reserva> reservas_encontradas = new ArrayList<Reserva>();
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, likeFactory(conditions));

        connect();
        try {
            result_set = statement.executeQuery(sql_query);

            while (result_set.next()) {
                int temp_id_reserva = result_set.getInt("id_reserva");
                reservas_encontradas.add(new Reserva(temp_id_reserva, result_set
                        .getString("codigo"), result_set.getDate("data_da_reserva"), result_set
                        .getBoolean("status"), result_set.getDouble("valor"), result_set
                        .getString("tipo_pagamento"), result_set.getInt("qtd_parcelas"),
                        UsuarioController.getUsuarioById(result_set.getInt("id_usuario")),
                        ReservaController.getListaDePassageirosById(temp_id_reserva),
                        ViagemController.getViagemById(result_set.getInt("id_viagem"))));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return reservas_encontradas;
    }

    public Reserva getReservaByCodigo(String codigo) {
        Reserva reserva_encontrada = null;
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "codigo = " + codigo);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                int temp_id_reserva = result_set.getInt("id_reserva");
                reserva_encontrada =
                        new Reserva(temp_id_reserva, result_set.getString("codigo"),
                                result_set.getDate("data_da_reserva"),
                                result_set.getBoolean("status"), result_set.getDouble("valor"),
                                result_set.getString("tipo_pagamento"),
                                result_set.getInt("qtd_parcelas"),
                                UsuarioController.getUsuarioById(result_set.getInt("id_usuario")),
                                ReservaController.getListaDePassageirosById(temp_id_reserva),
                                ViagemController.getViagemById(result_set.getInt("id_viagem")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return reserva_encontrada;
    }

    public Reserva getReservaById(int id) {
        Reserva reserva_encontrada = null;
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "id_reserva = " + id);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                reserva_encontrada =
                        new Reserva(id, result_set.getString("codigo"),
                                result_set.getDate("data_da_reserva"),
                                result_set.getBoolean("status"), result_set.getDouble("valor"),
                                result_set.getString("tipo_pagamento"),
                                result_set.getInt("qtd_parcelas"),
                                UsuarioController.getUsuarioById(result_set.getInt("id_usuario")),
                                ReservaController.getListaDePassageirosById(id),
                                ViagemController.getViagemById(result_set.getInt("id_viagem")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return reserva_encontrada;
    }

    public ArrayList<Passageiro> getListaDePassageirosById(int id_reserva) {
        final String id_passageiro_label = "id_passageiro";
        ArrayList<Passageiro> lista_de_passageiros_encontrada = new ArrayList<Passageiro>();
        String sql_query =
                selectFactory(tabela_relacionamento, new String[] { id_passageiro_label },
                        "id_reserva = " + id_reserva);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            while (result_set.next()) {
                lista_de_passageiros_encontrada.add(PassageiroController
                        .getPassageiroById(result_set.getInt(id_passageiro_label)));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return lista_de_passageiros_encontrada;
    }

    public void alterarReserva(Reserva reserva_modificada) {
        String sql_query =
                updateFactory(tabela, reserva_modificada.toHashMap(), "id_reserva = "
                        + reserva_modificada.getId());

        connect();
        try {
            statement.executeUpdate(sql_query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();
    }

    public void deletarReserva(int id) {
        String sql_query = deleteFactory(tabela, "id_reserva = " + id);

        connect();
        try {
            statement.executeUpdate(sql_query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();
    }
}
