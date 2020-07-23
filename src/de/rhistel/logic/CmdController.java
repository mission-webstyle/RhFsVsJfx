package de.rhistel.logic;

import de.rhistel.ConsoleReader;
import de.rhistel.model.Customer;
import de.rhistel.model.EPronoun;

import java.util.List;

import static de.rhistel.settings.ApplicationTexts.*;
import static de.rhistel.settings.UserCommands.*;

/**
 * Startet den Aufbau der
 * verschiedenen Programmteile
 * und kontrolliert den Programmflussad
 */
public class CmdController {
	//region 0.Konstanten
	private static final int NON_VALID_INDEX_VALUE = -1;
	//endregion
	
	//region 1. Decl. and Init Attribute / Variablen
	
	/**
	 * Enthaelt alle Kunden die zur Laufzeit
	 * gelesen, angelegt, geaendert oder geloescht werden.
	 */
	private List<Customer> listOfAllCustomers;
	
	//endregion
	
	//region 2. Konstruktor
	
	/**
	 * Standardkonstruktor
	 * zum direkten generieren
	 * der Kundenliste {@link CmdController#listOfAllCustomers}
	 */
	public CmdController() {
		
		//Generiert eine leere Kundenliste
		this.listOfAllCustomers = CsvFileHandler.getInstance().readCustomersFromFile();
		
	}
	//endregion
	
	
	//region 3. Hauptmenus
	
	/**
	 * Gibt den Programmnamen und das Hauptmenu aus und
	 * wertet den die Usereingabe aus um die weiteren
	 * Programmfunktionen passend dazu einzuleitet
	 */
	public void generateMainMenu() {
		boolean exitApplication = false;
		
		while (!exitApplication) {
			this.printApplicationHeader();
			this.printMainMenu();
			
			switch (this.readIntUserCmd(false)) {
				case CMD_CUSTOMER_ADMIN -> this.generateCustomerAdminMenu();
				case CMD_CALC_BMI -> this.startBmiCalculation();
				case CMD_EXIT_BACK -> exitApplication = true;
				default -> System.out.println(USER_MSG_NO_SUCH_CMD);
			}
		}
	}
	
	
	/**
	 * Gibt den Programmnamen auf der Konsole aus
	 */
	private void printApplicationHeader() {
		System.out.println(APPLICATION_NAME);
	}
	
	/**
	 * Gibt das Hauptmenu auf der Konsole aus
	 */
	private void printMainMenu() {
		System.out.println(MAIN_MENU_TEXT_CUSTOMER_ADMIN);
		System.out.println(MAIN_MENU_TEXT_CALC_BMI);
		System.out.println(MAIN_MENU_TEXT_EXIT);
	}
	//endregion
	
	//region 4. Kundenverwaltung
	
