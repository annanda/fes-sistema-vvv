package dao;

import model.Pessoa;

public class PessoaDAO extends DAO {
	public Pessoa getPessoaByCodigo(int codigo) {
		connect();
		// TODO: Buscar um objeto Pessoa pelo codigo passado
		disconnect();
		return null;
	}

	public int cadastrarPessoa(Pessoa nova_pessoa) {
		int id = 0;
		connect();
		// TODO: Cadastrar o objeto passado
		disconnect();
		return id;
	}

	public void alterarPessoa(Pessoa pessoa_modificada) {
		connect();
		// TODO: Alterar o objeto no banco de acordo com o passado
		disconnect();
	}

	public int getId(Pessoa nova_pessoa) {
		// TODO Auto-generated method stub
		return 0;
	}
}
