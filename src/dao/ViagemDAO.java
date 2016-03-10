package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import controller.PercursoController;
import controller.ViagemController;
import model.Constants;
import model.Percurso;
import model.Viagem;

public class ViagemDAO extends DAO {
    private String tabela = "viagens";
    private String tabela_relacionamento = "viagens_percursos";

    public int cadastrarViagem(Viagem nova_viagem) {
        // setting labels for reusing
        final String id_viagem_label = "id_viagem";
        // setting variables and constants
        int id = 0;
        final String codigo = nova_viagem.getCodigo();
        // query to insert Viagem object in viagens table
        String sql_query =
                insertFactory(
                        tabela,
                        new String[] { nova_viagem.getNomeDoPacote(),
                                "" + nova_viagem.getLotacao(),
                                Constants.DATE_FORMAT.format(nova_viagem.getDataPartida()),
                                Constants.DATE_FORMAT.format(nova_viagem.getDataChegada()), codigo });

        connect();
        try {
            // inserting Viagem object in viagens table
            statement.executeUpdate(sql_query);

            // query to retrieve the new id inserted in viagens table
            sql_query =
                    selectFactory(tabela, new String[] { id_viagem_label }, "codigo = " + codigo);
            // retrieving the new id inserted in viagens table
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) { // testing if the Viagem object was successfully inserted
                id = result_set.getInt(id_viagem_label);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return id;
    }

    public int cadastrarViagemPercurso(int id_viagem, int id_percurso, int ordem) {
        int id = 0;
        String sql_query =
                insertFactory(tabela_relacionamento, new String[] { "" + id_viagem,
                        "" + id_percurso, "" + ordem });

        connect();
        try {
            statement.executeUpdate(sql_query);

            sql_query =
                    selectFactory(tabela_relacionamento, new String[] { "id_viagem_percurso" },
                            "id_viagem = " + id_viagem + " AND id_percurso = " + id_percurso
                                    + " AND ordem = " + ordem);
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

    public ArrayList<Viagem> listarViagens(HashMap<String, String> conditions) {
        ArrayList<Viagem> viagens_encontradas = new ArrayList<Viagem>();
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, likeFactory(conditions));

        connect();
        try {
            result_set = statement.executeQuery(sql_query);

            while (result_set.next()) {
                int temp_id_viagem = result_set.getInt("id_viagem");
                viagens_encontradas.add(new Viagem(temp_id_viagem, result_set
                        .getString("nome_do_pacote"), result_set.getDate("data_partida"),
                        result_set.getDate("data_chegada"), ViagemController
                                .getPlanoDeViagemByViagemId(temp_id_viagem), result_set
                                .getString("codigo")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return viagens_encontradas;
    }

    public Viagem getViagemByCodigo(String codigo) {
        Viagem viagem_encontrada = null;
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "codigo = " + codigo);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                int temp_id_viagem = result_set.getInt("id_viagem");
                viagem_encontrada =
                        new Viagem(temp_id_viagem, result_set.getString("nome_do_pacote"),
                                result_set.getDate("data_partida"),
                                result_set.getDate("data_chegada"),
                                ViagemController.getPlanoDeViagemByViagemId(temp_id_viagem),
                                result_set.getString("codigo"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return viagem_encontrada;
    }

    public Viagem getViagemById(int id) {
        Viagem viagem_encontrada = null;
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "id_viagem = " + id);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                viagem_encontrada =
                        new Viagem(id, result_set.getString("nome_do_pacote"),
                                result_set.getDate("data_partida"),
                                result_set.getDate("data_chegada"),
                                ViagemController.getPlanoDeViagemByViagemId(id),
                                result_set.getString("codigo"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return viagem_encontrada;
    }

    public ArrayList<Percurso> getPlanoDeViagemByViagemId(int id) {
        final String id_percurso_label = "id_percurso";
        ArrayList<Percurso> plano_de_viagem_encontrado = new ArrayList<Percurso>();
        String sql_query =
                selectFactory(tabela_relacionamento, new String[] { id_percurso_label, "ordem" },
                        "id_viagem = " + id);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            while (result_set.next()) {
                plano_de_viagem_encontrado.add(result_set.getInt("ordem"),
                        PercursoController.getPercursoById(result_set.getInt(id_percurso_label)));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return plano_de_viagem_encontrado;
    }

    public void alterarViagem(Viagem viagem_modificada) {
        int id_viagem = viagem_modificada.getId();
        String condition = "id_viagem = " + id_viagem;
        ArrayList<Percurso> plano_de_viagem_modificado = viagem_modificada.getPlanoDeViagem();
        String sql_query = updateFactory(tabela, viagem_modificada.toHashMap(), condition);

        connect();
        try {
            statement.executeUpdate(sql_query);
            sql_query = deleteFactory(tabela_relacionamento, condition);
            statement.executeUpdate(sql_query);
            for (int i = 0; i < plano_de_viagem_modificado.size(); i++) {
                sql_query =
                        insertFactory(tabela_relacionamento, new String[] { "" + id_viagem,
                                "" + plano_de_viagem_modificado.get(i).getId(), "" + i });
                statement.executeUpdate(sql_query);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();
    }

    public void deletarViagem(int id) {
        String sql_query = deleteFactory(tabela, "id_viagem = " + id);

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
