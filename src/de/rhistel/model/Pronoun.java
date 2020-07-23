package de.rhistel.model;

/**
 * Diese Klasse bildet das {@link EPronoun}
 * einfach nochmal ab um zu zeigen wie ein Enum
 * als Klasse aussieht.
 */
public class Pronoun {
	
	//region 0. Konstanten
	
	private static final String DIVERSE = "Keine Anrede";
	private static final String MR      = "Herr";
	private static final String MRS     = "Frau";
	
	private static final int I_DIVERSE_VALUE = 0;
	private static final int I_MR_VALUE      = 1;
	private static final int I_MRS_VALUE     = 2;
	//endregion
	
	//region 1. Decl. and Init Attribute
	private String strDescription;
	//endregion
	
	//region 2. Konstruktor
	
	public Pronoun() {
		strDescription = Pronoun.DIVERSE;
	}
	
	//endregion
	
	
	//region 3. Getter und Setter
	
	
	public String getDescription() {
		return strDescription;
	}
	
	public void setDescription(String strDescription) {
		if (strDescription.equalsIgnoreCase(DIVERSE)) {
			this.strDescription = Pronoun.DIVERSE;
		}
		
		if (strDescription.equalsIgnoreCase(MR)) {
			this.strDescription = Pronoun.MR;
			
		}
		
		if (strDescription.equalsIgnoreCase(MRS)) {
			this.strDescription = Pronoun.MRS;
		}
	}
	
	public int getIntValueByDescription(String strDescription) {
		int iValue = -1;
		
		if (strDescription.equalsIgnoreCase(DIVERSE)) {
			iValue = Pronoun.I_DIVERSE_VALUE;
		}
		
		if (strDescription.equalsIgnoreCase(MR)) {
			iValue = Pronoun.I_MR_VALUE;
		}
		
		if (strDescription.equalsIgnoreCase(MRS)) {
			iValue = Pronoun.I_MRS_VALUE;
		}
		
		return iValue;
	}
	//endregion
}
