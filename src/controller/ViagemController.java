package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Percurso;
import model.Viagem;
import dao.ViagemDAO;

public class ViagemController {
    public static HashMap<String, Integer> cadastrarViagem(String nome_do_pacote,
            String[] ids_percursos) {
        int id_viagem = 0;
        HashMap<String, Integer> ids_map = new HashMap<String, Integer>();
        ViagemDAO viagem_dao = new ViagemDAO();
        ArrayList<Percurso> plano_de_viagem =
                PercursoController.makePlanoDeViagemByIds(ids_percursos);
        Viagem nova_viagem = new Viagem(nome_do_pacote, plano_de_viagem);

        if (viagem_dao.getViagemByCodigo(nova_viagem.getCodigo()) == null) {
            id_viagem = viagem_dao.cadastrarViagem(nova_viagem);
            ids_map.put("id_viagem", id_viagem);

            for (int i = 0; i < plano_de_viagem.size(); i++) {
                int temp_id_percurso = plano_de_viagem.get(i).getId();
                int temp_id_viagem_percurso =
                        cadastrarViagemPercurso(id_viagem, temp_id_percurso, i);
                if (temp_id_viagem_percurso > 0) {
                    ids_map.put("id_percurso" + i, temp_id_percurso);
                }
            }
        } else {
            System.out.println("Viagem ja cadastrada no sistema");
        }

        return ids_map;
    }

    public static int cadastrarViagemPercurso(int id_viagem, int id_percurso, int ordem) {
        int id = 0;
        ViagemDAO viagem_dao = new ViagemDAO();

        id = viagem_dao.cadastrarViagemPercurso(id_viagem, id_percurso, ordem);

        return id;
    }

    public static ArrayList<Viagem> listarViagens(String nome_do_pacote_consultado,
            String data_partida_consultada, String data_chegada_consultada) {
        HashMap<String, String> conditions = new HashMap<String, String>();
        ViagemDAO viagem_dao = new ViagemDAO();

        conditions.put("nome_do_pacote", nome_do_pacote_consultado);
        conditions.put("data_partida", data_partida_consultada);
        conditions.put("data_chegada", data_chegada_consultada);

        return viagem_dao.listarViagens(conditions);
    }

    public static Viagem getViagemById(int id) {
        ViagemDAO viagem_dao = new ViagemDAO();

        return viagem_dao.getViagemById(id);
    }

    public static Viagem getViagemByCodigo(String codigo) {
        ViagemDAO viagem_dao = new ViagemDAO();

        return viagem_dao.getViagemByCodigo(codigo);
    }

    public static ArrayList<Percurso> getPlanoDeViagemByViagemId(int id_viagem) {
        ViagemDAO viagem_dao = new ViagemDAO();

        return viagem_dao.getPlanoDeViagemByViagemId(id_viagem);
    }

    public static void alterarViagem(int id, String nome_do_pacote_modificado,
            String lotacao_modificada, String[] ids_percursos_modificado) {
        ViagemDAO viagem_dao = new ViagemDAO();
        Viagem viagem_modificada = null;

        if ((viagem_modificada = viagem_dao.getViagemById(id)) != null) {
            String codigo_antigo = viagem_modificada.getCodigo();
            // instantiating a new object based on the retrieved by id to avoid problems with the
            // auto-generation of codigo
            viagem_modificada =
                    new Viagem(id, nome_do_pacote_modificado, null, null,
                            PercursoController.makePlanoDeViagemByIds(ids_percursos_modificado),
                            null);
            String novo_codigo = viagem_modificada.getCodigo();

            if (novo_codigo.equals(codigo_antigo) || getViagemByCodigo(novo_codigo) == null) {
                viagem_dao.alterarViagem(viagem_modificada);
            } else {
                System.out
                        .println("Codigo nao disponivel. Ja existe uma viagem cadastrada dessa forma.");
            }
        }
    }

    public static void deletarViagem(int id) {
        ViagemDAO viagem_dao = new ViagemDAO();

        if (viagem_dao.getViagemById(id) != null) {
            viagem_dao.deletarViagem(id);
        }
    }
}
