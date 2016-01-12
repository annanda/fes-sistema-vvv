package controller;

import java.text.ParseException;
import java.util.ArrayList;

import model.Constants;
import model.Percurso;
import dao.PercursoDAO;

public class PercursoController {
	public static int cadastrarPercurso(String codigo_cidade_partida,
			String codigo_cidade_destino, String hora_partida,
			String horas_duracao, String codigo_aeroporto) {
		int id = 0;
		PercursoDAO percurso_dao = new PercursoDAO();
		try {
			Percurso novo_percurso = new Percurso(
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

	public static ArrayList<Percurso> listarPercursos() {
		PercursoDAO percurso_dao = new PercursoDAO();

		return percurso_dao.listarPercursos();
	}

	public static Percurso getPercursoByCodigo(String codigo) {
		PercursoDAO percurso_dao = new PercursoDAO();

		return percurso_dao.getPercursoByCodigo(codigo);
	}

	// There's no set method in Percurso Model: there's no update to Percurso;

	public static void deletarPercurso(int id) {
		PercursoDAO percurso_dao = new PercursoDAO();

		if (percurso_dao.getPercursoById(id) != null) {
			percurso_dao.deletarPercurso(id);
		} else {
			System.out.println("Percurso nao encontrado");
		}
	}
}
