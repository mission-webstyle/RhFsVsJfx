package de.rhistel.model;

/**
 * Diese Klasse repraesentiert
 * einen Kunden. Mit folgenden
 * Eigenschaften.
 * <p>
 * Diese Klasse ist eine Modelklasse,
 * Datenhaltungsklasse.
 * [P]lain [O]ld [J]ava [O]bject - Klasse
 * <p>
 * Enthaelt keinerlei Logik und wird gentutzt
 * um Daten komfortabel von Programmteil A zu
 * Programmteil B zu schieben.
 */
public class Customer extends ABaseModel {
	
	//region 0. Konstanten
	
	private static final int SPLIT_INDEX_CUSTOMER_DATA         = 0;
	private static final int SPLIT_INDEX_CUSTOMER_ADDRESS_DATA = 1;
	
	private static final int SPLIT_INDEX_TITLE      = 1;
	private static final int SPLIT_INDEX_FIRST_NAME = 2;
	private static final int SPLIT_INDEX_LAST_NAME  = 3;
	private static final int SPLIT_INDEX_BDAY       = 4;
	
	private static final int SPLIT_INDEX_AGE = 5;
	private static final int SPLIT_INDEX_SEX = 6;
	
	private static final int SPLIT_INDEX_HEIGHT = 7;
	private static final int SPLIT_INDEX_WEIGHT = 8;
	
	private static final int SPLIT_INDEX_PHONE_NR = 9;
	private static final int SPLIT_INDEX_EMAIL    = 10;
	//endregion
	
	//region 1. Decl. and Init Attribute
	
	private EPronoun ePronoun;
	
	private String strFirstName;
	private String strLastName;
	private String strBday;
	
	private int iAge;
	private int iSex;
	
	
	private double dblHeight;
	private double dblWeight;
	
	private String strPhoneNr;
	private String strEmail;
	
	private Address customerAddress;
	
	//endregion
	
	//region 2. Konstruktor mit genererien lassen mit alt + einfg / rechtsklick generate -> SELECT NONE
	
	/**
	 * Standardkonstuktor
	 * Zum direkten initalisieren
	 * aller Attribute der Klasse
	 */
	public Customer() {
		this.ePronoun = EPronoun.DIVERSE;
		
		this.strFirstName = DEF_VALUE_STR;
		this.strLastName  = DEF_VALUE_STR;
		this.strBday      = DEF_VALUE_STR;
		
		this.iAge = DEF_VALUE_INT;
		this.iSex = DEF_VALUE_INT;
		
		this.dblHeight = DEF_VALUE_DBL;
		this.dblWeight = DEF_VALUE_DBL;
		
		this.strPhoneNr = DEF_VALUE_STR;
		this.strEmail   = DEF_VALUE_STR;
		
		this.customerAddress = new Address();
		
	}
	
	//endregion
	
	//region 3. Getter und Setter generieren lassen mit Strg + - Einfg oder Rechtsklick->Generate
	
	public EPronoun getEPronoun() {
		return ePronoun;
	}
	
	public void setEPronoun(EPronoun ePronoun) {
		this.ePronoun = ePronoun;
	}
	
	public String getFirstName() {
		return strFirstName;
	}
	
	public void setFirstName(String strFirstName) {
		this.strFirstName = strFirstName;
	}
	
	public String getLastName() {
		return strLastName;
	}
	
	public void setLastName(String strLastName) {
		this.strLastName = strLastName;
	}
	
	public String getFullName() {
		return strFirstName + " " + strLastName;
	}
	
	public String getBday() {
		return strBday;
	}
	
	public void setBday(String strBday) {
		this.strBday = strBday;
	}
	
	
	public Integer getAge() {
		return iAge;
	}
	
	public void setAge(int iAge) {
		this.iAge = iAge;
	}
	
	public Integer getSex() {
		return iSex;
	}
	
	public void setSex(int iSex) {
		this.iSex = iSex;
	}
	
	public Double getHeight() {
		return dblHeight;
	}
	
	public void setHeight(double dblHeight) {
		this.dblHeight = dblHeight;
	}
	
	public Double getWeight() {
		return dblWeight;
	}
	
	public void setWeight(double dblWeight) {
		this.dblWeight = dblWeight;
	}
	
	public String getPhoneNr() {
		return strPhoneNr;
	}
	
	public void setPhoneNr(String strPhoneNr) {
		this.strPhoneNr = strPhoneNr;
	}
	
	public String getEmail() {
		return strEmail;
	}
	
	public void setEmail(String strEmail) {
		this.strEmail = strEmail;
	}
	
