package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import controller.PercursoController;
import model.Constants;
import model.Modal;

public class ModalDAO extends DAO {
    final private String tabela = "modais";

    public int cadastrarModal(Modal novo_modal) {
        final String id_modal_label = "id_modal";
        int id = 0;
        String codigo = novo_modal.getCodigo();
        String sql_query =
                insertFactory(
                        tabela,
                        new String[] { novo_modal.getTipo(), codigo, novo_modal.getCompanhia(),
                                "" + novo_modal.getCapacidade(), novo_modal.getModelo(),
                                "" + novo_modal.getAnoFabricacao(),
                                "" + novo_modal.getEmManutencao(), "" + novo_modal.getEmUso(),
                                Constants.DATETIME_FORMAT.format(novo_modal.getDataManutencao()) });

        connect();
        try {
            statement.executeUpdate(sql_query);
            sql_query =
                    selectFactory(tabela, new String[] { id_modal_label }, "codigo = '" + codigo
                            + Constants.SINGLE_QUOTE);
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                id = result_set.getInt(id_modal_label);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return id;
    }

    public ArrayList<Modal> listarModais(HashMap<String, String> conditions) {
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, likeFactory(conditions));
        ArrayList<Modal> modais_encontrados = new ArrayList<Modal>();

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            while (result_set.next()) {
                int id_modal = result_set.getInt("id_modal");
                modais_encontrados.add(new Modal(id_modal, PercursoController
                        .getPercursoByModal(id_modal), result_set.getString("tipo"), result_set
                        .getString("codigo"), result_set.getString("companhia"), result_set
                        .getInt("capacidade"), result_set.getString("modelo"), result_set
                        .getInt("ano_fabricacao"), result_set.getInt("em_manutencao"), result_set
                        .getInt("em_uso"), result_set.getDate("data_manutencao")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return modais_encontrados;
    }

    public void alterarModal(Modal modal_modificado) {
        int id = modal_modificado.getId();
        String sql_query = updateFactory(tabela, modal_modificado.toHashMap(), "id_modal = " + id);

        connect();
        try {
            statement.executeUpdate(sql_query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();
    }

    public Modal getModalById(int id) {
        Modal modal_encontrado = null;
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "id_modal = " + id);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                modal_encontrado =
                        new Modal(id, PercursoController.getPercursoByModal(id),
                                result_set.getString("tipo"), result_set.getString("codigo"),
                                result_set.getString("companhia"), result_set.getInt("capacidade"),
                                result_set.getString("modelo"),
                                result_set.getInt("ano_fabricacao"),
                                result_set.getInt("em_manutencao"), result_set.getInt("em_uso"),
                                result_set.getDate("data_manutencao"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return modal_encontrado;
    }

    public Modal getModalByCodigo(String codigo) {
        Modal modal_encontrado = null;
        String sql_query =
                selectFactory(tabela, new String[] { Constants.ASTERISK }, "codigo = " + codigo);

        connect();
        try {
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) {
                int id = result_set.getInt("id_modal");
                modal_encontrado =
                        new Modal(id, PercursoController.getPercursoByModal(id),
                                result_set.getString("tipo"), codigo,
                                result_set.getString("companhia"), result_set.getInt("capacidade"),
                                result_set.getString("modelo"),
                                result_set.getInt("ano_fabricacao"),
                                result_set.getInt("em_manutencao"), result_set.getInt("em_uso"),
                                result_set.getDate("data_manutencao"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return modal_encontrado;
    }

    public void deletarModal(int id) {
        String condition = "id_modal = " + id;
        String sql_query = deleteFactory(tabela, condition);

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
