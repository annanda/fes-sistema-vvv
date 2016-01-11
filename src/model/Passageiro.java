package model;

import java.util.Date;
import java.util.HashMap;

public class Passageiro extends Pessoa {
	protected int id;
	private String cpf;
	private String telefone;
	private String profissao;
	private Date data_de_nascimento;
	private Passageiro responsavel;

	// CONSTRUCTOR
	public Passageiro(String nome, String endereco, String codigo, String cpf,
			String telefone, String profissao, Date data_de_nascimento, Passageiro responsavel) {
		super(nome, endereco, codigo);
		// Setting non-specified attributes...
		this.id = 0;

		this.cpf = cpf;
		setTelefone(telefone);
		setProfissao(profissao);
		this.data_de_nascimento = data_de_nascimento;
		setResponsavel(responsavel);
	}

	public Passageiro(int id_passageiro, int id_pessoa, String nome, String endereco, String codigo,
			String cpf, String telefone, String profissao, Date data_de_nascimento, Passageiro responsavel) {
		super(id_pessoa, nome, endereco, codigo);
		this.id = id_passageiro;
		this.cpf = cpf;
		setTelefone(telefone);
		setProfissao(profissao);
		this.data_de_nascimento = data_de_nascimento;
		setResponsavel(responsavel);
	}

	// (BEGIN) GETTERS & SETTERS

	public int getId() {
		return id;
	}
	
	public int getParentId() {
		return super.getId();
	}

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

	/*
	 * Turns your object into a HashMap object with all columns (attributes)
	 * (except for its own id) names as keys of type String and their values as
	 * values also of type String
	 */
	public HashMap<String, String> toHashMap() {
		HashMap<String, String> passageiro = new HashMap<String, String>();

		passageiro.put("id_pessoa", Integer.toString(this.getParentId()));
		passageiro.put("id_pessoa_responsavel", Integer.toString(this.getResponsavel().getId()));
		passageiro.put("cpf", this.getCpf());
		passageiro.put("telefone", this.getTelefone());
		passageiro.put("profissao", this.getProfissao());
		passageiro.put("data_de_nascimento", Constants.DATE_FORMAT.format(this.getDataDeNascimento()));

		return passageiro;
	}
}
