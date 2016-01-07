package model;

import java.util.Date;

public class Passageiro extends Pessoa {
	private String cpf;
	private String telefone;
	private String profissao;
	private Date data_de_nascimento;
	private Passageiro responsavel;

	// CONSTRUCTOR
	public Passageiro(String nome, String endereco, int codigo, String cpf,
			String telefone, String profissao, Date data_de_nascimento, Passageiro responsavel) {
		super(nome, endereco, codigo);
		this.cpf = cpf;
		setTelefone(telefone);
		setProfissao(profissao);
		this.data_de_nascimento = data_de_nascimento;
		setResponsavel(responsavel);
	}

	// (BEGIN) GETTERS & SETTERS
	public String getCpf() {
		return cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Date getDataDeNascimento() {
		return data_de_nascimento;
	}

	public Passageiro getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Passageiro responsavel) {
		this.responsavel = responsavel;
	}
	// (END) GETTERS & SETTERS
}
