package model;

public class Usuario extends Pessoa {
	private String email;
	private String senha;
	private int nivel_permissao;

	// (BEGIN) CONSTRUCTORS
	/*
	 * Constructor Pattern
	 */
	public Usuario(String nome, String endereco, int codigo, String email,
			String senha, int nivel_permissao) {
		super(nome, endereco, codigo);
		setEmail(email);
		setSenha(senha);
		setNivelPermissao(nivel_permissao);
	}
	
	/*
	 * This constructor handles all arguments as String types and treat each of
	 * them to successfully instantiate a Usuario object. It's useful when
	 * getting values from java swing
	 */
	public Usuario(String nome, String endereco, String codigo, String email,
			String senha, String nivel_permissao) {
		// super call...
		super(nome, endereco, codigo);
		
		// treating...
		int int_nivel_permissao = Integer.parseInt(nivel_permissao);
		
		// instantiating...
		setEmail(email);
		setSenha(senha);
		setNivelPermissao(int_nivel_permissao);
	}
	// (END) CONSTRUCTORS

	// (BEGIN) GETTERS & SETTERS
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getNivelPermissao() {
		return nivel_permissao;
	}

	public void setNivelPermissao(int nivel_permissao) {
		this.nivel_permissao = nivel_permissao;
	}
	// (END) GETTERS & SETTERS
	
	protected void fazerReserva(Reserva reserva) {
		
	}
}
