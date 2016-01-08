package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import model.Constants;

public class DAO {
	protected Connection connection;
	protected Statement statement;
	protected ResultSet result_set;

	protected String likeFactory(HashMap<String, String> conditions) {
		String sql_like_condition = "";
		Object[] keys = conditions.keySet().toArray();
		int count = keys.length;

		for (Object key : keys) {
			sql_like_condition += key + " LIKE '%" + conditions.get(key) + "%'";

			if (count != 1) {
				sql_like_condition += " AND ";
			}

			count--;
		}

		return sql_like_condition;
	}

	/**
	 * @param tabela
	 * @param conditions
	 * @return
	 */
	protected String deleteFactory(String tabela, String conditions) {
		String query = "DELETE FROM " + tabela + " WHERE " + conditions
				+ Constants.SEMICOLON;

		System.out.println(query);

		return query;
	}

	/**
	 * @param tabela
	 * @param colunas
	 * @param valores
	 * @param conditions
	 * @return
	 */
	protected String updateFactory(String tabela,
			HashMap<String, String> coluna_valor, String conditions) {
		Object[] colunas = coluna_valor.keySet().toArray();
		int count = colunas.length;
		String query = "UPDATE " + tabela + " SET ";

		for (Object coluna : colunas) {
			query += coluna + " = '" + coluna_valor.get(coluna)
					+ Constants.SINGLE_QUOTE;

			if (count != 1) {
				query += " WHERE " + conditions + Constants.COMMA;
			} else {
				query += Constants.SEMICOLON;
			}

			count--;
		}

		System.out.println(query);

		return query;
	}

	/**
	 * @param tabela
	 * @param colunas
	 * @param conditions
	 * @return
	 */
	protected String selectFactory(String tabela, String[] colunas,
			String conditions) {
		int count = colunas.length;
		String query = "SELECT ";

		for (String coluna : colunas) {
			query += coluna;

			if (count == 1) {
				query += " FROM " + tabela;
				if (!conditions.isEmpty()) {
					query += " WHERE " + conditions;
				}
				query += Constants.SEMICOLON;
			} else {
				query += Constants.COMMA;
			}

			count--;
		}

		System.out.println(query);

		return query;
	}

	/**
	 * @param tabela
	 * @param valores
	 * @return
	 */
	protected String insertFactory(String tabela, String[] valores) {
		int count = valores.length;
		String query = "INSERT INTO " + tabela + " VALUES('";

		for (String valor : valores) {
			query += valor;

			if (count == 1) {
				query += "')" + Constants.SEMICOLON;
			} else {
				query += Constants.SINGLE_QUOTE + Constants.COMMA
						+ Constants.SINGLE_QUOTE;
			}

			count--;
		}

		System.out.println(query);

		return query;
	}

	/*
	 * Connect to the database chosen in ConnectionFactory
	 */
	protected void connect() {
		connection = ConnectionFactory.createConnection();
		// System.out.println("Connected!");
		try {
			statement = connection.createStatement();
			// System.out.println("Statement created succesfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Disconnect from database connected with Connect method
	 */
	protected void disconnect() {
		try {
			if (!statement.isClosed()) {
				statement.close();
				// System.out.println("Statement destroyed successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (!connection.isClosed()) {
				connection.close();
				// System.out.println("Disconnected!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
