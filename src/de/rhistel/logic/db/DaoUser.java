package de.rhistel.logic.db;

import de.rhistel.model.ABaseModel;
import de.rhistel.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Stellt Klasse da die die Daten der users Tabelle auf der Datenbank
 * speichert, aendert und loescht DAO  steht fuer Data Accesss Object
 * Sie enthaelt alle konkreten CRUD Funktionen fuer diese eine DbTabelle
 * TODO 3 Kopieren ausdruecklich fuer diese Klasse erwuenscht
 */
public class DaoUser extends ADao {
	
	//region 0. Konstanten
	private static final String TBL_NAME_USER = "users";
	
	/*
	 * Spaltennamen
	 */
	
	private static final String COL_NAME_USER_NAME                    = "userName";
	private static final String COL_NAME_USER_NAME_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK
			+ "userName"
			+ CHAR_COL_BACK_TICK;
	
	private static final String COL_NAME_USER_PW                    = "userPw";
	private static final String COL_NAME_USER_PW_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK
			+ "userPw"
			+ CHAR_COL_BACK_TICK;
	//endregion
	
	//region 1. Decl. and Attribute
	//endregion
	
	//region 2. Konstruktor
	
	/**
	 * STandard Konstruktor zum direkten setzen
	 */
	public DaoUser() {
		super(TBL_NAME_USER);
	}
	//endregion
	
	
	//region 3. CRUD Operationen
	
