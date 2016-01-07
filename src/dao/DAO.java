package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	private Connection connection;
	private Statement statement;
	
	protected void connect() {
		connection = ConnectionFactory.createConnection();
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void disconnect() {
		try {
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (!statement.isClosed()) {
				statement.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
