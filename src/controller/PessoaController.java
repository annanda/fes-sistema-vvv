package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Pessoa;
import dao.PessoaDAO;

public class PessoaController {
  public static int cadastrarPessoa(String nome, String endereco, String codigo) {
    PessoaDAO pessoa_dao = new PessoaDAO();
    Pessoa nova_pessoa;
    int id;

    if ((nova_pessoa = pessoa_dao.getPessoaByCodigo(codigo)) == null) {
      nova_pessoa = new Pessoa(nome, endereco, codigo);
      id = pessoa_dao.cadastrarPessoa(nova_pessoa);
    } else {
      id = nova_pessoa.getId();
    }

    return id;
  }

  public static ArrayList<Pessoa> listarPessoas(String nome_consultado, String endereco_consultado,
      String codigo_consultado) {
    // setting up the filters...
    HashMap<String, String> conditions = new HashMap<String, String>();
    conditions.put("nome", nome_consultado);
    conditions.put("endereco", endereco_consultado);
    conditions.put("codigo", codigo_consultado);

    // sending it to DAO class to finally insert it into BD
    PessoaDAO pessoa_dao = new PessoaDAO();

    return pessoa_dao.listarPessoas(conditions);
  }

  public static void alterarPessoa(int id_pessoa, String novo_nome, String novo_endereco,
      String novo_codigo) {
    PessoaDAO pessoa_dao = new PessoaDAO();

    Pessoa pessoa_modificada = new Pessoa(id_pessoa, novo_nome, novo_endereco, novo_codigo);

    pessoa_dao.alterarPessoa(pessoa_modificada);
  }

  public static void deletarPessoa(int id) {
    PessoaDAO pessoa_dao = new PessoaDAO();
    if (pessoa_dao.getPessoaById(id) != null) {
      pessoa_dao.deletarPessoa(id);
    } else {
      System.out.println("Pessoa nao encontrada");
    }
  }
}
