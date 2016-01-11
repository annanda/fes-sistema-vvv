package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Constants;
import model.Modal;
import model.Percurso;

public class ModalDAO extends DAO {
	private String tabela = "modais";
	private String tabela_relacionamento = "modais_percursos";

	public int cadastrarModal(Modal novo_modal) {
		int id = 0;
		String codigo = novo_modal.getCodigo();
		String sql_query = insertFactory(
				tabela,
				new String[] {
						novo_modal.getTipo(),
						codigo,
						novo_modal.getCompanhia(),
						"" + novo_modal.getCapacidade(),
						novo_modal.getModelo(),
						"" + novo_modal.getAnoFabricacao(),
						"" + novo_modal.getEmManutencao(),
						"" + novo_modal.getEmUso(),
						Constants.DATE_FORMAT.format(novo_modal
								.getDataManutencao()) });

		connect();
		try {
			statement.executeUpdate(sql_query);
			sql_query = selectFactory(tabela, new String[] { "id_modal" },
					"codigo = '" + codigo + Constants.SINGLE_QUOTE);
			result_set = statement.executeQuery(sql_query);
			if (result_set.first()) {
				id = result_set.getInt("id_modal");

				ArrayList<Percurso> percursos = novo_modal.getPercursos();
				for (Percurso percurso : percursos) {
					sql_query = insertFactory(tabela_relacionamento,
							new String[] { "" + id, "" + percurso.getId() });
					statement.executeUpdate(sql_query);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return id;
	}

	public ArrayList<Modal> listarModais(HashMap<String, String> conditions) {
		String sql_query = selectFactory(tabela,
				new String[] { Constants.ASTERISK }, likeFactory(conditions));
		ArrayList<Modal> modais_encontrados = new ArrayList<Modal>();

		connect();
		try {
			result_set = statement.executeQuery(sql_query);
			while (result_set.next()) {
				int temp_id_modal = result_set.getInt("id_modal");
				String temp_sql_query = selectFactory(tabela_relacionamento,
						new String[] { Constants.ASTERISK }, "id_modal = "
								+ temp_id_modal);
				ResultSet temp_result_set = statement
						.executeQuery(temp_sql_query);
				ArrayList<Percurso> percursos = new ArrayList<Percurso>();

				while (temp_result_set.next()) {
					percursos.add(new Percurso(temp_result_set
							.getInt("id_percurso"), temp_result_set
							.getDate("hora_partida"), temp_result_set
							.getInt("horas_duracao"), temp_result_set
							.getString("codigo_aeroporto"), temp_result_set
							.getInt("id_cidade_partida"), temp_result_set
							.getInt("id_cidade_chegada")));
				}

				modais_encontrados.add(new Modal(temp_id_modal, percursos,
						result_set.getString("tipo"), result_set
								.getString("codigo"), result_set
								.getString("companhia"), result_set
								.getInt("capacidade"), result_set
								.getString("modelo"), result_set
								.getInt("ano_fabricacao"), result_set
								.getBoolean("em_manutencao"), result_set
								.getBoolean("em_uso"), result_set
								.getDate("data_manutencao")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return modais_encontrados;
	}

	public void alterarModal(Modal modal_modificado) { // tabela_relacionamento
		int id = modal_modificado.getId();
		String sql_query = updateFactory(tabela, modal_modificado.toHashMap(),
				"id_modal = " + id);

		connect();
		try {
			statement.executeUpdate(sql_query);

			sql_query = deleteFactory(tabela_relacionamento, "id_modal = " + id);
			statement.executeUpdate(sql_query);

			ArrayList<Percurso> percursos = modal_modificado.getPercursos();
			for (Percurso percurso : percursos) {
				sql_query = insertFactory(tabela_relacionamento, new String[] {
						"" + id, "" + percurso.getId() });
				statement.executeUpdate(sql_query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
	}
}
