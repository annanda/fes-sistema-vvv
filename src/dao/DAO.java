package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	private static Connection connection = null;

	public Connection getConnection() {
		return connection;
	}

	public static Connection Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost/vvv_db?user=root");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

	public static void Disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public static void main(String[] args) {
	// DAO.Connect();
	// try {
	// System.out.println(connection.isClosed());
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// DAO.Disconnect();
	// try {
	// System.out.println(connection.isClosed());
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
}
