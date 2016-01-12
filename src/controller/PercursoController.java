package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Constants;
import model.Percurso;
import dao.PercursoDAO;

public class PercursoController {
	public static int cadastrarPercurso(String id_modal,
			String codigo_cidade_partida, String codigo_cidade_destino,
			String hora_partida, String horas_duracao, String codigo_aeroporto) {
		int id = 0;
		PercursoDAO percurso_dao = new PercursoDAO();
		try {
			Percurso novo_percurso = new Percurso(
					ModalController.getModalById(Integer.parseInt(id_modal)),
					Constants.DATETIME_FORMAT.parse(hora_partida),
					Integer.parseInt(horas_duracao), codigo_aeroporto,
					CidadeController.getCidadeByCodigo(codigo_cidade_partida),
					CidadeController.getCidadeByCodigo(codigo_cidade_destino));
			if (percurso_dao.getPercursoByCodigo(novo_percurso.getCodigo()) == null) {
				id = percurso_dao.cadastrarPercurso(novo_percurso);
			} else {
				System.out.println("Percurso ja cadastrado");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}

	public static ArrayList<Percurso> listarPercursos(
			String id_cidade_partida_consultada,
			String id_cidade_chegada_consultada) {
		PercursoDAO percurso_dao = new PercursoDAO();
		HashMap<String, String> conditions = new HashMap<String, String>();

		conditions.put("id_cidade_partida", id_cidade_partida_consultada);
		conditions.put("id_cidade_chegada", id_cidade_chegada_consultada);

		return percurso_dao.listarPercursos(conditions);
	}

	public static Percurso getPercursoByCodigo(String codigo) {
		PercursoDAO percurso_dao = new PercursoDAO();

		return percurso_dao.getPercursoByCodigo(codigo);
	}

	public static ArrayList<Percurso> getPercursoByModal(int id) {
		PercursoDAO percurso_dao = new PercursoDAO();

		return percurso_dao.getPercursoByModal(id);
	}

	public static void alterarPercurso(Percurso percurso_modificado) {
		PercursoDAO percurso_dao = new PercursoDAO();

		if (percurso_dao.getPercursoById(percurso_modificado.getId()) != null) {
			percurso_dao.alterarPercurso(percurso_modificado);
		} else {
			System.out.println("Percurso nao encontrado para alteracao");
		}
	}

	public static void deletarPercurso(int id) {
		PercursoDAO percurso_dao = new PercursoDAO();

		if (percurso_dao.getPercursoById(id) != null) {
			percurso_dao.deletarPercurso(id);
		} else {
			System.out.println("Percurso nao encontrado");
		}
	}
}
