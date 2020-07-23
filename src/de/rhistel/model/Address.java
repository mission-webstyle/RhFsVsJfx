package de.rhistel.model;

/**
 * Enthealt alle Eingeschfaten
 * einer Adresse ist eine Modelklasse
 */
public class Address extends ABaseModel {
	
	//region 0. Konstanten
	private static final int SPLIT_INDEX_STREET   = 1;
	private static final int SPLIT_INDEX_HOUSE_NR = 2;
	private static final int SPLIT_INDEX_ZIP      = 3;
	private static final int SPLIT_INDEX_CITY     = 4;
	//endregion
	
	
	//region 1. Decl. and Init Attribute
	private String strStreet;
	private String strHouseNr;
	private String strZip;
	private String strCity;
	//endregion
	
	//region 2. Konstruktor
	
	/**
	 * Standardkonsturktor zum direkten
	 * setzen aller Attribute
	 */
	public Address() {
		this.strStreet  = DEF_VALUE_STR;
		this.strHouseNr = DEF_VALUE_STR;
		this.strZip     = DEF_VALUE_STR;
		this.strCity    = DEF_VALUE_STR;
		
	}
	
	//endregion
	
	//region 3. Getter / Setter
	public String getStreet() {
		return strStreet;
	}
	
	public void setStreet(String strStreet) {
		this.strStreet = strStreet;
	}
	
	public String getHouseNr() {
		return strHouseNr;
	}
	
	public void setHouseNr(String strHouseNr) {
		this.strHouseNr = strHouseNr;
	}
	
	public String getZip() {
		return strZip;
	}
	
	public void setZip(String strZip) {
		this.strZip = strZip;
	}
	
	public String getCity() {
		return strCity;
	}
	
	public void setCity(String strCity) {
		this.strCity = strCity;
	}
	
	/**
	 * Gibt alle Attribiute inklusiv
	 * als CSV-Zeile zurueck.
	 * <p>
	 *  <ol>
	 * <li>{@link Address#SPLIT_INDEX_STREET} - {@link Address#strStreet}</li>
	 * <li>{@link Address#SPLIT_INDEX_HOUSE_NR} - {@link Address#strHouseNr}</li>
	 * <li>{@link Address#SPLIT_INDEX_ZIP} - {@link Address#strZip}</li>
	 * <li>{@link Address#SPLIT_INDEX_CITY} - {@link Address#strCity}</li>
	 * </ol>
	 *
	 * @return strCsvLine : {@link String} : CSV-Line == Strasse;42a;66450;Bexbach
	 */
	@Override
	public String getAllAttributesAsCsvLine() {
		String strCsvLine = "";
		
		strCsvLine += this.iId + DATA_ATTRIBUTE_SPLITTER;
		strCsvLine += this.strStreet + DATA_ATTRIBUTE_SPLITTER;
		strCsvLine += this.strHouseNr + DATA_ATTRIBUTE_SPLITTER;
		strCsvLine += this.strZip + DATA_ATTRIBUTE_SPLITTER;
		strCsvLine += this.strCity + "\n";
		
		return strCsvLine;
	}
	
	/**
	 * Setzt alle Attribiute
	 * basierend auf der in {@link Address#getAllAttributesAsCsvLine()}
	 * generierten CSV-Zeile.
	 *  <ol>
	 * <li>{@link Address#SPLIT_INDEX_STREET} - {@link Address#strStreet}</li>
	 * <li>{@link Address#SPLIT_INDEX_HOUSE_NR} - {@link Address#strHouseNr}</li>
	 * <li>{@link Address#SPLIT_INDEX_ZIP} - {@link Address#strZip}</li>
	 * <li>{@link Address#SPLIT_INDEX_CITY} - {@link Address#strCity}</li>
	 * </ol>
	 *
	 * @param strCsvLine: {@link String} : CSV-Zeile generiert aus {@link Address#getAllAttributesAsCsvLine()}
	 *
	 * @return strCsvLine : {@link String} : CSV-Line == Strasse;42a;66450;Bexbach
	 */
	@Override
	public void setAllAttributesFromCsvLine(String strCsvLine) throws Exception {
		//1. Split in einzelene Attribute
		String[] strSplitArray = strCsvLine.split(DATA_ATTRIBUTE_SPLITTER);
		
		//2. Setzen der Attribute
		this.iId        = Integer.valueOf(strSplitArray[SPLIT_INDEX_ID]);
		this.strStreet  = strSplitArray[SPLIT_INDEX_STREET];
		this.strHouseNr = strSplitArray[SPLIT_INDEX_HOUSE_NR];
		this.strZip     = strSplitArray[SPLIT_INDEX_ZIP];
		this.strCity    = strSplitArray[SPLIT_INDEX_CITY];
		
	}

//endregion
	
	//region 4. toString() Funktionen
	
	@Override
	public String toString() {
		return "Address{" +
				"strStreet='" + strStreet + '\'' +
				", strHouseNr='" + strHouseNr + '\'' +
				", strZip='" + strZip + '\'' +
				", strCity='" + strCity + '\'' +
				"} " + super.toString();
	}
	
	
	//endregion
}