	public Address getCustomerAddress() {
		return customerAddress;
	}
	
	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	/**
	 * Gibt alle Attribiute inklusive der Adddresse
	 * als CSV-Zeile zurueck.<br>
	 * <p>
	 * Beispiel:<br>
	 * <br>
	 * <ol>
	 *     <li>{@link Customer#SPLIT_INDEX_CUSTOMER_DATA}
	 *         <ol>
	 *     <li>{@link Customer#SPLIT_INDEX_TITLE} - {@link Customer#ePronoun}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_FIRST_NAME} - {@link Customer#strFirstName}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_LAST_NAME} - {@link Customer#strLastName}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_BDAY} - {@link Customer#strBday}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_AGE} - {@link Customer#iAge}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_SEX} - {@link Customer#iSex}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_HEIGHT} - {@link Customer#dblHeight}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_WEIGHT} - {@link Customer#dblWeight}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_PHONE_NR} - {@link Customer#strPhoneNr}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_EMAIL} - {@link Customer#strEmail}</li>
	 *     </ol>
	 *     </li>
	 *     <li>
	 *         {@link Customer#SPLIT_INDEX_CUSTOMER_ADDRESS_DATA}
	 *         <ol>
	 *             <li>
	 *                 {@link Customer#SPLIT_INDEX_CUSTOMER_ADDRESS_DATA} - {@link Customer#customerAddress#getAllAttributesAsCsvLine()}
	 *             </li>
	 *             </ol>
	 * </ol>
	 * Attribute:  strTitle;strFirstName;strLastName;strBday;iAge;iSex;dblHeight;dblWeight;strPhoneNr;strEmail<br>
	 * <p>
	 * Splitindex:  0                         1                   2              3<br>
	 * Attribute:  #address.getStreet(),address.getHouseNr(),address.getZip(),address.getCity()<br>
	 * <br>
	 * Es sind in der {@link Customer#setAllAttributesFromCsvLine(String)}drei Splits durch zufuerren das:
	 * <br>
	 * <ol>
	 * <li>Split der Hauptwerte hier in der Klasse {@link Customer} nach dem # dieses trennt Address und Kundenwerte.</li>
	 * <li>plit der Kundenwerte hier in der Klasse {@link Customer} nach dem ;</li>
	 * <li>Split der Adresswerte in der Klasse {@link Address} nach dem ;</li>
	 * </ol>
	 *
	 * @return strCsvLine : {@link String} : CSV-Line
	 */
	@Override
	public String getAllAttributesAsCsvLine() {
		String strCsvLine = "";
		
		strCsvLine += this.iId + DATA_ATTRIBUTE_SPLITTER;
		strCsvLine += this.ePronoun.getDescription() + DATA_ATTRIBUTE_SPLITTER;
		
		strCsvLine += this.strFirstName + DATA_ATTRIBUTE_SPLITTER;
		strCsvLine += this.strLastName + DATA_ATTRIBUTE_SPLITTER;
		strCsvLine += this.strBday + DATA_ATTRIBUTE_SPLITTER;
		
		strCsvLine += this.iAge + DATA_ATTRIBUTE_SPLITTER;
		strCsvLine += this.iSex + DATA_ATTRIBUTE_SPLITTER;
		
		strCsvLine += this.dblHeight + DATA_ATTRIBUTE_SPLITTER;
		strCsvLine += this.dblWeight + DATA_ATTRIBUTE_SPLITTER;
		
		strCsvLine += this.strPhoneNr + DATA_ATTRIBUTE_SPLITTER;
		strCsvLine += this.strEmail + DATA_RECORD_SPLITTER;
		strCsvLine += this.customerAddress.getAllAttributesAsCsvLine();
		
		
		return strCsvLine;
	}
	
