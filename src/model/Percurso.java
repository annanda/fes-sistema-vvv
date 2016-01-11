package model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import controller.CidadeController;
import controller.Controller;

public class Percurso {
	private int id;
	private Date hora_partida;
	private int horas_duracao_percurso;
	private String codigo_aeroporto;
	private Cidade partida;
	private Cidade destino;
	private String codigo;

	// (BEGIN) CONSTRUCTORS
	/*
	 * Constructor Pattern
	 */
	public Percurso(Date hora_partida, int horas_duracao_percurso,
			String codigo_aeroporto, Cidade partida, Cidade destino) {
		this.id = 0;

		this.hora_partida = hora_partida;
		this.horas_duracao_percurso = horas_duracao_percurso;
		this.codigo_aeroporto = codigo_aeroporto;
		this.partida = partida;
		this.destino = destino;

		this.codigo = this.toString();
	}

	public Percurso(int id, Date hora_partida, int horas_duracao_percurso,
			String codigo_aeroporto, int id_cidade_partida,
			int id_cidade_destino) {
		this.id = id;
		this.hora_partida = hora_partida;
		this.horas_duracao_percurso = horas_duracao_percurso;
		this.codigo_aeroporto = codigo_aeroporto;
		this.partida = CidadeController.getCidadeById(id_cidade_partida);
		this.destino = CidadeController.getCidadeById(id_cidade_destino);

		this.codigo = this.toString();
	}

	// (END) CONSTRUCTORS

	// (BEGIN) GETTERS & SETTERS
	public Date getHoraPartida() {
		return hora_partida;
	}

	public int getHorasDuracaoPercurso() {
		return horas_duracao_percurso;
	}

	public String getCodigoAeroporto() {
		return codigo_aeroporto;
	}

	public Cidade getPartida() {
		return partida;
	}

	public Cidade getDestino() {
		return destino;
	}

	public String getCodigo() {
		return codigo;
	}

	public int getId() {
		return id;
	}

	// (END) GETTERS & SETTERS

	/*
	 * Turns your object into a HashMap object with all columns (attributes)
	 * (except for its own id) names as keys of type String and their values as
	 * values also of type String
	 */
	public HashMap<String, String> toHashMap() {
		HashMap<String, String> percurso = new HashMap<String, String>();
		Date hora_partida = this.getHoraPartida();

		percurso.put("id_cidade_partida", "" + this.getPartida().getId());
		percurso.put("id_cidade_chegada", "" + this.getDestino().getId());
		percurso.put("hora_partida",
				(hora_partida != null) ? hora_partida.toString() : null);
		percurso.put("horas_duracao", "" + this.getHorasDuracaoPercurso());
		percurso.put("codigo_aeroporto", this.getCodigoAeroporto());
		percurso.put("codigo", this.getCodigo());

		return percurso;
	}

	/*
	 * Identifies the Percurso object turning it into string value. It's a must
	 * because Percurso has no other identifier than it's own id, and this is a
	 * huge trouble when making some algorithms at PercursoController
	 */
	public String toString() {
		String codigo_aeroporto = this.getCodigoAeroporto();
		Date hora_partida = this.getHoraPartida();
		codigo_aeroporto = (Controller.isNullOrEmpty(codigo_aeroporto)) ? ""
				: codigo_aeroporto;
		String string_hora_partida = (hora_partida != null) ? hora_partida
				.toString() : "";

		return this.getPartida().getIdentificador()
				+ this.getDestino().getIdentificador()
				+ string_hora_partida.replaceAll("\\s", "")
				+ this.getHorasDuracaoPercurso() + codigo_aeroporto;
	}

	// Model methods
	public Date calcHoraChegada() {
		Calendar c_hora_chegada = Calendar.getInstance();

		c_hora_chegada.setTime(hora_partida);
		c_hora_chegada.add(Calendar.HOUR, horas_duracao_percurso);

		return c_hora_chegada.getTime();
	}
}
