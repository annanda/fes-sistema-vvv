package model;

import static model.Constants.DATE_FORMAT;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class Percurso {
	private Date hora_partida;
	private int horas_duracao_viagem;
	private int codigo_aeroporto;
	private Cidade partida;
	private Cidade destino;
	
	// (BEGIN) CONSTRUCTORS
	/*
	 * Constructor Pattern
	 */
	public Percurso(Date hora_partida, int horas_duracao_viagem, int codigo_aeroporto) {
		this.hora_partida = hora_partida;
		this.horas_duracao_viagem = horas_duracao_viagem;
		this.codigo_aeroporto = codigo_aeroporto;
	}
	
	/*
	 * This constructor handles all arguments as String types and treat each of
	 * them to successfully instantiate a Percurso object. It's useful when
	 * getting values from java swing
	 */
	public Percurso(String hora_partida, String horas_duracao_viagem,
			String codigo_aeroporto) throws ParseException {
		// treating...
		Date date_hora_partida = DATE_FORMAT.parse(hora_partida);
		int int_horas_duracao_viagem = Integer.parseInt(horas_duracao_viagem);
		int int_codigo_aeroporto = Integer.parseInt(codigo_aeroporto);
		
		// instantiating...
		this.hora_partida = date_hora_partida;
		this.horas_duracao_viagem = int_horas_duracao_viagem;
		this.codigo_aeroporto = int_codigo_aeroporto;
	}
	// (END) CONSTRUCTORS
	
	// (BEGIN) GETTERS & SETTERS
	public Date getHoraPartida() {
		return hora_partida;
	}
	
	public int getHorasDuracaoViagem() {
		return horas_duracao_viagem;
	}
	
	public int getCodigoAeroporto() {
		return codigo_aeroporto;
	}

	public Cidade getPartida() {
		return partida;
	}

	public Cidade getDestino() {
		return destino;
	}
	// (END) GETTERS & SETTERS
	
	public Date calcHoraChegada() {
		Calendar cdate = Calendar.getInstance();
		Date hora_chegada;
		
		cdate.setTime(hora_partida);
		cdate.add(Calendar.HOUR, horas_duracao_viagem);
		hora_chegada = cdate.getTime();
		
		return hora_chegada;
	}
}
