package model;

public class GerenteVirtual extends Usuario {
    // CONSTRUCTOR
    public GerenteVirtual(String nome, String endereco, String codigo, String email, String senha) {
        super(nome, endereco, codigo, email, senha, 2);
    }

    public void aprovarVendas() {
        
    }
}
