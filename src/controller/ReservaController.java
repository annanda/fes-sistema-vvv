package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Passageiro;
import model.Reserva;
import dao.ReservaDAO;

public class ReservaController {
    public static HashMap<String, Integer> cadastrarReserva(String codigo, int qtd_parcelas,
            String tipo_pagamento, double valor, boolean status, String[] ids_passageiros,
            int id_reservante, int id_viagem) {
        int id_reserva = 0;
        HashMap<String, Integer> ids_map = new HashMap<String, Integer>();
        ReservaDAO reserva_dao = new ReservaDAO();
        ArrayList<Passageiro> lista_de_passageiros =
                PassageiroController.takeListaDePassageirosById(ids_passageiros);
        Reserva nova_reserva =
                new Reserva(codigo, null, status, valor, tipo_pagamento, qtd_parcelas,
                        UsuarioController.getUsuarioById(id_reservante), lista_de_passageiros,
                        ViagemController.getViagemById(id_viagem));

        if (reserva_dao.getReservaByCodigo(nova_reserva.getCodigo()) == null) {
            id_reserva = reserva_dao.cadastrarReserva(nova_reserva);
            ids_map.put("id_reserva", id_reserva);

            for (int i = 0; i < lista_de_passageiros.size(); i++) {
                int temp_id_passageiro = lista_de_passageiros.get(i).getId();
                int temp_id_reserva_passageiro =
                        cadastrarReservaPassageiro(id_reserva, temp_id_passageiro);
                if (temp_id_reserva_passageiro > 0) {
                    ids_map.put("id_passageiro" + i, temp_id_passageiro);
                }
            }
        } else {
            System.out.println("Reserva ja cadastrada no sistema");
        }

        return ids_map;
    }

    public static int cadastrarReservaPassageiro(int id_reserva, int id_passageiro) {
        int id = 0;
        ReservaDAO reserva_dao = new ReservaDAO();

        id = reserva_dao.cadastrarReservaPassageiro(id_reserva, id_passageiro);

        return id;
    }

    public static ArrayList<Reserva> listarReservas(String id_viagem_consultada,
            String id_usuario_consultado, String data_da_reserva_consultada,
            String status_consultado, String tipo_pagamento_consultado) {
        HashMap<String, String> conditions = new HashMap<String, String>();
        ReservaDAO reserva_dao = new ReservaDAO();

        conditions.put("id_viagem", id_viagem_consultada);
        conditions.put("id_usuario", id_usuario_consultado);
        conditions.put("data_da_reserva", data_da_reserva_consultada);
        conditions.put("status", status_consultado);
        conditions.put("tipo_pagamento", tipo_pagamento_consultado);

        return reserva_dao.listarReservas(conditions);
    }

    public static Reserva getReservaById(int id) {
        ReservaDAO reserva_dao = new ReservaDAO();

        return reserva_dao.getReservaById(id);
    }

    public static Reserva getReservaByCodigo(String codigo) {
        ReservaDAO reserva_dao = new ReservaDAO();

        return reserva_dao.getReservaByCodigo(codigo);
    }

    public static ArrayList<Passageiro> getListaDePassageirosById(int id_reserva) {
        ReservaDAO reserva_dao = new ReservaDAO();

        return reserva_dao.getListaDePassageirosById(id_reserva);
    }

    public static void alterarReserva(int id, boolean status_modificado,
            String tipo_pagamento_modificado, int qtd_parcelas_modificada) {
        ReservaDAO reserva_dao = new ReservaDAO();
        Reserva reserva_modificada = null;

        if ((reserva_modificada = reserva_dao.getReservaById(id)) != null) {
            reserva_modificada.setQtdParcelas(qtd_parcelas_modificada);
            reserva_modificada.setStatus(status_modificado);
            reserva_modificada.setTipoPagamento(tipo_pagamento_modificado);

            reserva_dao.alterarReserva(reserva_modificada);
        } else {
            System.out.println("Reserva nao encontrada para alteracao");
        }
    }

    public static void deletarReserva(int id) {
        ReservaDAO reserva_dao = new ReservaDAO();

        if (reserva_dao.getReservaById(id) != null) {
            reserva_dao.deletarReserva(id);
        } else {
            System.out.println("Reserva nao encontrada para deletar");
        }
    }
}
