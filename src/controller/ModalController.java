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

		try {
			Modal novo_modal = new Modal(null, tipo, codigo, companhia,
					Integer.parseInt(capacidade), modelo,
					Integer.parseInt(ano_fabricacao),
					Boolean.parseBoolean(em_manutencao),
					Boolean.parseBoolean(em_uso),
					Constants.DATETIME_FORMAT.parse(data_manutencao));
			id = modal_dao.cadastrarModal(novo_modal);
			for (String codigo_percurso : codigos_percursos) {
				Percurso temp_percurso_modificado = PercursoController
						.getPercursoByCodigo(codigo_percurso);
				temp_percurso_modificado.setModal(novo_modal);
				PercursoController.alterarPercurso(temp_percurso_modificado);
			}
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

	public static Modal getModalById(int id) {
		ModalDAO modal_dao = new ModalDAO();

		return modal_dao.getModalById(id);
	}

	public static void alterarModal(int id_modal, String[] codigos_percursos,
			String tipo, String codigo, String companhia, String capacidade,
			String modelo, String ano_fabricacao, String em_manutencao,
			String em_uso, String data_manutencao) {
		ModalDAO modal_dao = new ModalDAO();

		try {
			Modal modal_modificado = new Modal(id_modal, null, tipo, codigo,
					companhia, Integer.parseInt(capacidade), modelo,
					Integer.parseInt(ano_fabricacao),
					Boolean.parseBoolean(em_manutencao),
					Boolean.parseBoolean(em_uso),
					Constants.DATETIME_FORMAT.parse(data_manutencao));
			modal_dao.alterarModal(modal_modificado);
			
			for (String codigo_percurso : codigos_percursos) {
				Percurso temp_percurso_modificado = PercursoController
						.getPercursoByCodigo(codigo_percurso);
				temp_percurso_modificado.setModal(modal_modificado);
				PercursoController.alterarPercurso(temp_percurso_modificado);
			}
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
		}
	}
}
