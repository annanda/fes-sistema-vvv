package model;

import static model.Constants.DATE_FORMAT;

import java.text.ParseException;
import java.util.Date;

public class Modal {
	protected int id;
	private String tipo;
	private String codigo;
	private String companhia;
	private int capacidade;
	private String modelo;
	private int ano_fabricacao;
	private boolean em_manutencao;
	private boolean em_uso;
	private Date data_manutencao;

	// (BEGIN) CONSTRUCTORS
	/*
	 * Constructor Pattern
	 */
	public Modal(String tipo, String codigo, String companhia, int capacidade,
			String modelo, int ano_fabricacao, boolean em_manutencao,
			boolean em_uso, Date data_manutencao) {
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
	 * This constructor handles all arguments as String types and treat each of
	 * them to successfully instantiate a Modal object. It's useful when
	 * getting values from java swing
	 */
	public Modal(String tipo, String codigo, String companhia, String capacidade,
			String modelo, String ano_fabricacao, String em_manutencao,
			String em_uso, String data_manutencao) throws ParseException {
		// treating...
		int int_capacidade = Integer.parseInt(capacidade);
		int int_ano_fabricacao = Integer.parseInt(ano_fabricacao);
		boolean boolean_em_manutencao = Boolean.parseBoolean(em_manutencao);
		boolean boolean_em_uso = Boolean.parseBoolean(em_uso);
		Date date_data_manutencao = DATE_FORMAT.parse(data_manutencao);
		
		// instantiating...
		this.tipo = tipo;
		this.codigo = codigo;
		setCompanhia(companhia);
		setCapacidade(int_capacidade);
		setModelo(modelo);
		this.ano_fabricacao = int_ano_fabricacao;
		setEmManutencao(boolean_em_manutencao);
		setEmUso(boolean_em_uso);
		setDataManutencao(date_data_manutencao);
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

	public boolean getEmManutencao() {
		return em_manutencao;
	}
	public void setEmManutencao(boolean em_manutencao) {
		this.em_manutencao = em_manutencao;
	}

	public boolean getEmUso() {
		return em_uso;
	}
	public void setEmUso(boolean em_uso) {
		this.em_uso = em_uso;
	}

	public Date getDataManutencao() {
		return data_manutencao;
	}
	public void setDataManutencao(Date data_manutencao) {
		this.data_manutencao = data_manutencao;
	}
	// (END) GETTERS & SETTERS
}