	/**
	 * Fuegt einen einzelen Datensatz in die Datebanktabelle ein
	 *
	 * @param dbRwConnection           : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelToInsertIntoDbTable : {@link ABaseModel} : Model welches eingefuegt werden soll
	 */
	@Override
	public void insertDataRecordIntoDbTbl(Connection dbRwConnection, ABaseModel modelToInsertIntoDbTable) {
		
		
		if (modelToInsertIntoDbTable instanceof User) {
			//Decl and Init
			Statement dbStatementToExecute = null;
			
			User userToInsert = (User) modelToInsertIntoDbTable;
			
			
			try {
				//1. Db Connection ist bereits von DbManger generiert
				
				//2. Statement generieren lassen
				dbStatementToExecute = dbRwConnection.createStatement();
				
				/*
				 *3. SQL-String generieren
				 *
				 * INSERT INTO `users`(`userName`, `userPw`) VALUES ('hans','sad')
				 *"INSERT INTO `users`(`userName`, `userPw`) VALUES (\'" + userToInsert.getUserName() + "\',\'" +
				 *			userToInsert.getUserPwSha256HashValue() + "\')";
				 *
				 **/
				
				
				String strSqlStmtInsert = INSERT_TBL + this.strTableName
						+ CHAR_OPEN_BRACKET
						+ COL_NAME_USER_NAME_INC_COL_BACK_TICKS + CHAR_COMMA
						+ COL_NAME_USER_PW_INC_COL_BACK_TICKS
						+ CHAR_CLOSE_BRACKET
						+ VALUES_OPERATOR
						+ CHAR_OPEN_BRACKET
						+ CHAR_VALUE_BACK_TICK + userToInsert.getUserName() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
						+ CHAR_VALUE_BACK_TICK + userToInsert.getUserPwSha256HashValue() + CHAR_VALUE_BACK_TICK
						+ CHAR_CLOSE_BRACKET_SEMICOLON;
				
				//4. SQL - String an Satement objekt zum ausfuerhren geben
				dbStatementToExecute.execute(strSqlStmtInsert);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
				if (dbStatementToExecute != null) {
					//5. Schliessen der des Statements
					try {
						dbStatementToExecute.close();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					}
				}
				
				if (dbRwConnection != null) {
					//6. Schliessen der Verbindung
					try {
						dbRwConnection.close();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * Fuegt eine Liste von Datensaetzen in die Datebanktabelle ein
	 *
	 * @param dbRwConnection            : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelsToInsertIntoDbTable : {@link ABaseModel} : Models welches eingefuegt werden soll
	 */
	@Override
	public void insertDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends ABaseModel> modelsToInsertIntoDbTable) {
		if (!modelsToInsertIntoDbTable.isEmpty()) {
			//Decl and Init
			Statement dbStatementToExecute = null;
			
			try {
				//1. Db Connection ist bereits von DbManger generiert
				
				//2. Statement generieren lassen
				for (ABaseModel modelToInsert : modelsToInsertIntoDbTable) {
					
					if (modelToInsert instanceof User) {
						User userToInsert = (User) modelToInsert;
						
						dbStatementToExecute = dbRwConnection.createStatement();
						
						/*
						 * 3. SQL-String generieren
						 * INSERT INTO `users`(`userName`, `userPw`) VALUES ('hans','sad')
						 *"INSERT INTO `users`(`userName`, `userPw`) VALUES (\'" + userToInsert.getUserName() + "\',\'" +
						 *			userToInsert.getUserPwSha256HashValue() + "\')";
						 */
						String strSqlStmtInsert = INSERT_TBL + this.strTableName
								+ CHAR_OPEN_BRACKET
								+ COL_NAME_USER_NAME_INC_COL_BACK_TICKS + CHAR_COMMA
								+ COL_NAME_USER_PW_INC_COL_BACK_TICKS
								+ CHAR_CLOSE_BRACKET
								+ VALUES_OPERATOR
								+ CHAR_OPEN_BRACKET
								+ CHAR_VALUE_BACK_TICK + userToInsert.getUserName() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
								+ CHAR_VALUE_BACK_TICK + userToInsert.getUserPwSha256HashValue() + CHAR_VALUE_BACK_TICK
								+ CHAR_CLOSE_BRACKET_SEMICOLON;
						
						//4. SQL - String an Satement objekt zum ausfuerhren geben
						dbStatementToExecute.execute(strSqlStmtInsert);
//					dbStatementToExecute.executeUpdate("INSERT...",Statement.RETURN_GENERATED_KEYS);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
				if (dbStatementToExecute != null) {
					//5. Schliessen der des Statements
					try {
						dbStatementToExecute.close();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					}
				}
				
				if (dbRwConnection != null) {
					//6. Schliessen der Verbindung
					try {
						dbRwConnection.close();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					}
				}
			}
		}
	}
	
	
	/**
	 * Aendert einen einzelen Datensatz in der Datebanktabelle
	 *
	 * @param dbRwConnection           : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelToUpdateIntoDbTable : {@link ABaseModel} : Model welches geaendert werden soll
	 */
	@Override
	public void updateDataRecordIntoDbTbl(Connection dbRwConnection, ABaseModel modelToUpdateIntoDbTable) {
		
		if (modelToUpdateIntoDbTable instanceof User) {
			//Decl and Init
			Statement dbStatementToExecute = null;
			User      userToUpdate         = (User) modelToUpdateIntoDbTable;
			
			try {
				//1. Db Connection ist bereits vom DbManager geoeffnet wordem
				
				//2. Statement generieren lassen
				dbStatementToExecute = dbRwConnection.createStatement();
				
				/*
				 * 3. SQL-String generieren
				 *
				 * UPDATE `users` SET `userName`='bernd' WHERE _id = 2
				 *
				 * "UPDATE `tbl_user` " +
				 *   "SET `userName` = \'" + userToUpdate.getUserName() + "\', "
				 *   + "`userPw` = \'" + userToUpdate.getUserPwSha256HashValue() + "\' "
				 * + "WHERE _id = " + userToUpdate.getId();
				 *UPDATE `users` SET `userName`='bernd' WHERE _id = 2
				 */
				
				
				String strSqlStmtUserUpdate =
						UPDATE_TBL + this.strTableName
								+ SET_OPERATOR
								+ COL_NAME_USER_NAME_INC_COL_BACK_TICKS
								+ EQUALS_OPERATOR
								+ CHAR_VALUE_BACK_TICK
								+ userToUpdate.getUserName()
								+ CHAR_VALUE_BACK_TICK + CHAR_COMMA
								+ COL_NAME_USER_PW_INC_COL_BACK_TICKS
								+ EQUALS_OPERATOR
								+ CHAR_VALUE_BACK_TICK
								+ userToUpdate.getUserPwSha256HashValue()
								+ CHAR_VALUE_BACK_TICK
								+ WHERE_CONDITION + COL_NAME_ID_INC_COL_BACK_TICKS
								+ EQUALS_OPERATOR + userToUpdate.getId() + CHAR_SEMICOLON;
				
				//4. SQL - String an Satement objekt zum ausfuerhren geben
				dbStatementToExecute.executeUpdate(strSqlStmtUserUpdate);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
				if (dbStatementToExecute != null) {
					//5. Schliessen der des Statements
					try {
						dbStatementToExecute.close();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					}
				}
				
				if (dbRwConnection != null) {
					//6. Schliessen der Verbindung
					try {
						dbRwConnection.close();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * Aendert eine Liste von Datensaetzen in der Datebanktabelle
	 *
	 * @param dbRwConnection            : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelsToUpdateIntoDbTable : {@link ABaseModel} : Models welches geaendert werden soll
	 */
	@Override
	public void updateDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends ABaseModel> modelsToUpdateIntoDbTable) {
		if (!modelsToUpdateIntoDbTable.isEmpty()) {
			
			for (ABaseModel modelToUpdate : modelsToUpdateIntoDbTable) {
				//Decl and Init
				Statement dbStatementToExecute = null;
				
				if (modelToUpdate instanceof User) {
					User userToUpdate = (User) modelToUpdate;
					
					try {
						//1. Db Connection ist bereits vom DbManager geoeffnet wordem
						
						//2. Statement generieren lassen
						dbStatementToExecute = dbRwConnection.createStatement();
						
						/*
						 * 3. SQL-String generieren
						 *
						 * UPDATE `users` SET `userName`='bernd' WHERE _id = 2
						 * "UPDATE `tbl_user` " +
						 *   "SET `userName` = \'" + userToUpdate.getUserName() + "\', "
						 *   + "`userPw` = \'" + userToUpdate.getUserPwSha256HashValue() + "\' "
						 * + "WHERE _id = " + userToUpdate.getId();
						 *
						 */
						
						
						String strSqlStmtUserUpdate =
								UPDATE_TBL + this.strTableName
										+ SET_OPERATOR
										+ COL_NAME_USER_NAME_INC_COL_BACK_TICKS
										+ EQUALS_OPERATOR
										+ CHAR_VALUE_BACK_TICK
										+ userToUpdate.getUserName()
										+ CHAR_VALUE_BACK_TICK + CHAR_COMMA
										+ COL_NAME_USER_PW_INC_COL_BACK_TICKS
										+ EQUALS_OPERATOR
										+ CHAR_VALUE_BACK_TICK
										+ userToUpdate.getUserPwSha256HashValue()
										+ CHAR_VALUE_BACK_TICK
										+ WHERE_CONDITION + COL_NAME_ID_INC_COL_BACK_TICKS
										+ EQUALS_OPERATOR + userToUpdate.getId() + CHAR_SEMICOLON;
						
						//4. SQL - String an Satement objekt zum ausfuerhren geben
						dbStatementToExecute.executeUpdate(strSqlStmtUserUpdate);
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						
						if (dbStatementToExecute != null) {
							//5. Schliessen der des Statements
							try {
								dbStatementToExecute.close();
							} catch (SQLException sqlEx) {
								sqlEx.printStackTrace();
							}
						}
						
						if (dbRwConnection != null) {
							//6. Schliessen der Verbindung
							try {
								dbRwConnection.close();
							} catch (SQLException sqlEx) {
								sqlEx.printStackTrace();
							}
						}
					}
				}
			}
		}
		
	}
	
	/**
	 * Gibt alle Datensaetze eine Datenbanktabelle als {@link List} zurueck
	 *
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 *
	 * @return allDataRecordsFromDbTbl : {@link List} Objects extended from {@link ABaseModel} : Liste aller Datensaetze
	 */
	@Override
	public List<User> getAllDataRecordsFromDbTbl(Connection dbRwConnection) {
		List<User> allUsersFromDbTable = new ArrayList<>();
		
		//Decl. and Init
		
		Statement dbStatementToExecute = null;
		
		try {
			//1. Rw Db Connection ist bereits vom DbManger geoeffenent und Integriert
			
			//2. Geneieren des Statenements
			dbStatementToExecute = dbRwConnection.createStatement();
			
			//3. Query generieren und  absetzen und Ergebnismenge merken
			String strSqlStmtGetAll = SELECT_TBL + this.strTableName;
			
			ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(strSqlStmtGetAll);
			
			//4. ResultSet == Ergebnismenge durchlaufen bis kein Datensaezte mehr da sind
			while (resultSetFromExecutedQuery.next()) {
				
				//5. Aus der Ergebenismenge einen User beschafften
				User userFromDbTable = getModelFromResultSet(resultSetFromExecutedQuery);
				
				//6. Modelobjekt zur passenden Liste adden
				allUsersFromDbTable.add(userFromDbTable);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if (dbStatementToExecute != null) {
				//5. Schliessen der des Statements
				try {
					dbStatementToExecute.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
			
			if (dbRwConnection != null) {
				//6. Schliessen der Verbindung
				try {
					dbRwConnection.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
		
		return allUsersFromDbTable;
	}
	
	/**
	 * Gibt einen bestimmten Datensatz einer Datenbanktabelle zurueck
	 *
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param iId
	 *
	 * @return specificDataRecordFoundById : {@link ABaseModel}  oder abgeleitet davon: Gesuchtes Objekt oder null,
	 * sollte es dieses nicht geben
	 */
	@Override
	public User getSpecificDataRecordFromDbTblById(Connection dbRwConnection, int iId) {
		User searchedUser = null;
		
		//Decl. and Init
		
		Statement dbStatementToExecute = null;
		
		try {
			//1. Rw Db Connection ist bereits vom DbManger geoeffenent und Integriert
			
			//2. Geneieren des Statenements
			dbStatementToExecute = dbRwConnection.createStatement();
			
			/*
			 * 3. Query generieren und  absetzen und Ergebnismenge merken
			 * SELECT * FROM users WHERE `_id` = 5
			 */
			String strSqlStmtGetAll = SELECT_TBL + this.strTableName
					+ WHERE_CONDITION + COL_NAME_ID_INC_COL_BACK_TICKS + EQUALS_OPERATOR + iId;
			
			ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(strSqlStmtGetAll);
			
			//4. ResultSet == Ergebnismenge durchlaufen bis kein Datensaezte mehr da sind
			if (resultSetFromExecutedQuery.first()) {
				
				//5. Aus der Ergebenismenge einen User beschafften
				User userFromDbTable = getModelFromResultSet(resultSetFromExecutedQuery);
				
				//6. Modelobjekt zur passenden Liste adden
				searchedUser = userFromDbTable;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if (dbStatementToExecute != null) {
				//5. Schliessen der des Statements
				try {
					dbStatementToExecute.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
			
			if (dbRwConnection != null) {
				//6. Schliessen der Verbindung
				try {
					dbRwConnection.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
		
		return searchedUser;
	}
	
	/**
	 * Gibt einen bestimmten Datensatz einer Datenbanktabelle zurueck
	 *
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param strUserName    : {@link String} Benutzername
	 * @param strUserPw      : {@link String} Benutzerpw
	 *
	 * @return searchedUser : {@link User}  oder abgeleitet davon: Gesuchtes Objekt oder null,
	 * sollte es dieses nicht geben
	 */
	public User getSpecificUserByNameAndPw(Connection dbRwConnection, String strUserName, String strUserPw) {
		User searchedUser = null;
		
		//Decl. and Init
		
		Statement dbStatementToExecute = null;
		
		try {
			//1. Rw Db Connection ist bereits vom DbManger geoeffenent und Integriert
			
			//2. Geneieren des Statenements
			dbStatementToExecute = dbRwConnection.createStatement();
			
			/*
			 * 3. Query generieren und  absetzen und Ergebnismenge merken
			 * SELECT * FROM user WHERE `userName` = 'Hans' AND `userPw` = 'geheim'
			 */
			String strSqlStmtGetByUserNamePw = SELECT_TBL + this.strTableName
					+ WHERE_CONDITION
					+ COL_NAME_USER_NAME_INC_COL_BACK_TICKS
					+ EQUALS_OPERATOR + CHAR_VALUE_BACK_TICK + strUserName + CHAR_VALUE_BACK_TICK
					+ AND_OPERATOR
					+ COL_NAME_USER_PW_INC_COL_BACK_TICKS
					+ EQUALS_OPERATOR + CHAR_VALUE_BACK_TICK + strUserPw + CHAR_VALUE_BACK_TICK;
			
			ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(strSqlStmtGetByUserNamePw);
			
			//4. ResultSet == Ergebnismenge durchlaufen bis kein Datensaezte mehr da sind
			if (resultSetFromExecutedQuery.first()) {
				
				//5. Aus der Ergebenismenge einen User beschafften
				User userFromDbTable = getModelFromResultSet(resultSetFromExecutedQuery);
				
				/*
				 * Noch sicherer jetzt einfach checen userFromDbTable != null return true else false und nicht
				 * alles zrueuckgegeben oder in getModelFromResultSet nur UserName und id setzen und pw nicht
				 */
				
				//6. Modelobjekt zur passenden Liste adden
				searchedUser = userFromDbTable;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if (dbStatementToExecute != null) {
				//5. Schliessen der des Statements
				try {
					dbStatementToExecute.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
			
			if (dbRwConnection != null) {
				//6. Schliessen der Verbindung
				try {
					dbRwConnection.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
		
		return searchedUser;
	}
	
	/**
	 * Loescht einen bestimmten Datensatz aus der Datenbanktabelle
	 *
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param iId            : int : Id des Objektes welches in der DbTabelle geloscht werden soll
	 */
	@Override
	public void deleteDataRecordInDbTblById(Connection dbRwConnection, int iId) {
		//Decl. and Init
		Statement dbStatementToExecute = null;
		
		try {
			//1 Db Connection bereits vom DbManager geoeffent
			
			//2. Geneieren des Statenements
			dbStatementToExecute = dbRwConnection.createStatement();
			
			/*
			 * 3. Statement geneireren
			 * String strSqlDeleteUserById = "DELETE FROM `users` WHERE `_id` = " + iId;
			 * String strSqlDeleteUserById = DELETE FROM `users` WHERE _id = 2
			 */
			
			String strSqlDeleteUserById = DELETE_FROM_TBL + this.strTableName
					+ WHERE_CONDITION + COL_NAME_ID_INC_COL_BACK_TICKS + EQUALS_OPERATOR + iId;
			
			dbStatementToExecute.executeUpdate(strSqlDeleteUserById);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if (dbStatementToExecute != null) {
				//5. Schliessen der des Statements
				try {
					dbStatementToExecute.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
			
			if (dbRwConnection != null) {
				//6. Schliessen der Verbindung
				try {
					dbRwConnection.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
	}
	
	//endregion
	
	//region Hilfsmethoden und Funktionen
	
	/**
	 * Nimmt die Ergebnismenge und formt ein konkretes Model daraus
	 *
	 * @param currentResultSet : {@link ResultSet} : Ergebnismenge der aktuellen Abfrage
	 *
	 * @return aBaseModel : {@link ABaseModel} : Model abgeleitet von der Basisklasse
	 *
	 * @throws SQLException
	 */
	@Override
	public User getModelFromResultSet(ResultSet currentResultSet) throws SQLException {
		//5. Ueber den Spaltennamen die Spaltenindizes auslesen
		int iColumnIndexId       = currentResultSet.findColumn(COL_NAME_ID);
		int iColumnIndexUserName = currentResultSet.findColumn(COL_NAME_USER_NAME);
		int iColumnIndexUserPw   = currentResultSet.findColumn(COL_NAME_USER_PW);
		
		//6. Durch Auswahl des Datentyps und angabe des Spaltenindizes auselsen der Daten
		int    iId         = currentResultSet.getInt(iColumnIndexId);
		String strUserName = currentResultSet.getString(iColumnIndexUserName);
		String strUserPw   = currentResultSet.getString(iColumnIndexUserPw);
		
		//7. Neues Modelobjekt generieren
		User userFromDbTable = new User(iId, strUserName, strUserPw);
		
		return userFromDbTable;
	}
	//endregion
}