	/**
	 * Generiert das Submenu Kundenverwaltung
	 * mit den folgenden Punkten
	 * <ul>
	 *     <li>Neuer Kunde erfassen</li>
	 *     <li>Alle Kunden anzeigen</li>
	 * </ul>
	 * und wertet Dementsprechend die Usereingabe
	 * aus.
	 */
	private void generateCustomerAdminMenu() {
		boolean navigateBack = false;
		
		while (!navigateBack) {
			this.printCustomerAdminMenu();
			
			switch (this.readIntUserCmd(false)) {
				case CMD_ADD_NEW_CUSTOMER -> this.addNewCustomer();
				case CMD_UPDATE_CUSTOMER -> this.updateCustomer();
				case CMD_DELETE_CUSTOMER -> this.deleteCustomer();
				case CMD_SHOW_ALL_CUSTOMERS -> this.showAllCustomers();
				case CMD_EXIT_BACK -> navigateBack = true;
			}
		}
	}
	
	
	/**
	 * Legt einen neuen Kunden an
	 */
	private void addNewCustomer() {
		
		//Alle Kundendaten auslesen bei Falscheingabe ist der Rueckgabewert null
		Customer customerFromUi = this.getCustomerDataFromUi();
		
		//Checken ob alle Daten richtig waren
		if (customerFromUi != null) {
			
			//Einen neuen Kunden zur Liste hinzufuegenA
			this.listOfAllCustomers.add(customerFromUi);
			
			//Veraenderung in die Dateis speichern
			CsvFileHandler.getInstance().saveCustomersToCsvFile(this.listOfAllCustomers);
			
			//Usernachricht Aktualisierung war erfolgreich
			System.out.println(USER_MSG_SAVED_SUCCESSFULLY);
		}
	}
	
	
	/**
	 * Aendert einen durch den Index bestimmten Kunden innerhalb
	 * der Liste.
	 */
	private void updateCustomer() {
		
		//Checken ob die Liste nicht leer ist
		if (!this.checkIfCustomerListIsEmpty()) {
			
			//Alle Kunden anzeigen lassen
			this.showAllCustomers();
			
			//Index auslesen
			int indexOfCustomerToEdit = this.getValidIndex();
			
			//Checken ob der eingelesene Index gueltig ist
			if (indexOfCustomerToEdit > NON_VALID_INDEX_VALUE) {
				
				//Alle Kundendaten auslesen bei Falscheingabe ist der Rueckgabewert null
				Customer editedCustomer = this.getCustomerDataFromUi();
				
				//Checken ob alle Daten richtig waren
				if (editedCustomer != null) {
					
					//Einen bestehenden Kunden in der  Liste mit den neuen Daten ersetzen
					this.listOfAllCustomers.set(indexOfCustomerToEdit, editedCustomer);
					
					//Veraenderung in die Dateis speichern
					CsvFileHandler.getInstance().saveCustomersToCsvFile(this.listOfAllCustomers);
					
					//Usernachricht Aktualisierung war erfolgreich
					System.out.println(USER_MSG_UPDATED_SUCCESSFULLY);
				}
				
			}
			
		}
		
	}
	
	/**
	 * Zeigt alle Kunden an, erwartet eine Usersereingabe
	 * des Indizes des Kunden der geloscht werden soll.
	 * Macht eine Sicherheitsabfrage und loescht den Kunden
	 */
	private void deleteCustomer() {
		
		//Checken ob die Liste nicht leer ist
		if (!this.checkIfCustomerListIsEmpty()) {
			
			//Alle Kunden anzeigen lassen
			this.showAllCustomers();
			
			//Index des zu loeschenden Kunden beschaffen
			int indexOfCustomerToDelete = this.getValidIndex();
			
			//Checken ob der Index valide ist
			if (indexOfCustomerToDelete > NON_VALID_INDEX_VALUE) {
				
				//Sicherheitsabfrage ausgeben
				System.out.println(USER_MSG_DELETE);
				
				//Besetaetigungwert einlesen
				int iDeleteConfirmationCmd = this.readIntUserCmd(false);
				
				//Wenn der User wirklich loeschen moechte
				if (iDeleteConfirmationCmd == CMD_YES) {
					
					//Eigentliches Loeschen des Kunden vornehemen
					this.listOfAllCustomers.remove(indexOfCustomerToDelete);
					
					//Veraenderung in die Dateis speichern
					CsvFileHandler.getInstance().saveCustomersToCsvFile(this.listOfAllCustomers);
					
					//Usernachricht dass das Loeschen erfolgreich war
					System.out.println(USER_MSG_DELETED_SUCCESSFULLY);
				}
			}
			
		}
		
	}
	
	
	/**
	 * Kunde ausgeben mit der ToStringFunktion
	 * Zeigt alle erfassten Kunden an
	 */
	private void showAllCustomers() {
		
		if (!this.checkIfCustomerListIsEmpty()) {
			
			/**
			 * Ausgbae wenn der Index benotigt wird um
			 * zum Beispiel einen speziellen Kunden auszuwaehlen/zu identifizieren
			 */
			String strCustomerFormatPattern = INDEX_TEXT + " [ %s ]"
					+ FIRST_NAME_TEXT + " %s"
					+ LAST_NAME_TEXT + " %s"
					+ BDAY_TEXT + " %s";
			
			for (int index = 0; index < listOfAllCustomers.size(); index++) {
				
				//Aktueller Kunde aus Kundenliste auslesen
				Customer c = listOfAllCustomers.get(index);
				
				System.out.println(
						String.format(strCustomerFormatPattern, index, c.getFirstName(), c.getLastName(), c.getBday()));
			}
			
			System.out.println("\n");
			
		}
	}
	
