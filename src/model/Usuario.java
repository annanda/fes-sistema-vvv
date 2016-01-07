package model;

public class Usuario extends Pessoa {
	protected int id;
	private String email;
	private String senha;
	private int nivel_permissao;

	// CONSTRUCTOR
	public Usuario(String nome, String endereco, int codigo, String email,
			String senha, int nivel_permissao) {
		super(nome, endereco, codigo);
		setEmail(email);
		setSenha(senha);
		setNivelPermissao(nivel_permissao);
	}

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
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getParentId() {
		return super.getId();
	}
	
	public void setParentId(int id) {
		super.setId(id);
	}
	// (END) GETTERS & SETTERS
	
	protected void fazerReserva(Reserva reserva) {
		
	}
}
