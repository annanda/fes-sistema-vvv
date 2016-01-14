package model;

import java.util.HashMap;

public class Pessoa {
    protected int id;
    protected String nome;
    protected String endereco;
    private String codigo;

    // (BEGIN) CONSTRUCTORS
    /*
     * For logic purpose
     */
    public Pessoa(String nome, String endereco, String codigo) {
        // Setting non-specified attributes...
        this.id = 0;

        setNome(nome);
        setEndereco(endereco);
        this.codigo = codigo;
    }

    /*
     * For database returns
     */
    public Pessoa(int id, String nome, String endereco, String codigo) {
        this(nome, endereco, codigo);
        this.id = id;
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

    public String getCodigo() {
        return codigo;
    }

    public int getId() {
        return this.id;
    }

    // (END) GETTERS & SETTERS

    /*
     * Turns your object into a HashMap object with all columns (attributes) (except for its own id)
     * names as keys of type String and their values as values also of type String
     */
    public HashMap<String, String> toHashMap() {
        HashMap<String, String> pessoa = new HashMap<String, String>();

        pessoa.put("nome", this.getNome());
        pessoa.put("endereco", this.getEndereco());

        return pessoa;
    }
}
