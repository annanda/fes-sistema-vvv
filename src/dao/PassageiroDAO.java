package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Constants;
import model.Passageiro;

public class PassageiroDAO extends DAO {
	private String tabela = "passageiros";
	private String tabela_joined_pessoas = "passageiros AS pas INNER JOIN pessoas AS pes ON pas.id_pessoa = pes.id_pessoa";

	public int cadastrarPassageiro(Passageiro novo_passageiro) {
		int id = 0;
		String cpf = novo_passageiro.getCpf();
		// INSERT INTO passageiros VALUES('int id_pessoa', 'int id_passageiro_responsavel',
		// 'String cpf', 'String telefone', 'String profissao', 'Date data_de_nascimento');
		String sql_query = insertFactory(
				tabela,
				new String[] {
						Integer.toString(novo_passageiro.getParentId()),
						Integer.toString(novo_passageiro.getResponsavel().getId()),
						novo_passageiro.getCpf(),
						novo_passageiro.getTelefone(),
						novo_passageiro.getProfissao(),
						Constants.DATE_FORMAT.format(novo_passageiro.getDataDeNascimento())
				});
		connect();
		try {
			statement.executeUpdate(sql_query);
			sql_query = selectFactory(tabela, new String[] { "id_passageiro" },
					"cpf = " + Constants.SINGLE_QUOTE + cpf + Constants.SINGLE_QUOTE);
			result_set = statement.executeQuery(sql_query);
			if (result_set.first()) {
				id = result_set.getInt("id_passageiro");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return id;
	}

	public ArrayList<Passageiro> listarPassageiros(HashMap<String, String> conditions) {
		// SELECT pas.*, pes.* FROM passageiros AS pas INNER JOIN pessoas AS pes ON
		// pas.id_pessoa = pes.id_pessoa WHERE conditions.keys[i] LIKE
		// '%conditions.values[i]%' AND ...;
		String sql_query = selectFactory(
				tabela_joined_pessoas,
				new String[] { "pas.*", "pes.*" }, likeFactory(conditions));
		ArrayList<Passageiro> passageiros_encontrados = new ArrayList<Passageiro>();

		connect();
		try {
			result_set = statement.executeQuery(sql_query);
			while (result_set.next()) {
				Passageiro responsavel = null;
				int id_pessoa_responsavel = result_set.getInt("id_pessoa_responsavel");
				if (id_pessoa_responsavel > 0)
					responsavel = getPassageiroById(id_pessoa_responsavel);
				
				passageiros_encontrados.add(new Passageiro(
						result_set.getInt("id_passageiro"),
						result_set.getInt("id_pessoa"),
						result_set.getString("nome"),
						result_set.getString("endereco"),
						result_set.getString("codigo"),
						result_set.getString("cpf"),
						result_set.getString("telefone"),
						result_set.getString("profissao"),
						result_set.getDate("data_de_nascimento"),
						responsavel));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return passageiros_encontrados;
	}

	public Passageiro getPassageiroByCpf(String cpf) {
		// SELECT pas.*, pes.* FROM passageiros AS pas INNER JOIN pessoas AS pes ON
		// pas.id_pessoa = pes.id_pessoa WHERE cpf = 'String cpf';
		String sql_query = selectFactory(
				tabela_joined_pessoas,
				new String[] { "pas.*", "pes.*" },
				"cpf = " + Constants.SINGLE_QUOTE + cpf + Constants.SINGLE_QUOTE);
		Passageiro passageiro_encontrado = null;

		connect();
		try {
			result_set = statement.executeQuery(sql_query);
			if (result_set.first()) {
				Passageiro responsavel = null;
				int id_pessoa_responsavel = result_set.getInt("id_pessoa_responsavel");
				if (id_pessoa_responsavel > 0)
					responsavel = getPassageiroById(id_pessoa_responsavel);
				
				passageiro_encontrado = new Passageiro(
						result_set.getInt("id_passageiro"),
						result_set.getInt("id_pessoa"),
						result_set.getString("nome"),
						result_set.getString("endereco"),
						result_set.getString("codigo"),
						result_set.getString("cpf"),
						result_set.getString("telefone"),
						result_set.getString("profissao"),
						result_set.getDate("data_de_nascimento"),
						responsavel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return passageiro_encontrado;
	}

	public Passageiro getPassageiroByCodigo(String codigo) {
		// SELECT pas.*, pes.* FROM passageiros AS pas INNER JOIN pessoas AS pes ON
		// pas.id_pessoa = pes.id_pessoa WHERE codigo = 'String codigo';
		String sql_query = selectFactory(
				tabela_joined_pessoas,
				new String[] { "pas.*", "pes.*" },
				"codigo = " + Constants.SINGLE_QUOTE + codigo + Constants.SINGLE_QUOTE);
		Passageiro passageiro_encontrado = null;

		connect();
		try {
			result_set = statement.executeQuery(sql_query);
			if (result_set.first()) {
				Passageiro responsavel = null;
				int id_pessoa_responsavel = result_set.getInt("id_pessoa_responsavel");
				if (id_pessoa_responsavel > 0)
					responsavel = getPassageiroById(id_pessoa_responsavel);
				
				passageiro_encontrado = new Passageiro(
						result_set.getInt("id_passageiro"),
						result_set.getInt("id_pessoa"),
						result_set.getString("nome"),
						result_set.getString("endereco"),
						result_set.getString("codigo"),
						result_set.getString("cpf"),
						result_set.getString("telefone"),
						result_set.getString("profissao"),
						result_set.getDate("data_de_nascimento"),
						responsavel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return passageiro_encontrado;
	}

	public Passageiro getPassageiroById(int id) {
		// SELECT pas.*, pes.* FROM passageiros AS pas INNER JOIN pessoas AS pes ON
		// pas.id_pessoa = pes.id_pessoa WHERE id_passageiro = 'int id';
		String sql_query = selectFactory(
				tabela_joined_pessoas,
				new String[] { "pas.*", "pes.*" },
				"id_passageiro = " + Constants.SINGLE_QUOTE + id + Constants.SINGLE_QUOTE);
		Passageiro passageiro_encontrado = null;

		connect();
		try {
			result_set = statement.executeQuery(sql_query);
			if (result_set.first()) {
				Passageiro responsavel = null;
				int id_pessoa_responsavel = result_set.getInt("id_pessoa_responsavel");
				if (id_pessoa_responsavel > 0)
					responsavel = getPassageiroById(id_pessoa_responsavel);
				
				passageiro_encontrado = new Passageiro(
						result_set.getInt("id_passageiro"),
						result_set.getInt("id_pessoa"),
						result_set.getString("nome"),
						result_set.getString("endereco"),
						result_set.getString("codigo"),
						result_set.getString("cpf"),
						result_set.getString("telefone"),
						result_set.getString("profissao"),
						result_set.getDate("data_de_nascimento"),
						responsavel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return passageiro_encontrado;
	}

	public void alterarPassageiro(Passageiro passageiro_modificado) {
		// UPDATE passageiros SET id_pessoa = 'int id_pessoa',
		// id_passageiro_responsavel = 'int id_passageiro_responsavel
		// cpf = 'String cpf', telefone = 'String telefone', profissao = 'String profissao',
		// data_de_nascimento = 'Date data_de_nascimento' WHERE id_passageiro = int id_passageiro;
		String sql_query = updateFactory(tabela,
				passageiro_modificado.toHashMap(),
				"id_passageiro = " + passageiro_modificado.getId());
		connect();
		try {
			statement.executeUpdate(sql_query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
	}

	public void deletarPassageiro(int id) {
		// DELETE FROM passageiros WHERE id_passageiro = int id;
		String sql_query = deleteFactory(tabela, "id_passageiro = " + id);

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
