package model;

import static model.Constants.DATETIME_FORMAT;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Reserva {
  protected int id;
  private String codigo;
  private Date data_da_reserva;
  private boolean status;
  private float valor;
  private String tipo_pagamento;
  private int qtd_parcelas;

  // (BEGIN) CONSTRUCTORS
  /*
   * Constructor Pattern
   */
  public Reserva(String codigo, Date data, boolean status, float valor, String tipo_pagamento,
      int qtd_parcelas) {
    this.codigo = codigo;
    this.data_da_reserva = data;
    setStatus(status);
    setValor(valor);
    setTipoPagamento(tipo_pagamento);
    setQtdParcelas(qtd_parcelas);
  }

  /*
   * This constructor handles all arguments as String types and treat each of them to successfully
   * instantiate a Reserva object. It's useful when getting values from java swing
   */
  public Reserva(String codigo, String data, String status, String valor, String tipo_pagamento,
      String qtd_parcelas) throws ParseException {
    // treating...
    Date date_data = DATETIME_FORMAT.parse(data);
    boolean boolean_status = Boolean.parseBoolean(status);
    float float_valor = Float.parseFloat(valor);
    int int_qtd_parcelas = Integer.parseInt(qtd_parcelas);

    // instantiating...
    this.codigo = codigo;
    this.data_da_reserva = date_data;
    setStatus(boolean_status);
    setValor(float_valor);
    setTipoPagamento(tipo_pagamento);
    setQtdParcelas(int_qtd_parcelas);
  }

  // (END) CONSTRUCTORS

  // (BEGIN) GETTERS & SETTERS
  public String getCodigo() {
    return codigo;
  }

  public Date getData() {
    return data_da_reserva;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public float getValor() {
    return valor;
  }

  public void setValor(float valor) {
    this.valor = valor;
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

  // (END) GETTERS & SETTERS

  private float calcularParcelas(float valor, int qtd_parcelas) {
    return valor;
  }

  private ArrayList<Ticket> gerarTickets(boolean status) {
    return null;
  }
}
