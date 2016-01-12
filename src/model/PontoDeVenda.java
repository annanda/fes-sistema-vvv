package model;

public class PontoDeVenda {
  protected int id;
  private String nome;
  private String endereco;
  private String telefone;
  private int codigo;
  private String cnpj;

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

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public int getCodigo() {
    return codigo;
  }

  public String getCnpj() {
    return cnpj;
  }
}
