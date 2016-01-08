package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Constants;
import model.Usuario;

public class UsuarioDAO extends DAO {
	private String tabela = "usuarios";

	public int cadastrarUsuario(Usuario novo_usuario) {
		int id = 0;
		String email = novo_usuario.getEmail();
		// INSERT INTO usuarios VALUES('int codigo', 'String email', 'String
		// senha', 'int nivel_permissao');
		String sql_query = insertFactory(
				tabela,
				new String[] { "" + novo_usuario.getParentId(), email,
						novo_usuario.getSenha(),
						"" + novo_usuario.getNivelPermissao() });
		connect();
		try {
			statement.executeQuery(sql_query);
			sql_query = selectFactory(tabela, new String[] { "id" },
					"email = '" + email + Constants.SINGLE_QUOTE);
			result_set = statement.executeQuery(sql_query);
			result_set.first();
			id = result_set.getInt("id_usuario");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return id;
	}

	public ArrayList<Usuario> listarUsuarios(HashMap<String, String> conditions) {
		// SELECT usu.*, pes.* FROM usuarios AS usu INNER JOIN pessoas AS pes ON
		// usu.id_pessoa = pes.id_pessoa WHERE conditions.keys[i] LIKE
		// '%conditions.values[i]%' AND ...;
		String sql_query = selectFactory(
				tabela
						+ " AS usu INNER JOIN pessoas AS pes ON usu.id_pessoa = pes.id_pessoa",
				new String[] { "usu.*", "pes.*" }, likeFactory(conditions));
		ArrayList<Usuario> usuarios_encontrados = new ArrayList<Usuario>();

		connect();
		try {
			result_set = statement.executeQuery(sql_query);
			while (result_set.next()) {
				usuarios_encontrados.add(new Usuario(result_set
						.getInt("id_usuario"), result_set.getInt("id_pessoa"),
						result_set.getString("nome"), result_set
								.getString("endereco"), result_set
								.getInt("codigo"), result_set
								.getString("email"), result_set
								.getString("senha"), result_set
								.getInt("nivel_permissao")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return usuarios_encontrados;
	}

	public Usuario getUsuarioByEmail(String email) {
		// SELECT usu.*, pes.* FROM usuarios AS usu INNER JOIN pessoas AS pes ON
		// usu.id_pessoa = pes.id_pessoa WHERE email = 'String email';
		String sql_query = selectFactory(
				tabela
						+ " AS usu INNER JOIN pessoas AS pes ON usu.id_pessoa = pes.id_pessoa",
				new String[] { "usu.*", "pes.*" }, "email = '" + email
						+ Constants.SINGLE_QUOTE);
		Usuario usuario_encontrado = null;

		connect();
		try {
			result_set = statement.executeQuery(sql_query);
			result_set.first();
			usuario_encontrado = new Usuario(result_set.getInt("id_usuario"),
					result_set.getInt("id_pessoa"),
					result_set.getString("nome"),
					result_set.getString("endereco"),
					result_set.getInt("codigo"), result_set.getString("email"),
					result_set.getString("senha"),
					result_set.getInt("nivel_permissao"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return usuario_encontrado;
	}

	public Usuario getUsuarioByCodigo(int codigo) {
		// SELECT usu.*, pes.* FROM usuarios AS usu INNER JOIN pessoas AS pes ON
		// usu.id_pessoa = pes.id_pessoa WHERE codigo = 'int codigo';
		String sql_query = selectFactory(
				tabela
						+ " AS usu INNER JOIN pessoas AS pes ON usu.id_pessoa = pes.id_pessoa",
				new String[] { "usu.*", "pes.*" }, "codigo = '" + codigo
						+ Constants.SINGLE_QUOTE);
		Usuario usuario_encontrado = null;

		connect();
		try {
			result_set = statement.executeQuery(sql_query);
			result_set.first();
			usuario_encontrado = new Usuario(result_set.getInt("id_usuario"),
					result_set.getInt("id_pessoa"),
					result_set.getString("nome"),
					result_set.getString("endereco"),
					result_set.getInt("codigo"), result_set.getString("email"),
					result_set.getString("senha"),
					result_set.getInt("nivel_permissao"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return usuario_encontrado;
	}

	public Usuario getUsuarioById(int id) {
		// SELECT usu.*, pes.* FROM usuarios AS usu INNER JOIN pessoas AS pes ON
		// usu.id_pessoa = pes.id_pessoa WHERE id_usuario = 'int id';
		String sql_query = selectFactory(
				tabela
						+ " AS usu INNER JOIN pessoas AS pes ON usu.id_pessoa = pes.id_pessoa",
				new String[] { "usu.*", "pes.*" }, "id_usuario = '" + id
						+ Constants.SINGLE_QUOTE);
		Usuario usuario_encontrado = null;

		connect();
		try {
			result_set = statement.executeQuery(sql_query);
			result_set.first();
			usuario_encontrado = new Usuario(result_set.getInt("id_usuario"),
					result_set.getInt("id_pessoa"),
					result_set.getString("nome"),
					result_set.getString("endereco"),
					result_set.getInt("codigo"), result_set.getString("email"),
					result_set.getString("senha"),
					result_set.getInt("nivel_permissao"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();

		return usuario_encontrado;
	}

	public void alterarUsuario(Usuario usuario_modificado) {
		// UPDATE usuarios SET id_pessoa = 'int id_pessoa', email = 'String
		// email', senha = 'String senha', nivel_permissao = 'int
		// nivel_permissao' WHERE id_usuario = int id_usuario;
		String sql_query = updateFactory(tabela,
				usuario_modificado.toHashMap(), "id_usuario = "
						+ usuario_modificado.getId());
		connect();
		try {
			statement.executeUpdate(sql_query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
	}

	public void deletarUsuario(int id) {
		// DELETE FROM usuarios WHERE id_usuario = int id;
		String sql_query = deleteFactory(tabela, "id_usuario = " + id);

		connect();
		try {
			statement.executeQuery(sql_query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
	}

	public static void main(String[] args) {
		// UsuarioDAO ud = new UsuarioDAO();
		// Usuario u = new Usuario(1, 1, "Thales", "Etr. Mato Alto", 8001,
		// "thcmatias@gmail.com", "aeho", 3);
		// ud.cadastrarUsuario(u);
		// ud.getUsuarioByEmail("");
		// ud.getUsuarioByCodigo(0);
		// ud.alterarUsuario(u);
		// ud.getUsuarioById(0);
		// ud.deletarUsuario(0);
		// ud.listarUsuarios(new HashMap<String, String>());
	}
}
