package model;

import java.util.ArrayList;
import java.util.Date;

public class Viagem {
	private int id;
	private String nome_do_pacote;
	private int lotacao;
	private Date data_partida;
	private Date data_chegada;
	private ArrayList<Percurso> plano_de_viagem;

	// CONSTRUCTOR
	public Viagem(String nome_do_pacote, ArrayList<Percurso> plano_de_viagem) {
		setNomeDoPacote(nome_do_pacote);
		setPlanoDeViagem(plano_de_viagem);
	}

	// (BEGIN) GETTERS & SETTERS
	public String getNomeDoPacote() {
		return nome_do_pacote;
	}

	public void setNomeDoPacote(String nome_do_pacote) {
		this.nome_do_pacote = nome_do_pacote;
	}

	public int getId() {
		return id;
	}

	public int getLotacao() {
		return lotacao;
	}

	public void setLotacao(int lotacao) {
		this.lotacao = lotacao;
	}

	public Date getDataPartida() {
		return data_partida;
	}

	public Date getDataChegada() {
		return data_chegada;
	}

	public ArrayList<Percurso> getPlanoDeViagem() {
		return plano_de_viagem;
	}

	public void setPlanoDeViagem(ArrayList<Percurso> plano_de_viagem) {
		this.plano_de_viagem = plano_de_viagem;
	}
	// (END) GETTERS & SETTERS
}
