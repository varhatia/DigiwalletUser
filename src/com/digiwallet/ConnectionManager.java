package com.digiwallet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

import org.h2.jdbcx.JdbcConnectionPool;

public class ConnectionManager {

	private static final String DRIVER_CLASS = "org.h2.Driver";
	private static final String URL = "jdbc:h2:{0};AUTO_SERVER=TRUE";

	private JdbcConnectionPool cp = null;
	private String completeUrl = URL;

	private static ConnectionManager connectionManager;
	
	public static ConnectionManager getInstance()
	{
		if(connectionManager == null)
		{
			connectionManager = new ConnectionManager();
		}
		return connectionManager;
	}
	
	private ConnectionManager() {
		start();
	}

	public void start() {
		try {
			System.out
					.println("***************Starting H2 DB Service**************************");
			System.out.println("Loading H2 Driver");
			Class.forName(DRIVER_CLASS);
			// Sentinel connection to start the database engine and keep it open

			System.out.println("Creating H2 Connection Pool");

			ConnectionProperties properties = new ConnectionProperties();
			completeUrl = MessageFormat.format(URL,
					properties.getProperty(ConnectionProperties.DB_PATH));
			cp = JdbcConnectionPool.create(completeUrl,
					properties.getProperty(ConnectionProperties.DB_USER),
					properties.getProperty(ConnectionProperties.DB_PASSWORD));

			System.out.println("Starting H2 Console");
			System.out.println("H2 Console service at http://localhost:8082");
			System.out
					.println("***************H2 DB Service Started**************************");

		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load H2 JDBC driver " + DRIVER_CLASS);
		} catch (Exception e) {
			System.out.println("Unable to initialize H2 DB service ");
		}
	}

	public void stop() {
		cp.dispose();
	}

	public String getURL() {
		return completeUrl;
	}

	public Connection getConnection() {
		try {
			return cp.getConnection();
		} catch (SQLException e) {
			System.out.println("Could not connect");
			e.printStackTrace();
		}
		return null;
	}

	public List<User> getAllUsers(Connection connection) {
		UserDAO userDAO = new UserDAO();
		return userDAO.getAllUsers(connection);
	}

	public User getUser(Connection connection, String phoneNumber,
			String password) {

		UserDAO userDAO = new UserDAO();
		return userDAO.getUser(connection, phoneNumber, password);
	}

	public int addUser(Connection connection, User user) {
		UserDAO userDAO = new UserDAO();
		return userDAO.addUser(connection, user);
	}

	public List<Place> getPlaces(Connection connection, String type) {

		PlaceDAO placeDAO = new PlaceDAO();
		return placeDAO.getPlaces(connection, type);
	}

	public List<Place> getPlaces(Connection connection, String type, String shopId) {

		PlaceDAO placeDAO = new PlaceDAO();
		return placeDAO.getPlaces(connection, type, shopId);
	}

	public List<Offer> getOffers(Connection connection) {

		OfferDAO offerDAO = new OfferDAO();
		return offerDAO.getOffers(connection);
	}

	public List<Shop> getShops(Connection connection, String custId) {

		ShopDAO shopDAO = new ShopDAO();
		return shopDAO.getShops(connection, custId);
	}

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DriverManager
					.getConnection("jdbc:h2:~/test;USER=sa;PASSWORD=");
			if (conn != null) {
				System.out.println("Connected");
			}

			ConnectionManager connectionManager = new ConnectionManager();
			Connection connection2 = connectionManager.getConnection();

			if (connection2 != null) {
				System.out.println("Connected2");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
