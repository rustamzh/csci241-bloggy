package database;

import play.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseJDBC implements Database {

	private static final String USER = "r.zhumagambetov";
	private static final String PASSWORD = "31NIHY1";
	private static final String PORT = "80";
	private static final String SERVER = "46.101.171.158";
	private static final String DATABASE = "rustam_zhumagambetov";
	private static final String DBMS = "mysql";
	/*private static final String USER = "root";
	private static final String PASSWORD = "123456789abc";
	private static final String PORT = "3306";
	private static final String SERVER = "localhost";
	private static final String DATABASE = "bloggybase";
	private static final String DBMS = "mysql";*/
	
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
