package model;

public class GerenteVirtual extends Usuario {
  // CONSTRUCTOR
  public GerenteVirtual(String nome, String endereco, String codigo, String email, String senha,
      int nivel_permissao) {
    super(nome, endereco, codigo, email, senha, nivel_permissao);
  }

  private void aprovarVendas() {

  }
}
