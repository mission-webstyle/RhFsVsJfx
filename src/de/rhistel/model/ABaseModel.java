package de.rhistel.model;

/**
 * Diese Klasse definiert
 * alle Methoden / Funktionen
 * Eigenschaften und Konstanten
 * die in ALLEN Modelklassen dieses
 * Projekts gleichermaßen genutzt werden.
 */
public abstract class ABaseModel {
	
	//region 0. Konstanten
	protected static final String DEF_VALUE_STR = ">noValueYet<";
	protected static final int    DEF_VALUE_INT = -1;
	protected static final double DEF_VALUE_DBL = -1.00D;
	
	protected static final String DATA_RECORD_SPLITTER    = "#";
	protected static final String DATA_ATTRIBUTE_SPLITTER = ";";
	
	protected static final int SPLIT_INDEX_ID = 0;
	//endregion
	
	//region 1. Decl. and Init Attribute
	//TODO 1 IdErweiterungen in BaseModel mit SPLIT_INDEX Getter und Setter anpassen in Customer, Address und neuer Userklasse
	protected int iId;
	//endregion
	
	//region 2. Konstruktor
	protected ABaseModel() {
		this.iId = DEF_VALUE_INT;
	}
	
	protected ABaseModel(int iId) {
		this.iId = iId;
	}
	//endregion
	
	//region 3. Getter und Setter
	public int getId() {
		return iId;
	}
	
	public void setId(int iId) {
		this.iId = iId;
	}
	//endregion
	
	//region 4. Spezielle Meethoden und Funktionen
	
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
	public abstract String getAllAttributesAsCsvLine();
	
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
	public abstract void setAllAttributesFromCsvLine(String strCsvLine) throws Exception;
	//endregion
	
	//region 5. toString Funktion
	
	@Override
	public String toString() {
		return "ABaseModel{" +
				"iId=" + iId +
				'}';
	}
	
	//endregion
}
