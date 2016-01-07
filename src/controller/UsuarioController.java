package controller;

import java.util.ArrayList;

import dao.PessoaDAO;
import dao.UsuarioDAO;
import model.Pessoa;
import model.Usuario;

public class UsuarioController {
	public static void cadastrarUsuario(String nome, String endereco,
			String codigo, String email, String senha, String nivel_permissao) {
		// testing if email is unique...
		UsuarioDAO usuario_dao = new UsuarioDAO();
		if ((usuario_dao.getUsuarioByEmail(email)) == null) {
			// treating non-String arguments...
			int int_codigo = Integer.parseInt(codigo);
			int int_nivel_permissao = Integer.parseInt(nivel_permissao);

			// instantiating the new object...
			Usuario novo_usuario = new Usuario(nome, endereco, int_codigo,
					email, senha, int_nivel_permissao);

			// sending it to its respectively DAO class to finally insert it
			// into BD
			PessoaDAO pessoa_dao = new PessoaDAO();

			if ((pessoa_dao.getPessoaByCodigo(int_codigo)) == null) {
				pessoa_dao.cadastrarPessoa((Pessoa) novo_usuario);
			}
			usuario_dao.cadastrarUsuario(novo_usuario);
		} else {
			System.out
					.println("Email ja cadastrado. Usuario pode ja estar cadastrado no sistema");
		}
	}

	public static ArrayList<Usuario> listarUsuarios(String nome_consultado,
			String endereco_consultado, String codigo_consultado,
			String email_consultado) {
		// TODO: Pensar melhor nisso aqui e fazer o que for necessario no
		// UsuarioDAO
		// if all parameters are empty...
		if (Controller.isNullOrEmpty(nome_consultado)
				&& Controller.isNullOrEmpty(endereco_consultado)
				&& Controller.isNullOrEmpty(codigo_consultado)
				&& Controller.isNullOrEmpty(email_consultado)) {
			// ... lists all Usuarios
		} else {
			// using parameters as filters

		}
		return null;
	}

	public static void alterarUsuario(String novo_nome, String novo_endereco,
			String codigo, String novo_email, String nova_senha,
			String novo_nivel_permissao) {
		// testing if email still is unique...
		UsuarioDAO usuario_dao = new UsuarioDAO();
		if (usuario_dao.getUsuarioByEmail(novo_email) == null) {
			// treating non-String arguments...
			int int_codigo = Integer.parseInt(codigo);
			int int_novo_nivel_permissao = Integer
					.parseInt(novo_nivel_permissao);

			// instantiating the modified object...
			Usuario usuario_modificado = new Usuario(novo_nome, novo_endereco,
					int_codigo, novo_email, nova_senha,
					int_novo_nivel_permissao);

			// sending it to DAO class to finally insert it into BD
			PessoaDAO pessoa_dao = new PessoaDAO();

			pessoa_dao.alterarPessoa((Pessoa) usuario_modificado);
			usuario_dao.alterarUsuario(usuario_modificado);
		} else {
			System.out.println("Email ja cadastrado. Escolha outro, por favor");
		}
	}

	public static void deletarUsuario(int id) {
		UsuarioDAO usuario_dao = new UsuarioDAO();
		if (usuario_dao.getUsuarioById(id) != null) {
			usuario_dao.deletarUsuario(id);
		} else {
			System.out.println("Usuario nao encontrado");
		}
	}
}