package model;

public class Pessoa {
	protected int id;
	protected String nome;
	protected String endereco;
	private int codigo;

	// (BEGIN) CONSTRUCTORS
	/*
	 * For logic purpose
	 */
	public Pessoa(String nome, String endereco, int codigo) {
		// Setting non-specified attributes...
		setId(0);
		
		setNome(nome);
		setEndereco(endereco);
		this.codigo = codigo;
	}
	
	/*
	 * For database returns
	 */
	public Pessoa(int id, String nome, String endereco, int codigo) {
		setId(id);
		setNome(nome);
		setEndereco(endereco);
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	// (END) GETTERS & SETTERS
}
