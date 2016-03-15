package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Constants;
import model.Percurso;

public class PercursoDAO extends DAO {
    private String tabela = "percursos";

    public int cadastrarPercurso(Percurso novo_percurso) {
        int id = 0;
        String codigo = novo_percurso.getCodigo();
        String sql_query =
                insertFactory(
                        tabela,
                        new String[] {
                                ((novo_percurso.getModal() != null) ? ""
                                        + novo_percurso.getModal().getId() : null),
                                "" + novo_percurso.getPartida().getId(),
                                "" + novo_percurso.getDestino().getId(),
                                Constants.DATETIME_FORMAT.format(novo_percurso.getHoraPartida()),
                                "" + novo_percurso.getHorasDuracaoPercurso(),
                                novo_percurso.getCodigoAeroporto(), codigo });

        connect();
        try {
            statement.executeUpdate(sql_query);
            sql_query =
                    selectFactory(tabela, new String[] { "id_percurso" }, "codigo = '" + codigo
                            + Constants.SINGLE_QUOTE);
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                id = result_set.getInt("id_percurso");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return id;
    }

    public ArrayList<Percurso> listarPercursos(HashMap<String, String> conditions) {
        ArrayList<Percurso> percursos_encontrados = new ArrayList<Percurso>();
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, likeFactory(conditions));

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            while (result_set.next()) {
                percursos_encontrados.add(new Percurso(result_set.getInt("id_percurso"), result_set
                        .getInt("id_modal"), result_set.getTimestamp("hora_partida"), result_set
                        .getInt("horas_duracao"), result_set.getString("codigo_aeroporto"),
                        result_set.getInt("id_cidade_partida"), result_set
                                .getInt("id_cidade_chegada"), result_set.getString("codigo")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return percursos_encontrados;
    }

    public ArrayList<Percurso> getPercursoByModal(int id_modal) {
        ArrayList<Percurso> percursos_encontrados = new ArrayList<Percurso>();
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "id_modal = " + id_modal);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            while (result_set.next()) {
                percursos_encontrados.add(new Percurso(result_set.getInt("id_percurso"), result_set
                        .getInt("id_modal"), result_set.getTimestamp("hora_partida"), result_set
                        .getInt("horas_duracao"), result_set.getString("codigo_aeroporto"),
                        result_set.getInt("id_cidade_partida"), result_set
                                .getInt("id_cidade_chegada"), result_set.getString("codigo")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return percursos_encontrados;
    }

    public Percurso getPercursoByCodigo(String codigo) {
        Percurso percurso_encontrado = null;
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "codigo = '" + codigo
                        + Constants.SINGLE_QUOTE);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                percurso_encontrado =
                        new Percurso(result_set.getInt("id_percurso"),
                                result_set.getInt("id_modal"),
                                result_set.getTimestamp("hora_partida"),
                                result_set.getInt("horas_duracao"),
                                result_set.getString("codigo_aeroporto"),
                                result_set.getInt("id_cidade_partida"),
                                result_set.getInt("id_cidade_chegada"),
                                result_set.getString("codigo"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return percurso_encontrado;
    }

    public Percurso getPercursoById(int id) {
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "id_percurso = " + id);
        Percurso percurso_encontrado = null;

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                percurso_encontrado =
                        new Percurso(result_set.getInt("id_percurso"),
                                result_set.getInt("id_modal"),
                                result_set.getTimestamp("hora_partida"),
                                result_set.getInt("horas_duracao"),
                                result_set.getString("codigo_aeroporto"),
                                result_set.getInt("id_cidade_partida"),
                                result_set.getInt("id_cidade_chegada"),
                                result_set.getString("codigo"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return percurso_encontrado;
    }

    public void deletarPercurso(int id) {
        String sql_query = deleteFactory(tabela, "id_percurso = " + id);

        connect();
        try {
            statement.executeUpdate(sql_query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();
    }

    public void alterarPercurso(Percurso percurso_modificado) {
        String sql_query =
                updateFactory(tabela, percurso_modificado.toHashMap(), "id_percurso = "
                        + percurso_modificado.getId());

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
