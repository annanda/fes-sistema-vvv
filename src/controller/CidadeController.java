package controller;

import java.util.ArrayList;
import java.util.HashMap;

import dao.CidadeDAO;
import model.Cidade;

public class CidadeController {
    public static int cadastrarCidade(String nome, String identificador, String codigo) {
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
        conditions.put("identificador", identificador_consultado);
        conditions.put("codigo", codigo_consultado);

        // sending it to DAO class to finally insert it into BD
        CidadeDAO cidade_dao = new CidadeDAO();

        return cidade_dao.listarCidades(conditions);
    }

    public static Cidade getCidadeByCodigo(String codigo) {
        CidadeDAO cidade_dao = new CidadeDAO();

        return cidade_dao.getCidadeByCodigo(codigo);
    }

    public static Cidade getCidadeById(int id) {
        CidadeDAO cidade_dao = new CidadeDAO();

        return cidade_dao.getCidadeById(id);
    }

    public static Cidade getCidadeByIdentificador(String identificador) {
        CidadeDAO cidade_dao = new CidadeDAO();

        return cidade_dao.getCidadeByIdentificador(identificador);
    }

    public static void alterarCidade(int id, String nome_modificado, String identificador_modificado) {
        CidadeDAO cidade_dao = new CidadeDAO();

        Cidade cidade_modificada = getCidadeById(id);
        if (cidade_modificada != null) {
            if (identificador_modificado.equals(cidade_modificada.getIdentificador())
                    || getCidadeByIdentificador(identificador_modificado) == null) {
                cidade_modificada.setIdentificador(identificador_modificado);
                cidade_modificada.setNome(nome_modificado);

                cidade_dao.alterarCidade(cidade_modificada);
            } else {
                System.out
                        .println("Identificador ja em uso. Escolha outro identificador para alterar corretamente");
            }
        } else {
            System.out.println("Cidade nao encontrada para alteracao");
        }
    }

    public static void deletarCidade(int id) {
        CidadeDAO cidade_dao = new CidadeDAO();

        if (cidade_dao.getCidadeById(id) != null) {
            cidade_dao.deletarCidade(id);
        } else {
            System.out.println("Cidade nao encontrada para deletar");
        }
    }
}
