package de.rhistel.logic.db;

import de.rhistel.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Threadsicherer Zugriff auf die
 * Datenbank.
 * <p>
 * TODO 3 Kopieren ausdruecklich fuer diese Klasse erwuenscht
 */
public class DbManager {
	
	//region 0. Konstanten
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	
	private static final String DB_SERVER_IP_ADDRESS = "127.0.0.1";
	private static final String DB_NAME              = "/db_rhfsvs";
	
	private static final String DB_CONNECTION_URL = "jdbc:mariadb://" + DB_SERVER_IP_ADDRESS + DB_NAME;
	
	private static final String DB_USER_NAME = "root";
	private static final String DB_USER_PW   = "";
	//endregion
	
	//region 1. Decl. and Init Attribute
	private static DbManager instance;
	
	private DaoUser daoUser;
	//endregion
	
	//region 2. Konstruktor
	private DbManager() {
		//DAO Objekte generieren
		this.daoUser = new DaoUser();
	}
	//endregion
	
	//region 3. Getter der Instanz
	
	public static synchronized DbManager getInstance() {
		if (instance == null) {
			instance = new DbManager();
		}
		
		return instance;
	}
	//endregion
	
	//region 4. Database Connection
	
	/**
	 * Gibt eine generiert Datenbankverbindung mit Lese(r) als auch Schreibrechten(w)
	 * zurueckt oder null sollte etwas schiefgelaufen sein.
	 *
	 * @return rwDbConnection : {@link Connection} : Verbindung zur Datenbank mit rw - Rechten
	 */
	private Connection getRwDbConnection() {
		Connection rwDbConnection = null;
		
		try {
			//STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);
			
			//STEP 2: Open a connection
			rwDbConnection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USER_NAME, DB_USER_PW);
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return rwDbConnection;
	}
	//endregion
	
	//region 5. Tabellen CRUD Operation
	
	/**
	 * User zum einfuegen in die Datenbank
	 *
	 * @param userToInsert
	 */
	public void insertUserIntoDbTable(User userToInsert) {
		
		//Neue Verbindung erstellen
		Connection dbRwConnection = getRwDbConnection();
		
		//Sicherheitscheck
		if (dbRwConnection != null) {
			this.daoUser.insertDataRecordIntoDbTbl(dbRwConnection, userToInsert);
		}
	}
	
	/**
	 * User zum einfuegen in die Datenbank
	 *
	 * @param usersToInsert : {@link List} - {@link User} : Benutzer zum einfeugen
	 */
	public void insertUsersIntoDbTable(List<User> usersToInsert) {
		
		//Neue Verbindung erstellen
		Connection dbRwConnection = getRwDbConnection();
		
		//Sicherheitscheck
		if (dbRwConnection != null) {
			this.daoUser.insertDataRecordsIntoDbTbl(dbRwConnection, usersToInsert);
		}
	}
	
	/**
	 * User zum aendern in der Datenbanktabelle
	 *
	 * @param userToUpdate : {@link User} : User zum aendern
	 */
	public void updateUsersInDbTable(User userToUpdate) {
		
		//Neue Verbindung erstellen
		Connection dbRwConnection = getRwDbConnection();
		
		//Sicherheitscheck
		if (dbRwConnection != null) {
			this.daoUser.updateDataRecordIntoDbTbl(dbRwConnection, userToUpdate);
		}
	}
	
	/**
	 * User zum aendern in der Datenbanktabelle
	 *
	 * @param usersToUpdate : {@link List} {@link User} : User zum aendern
	 */
	public void updateUsersInDbTable(List<User> usersToUpdate) {
		
		//Neue Verbindung erstellen
		Connection dbRwConnection = getRwDbConnection();
		
		//Sicherheitscheck
		if (dbRwConnection != null) {
			this.daoUser.updateDataRecordsIntoDbTbl(dbRwConnection, usersToUpdate);
		}
	}
	
	
	/**
	 * Liest alle Daten aus der Testtabelle aus
	 *
	 * @return allUsersFromDbTable : {@link List} - {@link User}: Alle Benutzer aus Db-Tabelle
	 */
	public List<User> getAllUsersFromDbTable() {
		//Neue Verbindung erstellen
		Connection dbRwConnection      = getRwDbConnection();
		List<User> allUsersFromDbTable = new ArrayList<>();
		
		//Sicherheitscheck
		if (dbRwConnection != null) {
			allUsersFromDbTable = this.daoUser.getAllDataRecordsFromDbTbl(dbRwConnection);
		}
		
		return allUsersFromDbTable;
	}
	
	/**
	 * Sucht einen User mit der angegebene Id oder gibt null zurueck, sollte
	 * es keinen User mit der Id geben
	 *
	 * @param iId : int : Id des Objekts
	 *
	 * @return searchedUserFromDbTable : {@link User} : Gesuchter User wenn existent ansonsten null
	 */
	public User getUserById(int iId) {
		//Neue Verbindung erstellen
		Connection dbRwConnection = getRwDbConnection();
		User       searchedUser   = null;
		
		//Sicherheitscheck
		if (dbRwConnection != null) {
			searchedUser = this.daoUser.getSpecificDataRecordFromDbTblById(dbRwConnection, iId);
		}
		
		return searchedUser;
	}
	
	/**
	 * Checkt ob ein {@link User} mit dem entsprechenden Name und Passwort in der
	 * Datenbank hinterlegt ist. Ist dies der Fall bekommen wir die Daten des Users
	 * zurueck geliefert.
	 *
	 * @param strUserName : {@link String} : Username nach dem in der Datenbank gesucht wird
	 * @param strUserPw   : {@link String} : Userpasswort nach dem in der Datenbank gesucht wird
	 *
	 * @return searchedUser : {@link User} : Gesuchter {@link User} oder null sollte dieser nicht existieren
	 */
	public User getUserByNameAndPw(String strUserName, String strUserPw) {
		//Neue Verbindung erstellen
		Connection dbRwConnection = getRwDbConnection();
		User       searchedUser   = null;
		
		//Sicherheitscheck
		if (dbRwConnection != null) {
			searchedUser = this.daoUser.getSpecificUserByNameAndPw(dbRwConnection, strUserName, strUserPw);
		}
		
		return searchedUser;
	}
	
	/**
	 * Loescht einen User durch die Angabe der Id
	 *
	 * @param iId : int : id des USers
	 */
	public void deleteUserById(int iId) {
		//Neue Verbindung erstellen
		Connection dbRwConnection = getRwDbConnection();
		
		//Sicherheitscheck
		if (dbRwConnection != null) {
			this.daoUser.deleteDataRecordInDbTblById(getRwDbConnection(), iId);
		}
		
	}
	//endregion
	
	//TODO 5 Wrapping CRUD Methoden und Funktionen fuer das neue daoCustomerObjekt implementieren sowie beim daoUser
}
