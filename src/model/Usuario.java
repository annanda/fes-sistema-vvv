package model;

import java.util.ArrayList;
import java.util.HashMap;

import controller.ReservaController;

public class Usuario extends Pessoa {
    protected int id;
    private String email;
    private String senha;
    private int nivel_permissao;

    // (BEGIN) CONSTRUCTORS
    /*
     * For logic purpose
     */
    public Usuario(String nome, String endereco, String codigo, String email, String senha,
            int nivel_permissao) {
        super(nome, endereco, codigo);
        // Setting non-specified attributes...
        this.id = 0;

        setEmail(email);
        setSenha(senha);
        setNivelPermissao(nivel_permissao);
    }

    /*
     * For database returns
     */
    public Usuario(int id_usuario, int id_pessoa, String nome, String endereco, String codigo,
            String email, String senha, int nivel_permissao) {
        super(id_pessoa, nome, endereco, codigo);
        this.id = id_usuario;
        setEmail(email);
        setSenha(senha);
        setNivelPermissao(nivel_permissao);
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

    public int getId() {
        return this.id;
    }

    public int getParentId() {
        return super.getId();
    }

    // (END) GETTERS & SETTERS

    /*
     * Turns your object into a HashMap object with all columns (attributes) (except for its own id)
     * names as keys of type String and their values as values also of type String
     */
    public HashMap<String, String> toHashMap() {
        HashMap<String, String> usuario = new HashMap<String, String>();

        usuario.put("email", this.getEmail());
        usuario.put("senha", this.getSenha());
        usuario.put("nivel_permissao", "" + this.getNivelPermissao());

        return usuario;
    }

    protected void fazerReserva(Reserva reserva) {
        ArrayList<Passageiro> lista_passageiros = reserva.getPassageiros();
        ArrayList<String> lista_ids_passageiros = new ArrayList<String>();

        for (Passageiro passageiro : lista_passageiros) {
            lista_ids_passageiros.add("" + passageiro.getId());
        }

        HashMap<String, Integer> hm =
                ReservaController.cadastrarReserva(reserva.getCodigo(), reserva.getQtdParcelas(),
                        reserva.getTipoPagamento(), reserva.getValor(), reserva.getStatus(),
                        lista_ids_passageiros.toArray(new String[lista_ids_passageiros.size()]),
                        this.getId(), reserva.getViagem().getId());

        if (hm.get("id_reserva") > 0) {
            System.out.println("Reserva feita com sucesso");
        } else {
            System.out.println("Erro ao criar Reserva");
        }
    }
}
