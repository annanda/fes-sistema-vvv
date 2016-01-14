package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Constants;
import model.Cidade;

public class CidadeDAO extends DAO {
    private String tabela = "cidades";

    public int cadastrarCidade(Cidade nova_cidade) {
        int id = 0;
        String codigo = nova_cidade.getCodigo();
        String sql_query =
                insertFactory(tabela,
                        new String[] { nova_cidade.getNome(), nova_cidade.getIdentificador(),
                                codigo });

        connect();
        try {
            statement.executeUpdate(sql_query);
            sql_query =
                    selectFactory(tabela, new String[] { "id_cidade" }, "codigo = '" + codigo
                            + Constants.SINGLE_QUOTE);
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                id = result_set.getInt("id_cidade");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return id;
    }

    public ArrayList<Cidade> listarCidades(HashMap<String, String> conditions) {
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, likeFactory(conditions));
        ArrayList<Cidade> cidades_encontradas = new ArrayList<Cidade>();

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            while (result_set.next()) {
                cidades_encontradas.add(new Cidade(result_set.getInt("id_cidade"), result_set
                        .getString("nome"), result_set.getString("identificador"), result_set
                        .getString("codigo")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return cidades_encontradas;
    }

    public Cidade getCidadeByCodigo(String codigo) {
        Cidade cidade_encontrada = null;
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "codigo = '" + codigo
                        + Constants.SINGLE_QUOTE);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                cidade_encontrada =
                        new Cidade(result_set.getInt("id_cidade"), result_set.getString("nome"),
                                result_set.getString("identificador"),
                                result_set.getString("codigo"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return cidade_encontrada;
    }

    public Cidade getCidadeById(int id) {
        Cidade cidade_encontrada = null;
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "id_cidade = " + id);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                cidade_encontrada =
                        new Cidade(result_set.getInt("id_cidade"), result_set.getString("nome"),
                                result_set.getString("identificador"),
                                result_set.getString("codigo"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return cidade_encontrada;
    }

    public Cidade getCidadeByIdentificador(String identificador) {
        Cidade cidade_encontrada = null;
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "identificador = "
                        + identificador);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                cidade_encontrada =
                        new Cidade(result_set.getInt("id_cidade"), result_set.getString("nome"),
                                result_set.getString("identificador"),
                                result_set.getString("codigo"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return cidade_encontrada;
    }

    public void alterarCidade(Cidade cidade_modificada) {
        String sql_query =
                updateFactory(tabela, cidade_modificada.toHashMap(), "id_cidade = "
                        + cidade_modificada.getId());

        connect();
        try {
            statement.executeUpdate(sql_query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();
    }

    public void deletarCidade(int id) {
        String sql_query = deleteFactory(tabela, "id_cidade = " + id);

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
