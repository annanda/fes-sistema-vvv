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

  // (BEGIN) CONSTRUCTOR
  public Viagem(String nome_do_pacote, ArrayList<Percurso> plano_de_viagem) {
    this.id = 0;

    setNomeDoPacote(nome_do_pacote);
    setLotacao(plano_de_viagem);
    this.data_partida = this.plano_de_viagem.get(0).getHoraPartida();
    this.data_chegada = this.plano_de_viagem.get(this.plano_de_viagem.size() - 1).calcHoraChegada();
    setPlanoDeViagem(plano_de_viagem);
  }

  public Viagem(int id, String nome_do_pacote, Date data_partida, Date data_chegada,
      ArrayList<Percurso> plano_de_viagem) {
    this.id = id;
    setNomeDoPacote(nome_do_pacote);
    setLotacao(plano_de_viagem);
    this.data_partida = data_partida;
    this.data_chegada = data_chegada;
    setPlanoDeViagem(plano_de_viagem);
  }

  // (END) CONSTRUCTOR

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

  public void setLotacao(ArrayList<Percurso> plano_de_viagem) {
    boolean test = true;
    int lotacao = 0;
    int capacidade;

    for (Percurso percurso : plano_de_viagem) {
      if (test) {
        test = false;
        lotacao = percurso.getModal().getCapacidade();
      } else {
        capacidade = percurso.getModal().getCapacidade();
        if (capacidade < lotacao) {
          lotacao = capacidade;
        }
      }
    }
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