	/**
	 * Setzt alle Attribiute inklusive der Adddresse
	 * basierend auf der in {@link Customer#getAllAttributesAsCsvLine()}
	 * generierten CSV-Zeile.
	 * <p>
	 * <p>
	 * Beispiel:<br>
	 * <br>
	 * <ol>
	 *     <li>{@link Customer#SPLIT_INDEX_CUSTOMER_DATA}
	 *         <ol>
	 *     <li>{@link Customer#SPLIT_INDEX_TITLE} - {@link Customer#ePronoun}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_FIRST_NAME} - {@link Customer#strFirstName}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_LAST_NAME} - {@link Customer#strLastName}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_BDAY} - {@link Customer#strBday}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_AGE} - {@link Customer#iAge}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_SEX} - {@link Customer#iSex}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_HEIGHT} - {@link Customer#dblHeight}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_WEIGHT} - {@link Customer#dblWeight}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_PHONE_NR} - {@link Customer#strPhoneNr}</li>
	 *     <li>{@link Customer#SPLIT_INDEX_EMAIL} - {@link Customer#strEmail}</li>
	 *     </ol>
	 *     </li>
	 *     <li>
	 *         {@link Customer#SPLIT_INDEX_CUSTOMER_ADDRESS_DATA}
	 *         <ol>
	 *             <li>
	 *                 {@link Customer#SPLIT_INDEX_CUSTOMER_ADDRESS_DATA} - {@link Customer#customerAddress#getAllAttributesAsCsvLine()}
	 *             </li>
	 *             </ol>
	 * </ol>
	 * Attribute:  strTitle;strFirstName;strLastName;strBday;iAge;iSex;dblHeight;dblWeight;strPhoneNr;strEmail<br>
	 * <p>
	 * Splitindex:  0                         1                   2              3<br>
	 * Attribute:  #address.getStreet(),address.getHouseNr(),address.getZip(),address.getCity()<br>
	 * <br>
	 * Es sind in der {@link Customer#setAllAttributesFromCsvLine(String)}drei Splits durch zufuerren das:
	 * <br>
	 * <ol>
	 * <li>Split der Hauptwerte hier in der Klasse {@link Customer} nach dem # dieses trennt Address und Kundenwerte.</li>
	 * <li>plit der Kundenwerte hier in der Klasse {@link Customer} nach dem ;</li>
	 * <li>Split
	 */
	@Override
	public void setAllAttributesFromCsvLine(String strCsvLine) throws Exception {
		
		//1. Hauptdatensaezte beide Splitten
		String[] strSplitArrayCustomerAndAddressData = strCsvLine.split(DATA_RECORD_SPLITTER);
		
		//2. Kundendaten extrahieren
		String strCsvLineCustomerData = strSplitArrayCustomerAndAddressData[SPLIT_INDEX_CUSTOMER_DATA];
		//3. Addressdaten extrhieren
		String strCsvLineCustomerAddressData = strSplitArrayCustomerAndAddressData[SPLIT_INDEX_CUSTOMER_ADDRESS_DATA];
		
		//3. Kundendaten splitten
		String[] strSplitArrayCustomerData = strCsvLineCustomerData.split(DATA_ATTRIBUTE_SPLITTER);
		
		//4. Kundendaten setzen
		this.iId      = Integer.valueOf(strSplitArrayCustomerData[SPLIT_INDEX_ID]);
		this.ePronoun = EPronoun.getEPronounByDescription(strSplitArrayCustomerData[SPLIT_INDEX_TITLE]);
		
		this.strFirstName = strSplitArrayCustomerData[SPLIT_INDEX_FIRST_NAME];
		this.strLastName  = strSplitArrayCustomerData[SPLIT_INDEX_LAST_NAME];
		this.strBday      = strSplitArrayCustomerData[SPLIT_INDEX_BDAY];
		
		this.iAge = Integer.parseInt(strSplitArrayCustomerData[SPLIT_INDEX_AGE]);
		this.iSex = Integer.parseInt(strSplitArrayCustomerData[SPLIT_INDEX_SEX]);
		
		this.dblHeight = Double.parseDouble(strSplitArrayCustomerData[SPLIT_INDEX_HEIGHT]);
		this.dblWeight = Double.parseDouble(strSplitArrayCustomerData[SPLIT_INDEX_WEIGHT]);
		
		this.strPhoneNr = strSplitArrayCustomerData[SPLIT_INDEX_PHONE_NR];
		this.strEmail   = strSplitArrayCustomerData[SPLIT_INDEX_EMAIL];
		
		//5. Address Daten splitten und setzen lassen
		this.customerAddress.setAllAttributesFromCsvLine(strCsvLineCustomerAddressData);
		
	}
	
	//endregion
	
	//region 4. toString() Funktionen generieren lassen mit Strg + Einfg oder Rechtsklick->Generate
	
	
	@Override
	public String toString() {
		return "Customer{" +
				"ePronoun=" + ePronoun +
				", strFirstName='" + strFirstName + '\'' +
				", strLastName='" + strLastName + '\'' +
				", strBday='" + strBday + '\'' +
				", iAge=" + iAge +
				", iSex=" + iSex +
				", dblHeight=" + dblHeight +
				", dblWeight=" + dblWeight +
				", strPhoneNr='" + strPhoneNr + '\'' +
				", strEmail='" + strEmail + '\'' +
				", customerAddress=" + customerAddress.toString() +
				"} " + super.toString();
	}
	//endregion
}



	

