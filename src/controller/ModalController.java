package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Constants;
import model.Modal;
import model.Percurso;
import dao.ModalDAO;

public class ModalController {
	public static int cadastrarModal(String[] codigos_percursos, String tipo,
			String codigo, String companhia, String capacidade, String modelo,
			String ano_fabricacao, String em_manutencao, String em_uso,
			String data_manutencao) {
		int id = 0;
		ModalDAO modal_dao = new ModalDAO();
		ArrayList<Percurso> percursos = new ArrayList<Percurso>();

		for (String codigo_percurso : codigos_percursos) {
			percursos.add(PercursoController.getPercursoByCodigo(codigo_percurso));
		}

		try {
			id = modal_dao.cadastrarModal(new Modal(percursos, tipo, codigo, companhia,
					Integer.parseInt(capacidade), modelo, Integer.parseInt(ano_fabricacao),
					Integer.parseInt(em_manutencao), Integer.parseInt(em_uso),
					Constants.DATETIME_FORMAT.parse(data_manutencao)));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}

	public static ArrayList<Modal> listarModais(String tipo_consultado,
			String codigo_consultado, String companhia_consultada,
			String capacidade_consultada, String modelo_consultado,
			String ano_fabricacao_consultado) {
		ModalDAO modal_dao = new ModalDAO();
		HashMap<String, String> conditions = new HashMap<String, String>();

		conditions.put("tipo", tipo_consultado);
		conditions.put("codigo", codigo_consultado);
		conditions.put("companhia", companhia_consultada);
		conditions.put("capacidade", "" + capacidade_consultada);
		conditions.put("modelo", modelo_consultado);
		conditions.put("ano_fabricacao", "" + ano_fabricacao_consultado);

		return modal_dao.listarModais(conditions);
	}

	public static void alterarModal(int id_modal, String[] codigos_percursos,
			String tipo, String codigo, String companhia, String capacidade,
			String modelo, String ano_fabricacao, String em_manutencao,
			String em_uso, String data_manutencao) {
		ModalDAO modal_dao = new ModalDAO();
		ArrayList<Percurso> percursos = new ArrayList<Percurso>();

		for (String codigo_percurso : codigos_percursos) {
			percursos.add(PercursoController
					.getPercursoByCodigo(codigo_percurso));
		}

		try {
			modal_dao.alterarModal(new Modal(id_modal, percursos, tipo, codigo, companhia,
					Integer.parseInt(capacidade), modelo, Integer.parseInt(ano_fabricacao),
					Integer.parseInt(em_manutencao), Integer.parseInt(em_uso),
					Constants.DATETIME_FORMAT.parse(data_manutencao)));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deletarModal(int id) {
		ModalDAO modal_dao = new ModalDAO();
		
		if (modal_dao.getModalById(id) != null) {
			modal_dao.deletarModal(id);
		} else {
			System.out.println("Modal nao encontrado");
		}
	}
}
