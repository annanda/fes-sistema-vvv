package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class Reserva {
    private int id;
    private String codigo;
    private Date data_da_reserva;
    private boolean status;
    private double valor;
    private String tipo_pagamento;
    private int qtd_parcelas;
    private Usuario reservante;
    private ArrayList<Passageiro> passageiros;
    private Viagem viagem;

    // (BEGIN) CONSTRUCTORS
    public Reserva(String codigo, Date data_da_reserva, boolean status, double valor,
            String tipo_pagamento, int qtd_parcelas, Usuario reservante,
            ArrayList<Passageiro> passageiros, Viagem viagem) {
        this.id = 0;
        this.codigo = codigo;
        this.data_da_reserva = (data_da_reserva == null) ? new Date() : data_da_reserva;
        setStatus(status);
        this.valor = valor;
        setTipoPagamento(tipo_pagamento);
        setQtdParcelas(qtd_parcelas);
        this.reservante = reservante;
        this.passageiros = passageiros;
        this.viagem = viagem;
    }

    public Reserva(int id, String codigo, Date data_da_reserva, boolean status, double valor,
            String tipo_pagamento, int qtd_parcelas, Usuario reservante,
            ArrayList<Passageiro> passageiros, Viagem viagem) {
        this.id = id;
        this.codigo = codigo;
        this.data_da_reserva = (data_da_reserva == null) ? new Date() : data_da_reserva;
        setStatus(status);
        this.valor = valor;
        setTipoPagamento(tipo_pagamento);
        setQtdParcelas(qtd_parcelas);
        this.reservante = reservante;
        this.passageiros = passageiros;
        this.viagem = viagem;
    }

    // (END) CONSTRUCTORS

    // (BEGIN) GETTERS & SETTERS
    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public Date getDataDaReserva() {
        return data_da_reserva;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public String getTipoPagamento() {
        return tipo_pagamento;
    }

    public void setTipoPagamento(String tipo_pagamento) {
        this.tipo_pagamento = tipo_pagamento;
    }

    public int getQtdParcelas() {
        return qtd_parcelas;
    }

    public void setQtdParcelas(int qtd_parcelas) {
        this.qtd_parcelas = qtd_parcelas;
    }

    public Usuario getReservante() {
        return reservante;
    }

    public ArrayList<Passageiro> getPassageiros() {
        return passageiros;
    }

    public Viagem getViagem() {
        return viagem;
    }

    // (END) GETTERS & SETTERS

    /*
     * Turns your object into a HashMap object with all columns (attributes) (except for its own id)
     * names as keys of type String and their values as values also of type String
     */
    public HashMap<String, String> toHashMap() {
        HashMap<String, String> reserva = new HashMap<String, String>();

        reserva.put("status", "" + this.status);
        reserva.put("tipo_pagamento", this.tipo_pagamento);
        reserva.put("qtd_parcelas", "" + this.qtd_parcelas);

        return reserva;
    }

    public double[] calcularParcelas() {
        double[] valores_parcelas = new double[this.qtd_parcelas];
        DecimalFormat decimal_format = new DecimalFormat("#.00");
        double primeiras_parcelas =
                Double.parseDouble(decimal_format.format(this.valor / this.qtd_parcelas));
        int qtd_primeiras_parcelas = this.qtd_parcelas - 1;

        for (int i = 0; i < qtd_primeiras_parcelas; i++) {
            valores_parcelas[i] = primeiras_parcelas;
        }
        valores_parcelas[qtd_primeiras_parcelas] =
                this.valor - (primeiras_parcelas * qtd_primeiras_parcelas);

        return valores_parcelas;
    }

    public ArrayList<Ticket> gerarTickets() {
        if (this.status) {
            ArrayList<Percurso> plano_de_viagem = this.viagem.getPlanoDeViagem();
            ArrayList<Ticket> tickets = new ArrayList<Ticket>();
            Random randomizador = new Random();

            for (Percurso percurso : plano_de_viagem) {
                tickets.add(new Ticket(randomizador.nextInt(1000000000) + 100000000,
                        Constants.GENERATE, this, percurso));
            }

            return tickets;
        } else {
            return null;
        }
    }
}
