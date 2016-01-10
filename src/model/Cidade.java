package model;

import java.util.HashMap;

public class Cidade {
	protected int id;
	private String nome;
	private String identificador;
	private String codigo;

	// (BEGIN) CONSTRUCTORS
	/*
	 * Constructor Pattern
	 */
	public Cidade(String nome, String identificador, String codigo) {
		// Setting non-specified attributes...
		this.id = 0;

		setNome(nome);
		this.identificador = identificador;
		this.codigo = codigo;
	}

	public Cidade(int id, String nome, String identificador, String codigo) {
		this(nome, identificador, codigo);
		this.id = id;
	}

	// (END) CONSTRUCTORS

	// (BEGIN) GETTERS & SETTERS
	public int getId() {
		return id;
	}

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

	/*
	 * Turns your object into a HashMap object with all columns (attributes)
	 * (except for its own id) names as keys of type String and their values as
	 * values also of type String
	 */
	public HashMap<String, String> toHashMap() {
		HashMap<String, String> cidade = new HashMap<String, String>();

		cidade.put("nome", this.getNome());
		cidade.put("identificador", this.getIdentificador());
		cidade.put("codigo", this.getCodigo());

		return cidade;
	}
}
