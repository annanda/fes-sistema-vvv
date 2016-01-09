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
		int codigo = nova_pessoa.getCodigo();
		String sql_query = insertFactory(
				tabela,
				new String[] { nova_pessoa.getNome(),
						nova_pessoa.getEndereco(), "" + nova_pessoa.getCodigo() });

		connect();
		try {
			statement.executeQuery(sql_query);
			sql_query = selectFactory(tabela, new String[] { "id" },
					"codigo = " + codigo);
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
		// TODO Auto-generated method stub
		return null;
	}

	public Pessoa getPessoaByCodigo(int codigo) {
		Pessoa pessoa_encontrada = null;
		String sql_query = selectFactory(tabela,
				new String[] { Constants.ASTERISK }, "codigo = " + codigo);

		connect();
		try {
			result_set = statement.executeQuery(sql_query);
			if (result_set.first()) {
				pessoa_encontrada = new Pessoa(result_set.getInt("id_pessoa"),
						result_set.getString("nome"),
						result_set.getString("endereco"),
						result_set.getInt("codigo"));
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
				pessoa_encontrada = new Pessoa(result_set.getInt("id_pessoa"),
						result_set.getString("nome"),
						result_set.getString("endereco"),
						result_set.getInt("codigo"));
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
			statement.executeQuery(sql_query);
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
			statement.executeQuery(sql_query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
	}
}