package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.PontoDeVenda;
import dao.PontoDeVendaDAO;

public class PontoDeVendaController {
    public static int cadastrarPontoDeVenda(String nome, String endereco, String telefone,
            String codigo, String cnpj) {
        int id = 0;
        PontoDeVendaDAO ponto_de_venda_dao = new PontoDeVendaDAO();

        if (ponto_de_venda_dao.getPontoDeVendaByCodigo(codigo) == null) {
            id =
                    ponto_de_venda_dao.cadastrarPontoDeVenda(new PontoDeVenda(nome, endereco,
                            telefone, codigo, cnpj));
        } else {
            System.out
                    .println("Codigo ja cadastrado. Ponto de Venda pode ja ter sido cadastrado no sistema");
        }

        return id;
    }

    public static ArrayList<PontoDeVenda> listarPontosDeVenda(String nome_consultado,
            String endereco_consultado, String cnpj_consultado) {
        PontoDeVendaDAO ponto_de_venda_dao = new PontoDeVendaDAO();
        HashMap<String, String> conditions = new HashMap<String, String>();

        conditions.put("nome", nome_consultado);
        conditions.put("endereco", endereco_consultado);
        conditions.put("cnpj", cnpj_consultado);

        return ponto_de_venda_dao.listarPontosDeVenda(conditions);
    }

    public static PontoDeVenda getPontoDeVendaById(int id) {
        PontoDeVendaDAO ponto_de_venda_dao = new PontoDeVendaDAO();

        return ponto_de_venda_dao.getPontoDeVendaById(id);
    }

    public static PontoDeVenda getPontoDeVendaByCodigo(String codigo) {
        PontoDeVendaDAO ponto_de_venda_dao = new PontoDeVendaDAO();

        return ponto_de_venda_dao.getPontoDeVendaByCodigo(codigo);
    }

    public static void alterarPontoDeVenda(int id, String nome_modificado,
            String endereco_modificado, String telefone_modificado) {
        PontoDeVendaDAO ponto_de_venda_dao = new PontoDeVendaDAO();
        PontoDeVenda ponto_de_venda_modificado = getPontoDeVendaById(id);

        if (ponto_de_venda_modificado != null) {
            ponto_de_venda_modificado.setNome(nome_modificado);
            ponto_de_venda_modificado.setEndereco(endereco_modificado);
            ponto_de_venda_modificado.setTelefone(telefone_modificado);

            ponto_de_venda_dao.alterarPontoDeVenda(ponto_de_venda_modificado);
        } else {
            System.out.println("Ponto de Venda nao encontrado para alteracao");
        }
    }

    public static void deletarPontoDeVenda(int id) {
        PontoDeVendaDAO ponto_de_venda_dao = new PontoDeVendaDAO();

        if (ponto_de_venda_dao.getPontoDeVendaById(id) != null) {
            ponto_de_venda_dao.deletarPontoDeVenda(id);
        } else {
            System.out.println("Ponto de Venda nao encontrado para deletar");
        }
    }
}
