package de.rhistel.model;

/**
 * Bildet die Anreden
 * eines Kunden ab.
 * <p>
 * Eine Enumaration kurz Enum genannt
 * bildet geordnete Werte ab. Die Reihenfolge
 * dieser Werte bestimmt die Ordnungszahl. Jeder
 * Wert immer eine Ordnungs zahl. Die sogeannte
 * Ordinalzahl oder kur ordinal (im Jargon).
 * <p>
 * Eine Enumartion wird immer dann genutzt, wenn
 * ein einfacher Ordnungswert in Form einer Konstante
 * oder Variable nicht aussreicht und man noch Zusatzinfos
 * zu den Werten haben moechte. Oder wenn die Konstanten
 * alleine vom Namen her nicht sehr aussagekreaftig sind
 * und man diese zentralisieren moechte, ein Klass aber
 * ein Overkkill ist.
 * <p>
 * Wenn die reine Sortierung nicht aussreicht
 * kann einem Ordungswert auch eine Eigenschaft gegeben werden.
 * Diese muss dann direkt bei der Definiertung mit angegeben werdne
 * Begrenzung auf Datentypen gibt es nicht. Es kann alles verwendet werden
 * hier gibte Beschreibung vom Typ String. Die Beschreibeung oder Zusatzinfgormation
 * muss wie auch in Klassen als Attribut definiert werden. Wenn
 * diese Beschreibung gesetzt werden muss Konstruktor ohne Modifier
 * implementiert werden. Das Enum kann auch Getter fuer besagte Attribute
 * enthalten.
 */
public enum EPronoun {
	//region 0. Konstanten mit Beschreibung
	DIVERSE("Keine Anrede"),
	MR("Herr"),
	MRS("Frau");
	//endregion
	
	//region 1. Decl. and Init Attribute
	private String strDescription;
	//endregion
	
	//region 2. Konstruktor
	EPronoun(String strDescription) {
		this.strDescription = strDescription;
	}
	//endregion
	
	//region 3. Getter und Setter
	public String getDescription() {
		return strDescription;
	}
	
	/**
	 * Factoryfunktion
	 * Gibt ein Enumobjekt zurueck auf Basis
	 * der gelieferten Beschreibung
	 *
	 * @param strDescription : {@link String} : Beschreibung nach der gesucht wird
	 *
	 * @return EPronoun : {@link EPronoun} : Wird die Beschreibung nicht gefunden so
	 * wird automatisch {@link EPronoun#DIVERSE} zurueckgegeben
	 */
	public static synchronized EPronoun getEPronounByDescription(String strDescription) {
		if (EPronoun.DIVERSE.getDescription().equalsIgnoreCase(strDescription)) {
			return EPronoun.DIVERSE;
		}
		
		if (EPronoun.MR.getDescription().equalsIgnoreCase(strDescription)) {
			return EPronoun.MR;
		}
		
		if (EPronoun.MRS.getDescription().equalsIgnoreCase(strDescription)) {
			return EPronoun.MRS;
		}
		
		return EPronoun.DIVERSE;
	}
	//endregion
	
}
