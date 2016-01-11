package dao;

import java.sql.SQLException;

import model.Constants;
import model.Modal;

public class ModalDAO extends DAO {
	private String tabela = "modais";

	public int cadastrarModal(Modal novo_modal) {
		int id = 0;
		String sql_query = insertFactory(
				tabela,
				new String[] { "" + novo_modal.getIdPercurso(),
						novo_modal.getTipo(), novo_modal.getCodigo(),
						novo_modal.getCompanhia(),
						"" + novo_modal.getCapacidade(),
						novo_modal.getModelo(),
						"" + novo_modal.getAnoFabricacao(),
						"" + novo_modal.getEmManutencao(),
						"" + novo_modal.getEmUso(),
						novo_modal.getDataManutencao().toString() });

		connect();
		try {
			statement.executeUpdate(sql_query);
			sql_query = selectFactory(tabela, new String[] { "id_modal" },
					"codigo = '" + novo_modal.getCodigo()
							+ Constants.SINGLE_QUOTE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return id;
	}
}
