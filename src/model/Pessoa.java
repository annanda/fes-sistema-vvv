package model;

public class Pessoa {
	protected String nome;
	protected String endereco;
	private int codigo;

	// (BEGIN) CONSTRUCTORS
	/*
	 * Constructor Pattern
	 */
	public Pessoa(String nome, String endereco, int codigo) {
		setNome(nome);
		setEndereco(endereco);
		this.codigo = codigo;
	}
	
	/*
	 * This constructor handles all arguments as String types and treat each of
	 * them to successfully instantiate a Pessoa object. It's useful when
	 * getting values from java swing
	 */
	public Pessoa(String nome, String endereco, String codigo) {
		// treating...
		int int_codigo = Integer.parseInt(codigo);
		
		// instantiating...
		setNome(nome);
		setEndereco(endereco);
		this.codigo = int_codigo;
	}
	// (END) CONSTRUCTORS

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
