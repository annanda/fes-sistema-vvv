package controller;

import java.util.ArrayList;
import java.util.HashMap;

import dao.CidadeDAO;
import model.Cidade;

public class CidadeController {
	public static int cadastrarCidade(String nome, String identificador,
			String codigo) {
		int id = 0;
		CidadeDAO cidade_dao = new CidadeDAO();
		Cidade nova_cidade;

		if ((nova_cidade = cidade_dao.getCidadeByCodigo(codigo)) == null) {
			nova_cidade = new Cidade(nome, identificador, codigo);
			id = cidade_dao.cadastrarCidade(nova_cidade);
		} else {
			System.out.println("Codigo de cidade ja cadastrado no sistema");
		}

		return id;
	}

	public static ArrayList<Cidade> listarCidades(String nome_consultado,
			String identificador_consultado, String codigo_consultado) {
		// setting up the filters...
		HashMap<String, String> conditions = new HashMap<String, String>();
		conditions.put("nome", nome_consultado);
		conditions.put("endereco", identificador_consultado);
		conditions.put("codigo", codigo_consultado);

		// sending it to DAO class to finally insert it into BD
		CidadeDAO cidade_dao = new CidadeDAO();

		return cidade_dao.listarCidades(conditions);
	}

	public static void alterarCidade(int id_cidade, String novo_nome,
			String novo_identificador, String novo_codigo) {
		CidadeDAO cidade_dao = new CidadeDAO();

		Cidade cidade_modificada = new Cidade(id_cidade, novo_nome,
				novo_identificador, novo_codigo);

		cidade_dao.alterarCidade(cidade_modificada);
	}

	public static void deletarCidade(int id) {
		CidadeDAO cidade_dao = new CidadeDAO();
		if (cidade_dao.getCidadeById(id) != null) {
			cidade_dao.deletarCidade(id);
		} else {
			System.out.println("Pessoa nao encontrada");
		}
	}
}
