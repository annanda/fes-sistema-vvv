package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Constants;
import model.PontoDeVenda;

public class PontoDeVendaDAO extends DAO {
    final private String tabela = "pontos_de_venda";

    public int cadastrarPontoDeVenda(PontoDeVenda ponto_de_venda) {
        int id = 0;
        String codigo = ponto_de_venda.getCodigo();
        String sql_query =
                insertFactory(tabela,
                        new String[] { ponto_de_venda.getNome(), ponto_de_venda.getEndereco(),
                                ponto_de_venda.getTelefone(), codigo, ponto_de_venda.getCnpj() });

        connect();
        try {
            statement.executeUpdate(sql_query);
            sql_query =
                    selectFactory(tabela, new String[] { "id_ponto_de_venda" }, "codigo = '"
                            + codigo + Constants.SINGLE_QUOTE);
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                id = result_set.getInt("id_ponto_de_venda");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return id;
    }

    public ArrayList<PontoDeVenda> listarPontosDeVenda(HashMap<String, String> conditions) {
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, likeFactory(conditions));
        ArrayList<PontoDeVenda> pontos_de_venda_encontrados = new ArrayList<PontoDeVenda>();

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            while (result_set.next()) {
                pontos_de_venda_encontrados.add(new PontoDeVenda(result_set
                        .getInt("id_ponto_de_venda"), result_set.getString("nome"), result_set
                        .getString("endereco"), result_set.getString("telefone"), result_set
                        .getString("codigo"), result_set.getString("cnpj")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return pontos_de_venda_encontrados;
    }

    public PontoDeVenda getPontoDeVendaById(int id) {
        PontoDeVenda ponto_de_venda_encontrado = null;
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "id_ponto_de_venda = "
                        + id);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                ponto_de_venda_encontrado =
                        new PontoDeVenda(result_set.getInt("id_ponto_de_venda"),
                                result_set.getString("nome"), result_set.getString("endereco"),
                                result_set.getString("telefone"), result_set.getString("codigo"),
                                result_set.getString("cnpj"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return ponto_de_venda_encontrado;
    }

    public PontoDeVenda getPontoDeVendaByCodigo(String codigo) {
        PontoDeVenda ponto_de_venda_encontrado = null;
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "codigo = '" + codigo
                        + Constants.SINGLE_QUOTE);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                ponto_de_venda_encontrado =
                        new PontoDeVenda(result_set.getInt("id_ponto_de_venda"),
                                result_set.getString("nome"), result_set.getString("endereco"),
                                result_set.getString("telefone"), result_set.getString("codigo"),
                                result_set.getString("cnpj"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return ponto_de_venda_encontrado;
    }

    public void alterarPontoDeVenda(PontoDeVenda ponto_de_venda_modificado) {
        String sql_query =
                updateFactory(tabela, ponto_de_venda_modificado.toHashMap(), "id_ponto_de_venda = "
                        + ponto_de_venda_modificado.getId());

        connect();
        try {
            statement.executeUpdate(sql_query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();
    }

    public void deletarPontoDeVenda(int id) {
        String sql_query = deleteFactory(tabela, "id_ponto_de_venda = " + id);

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
