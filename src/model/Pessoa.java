package model;

public class Pessoa {
	protected String nome;
	protected String endereco;
	private int codigo;

	// CONSTRUCTOR
	public Pessoa(String nome, String endereco, int codigo) {
		setNome(nome);
		setEndereco(endereco);
		this.codigo = codigo;
	}

	// (BEGIN) GETTERS & SETTERS
	public String getNome() {
		return nome;
	}

	protected void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	protected void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getCodigo() {
		return codigo;
	}
	// (END) GETTERS & SETTERS
}
