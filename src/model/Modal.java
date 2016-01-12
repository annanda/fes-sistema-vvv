package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Modal {
	private int id;
	private ArrayList<Percurso> percursos;
	private String tipo;
	private String codigo;
	private String companhia;
	private int capacidade;
	private String modelo;
	private int ano_fabricacao;
	private int em_manutencao;
	private int em_uso;
	private Date data_manutencao;

	// (BEGIN) CONSTRUCTORS
	/*
	 * Constructor Pattern
	 */
	public Modal(ArrayList<Percurso> percursos, String tipo, String codigo,
			String companhia, int capacidade, String modelo,
			int ano_fabricacao, int em_manutencao, int em_uso,
			Date data_manutencao) {
		// Setting non-specified attributes...
		this.id = 0;

		setPercursos(percursos);
		this.tipo = tipo;
		this.codigo = codigo;
		setCompanhia(companhia);
		setCapacidade(capacidade);
		setModelo(modelo);
		this.ano_fabricacao = ano_fabricacao;
		setEmManutencao(em_manutencao);
		setEmUso(em_uso);
		setDataManutencao(data_manutencao);
	}

	/*
	 * For database returns
	 */
	public Modal(int id, ArrayList<Percurso> percursos, String tipo,
			String codigo, String companhia, int capacidade, String modelo,
			int ano_fabricacao, int em_manutencao, int em_uso,
			Date data_manutencao) {
		this(percursos, tipo, codigo, companhia, capacidade, modelo,
				ano_fabricacao, em_manutencao, em_uso, data_manutencao);
		this.id = id;
	}

	// (END) CONSTRUCTORS

	// (BEGIN) GETTERS & SETTERS
	public String getTipo() {
		return tipo;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getCompanhia() {
		return companhia;
	}

	public void setCompanhia(String companhia) {
		this.companhia = companhia;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAnoFabricacao() {
		return ano_fabricacao;
	}

	public int getEmManutencao() {
		return em_manutencao;
	}

	public void setEmManutencao(int em_manutencao) {
		this.em_manutencao = em_manutencao;
	}

	public int getEmUso() {
		return em_uso;
	}

	public void setEmUso(int em_uso) {
		this.em_uso = em_uso;
	}

	public Date getDataManutencao() {
		return data_manutencao;
	}

	public void setDataManutencao(Date data_manutencao) {
		this.data_manutencao = data_manutencao;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Percurso> getPercursos() {
		return percursos;
	}

	public void setPercursos(ArrayList<Percurso> percursos) {
		this.percursos = percursos;
	}

	// (END) GETTERS & SETTERS

	/*
	 * Turns your object into a HashMap object with all columns (attributes)
	 * (except for its own id) names as keys of type String and their values as
	 * values also of type String
	 */
	public HashMap<String, String> toHashMap() {
		HashMap<String, String> modal = new HashMap<String, String>();
		Date data_manutencao = this.getDataManutencao();

		modal.put("tipo", this.getTipo());
		modal.put("codigo", this.getCodigo());
		modal.put("companhia", this.getCompanhia());
		modal.put("capacidade", "" + this.getCapacidade());
		modal.put("modelo", this.getModelo());
		modal.put("ano_fabricacao", "" + this.getAnoFabricacao());
		modal.put("em_manutencao", "" + this.getEmManutencao());
		modal.put("em_uso", "" + this.getEmUso());
		modal.put("data_manutencao",
				(data_manutencao != null) ? data_manutencao.toString() : null);

		return modal;
	}
}
