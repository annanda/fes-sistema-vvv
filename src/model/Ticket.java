package model;

public class Ticket {
    private int id;
    private int numero;
    private String id_localizador;
    private Reserva reserva;
    private Percurso percurso;

    // (BEGIN) CONSTRUCTORS
    public Ticket(int numero, String id_localizador, Reserva reserva, Percurso percurso) {
        this.id = 0;
        this.numero = numero;
        this.reserva = reserva;
        this.percurso = percurso;

        if (id_localizador.equals(Constants.GENERATE)) {
            this.id_localizador =
                    this.id + "vvvp=" + this.percurso.getId() + "r=" + this.reserva.getId();
        } else {
            this.id_localizador = id_localizador;
        }
    }

    public Ticket(int id, int numero, String id_localizador, Reserva reserva, Percurso percurso) {
        this.id = id;
        this.numero = numero;
        this.id_localizador = id_localizador;
        this.reserva = reserva;
        this.percurso = percurso;
    }

    // (END) CONSTRUCTORS

    // (BEGIN) GETTERS & SETTERS
    public int getNumero() {
        return numero;
    }

    public String getIdLocalizador() {
        return id_localizador;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public Percurso getPercurso() {
        return percurso;
    }

    public int getId() {
        return id;
    }

    // (END) GETTERS & SETTERS
}
