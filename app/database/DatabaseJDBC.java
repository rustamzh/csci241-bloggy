package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import database.Database;

import play.Logger;

public class DatabaseJDBC implements Database {

	/*private static final String USER = "v56jxrify4fkgzze";
	private static final String PASSWORD = "whu1t5b83x003cw5";
	private static final String PORT = "3306";
	private static final String SERVER = "vhw3t8e71xdz9k14.cbetxkdyhwsb.us-east-1.rds.amazonaws.com";
	private static final String DATABASE = "dhrtt8tgflpynwmz";
	private static final String DBMS = "mysql";*/
	private static final String USER = "root";
	private static final String PASSWORD = "123456789abc";
	private static final String PORT = "3306";
	private static final String SERVER = "localhost";
	private static final String DATABASE = "bloggybase";
	private static final String DBMS = "mysql";
	
	private static DatabaseJDBC databaseInstance = null;
	
	public static DatabaseJDBC getInstance() {
		if (databaseInstance == null)
			databaseInstance = new DatabaseJDBC();
		
		return databaseInstance;
	}
	
	private Connection conn = null;
	
	private DatabaseJDBC() {
		
	}
	
	public Connection getConnection() {
		if (conn == null) {
			try {
				Connection temp = null;
				Properties connectionProps = new Properties();
				connectionProps.put("user", USER);
				connectionProps.put("password", PASSWORD);
				temp = DriverManager.getConnection("jdbc:" + DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE, connectionProps);
				conn = temp;
			} catch (SQLException e) {
				Logger.info("SQLException during database connection: " + e.getMessage());
			}
		}
		
		return conn;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
}
