package model;

public class GerenteVirtual extends Usuario {
	// (BEGIN) CONSTRUCTORS
	/*
	 * Constructor Pattern
	 */
	public GerenteVirtual(String nome, String endereco, int codigo,
			String email, String senha, int nivel_permissao) {
		super(nome, endereco, codigo, email, senha, nivel_permissao);
	}
	// (END) CONSTRUCTORS
	
	private void aprovarVendas () {
		
	}
}
