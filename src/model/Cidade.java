package model;

public class Cidade {
	private String nome;
	private String identificador;
	private String codigo;

	// (BEGIN) CONSTRUCTORS
	/*
	 * Constructor Pattern
	 */
	public Cidade(String nome, String identificador, String codigo) {
		setNome(nome);
		this.identificador = identificador;
		this.codigo = codigo;
	}

	// (END) CONSTRUCTORS

	// (BEGIN) GETTERS & SETTERS
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdentificador() {
		return identificador;
	}

	public String getCodigo() {
		return codigo;
	}
	// (END) GETTERS & SETTERS
}
