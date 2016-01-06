package model;

import java.util.ArrayList;

public class Viagem {
	private ArrayList<Percurso> plano_de_viagem;
	
	// CONSTRUCTOR
	public Viagem(ArrayList<Percurso> plano_de_viagem) {
		setPlanoDeViagem(plano_de_viagem);
	}

	// (BEGIN) GETTERS & SETTERS
	public ArrayList<Percurso> getPlanoDeViagem() {
		return plano_de_viagem;
	}

	public void setPlanoDeViagem(ArrayList<Percurso> plano_de_viagem) {
		this.plano_de_viagem = plano_de_viagem;
	}
	// (END) GETTERS & SETTERS
}
