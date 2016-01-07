package dao;

import model.Pessoa;

public class PessoaDAO extends DAO {
	public Pessoa getPessoaByCodigo(int codigo) {
		connect();
		// TODO: Buscar um objeto Pessoa pelo codigo passado
		disconnect();
		return null;
	}

	public void cadastrarPessoa(Pessoa nova_pessoa) {
		connect();
		// TODO: Cadastrar o objeto passado
		disconnect();
	}

	public void alterarPessoa(Pessoa pessoa_modificada) {
		connect();
		// TODO: Alterar o objeto no banco de acordo com o passado
		disconnect();
	}
}
