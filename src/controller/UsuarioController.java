package controller;

import java.util.ArrayList;
import java.util.HashMap;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioController {
  public static int cadastrarUsuario(String nome, String endereco, String codigo, String email,
      String senha, String nivel_permissao) {
    int id = 0;
    UsuarioDAO usuario_dao = new UsuarioDAO();

    Usuario novo_usuario = usuario_dao.getUsuarioByEmail(email);

    // testing if email is unique...
    if (novo_usuario == null) {
      // treating non-String arguments...
      int int_nivel_permissao = Integer.parseInt(nivel_permissao);

      // trying to insert a new Pessoa (if it doesn't exist before)
      int id_pessoa = PessoaController.cadastrarPessoa(nome, endereco, codigo);

      // instantiating the new object...
      novo_usuario =
          new Usuario(0, id_pessoa, nome, endereco, codigo, email, senha, int_nivel_permissao);

      // sending it to DAO class to finally insert it into BD
      id = usuario_dao.cadastrarUsuario(novo_usuario);
    } else {
      id = novo_usuario.getId();
      System.out.println("Email ja cadastrado. Usuario pode ja estar cadastrado no sistema");
    }

    return id;
  }

  public static ArrayList<Usuario> listarUsuarios(String nome_consultado,
      String endereco_consultado, String codigo_consultado, String email_consultado) {
    // setting up the filters...
    HashMap<String, String> conditions = new HashMap<String, String>();
    conditions.put("nome", nome_consultado);
    conditions.put("endereco", endereco_consultado);
    conditions.put("codigo", codigo_consultado);
    conditions.put("email", email_consultado);

    // sending it to DAO class to finally insert it into BD
    UsuarioDAO usuario_dao = new UsuarioDAO();

    return usuario_dao.listarUsuarios(conditions);
  }

  public static void alterarUsuario(int id_usuario, String novo_nome, String novo_endereco,
      String novo_codigo, String novo_email, String nova_senha, String novo_nivel_permissao) {
    UsuarioDAO usuario_dao = new UsuarioDAO();

    // treating non-String arguments...
    int int_novo_nivel_permissao = Integer.parseInt(novo_nivel_permissao);

    // instantiating the modified object...
    Usuario usuario_modificado =
        new Usuario(id_usuario, usuario_dao.getUsuarioById(id_usuario).getParentId(), novo_nome,
            novo_endereco, novo_codigo, novo_email, nova_senha, int_novo_nivel_permissao);

    PessoaController.alterarPessoa(usuario_modificado.getParentId(), usuario_modificado.getNome(),
        usuario_modificado.getEndereco(), usuario_modificado.getCodigo());

    // sending it to DAO class to finally insert it into BD
    usuario_dao.alterarUsuario(usuario_modificado);
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
