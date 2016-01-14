package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Constants;
import model.Modal;
import model.Percurso;
import dao.ModalDAO;

public class ModalController {
    public static int cadastrarModal(String[] codigos_percursos, String tipo, String codigo,
            String companhia, String capacidade, String modelo, String ano_fabricacao,
            String em_manutencao, String em_uso, String data_manutencao) {
        int id = 0;
        ModalDAO modal_dao = new ModalDAO();
        Modal novo_modal = getModalByCodigo(codigo);

        // testing if codigo is unique...
        if (novo_modal == null) {
            try {
                novo_modal =
                        new Modal(null, tipo, codigo, companhia, Integer.parseInt(capacidade),
                                modelo, Integer.parseInt(ano_fabricacao),
                                Integer.parseInt(em_manutencao), Integer.parseInt(em_uso),
                                Constants.DATETIME_FORMAT.parse(data_manutencao));

                // sending it to DAO class to finally insert it into BD
                id = modal_dao.cadastrarModal(novo_modal);

                // setting all percursos chosen to be made by this modal with it's id...
                for (String codigo_percurso : codigos_percursos) {
                    Percurso temp_percurso_modificado =
                            PercursoController.getPercursoByCodigo(codigo_percurso);
                    PercursoController.alterarPercurso(temp_percurso_modificado.getId(), "" + id,
                            "" + temp_percurso_modificado.getHorasDuracaoPercurso(),
                            temp_percurso_modificado.getCodigoAeroporto());
                }
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out
                    .println("Codigo ja cadastrado. Modal pode ja ter sido cadastrado no sistema");
        }

        return id;
    }

    public static ArrayList<Modal> listarModais(String tipo_consultado, String codigo_consultado,
            String companhia_consultada, String capacidade_consultada, String modelo_consultado,
            String ano_fabricacao_consultado) {
        ModalDAO modal_dao = new ModalDAO();
        HashMap<String, String> conditions = new HashMap<String, String>();

        conditions.put("tipo", tipo_consultado);
        conditions.put("codigo", codigo_consultado);
        conditions.put("companhia", companhia_consultada);
        conditions.put("capacidade", "" + capacidade_consultada);
        conditions.put("modelo", modelo_consultado);
        conditions.put("ano_fabricacao", "" + ano_fabricacao_consultado);

        return modal_dao.listarModais(conditions);
    }

    public static Modal getModalById(int id) {
        ModalDAO modal_dao = new ModalDAO();

        return modal_dao.getModalById(id);
    }

    public static Modal getModalByCodigo(String codigo) {
        ModalDAO modal_dao = new ModalDAO();

        return modal_dao.getModalByCodigo(codigo);
    }

    public static void alterarModal(int id, String[] codigos_percursos_modificados,
            String companhia_modificada, String capacidade_modificada,
            String em_manutencao_modificado, String em_uso_modificado,
            String data_manutencao_modificada) {
        ModalDAO modal_dao = new ModalDAO();

        Modal modal_modificado = getModalById(id);
        if (modal_modificado != null) {
            try {
                modal_modificado.setCapacidade(Integer.parseInt(capacidade_modificada));
                modal_modificado.setCompanhia(companhia_modificada);
                modal_modificado.setDataManutencao(Constants.DATE_FORMAT
                        .parse(data_manutencao_modificada));
                modal_modificado.setEmManutencao(Integer.parseInt(em_manutencao_modificado));
                modal_modificado.setEmUso(Integer.parseInt(em_uso_modificado));

                modal_dao.alterarModal(modal_modificado);

                for (String codigo_percurso_modificado : codigos_percursos_modificados) {
                    Percurso percurso_modificado =
                            PercursoController.getPercursoByCodigo(codigo_percurso_modificado);
                    PercursoController.alterarPercurso(percurso_modificado.getId(), "" + id, ""
                            + percurso_modificado.getHorasDuracaoPercurso(),
                            percurso_modificado.getCodigoAeroporto());
                }
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("Modal nao encontrado para alteracao");
        }
    }

    public static void deletarModal(int id) {
        ModalDAO modal_dao = new ModalDAO();

        if (modal_dao.getModalById(id) != null) {
            modal_dao.deletarModal(id);
        } else {
            System.out.println("Modal nao encontrado para deletar");
        }
    }
}
