package de.rhistel.logic.db;

import de.rhistel.model.ABaseModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * TODO 4 Die Tabelle uber phpMyAdmin in der Db  anlegen und diese Klasse implementieren
 * Verbindet sich mit der DbTabelle und biete
 * alle CRUD Operation fuer die Kunden an
 * die Tabelle sollte aus Zeitgruenden wie folgt aussehe.
 *
 * _id,titel,firstName,lastName,bday,age,sex,height,weight,phonenNr,email,street,housenr,zip,city
 * _id,titel,firstName,lastName,bday,age,sex,height,weight,phonenNr,email,fkeyAddressId
 */
public class DaoCustomer extends ADao {
	/**
	 * Standard Konstruktor zum direkten setzen
	 */
	public DaoCustomer() {
		super("customers");
	}
	
	/**
	 * Fuegt einen einzelen Datensatz in die Datebanktabelle ein
	 *
	 * @param dbRwConnection           : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelToInsertIntoDbTable : {@link ABaseModel} : Model welches eingefuegt werden soll
	 */
	@Override
	public void insertDataRecordIntoDbTbl(Connection dbRwConnection, ABaseModel modelToInsertIntoDbTable) {
	
	}
	
	/**
	 * Fuegt eine Liste von Datensaetzen in die Datebanktabelle ein
	 *
	 * @param dbRwConnection            : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelsToInsertIntoDbTable : {@link ABaseModel} : Models welches eingefuegt werden soll
	 */
	@Override
	public void insertDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends ABaseModel> modelsToInsertIntoDbTable) {
	
	}
	
	/**
	 * Aendert einen einzelen Datensatz in der Datebanktabelle
	 *
	 * @param dbRwConnection           : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelToUpdateIntoDbTable : {@link ABaseModel} : Model welches geaendert werden soll
	 */
	@Override
	public void updateDataRecordIntoDbTbl(Connection dbRwConnection, ABaseModel modelToUpdateIntoDbTable) {
	
	}
	
	/**
	 * Aendert eine Liste von Datensaetzen in der Datebanktabelle
	 *
	 * @param dbRwConnection            : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelsToUpdateIntoDbTable : {@link ABaseModel} : Models welches geaendert werden soll
	 */
	@Override
	public void updateDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends ABaseModel> modelsToUpdateIntoDbTable) {
	
	}
	
	/**
	 * Gibt alle Datensaetze eine Datenbanktabelle als {@link List} zurueck
	 *
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 *
	 * @return allDataRecordsFromDbTbl : {@link List} Objects extended from {@link ABaseModel} : Liste aller Datensaetze
	 */
	@Override
	public List<? extends ABaseModel> getAllDataRecordsFromDbTbl(Connection dbRwConnection) {
		return null;
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
	public ABaseModel getSpecificDataRecordFromDbTblById(Connection dbRwConnection, int iId) {
		return null;
	}
	
	/**
	 * Loescht einen bestimmten Datensatz aus der Datenbanktabelle
	 *
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param iId            : int : Id des Objektes welches in der DbTabelle geloscht werden soll
	 */
	@Override
	public void deleteDataRecordInDbTblById(Connection dbRwConnection, int iId) {
	
	}
	
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
	public ABaseModel getModelFromResultSet(ResultSet currentResultSet) throws SQLException {
		return null;
	}
}
