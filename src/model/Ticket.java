package model;

public class Ticket {
	private int numero;
	private String id_localizador;

	// (BEGIN) CONSTRUCTORS
	/*
	 * Constructor Pattern
	 */
	public Ticket(int numero, String id_localizador) {
		this.numero = numero;
		this.id_localizador = id_localizador;
	}

	/*
	 * This constructor handles all arguments as String types and treat each of
	 * them to successfully instantiate a Ticket object. It's useful when
	 * getting values from java swing
	 */
	public Ticket(String numero, String id_localizador) {
		// treating...
		int int_numero = Integer.parseInt(numero);

		// instantiating...
		this.numero = int_numero;
		this.id_localizador = id_localizador;
	}

	// (END) CONSTRUCTORS

	// (BEGIN) GETTERS & SETTERS
	public int getNumero() {
		return numero;
	}

	public String getIdLocalizador() {
		return id_localizador;
	}

	// (END) GETTERS & SETTERS
}
