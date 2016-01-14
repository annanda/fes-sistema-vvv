package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Constants;
import model.Percurso;
import dao.PercursoDAO;

public class PercursoController {
    public static int cadastrarPercurso(String id_modal, String codigo_cidade_partida,
            String codigo_cidade_destino, String hora_partida, String horas_duracao,
            String codigo_aeroporto) {
        int id = 0;
        PercursoDAO percurso_dao = new PercursoDAO();

        // in order to set the codigo attribute for testing if it's unique, it's obligatory to
        // instantiate novo_percurso before
        try {
            Percurso novo_percurso =
                    new Percurso(ModalController.getModalById(Integer.parseInt(id_modal)),
                            Constants.DATETIME_FORMAT.parse(hora_partida),
                            Integer.parseInt(horas_duracao), codigo_aeroporto,
                            CidadeController.getCidadeByCodigo(codigo_cidade_partida),
                            CidadeController.getCidadeByCodigo(codigo_cidade_destino));

            // testing if codigo is unique...
            if (percurso_dao.getPercursoByCodigo(novo_percurso.getCodigo()) == null) {
                // sending it to DAO class to finally insert it into BD
                id = percurso_dao.cadastrarPercurso(novo_percurso);
            } else {
                System.out
                        .println("Codigo ja cadastrado. Percurso pode ja ter sido cadastrado no sistema");
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return id;
    }

    public static ArrayList<Percurso> listarPercursos(String id_cidade_partida_consultada,
            String id_cidade_chegada_consultada, String hora_partida_consultada,
            String codigo_aeroporto_consultado) {
        PercursoDAO percurso_dao = new PercursoDAO();
        HashMap<String, String> conditions = new HashMap<String, String>();

        conditions.put("id_cidade_partida", id_cidade_partida_consultada);
        conditions.put("id_cidade_chegada", id_cidade_chegada_consultada);
        conditions.put("codigo_aeroporto", codigo_aeroporto_consultado);
        conditions.put("hora_partida", hora_partida_consultada);

        return percurso_dao.listarPercursos(conditions);
    }

    public static Percurso getPercursoByCodigo(String codigo) {
        PercursoDAO percurso_dao = new PercursoDAO();

        return percurso_dao.getPercursoByCodigo(codigo);
    }

    public static ArrayList<Percurso> getPercursoByModal(int id_modal) {
        PercursoDAO percurso_dao = new PercursoDAO();

        return percurso_dao.getPercursoByModal(id_modal);
    }

    public static Percurso getPercursoById(int id) {
        PercursoDAO percurso_dao = new PercursoDAO();

        return percurso_dao.getPercursoById(id);
    }

    public static void alterarPercurso(int id_percurso, String id_modal_modificado,
            String horas_duracao_modificada, String codigo_aeroporto_modificado) {
        PercursoDAO percurso_dao = new PercursoDAO();
        Percurso percurso_modificado = getPercursoById(id_percurso);

        if (percurso_modificado != null) {
            String codigo_antigo = percurso_modificado.getCodigo();
            // instantiating a new object based on the retrieved by id to avoid problems with the
            // auto-generation of codigo
            percurso_modificado =
                    new Percurso(id_percurso, Integer.parseInt(id_modal_modificado),
                            percurso_modificado.getHoraPartida(),
                            Integer.parseInt(horas_duracao_modificada),
                            codigo_aeroporto_modificado, percurso_modificado.getPartida().getId(),
                            percurso_modificado.getDestino().getId(), null);
            String novo_codigo = percurso_modificado.getCodigo();

            if (novo_codigo.equals(codigo_antigo) || getPercursoByCodigo(novo_codigo) == null) {
                percurso_dao.alterarPercurso(percurso_modificado);
            } else {
                System.out
                        .println("Codigo nao disponivel. Ja existe um percurso cadastrado dessa forma.");
            }
        } else {
            System.out.println("Percurso nao encontrado para alteracao");
        }
    }

    public static void deletarPercurso(int id) {
        PercursoDAO percurso_dao = new PercursoDAO();

        if (percurso_dao.getPercursoById(id) != null) {
            percurso_dao.deletarPercurso(id);
        } else {
            System.out.println("Percurso nao encontrado para deletar");
        }
    }

    public static ArrayList<Percurso> makePlanoDeViagemByIds(String[] id_percursos) {
        PercursoDAO percurso_dao = new PercursoDAO();
        ArrayList<Percurso> plano_de_viagem = new ArrayList<Percurso>();

        for (String id_percurso : id_percursos) {
            plano_de_viagem.add(percurso_dao.getPercursoById(Integer.parseInt(id_percurso)));
        }

        return plano_de_viagem;
    }
}
