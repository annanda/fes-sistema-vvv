package model;

import java.util.HashMap;

public class PontoDeVenda {
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String codigo;
    private String cnpj;

    public PontoDeVenda(String nome, String endereco, String telefone, String codigo, String cnpj) {
        this.id = 0;
        setNome(nome);
        setEndereco(endereco);
        setTelefone(telefone);
        this.codigo = codigo;
        this.cnpj = cnpj;
    }

    public PontoDeVenda(int id, String nome, String endereco, String telefone, String codigo,
            String cnpj) {
        this.id = id;
        setNome(nome);
        setEndereco(endereco);
        setTelefone(telefone);
        this.codigo = codigo;
        this.cnpj = cnpj;
    }

    public int getId() {
        return id;
    }

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

    public String getCodigo() {
        return codigo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public HashMap<String, String> toHashMap() {
        HashMap<String, String> ponto_de_venda = new HashMap<String, String>();

        ponto_de_venda.put("nome", this.getNome());
        ponto_de_venda.put("endereco", this.getEndereco());
        ponto_de_venda.put("telefone", this.getTelefone());

        return ponto_de_venda;
    }
}