	/**
	 * Druckt das Submenu auf die Konsole
	 */
	private void printCustomerAdminMenu() {
		System.out.println(CUSTOMER_ADMIN_HEADER_TEXT);
		System.out.println(CUSTOMER_ADMIN_MENU_TEXT_NEW_CUSTOMER);
		System.out.println(CUSTOMER_ADMIN_MENU_TEXT_UPDATE_CUSTOMER);
		System.out.println(CUSTOMER_ADMIN_MENU_TEXT_DELETE_CUSTOMER);
		System.out.println(CUSTOMER_ADMIN_MENU_TEXT_SHOW_ALL);
		System.out.println(BACK_TEXT);
	}
	//endregion
	
	//region 5. BMI-Berechnung
	
	/**
	 * Berechnet den BMI eins Kunden.
	 *
	 */
	private void startBmiCalculation() {
		//Nur etwas tun wenn Kundne angelegt wurden
		if (!checkIfCustomerListIsEmpty()) {
			
			//Alle Kunden zur Auswahl ausgeben
			showAllCustomers();
			
			//Kundenindex einlesen damit der BMI fuer diesen speziellen Kunden berechnet werden kann
			int indexOfCustomer = getValidIndex();
			
			//Wenn der Index richtig war
			if (indexOfCustomer > NON_VALID_INDEX_VALUE) {
				
				//Akutller Kunde aus Liste zurueckgeben lassen und an BmiAnlyzer weitergeben
				BmiAnalyzer.calcBmi(this.listOfAllCustomers.get(indexOfCustomer));
			}
		}
		
		
	}
	//endregion
	
	
	//region 6. Hilfsmethoden und Funktionen
	
