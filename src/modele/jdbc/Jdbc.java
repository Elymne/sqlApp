package modele.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class Jdbc {

	private static Jdbc singleton = null;

	private String driverType;
	private String serverName;
	private String networkProtocol;
	private int portNumber;
	private String databaseName;
	private String userName;
	private String userMdp;

	private Connection connexion = null;

	public Jdbc(String driverType, String serverName, String networkProtocol, int portNumber, String databaseName,
			String userName, String userMdp) {
		super();
		this.driverType = driverType;
		this.serverName = serverName;
		this.networkProtocol = networkProtocol;
		this.portNumber = portNumber;
		this.databaseName = databaseName;
		this.userName = userName;
		this.userMdp = userMdp;
	}
	
	public static Jdbc creer(String driverType, String serverName, String networkProtocol, int portNumber,
			String databaseName, String userName, String userMdp) {
		if (singleton == null)
			singleton = new Jdbc(driverType, serverName, networkProtocol, portNumber, databaseName, userName, userMdp);
		return singleton;
	}

	public Jdbc(String userName, String userMdp) {
		super();
		this.driverType = "thin";
		this.serverName = "soracle3";
		this.networkProtocol = "TCP";
		this.portNumber = 1521;
		this.databaseName = "db1";
		this.userName = userName;
		this.userMdp = userMdp;
	}
	
	public static Jdbc creer(String userName, String userMdp) {
		if (singleton == null)
			singleton = new Jdbc(userName, userMdp);
		return singleton;
	}
	
	public static Jdbc getInstance() {
		return singleton;
	}
	
	public void connection() throws SQLException {
		OracleDataSource oracleDS = new OracleDataSource();
		oracleDS.setDriverType(this.driverType);
		oracleDS.setServerName (this.serverName);
		oracleDS.setNetworkProtocol (this.networkProtocol);
		oracleDS.setPortNumber(this.portNumber);
		oracleDS.setDatabaseName (this.databaseName);
		this.connexion = oracleDS.getConnection(this.userName,this.userMdp);
		this.connexion.setAutoCommit(true);
	}
	
	public void deconnection() throws SQLException {
		this.connexion.close();
	}
	
	public Connection getConnection() {
		return this.connexion;
	}

	public static Jdbc getSingleton() {
		return singleton;
	}

	public static void setSingleton(Jdbc singleton) {
		Jdbc.singleton = singleton;
	}

	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getNetworkProtocol() {
		return networkProtocol;
	}

	public void setNetworkProtocol(String networkProtocol) {
		this.networkProtocol = networkProtocol;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMdp() {
		return userMdp;
	}

	public void setUserMdp(String userMdp) {
		this.userMdp = userMdp;
	}

	public Connection getConnexion() {
		return connexion;
	}

	public void setConnexion(Connection connexion) {
		this.connexion = connexion;
	}
}
