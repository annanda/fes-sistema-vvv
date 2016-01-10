package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Constants;
import model.Pessoa;

public class PessoaDAO extends DAO {
	private String tabela = "pessoas";

	public int cadastrarPessoa(Pessoa nova_pessoa) {
		int id = 0;
		String sql_query = insertFactory(
				tabela,
				new String[] {
						nova_pessoa.getNome(),
						nova_pessoa.getEndereco(),
						nova_pessoa.getCodigo()
				});

		connect();
		try {
			statement.executeUpdate(sql_query);
			sql_query = selectFactory(tabela, new String[] { "id_pessoa" },
					"codigo = " + Constants.SINGLE_QUOTE +
						nova_pessoa.getCodigo() + Constants.SINGLE_QUOTE);
			result_set = statement.executeQuery(sql_query);
			if (result_set.first()) {
				id = result_set.getInt("id_pessoa");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return id;
	}

	public ArrayList<Pessoa> listarPessoas(HashMap<String, String> conditions) {
		String sql_query = selectFactory(
				tabela + " AS usu INNER JOIN pessoas AS pes ON usu.id_pessoa = pes.id_pessoa",
				new String[] { "usu.*", "pes.*" }, likeFactory(conditions));
		ArrayList<Pessoa> pessoas_encontradas = new ArrayList<Pessoa>();

		connect();
		try {
			result_set = statement.executeQuery(sql_query);
			while (result_set.next()) {
				pessoas_encontradas.add(new Pessoa(
						result_set.getInt("id_pessoa"),
						result_set.getString("nome"),
						result_set.getString("email"),
						result_set.getString("codigo")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return pessoas_encontradas;
	}

	public Pessoa getPessoaByCodigo(String codigo) {
		Pessoa pessoa_encontrada = null;
		String sql_query = selectFactory(tabela,
				new String[] { Constants.ASTERISK },
				"codigo = " + Constants.SINGLE_QUOTE + codigo + Constants.SINGLE_QUOTE);

		connect();
		try {
			result_set = statement.executeQuery(sql_query);
			if (result_set.first()) {
				pessoa_encontrada = new Pessoa(
						result_set.getInt("id_pessoa"),
						result_set.getString("nome"),
						result_set.getString("endereco"),
						result_set.getString("codigo"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return pessoa_encontrada;
	}

	public Pessoa getPessoaById(int id) {
		Pessoa pessoa_encontrada = null;
		String sql_query = selectFactory(tabela,
				new String[] { Constants.ASTERISK }, "id_pessoa = " + id);

		connect();
		try {
			result_set = statement.executeQuery(sql_query);
			if (result_set.first()) {
				pessoa_encontrada = new Pessoa(
						result_set.getInt("id_pessoa"),
						result_set.getString("nome"),
						result_set.getString("endereco"),
						result_set.getString("codigo"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return pessoa_encontrada;
	}

	public void alterarPessoa(Pessoa pessoa_modificada) {
		String sql_query = updateFactory(tabela, pessoa_modificada.toHashMap(),
				"id_pessoa = " + pessoa_modificada.getId());

		connect();
		try {
			statement.executeUpdate(sql_query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
	}

	public void deletarPessoa(int id) {
		String sql_query = deleteFactory(tabela, "id_pessoa = " + id);

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
