package de.rhistel.settings;

import de.rhistel.logic.CmdController;

import static de.rhistel.settings.UserCommands.*;

/**
 * Enthaelt alle Programmtexte
 */
public class ApplicationTexts {
	
	//region 1 Allgemeine Konstanten / Texte
	
	/**
	 * Definiert den Namen des Programms
	 */
	public static final String APPLICATION_NAME = "FitnessStudio - Verwaltungssystem";
	
	
	public static final String BMI_HEADER_TEXT = "\n+++++++++++++++++++++++++\n"
			+ "+ [B]ody [M]ass [I]ndex +"
			+ "\n+++++++++++++++++++++++++\n";
	
	
	public static final String BACK_TEXT = "[\t" + CMD_EXIT_BACK + "\t]\t<< Zurück";
	
	public static final String ENTER_INDEX = "Bitte geben Sie den Index ein:\n";
	
	
	public static final String INPUT_PREFIX = ">> ";
	//endregion
	
	//region 2 main menu / Hauptmenu
	
	
	public static final String MAIN_MENU_TEXT_CUSTOMER_ADMIN =
			"[\t" + CMD_CUSTOMER_ADMIN + "\t]\tKundenverwaltung";
	
	public static final String MAIN_MENU_TEXT_CALC_BMI =
			"[\t" + CMD_CALC_BMI + "\t]\tBMI berechnen";
	
	public static final String MAIN_MENU_TEXT_EXIT =
			"[\t" + CMD_EXIT_BACK + "\t]\tProgramm beenden";
	//endregion
	
	//region 3 Kundenverwaltungsmenu
	
	public static final String CUSTOMER_ADMIN_HEADER_TEXT = "\n+++++Kundenverwaltung+++++\n";
	
	public static final String CUSTOMER_ADMIN_MENU_TEXT_NEW_CUSTOMER =
			"[\t" + CMD_ADD_NEW_CUSTOMER + "\t]\tNeuen\t\tKunden\t\thinzufügen";
	
	public static final String CUSTOMER_ADMIN_MENU_TEXT_UPDATE_CUSTOMER =
			"[\t" + CMD_UPDATE_CUSTOMER + "\t]\tBestehenden\tKunden\t\tändern";
	
	public static final String CUSTOMER_ADMIN_MENU_TEXT_DELETE_CUSTOMER =
			"[\t" + CMD_DELETE_CUSTOMER + "\t]\tBestehenden Kunden\t\tlöschen";
	
	public static final String CUSTOMER_ADMIN_MENU_TEXT_SHOW_ALL =
			"[\t" + CMD_SHOW_ALL_CUSTOMERS + "\t]\tAlle\t\tKunden\t\tanzeigen";
	
	//endregion
	
	//region 4. Kundendaten Eingabeaufforderungstexte
	
	public static final String INDEX_TEXT      = "\nIndex\t\t:\t";
	public static final String TITLE_TEXT      = "\nAnrede\t:\t";
	public static final String FIRST_NAME_TEXT = "\nVorname\t\t:\t";
	public static final String LAST_NAME_TEXT  = "\nNachname\t:\t";
	public static final String BDAY_TEXT       = "\nGeburtstag\t:\t";
	public static final String SEX_TEXT        = "\nGeschlecht\t:\t";
	public static final String HEIGHT_TEXT     = "\nGroesse\t:\t";
	public static final String WEIGHT_TEXT     = "\nGewicht\t:\t";
	public static final String PHONE_NR_TEXT   = "\nTelefonnummer\t:\t";
	public static final String EMAIL_TEXT      = "\nEmail\t:\t";
	public static final String STREET_TEXT     = "\nStrasse\t:\t";
	public static final String HOUSE_NR_TEXT   = "\nHausnummer\t:\t";
	public static final String ZIP_TEXT        = "\nPLZ\t:\t";
	public static final String CITY_TEXT       = "\nOrt\t:\t";
	
	
	//endregion
	
	//region 5 BMI Text
	
	
	public static final String CUSTOMER_NAME_TEXT = "\nKundenname\t\t\t\t\t:\t";
	
	public static final String BMI_WEIGHT_TEXT = "\nGewicht in kg\t\t\t\t:\t";
	public static final String BMI_HEIGHT_TEXT = "\nGröße in m\t\t\t\t\t:\t";
	
	public static final String BMI_AGE_TEXT = "\nAlter in ganzen Jahren\t\t:\t";
	
