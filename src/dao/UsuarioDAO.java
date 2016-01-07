package dao;

import java.sql.SQLException;

import model.Usuario;

public class UsuarioDAO extends DAO {
	public void cadastrarUsuario(Usuario novo_usuario) {
		String sql_query = "INSERT INTO Usuario VALUES('";
		connect();
		try {
			result_set = statement.executeQuery(sql_query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
	}

	public Usuario getUsuarioByEmail(String email) {
		connect();
		// TODO: Buscar um objeto Usuario pelo email passado
		disconnect();
		return null;
	}

	public Usuario getUsuarioByCodigo(int codigo) {
		connect();
		// TODO: Buscar um objeto Usuario pelo codigo passado
		disconnect();
		return null;
	}

	public void alterarUsuario(Usuario usuario_modificado) {
		connect();
		// TODO: Alterar o objeto no banco de acordo com o passado
		disconnect();
	}

	public Usuario getUsuarioById(int id) {
		connect();
		// TODO: Buscar um objeto Usuario pelo id passado
		disconnect();
		return null;
	}

	public void deletarUsuario(int id) {
		connect();
		// TODO: Deletar o objeto correspondente ao id passado
		disconnect();
	}
	
	public static void main(String[] args) {
		UsuarioDAO ud = new UsuarioDAO();
		ud.cadastrarUsuario(null);
		ud.getUsuarioByEmail(null);
		ud.getUsuarioByCodigo(0);
		ud.alterarUsuario(null);
		ud.getUsuarioById(0);
		ud.deletarUsuario(0);
	}
}
