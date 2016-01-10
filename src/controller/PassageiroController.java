package controller;

import java.text.ParseException;
import java.util.Date;

import dao.PassageiroDAO;
import model.Constants;
import model.Passageiro;

public class PassageiroController {
	public static void cadastrarPassageiro(String nome, String endereco,
			String codigo, String cpf, String telefone, String profissao,
			String data_de_nascimento, String responsavel) {
		// treating non-String arguments...
		Passageiro passageiro_responsavel = stringToPassageiro(responsavel);
		Date date_data_de_nascimento = null;
		try {
			date_data_de_nascimento = Constants.DATE_FORMAT.parse(data_de_nascimento);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// instantiating the new object...
		Passageiro novo_passageiro = new Passageiro(nome, endereco, codigo,
				cpf, telefone, profissao, date_data_de_nascimento, passageiro_responsavel);

		// sending it to its respectively DAO class to finally insert it into BD
		PassageiroDAO passageiro_dao = new PassageiroDAO();

		passageiro_dao.cadastrarPassageiro(novo_passageiro);
	}

	private static Passageiro stringToPassageiro(String responsavel) {
		// TODO Auto-generated method stub
		return null;
	}
}
