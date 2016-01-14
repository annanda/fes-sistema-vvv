package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import dao.PassageiroDAO;
import model.Passageiro;
import model.Constants;

public class PassageiroController {
    public static int cadastrarPassageiro(String nome, String endereco, String codigo, String cpf,
            String telefone, String profissao, String data_de_nascimento, String responsavel) {
        int id = 0;
        PassageiroDAO passageiro_dao = new PassageiroDAO();

        Passageiro novo_passageiro = passageiro_dao.getPassageiroByCpf(cpf);

        // testing if cpf is unique...
        if (novo_passageiro == null) {
            // treating non-String arguments...
            Passageiro passageiro_responsavel = null;
            if (responsavel != null && responsavel.isEmpty()) {
                int id_responsavel = Integer.parseInt(responsavel);
                passageiro_responsavel = passageiro_dao.getPassageiroById(id_responsavel);
            }
            Date date_data_de_nascimento = null;
            try {
                date_data_de_nascimento = Constants.DATE_FORMAT.parse(data_de_nascimento);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // trying to insert a new Pessoa (if it doesn't exist before)
            int id_pessoa = PessoaController.cadastrarPessoa(nome, endereco, codigo);

            // instantiating the new object...
            novo_passageiro =
                    new Passageiro(0, id_pessoa, nome, endereco, codigo, cpf, telefone, profissao,
                            date_data_de_nascimento, passageiro_responsavel);

            // sending it to DAO class to finally insert it into BD
            id = passageiro_dao.cadastrarPassageiro(novo_passageiro);
        } else {
            id = novo_passageiro.getId();
            System.out.println("CPF ja cadastrado. Passageiro pode ja estar cadastrado no sistema");
        }

        return id;
    }

    public static Passageiro getPassageiroById(int id) {
        PassageiroDAO passageiro_dao = new PassageiroDAO();

        return passageiro_dao.getPassageiroById(id);
    }

    public static ArrayList<Passageiro> listarPassageiros(String nome_consultado,
            String endereco_consultado, String codigo_consultado,
            String data_de_nascimento_consultada) {
        // setting up the filters...
        HashMap<String, String> conditions = new HashMap<String, String>();
        conditions.put("nome", nome_consultado);
        conditions.put("endereco", endereco_consultado);
        conditions.put("codigo", codigo_consultado);
        conditions.put("data_de_nascimento", data_de_nascimento_consultada);

        PassageiroDAO passageiro_dao = new PassageiroDAO();

        return passageiro_dao.listarPassageiros(conditions);
    }

    public static void alterarPassageiro(int id, String nome_modificado,
            String endereco_modificado, String telefone_modificado, String profissao_modificado,
            String id_responsavel_modificado) {
        PassageiroDAO passageiro_dao = new PassageiroDAO();

        // instantiating the modified object...
        Passageiro passageiro_modificado = getPassageiroById(id);
        // testing if what is about to be modified is in database...
        if (passageiro_modificado != null) {
            // treating non-String arguments...
            Passageiro passageiro_responsavel_modificado = null;
            if (id_responsavel_modificado != null && id_responsavel_modificado.isEmpty()) {
                int int_id_responsavel_modificado = Integer.parseInt(id_responsavel_modificado);
                passageiro_responsavel_modificado =
                        passageiro_dao.getPassageiroById(int_id_responsavel_modificado);
            }

            // modifying...
            passageiro_modificado.setEndereco(endereco_modificado);
            passageiro_modificado.setNome(nome_modificado);
            passageiro_modificado.setProfissao(profissao_modificado);
            passageiro_modificado.setResponsavel(passageiro_responsavel_modificado);
            passageiro_modificado.setTelefone(telefone_modificado);

            PessoaController.alterarPessoa(passageiro_modificado.getParentId(),
                    passageiro_modificado.getNome(), passageiro_modificado.getEndereco());

            // sending it to DAO class to finally insert it into BD
            passageiro_dao.alterarPassageiro(passageiro_modificado);
        } else {
            System.out.println("Passageiro nao encontrado para alteracao");
        }
    }

    public static void deletarPassageiro(int id) {
        PassageiroDAO passageiro_dao = new PassageiroDAO();

        if (passageiro_dao.getPassageiroById(id) != null) {
            passageiro_dao.deletarPassageiro(id);
        } else {
            System.out.println("Passageiro nao encontrado para deletar");
        }
    }
}