	public static final String BMI_CHOOSE_SEXES_TEXT = "[m=" + CMD_BMI_MALE
			+ "/w=" + CMD_BMI_FEMALE
			+ "/d=" + CMD_BMI_DIVERSE
			+ "]";
	public static final String BMI_SEX_TEXT          = "\nGeschlecht" + BMI_CHOOSE_SEXES_TEXT + "\t:\t";
	
	public static final String BMI_TEXT = "Ihr Body Mass Index ist: ";
	
	public static final String UNDER_WEIGHT_TEXT  = "Sie haben Untergewicht.";
	public static final String NORMAL_WEIGHT_TEXT = "Sie haben Normalgewicht.";
	
	public static final String BOUNDARY_NORMAL_WEIGHT_AND_OVER_WEIGHT_TEXT = "Sie liegen in der Grenze zwischen" +
			" Normal- und Übergewicht.";
	
	public static final String OVER_WEIGHT_TEXT                    = "Sie haben Übergewicht.";
	public static final String BOUNDARY_OVER_WEIGHT_AND_OBESE_TEXT = "Sie liegen in der Grenze zwischen" +
			" Übergewicht und Apdipositas.";
	
	public static final String OBESE_TEXT         = "Sie haben Adipositas.";
	public static final String HEAVILY_OBESE_TEXT = "Sie haben starke Adipositas.";
	
	
	//region 5. UserMessages
	/**
	 * StringFormatte fuer den Optimalen BMI mit dem alter
	 * in der Funktion {@link CmdController#getOptimalBmiValueInfo(int)}
	 * schoen zurueck zu geben. Dazu wird {@link String#format(String, Object...)} genutzt.
	 * Der erste Paramter ist sogenannte Formatter, die anderen folgen in der gleichen
	 * Reihenfolge wie im Formatter festgelegt  %d = Integerzahlen und %s = String.
	 * Heisst fuege an dieser Stelle etwas ein passend der Abkuerzung mit %
	 */
	public static final String OPTIMAL_BMI_VALUE_INFO_FORMATTER = "\nBasierend auf Ihrem Alter von %d Jahren, wäre der optimale BMI zwischen: %s";
	
	//region 3. Usernachrichten
	public static final String USER_MSG_PREFIX = "\n<<\t";
	public static final String USER_MSG_SUFFIX = "\t>>\n";
	
	public static final String USER_MSG_NO_SUCH_CMD                    = USER_MSG_PREFIX
			+ "Diesen Befehl gibt es nicht"
			+ USER_MSG_SUFFIX;
	public static final String USER_MSG_CHOOSE_VALID_NO_SUCH_SEX_VALUE = USER_MSG_PREFIX
			+ "Scoring nicht moeglich bitte geben Sie einen"
			+ "passenden Vorgabewert fuer das Geschlecht ein "
			+ BMI_CHOOSE_SEXES_TEXT
			+ USER_MSG_SUFFIX;
	
	
	public static final String USER_MSG_PLEASE_ENTER_EVERYTHING = USER_MSG_PREFIX
			+ "Bitte alles ausfüllen"
			+ USER_MSG_SUFFIX;
	
	
	public static final String USER_MSG_NO_CUSTOMERS_YET = USER_MSG_PREFIX
			+ "Noch keine Kunden vorhanden"
			+ USER_MSG_SUFFIX;
	
	
	public static final String USER_MSG_NO_SUCH_INDEX = USER_MSG_PREFIX
			+ "Diesen Index gibt es nicht. Bitte geben Sie einen der angezeigten Indizes an."
			+ USER_MSG_SUFFIX;
	
	public static final String USER_MSG_DELETE = USER_MSG_PREFIX
			+ "Wirklich löschen? [" + CMD_NO + " = nein / " + CMD_YES + " = ja]"
			+ USER_MSG_SUFFIX;
	
	public static final String USER_MSG_SAVED_SUCCESSFULLY   = USER_MSG_PREFIX
			+ "Erfolgreich gespeichert"
			+ USER_MSG_SUFFIX;
	public static final String USER_MSG_UPDATED_SUCCESSFULLY = USER_MSG_PREFIX
			+ "Erfolgreich geändert"
			+ USER_MSG_SUFFIX;
	
	public static final String USER_MSG_DELETED_SUCCESSFULLY = USER_MSG_PREFIX
			+ "Erfolgreich gelöscht"
			+ USER_MSG_SUFFIX;
	//endregion
	
	//endregion
	
	//endregion
	
	//region 5. Privater Konstruktor
	
	/**
	 * Standkonstruktor
	 */
	private ApplicationTexts() {
		//Nichts zun tun ausser privat zu sein, damit kei Objekter ausserhalb generiert werden kann.
	}
	//endregion
}
