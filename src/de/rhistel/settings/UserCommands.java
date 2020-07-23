package de.rhistel.settings;

/**
 * Diese Klasse enthaelt alle
 * Kommandos die der User eingeben kann
 * um das Programm zu bedienen
 */
public class UserCommands {
	
	//region 1. Allgemeine Kommandos
	public static final int CMD_EXIT_BACK = 0;
	
	public static final int CMD_NO = 0;
	public static final int CMD_YES = 1;
	//endregion
	
	//region 2. main Menu Kommandos
	public static final int CMD_CUSTOMER_ADMIN = 1;
	public static final int CMD_CALC_BMI       = 2;
	//endregion
	
	//region 3. Customer Admin Menu Kommandos
	public static final int CMD_ADD_NEW_CUSTOMER   = 1;
	public static final int CMD_UPDATE_CUSTOMER    = 2;
	public static final int CMD_DELETE_CUSTOMER    = 3;
	public static final int CMD_SHOW_ALL_CUSTOMERS = 4;
	//endregion
	
	//region 4. BMI / Geschlecht Kommandos
	public static final int CMD_BMI_MALE    = 1;
	public static final int CMD_BMI_FEMALE  = 2;
	public static final int CMD_BMI_DIVERSE = 3;
	//endregion
	
	//region 5. Privater Konstruktor
	
	/**
	 * Standkonstruktor
	 */
	private UserCommands() {
		//Nichts zun tun ausser privat zu sein, damit kei Objekter ausserhalb generiert werden kann.
	}
	
	//endregion
	
}