	/**
	 * Liest alle Kundendaten von der Ui (Konsole)
	 * aus und generiert ein neuen {@link Customer}, sollten
	 * die Eingabedaten valide (hier in userem Fall nur nicht leer) sein.
	 * und gibt diesen befuellt zurueck. Sind die Eingabedaten nicht valide
	 * (minimum eine Eingabe war leer oder bestand nur aus Leerzeichen) dann
	 * wird null zurueck gegeben. Es gibt nur einen Kunden mit richtigen Daten
	 *
	 * @return customerFromUi : {@link Customer} : Neuer befuellter Kunde bei richtigen Eingaben
	 * bei <b>Falscheingabe</b> wird <b> >null< </b> zurueck gegeben.
	 */
	private Customer getCustomerDataFromUi() {
		//Decl and Init
		Customer customerFromUi = null;
		
		//Eingabedaten auslesen
		System.out.print(TITLE_TEXT);
		String strPronounDescription = ConsoleReader.readStringValue();
		
		System.out.print(FIRST_NAME_TEXT);
		String strFirstName = ConsoleReader.readStringValue();
		
		System.out.print(LAST_NAME_TEXT);
		String strLastName = ConsoleReader.readStringValue();
		
		System.out.print(BDAY_TEXT);
		String strBday = ConsoleReader.readStringValue();
		
		System.out.print(BMI_AGE_TEXT);
		int iAge = ConsoleReader.readIntValue();
		
		System.out.print(SEX_TEXT);
		int iSex = ConsoleReader.readIntValue();
		
		System.out.print(HEIGHT_TEXT);
		Double dblHeight = ConsoleReader.readDoubleValue();
		
		System.out.print(WEIGHT_TEXT);
		Double dblWeight = ConsoleReader.readDoubleValue();
		
		System.out.print(PHONE_NR_TEXT);
		String strPhoneNr = ConsoleReader.readStringValue();
		
		System.out.print(EMAIL_TEXT);
		String strEmail = ConsoleReader.readStringValue();
		
		System.out.print(STREET_TEXT);
		String strStreet = ConsoleReader.readStringValue();
		
		System.out.print(HOUSE_NR_TEXT);
		String strHouseNr = ConsoleReader.readStringValue();
		
		System.out.print(ZIP_TEXT);
		String strZip = ConsoleReader.readStringValue();
		
		System.out.print(CITY_TEXT);
		String strCity = ConsoleReader.readStringValue();
		
		//Alle Eingaben in ein Array packen um Sie auf einmal zu checken
		String[] strUserInput = {
				strPronounDescription,
				strFirstName,
				strLastName,
				strBday,
				strPhoneNr,
				strEmail,
				strStreet,
				strHouseNr,
				strZip,
				strCity
		};
		
		boolean isUserInputValid = true;
		
		//Alle Eingabedaten durchlaufen
		for (String strValue : strUserInput) {
			
			//Wenn etwas leer ist gilt es als nicht korrekt / valide
			if (strValue.isEmpty() || strValue.isBlank()) {
				isUserInputValid = false;
			}
		}
		
		//Checken ob es falsch Eingaben gab
		if (isUserInputValid) {
			
			//Neues Kundenobjekt anlegen/generieren/instanziieren
			customerFromUi = new Customer();
			
			
			//Alle gecheckten Eingabedaten per setter dem Kundenobjekt geben
			customerFromUi.setEPronoun(EPronoun.getEPronounByDescription(strPronounDescription));
			customerFromUi.setFirstName(strFirstName);
			customerFromUi.setLastName(strLastName);
			
			customerFromUi.setBday(strBday);
			
			customerFromUi.setAge(iAge);
			customerFromUi.setSex(iSex);
			
			customerFromUi.setHeight(dblHeight);
			customerFromUi.setWeight(dblWeight);
			
			customerFromUi.setPhoneNr(strPhoneNr);
			customerFromUi.setEmail(strEmail);
			
			//Schon vorhandenes Blankoaddresskaertchen vom Formular ausfuellen
			customerFromUi.getCustomerAddress().setStreet(strStreet);
			customerFromUi.getCustomerAddress().setHouseNr(strHouseNr);
			customerFromUi.getCustomerAddress().setZip(strZip);
			customerFromUi.getCustomerAddress().setCity(strCity);
			
			
		} else {
			//Userhinweis ausgeben
			System.out.println(USER_MSG_PLEASE_ENTER_EVERYTHING);
		}
		
		return customerFromUi;
	}
	
	
	/**
	 * Checkt ob der Eingebene Index valide ist.
	 * Ist er nicht valide dann gibt diese Methode
	 * eine passende UserMsg aus <b>und liefert -1 zurueck</b>.
	 * War die Eingabe korrekt wird  der valide Index zurueck geliefert
	 *
	 * @return indexToReturn : int : Valider Index oder -1 bei Falscheingabe
	 */
	private int getValidIndex() {
		//Userausgabe zur Eingabeauafforderung
		int indexToReturn = NON_VALID_INDEX_VALUE;
		
		int indexFromUi = readIntUserCmd(true);
		
		if ((indexFromUi > -1) && (indexFromUi < listOfAllCustomers.size())) {
			indexToReturn = indexFromUi;
		} else {
			System.out.println(USER_MSG_NO_SUCH_INDEX);
		}
		
		return indexToReturn;
	}
	
	/**
	 * Ueberprueft ob die Liste leer ist.
	 * Sollte Sie leer sein gibt diese Methode
	 * eine passende Hinweismeldung aus
	 *
	 * @return isEmpty : boolean : true == Leer Liste - false == Liste ist nicht leer
	 * Meldung wurde ausgegeben
	 */
	private boolean checkIfCustomerListIsEmpty() {
		boolean isEmpty = listOfAllCustomers.isEmpty();
		
		if (isEmpty) {
			System.out.println(USER_MSG_NO_CUSTOMERS_YET);
		}
		return isEmpty;
	}
	
	/**
	 * Liest die Usereingabe aus und gibt Sie zurueck
	 *
	 * @param isIndex : boolean : true == Index Eingabauffroderung wird
	 *                gedruckt false == Wird nicht gedruckt
	 *
	 * @return iUserCmd : int : Usereingabekommando
	 */
	private int readIntUserCmd(boolean isIndex) {
		int iUserCmd = -1;
		
		//Eingabeaufforderung
		if (isIndex) {
			//Index auslesen
			System.out.println(INPUT_PREFIX + ENTER_INDEX);
			iUserCmd = ConsoleReader.readIntValue();
		}else{
			//Menuoption auslesen
			iUserCmd = ConsoleReader.readIntOption();
		}
		
		return iUserCmd;
	}
	
	
	//endregion
	
}