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
                    new Usuario(0, id_pessoa, nome, endereco, codigo, email, senha,
                            int_nivel_permissao);

            // sending it to DAO class to finally insert it into BD
            id = usuario_dao.cadastrarUsuario(novo_usuario);
        } else {
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

    public static Usuario getUsuarioById(int id) {
        UsuarioDAO usuario_dao = new UsuarioDAO();

        return usuario_dao.getUsuarioById(id);
    }

    public static Usuario getUsuarioByEmail(String email) {
        UsuarioDAO usuario_dao = new UsuarioDAO();

        return usuario_dao.getUsuarioByEmail(email);
    }

    public static void alterarUsuario(int id, String nome_modificado, String endereco_modificado,
            String email_modificado, String senha_modificada, String nivel_permissao_modificado) {
        UsuarioDAO usuario_dao = new UsuarioDAO();

        // instantiating the modified object...
        Usuario usuario_modificado = getUsuarioById(id);
        // testing if what is about to be modified is in database...
        if (usuario_modificado != null) {
            if (email_modificado.equalsIgnoreCase(usuario_modificado.getEmail())
                    || getUsuarioByEmail(email_modificado) == null) {
                // treating non-String arguments...
                int int_nivel_permissao_modificado = Integer.parseInt(nivel_permissao_modificado);

                // modifying...
                usuario_modificado.setEmail(email_modificado);
                usuario_modificado.setEndereco(endereco_modificado);
                usuario_modificado.setNivelPermissao(int_nivel_permissao_modificado);
                usuario_modificado.setNome(nome_modificado);
                if (senha_modificada != null)
                    usuario_modificado.setSenha(senha_modificada);

                // updating pessoa related to this usuario
                PessoaController.alterarPessoa(usuario_modificado.getParentId(),
                        usuario_modificado.getNome(), usuario_modificado.getEndereco());

                // sending it to DAO class to finally update it in BD
                usuario_dao.alterarUsuario(usuario_modificado);
            } else {
                System.out
                        .println("Email ja esta em uso. Escolha outro email para alterar corretamente");
            }
        } else {
            System.out.println("Usuario nao encontrado para alteracao");
        }
    }

    public static void deletarUsuario(int id) {
        UsuarioDAO usuario_dao = new UsuarioDAO();

        if (usuario_dao.getUsuarioById(id) != null) {
            usuario_dao.deletarUsuario(id);
        } else {
            System.out.println("Usuario nao encontrado para deletar");
        }
    }
}
