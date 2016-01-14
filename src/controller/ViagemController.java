package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Viagem;
import dao.ViagemDAO;

public class ViagemController {
    public static HashMap<String, Integer> cadastrarViagem(String nome_do_pacote,
            String[] id_percursos) {
        HashMap<String, Integer> ids_map = null;
        ViagemDAO viagem_dao = new ViagemDAO();
        Viagem nova_viagem =
                new Viagem(nome_do_pacote, PercursoController.makePlanoDeViagemByIds(id_percursos));

        if (viagem_dao.getViagemByCodigo(nova_viagem.getCodigo()) == null) {
            ids_map = viagem_dao.cadastrarViagem(nova_viagem);
        } else {
            System.out.println("Viagem ja cadastrada no sistema");
        }

        return ids_map;
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
    
    public static void alterarViagem(int id) {
        ViagemDAO viagem_dao = new ViagemDAO();
        Viagem viagem_modificada = null;
        
        if (viagem_dao.getViagemById(id) != null) {
            viagem_dao.alterarViagem(viagem_modificada);
        }
    }
}
