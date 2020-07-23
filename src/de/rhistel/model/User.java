package de.rhistel.model;

/**
 * TODO 2 Neue Userklasse integrieren(Ihr duerft diese Klasse kopieren)
 */
public class User extends ABaseModel {
	
	//region 0. Konstanten
	private static final int SPLIT_INDEX_USER_NAME                 = 1;
	private static final int SPLIT_INDEX_USER_PW_SHA256_HASH_VALUE = 2;
	//endregion
	
	//region 1. Decl, and Init Attribute
	
	private String strUserName;
	private String strUserPwSha256HashValue;
	//endregion
	
	//region 2. Konstruktor
	
	/**
	 * Standardkonstruktor zum direkten setzen aller Werte
	 *
	 * @param strUserName              : {@link String} : Benutzername
	 * @param strUserPwSha256HashValue : {@link String} - Hex - HashWert des Passwortes
	 */
	public User(String strUserName, String strUserPwSha256HashValue) {
		super(DEF_VALUE_INT);
		this.strUserName              = strUserName;
		this.strUserPwSha256HashValue = strUserPwSha256HashValue;
	}
	
	/**
	 * Standardkonstruktor zum direkten setzen aller Werte
	 *
	 * @param iId                      : int : DbId des Users
	 * @param strUserName              : {@link String} : Benutzername
	 * @param strUserPwSha256HashValue : {@link String} - Hex - HashWert des Passwortes
	 */
	public User(int iId, String strUserName, String strUserPwSha256HashValue) {
		super(iId);
		this.strUserName              = strUserName;
		this.strUserPwSha256HashValue = strUserPwSha256HashValue;
		
	}
	
	//endregion
	
	
	//region 3. Getter und Setter
	
	
	public String getUserName() {
		return strUserName;
	}
	
	public void setUserName(String strUserName) {
		this.strUserName = strUserName;
	}
	
	public String getUserPwSha256HashValue() {
		return strUserPwSha256HashValue;
	}
	
	public void setUserPwSha256HasValue(String strUserPwSha256HashValue) {
		this.strUserPwSha256HashValue = strUserPwSha256HashValue;
	}
	
	/**
	 * Definiert diese Funktion vor, sodass alle abgeleitetet
	 * Klasse diese Funktion implementieren muessen, da sich
	 * die konrkete Funktionalitaet jedoch von Modelklasse
	 * zu Modleklasse, wenn auch teilweise minimal. unterscheidt ist
	 * hier das Schluesselwort abstract gewaehlt worden, und eine konkrete
	 * Implementierung soll hier in dieser Klasse nicht stattfinden.
	 *
	 * @return strCsvLine : {@link String} : CSV-Zeile der konkret abgleiteten Kindklasse
	 */
	@Override
	public String getAllAttributesAsCsvLine() {
		return this.iId + DATA_ATTRIBUTE_SPLITTER + this.strUserName + DATA_ATTRIBUTE_SPLITTER +
				this.strUserPwSha256HashValue + "\n";
	}
	
	/**
	 * Definiert diese Funktion vor, sodass alle abgeleitetet
	 * Klasse diese Funktion implementieren muessen, da sich
	 * die konrkete Funktionalitaet jedoch von Modelklasse
	 * zu Modleklasse, wenn auch teilweise minimal. unterscheidt ist
	 * hier das Schluesselwort abstract gewaehlt worden, und eine konkrete
	 * Implementierung soll hier in dieser Klasse nicht stattfinden.
	 *
	 * @param strCsvLine : {@link String} : CSV-Zeile der konkret abgleiteten Kindklasse die alle Attribute
	 *                   setzt
	 */
	@Override
	public void setAllAttributesFromCsvLine(String strCsvLine) throws Exception {
		String[] strSplitArray = strCsvLine.split(DATA_ATTRIBUTE_SPLITTER);
		
		this.iId                      = Integer.valueOf(strSplitArray[SPLIT_INDEX_ID]);
		this.strUserName              = strSplitArray[SPLIT_INDEX_USER_NAME];
		this.strUserPwSha256HashValue = strSplitArray[SPLIT_INDEX_USER_PW_SHA256_HASH_VALUE];
	}
	
	//endregion
	
	//region 4 to String
	
	@Override
	public String toString() {
		return "User{" +
				"strUserName='" + strUserName + '\'' +
				"strUserName='" + strUserName + '\'' +
				", strUserPwSha256HashValue='" + strUserPwSha256HashValue + '\'' +
				"} " + super.toString();
	}
	
	
	//endregion
	
}
