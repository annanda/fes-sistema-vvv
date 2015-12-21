package model;

public class Passageiro extends Pessoa {
	private String cpf;
	private String telefone;
	private String profissao;

	// (BEGIN) CONSTRUCTORS
	/*
	 * Constructor Pattern
	 */
	public Passageiro(String nome, String endereco, int codigo, String cpf,
			String telefone, String profissao) {
		super(nome, endereco, codigo);
		this.cpf = cpf;
		setTelefone(telefone);
		setProfissao(profissao);
	}
	
	/*
	 * This constructor handles all arguments as String types and treat each of
	 * them to successfully instantiate a Pessoa object. It's useful when
	 * getting values from java swing
	 */
	public Passageiro(String nome, String endereco, String codigo, String cpf,
			String telefone, String profissao) {
		// super call...
		super(nome, endereco, codigo);
		// no treatment needed...
		// instantiating...
		this.cpf = cpf;
		setTelefone(telefone);
		setProfissao(profissao);
	}
	// (END) CONSTRUCTORS

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
	// (END) GETTERS & SETTERS
}
