package database.create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import application.builder.Card;

public class DatabaseCreator {

	// Logging
	private static final Logger log = LogManager.getLogger(DatabaseCreator.class);

	private static final String HOSTNAME = "mysql.hostinger.de";
	private static final String PORT = "3306";
	private static final String DATABASE_NAME = "u169461487_mw";
	private static final String USER = "u169461487_web";
	private static final String PASSWORD = "RUhhd7af6eGwdbXVsQ";

	private static Connection conn = null;

	public DatabaseCreator() {

		try {
			System.out.println("* Treiber laden");
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception e) {
			log.error("Unable to load driver.", e);
		}
		try {
			System.out.println("* Verbindung aufbauen");
			String url = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE_NAME
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, USER, PASSWORD);

			System.out.println("* Datenbank-Verbindung beenden");
			conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			e.printStackTrace();
		}

	}

	public void insertCards(ArrayList<Card> allCards) throws SQLException {
		try {
			
			connectToDB();
			
			final PreparedStatement statement = conn.prepareStatement("INSERT INTO cards (Name, Type, School) VALUES (?, ?, ?)");
			
			for (Card card : allCards) {
				statement.setString(1, card.getName());
				statement.setObject(2, card.getType());
			}
		} catch (SQLException ex) {
			throw (ex);
		}
	}
	
	private void connectToDB() throws SQLException {
		String url = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE_NAME
				+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		conn = DriverManager.getConnection(url, USER, PASSWORD);
	}
	
	
	
	
	//----------------------------------------------------------------------
	
	public static void main(final String[] args) throws Exception {
		new DatabaseCreator().insertCards(new ArrayList<Card>());
	}
}
