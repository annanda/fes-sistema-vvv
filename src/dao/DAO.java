package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	private Connection connection;
	private Statement statement;

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

	protected void disconnect() {
		try {
			if (!statement.isClosed()) {
				statement.close();
				// System.out.println("Statement destroyed succesfully!");
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
