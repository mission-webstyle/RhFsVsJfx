package de.rhistel.logic;
import de.rhistel.model.Customer;

import static de.rhistel.settings.ApplicationTexts.*;
import static de.rhistel.settings.UserCommands.*;

/**
 * Diese Klasse nimmt den BMI
 * als auch das Alter und das
 * Geschlecht einer erwachsenen Person
 * entgegen und bewertet diesen abhaengig
 * von diese Daten.
 */
public class BmiAnalyzer {
	
	//region 0. Konstanten
	/**
	 * Index fuer den Statischen Zugriff auf den Startwert eines Grenzarrays
	 * fuer BMI Wert in folgenden Funktionen:
	 * <ul>
	 *     <li>{@link BmiAnalyzer#maleScoring(int, double)}</li>
	 *     <li>{@link BmiAnalyzer#femaleScoring(int, double)}</li>
	 *     <li>{@link BmiAnalyzer#diverseScoring(int, double)}</li>
	 * </ul>
	 */
	private static final int BOUNDARY_START = 0;
	
	/**
	 * Index fuer den Statischen Zugriff auf den Startwert eines Grenzarrays
	 * fuer BMI Wert in folgenden Funktionen:
	 * <ul>
	 *     <li>{@link BmiAnalyzer#maleScoring(int, double)}</li>
	 *     <li>{@link BmiAnalyzer#femaleScoring(int, double)}</li>
	 *     <li>{@link BmiAnalyzer#diverseScoring(int, double)}</li>
	 * </ul>
	 */
	private static final int BOUNDARY_END = 1;
	
	/**
	 * Adipositas BMI >= Erster Wert und BMI <= Zweiter Wert
	 * Diese Grenze bei Maennern und Frauen gleich daher sind Sie
	 * als Konstante definiert
	 */
	
	private static final double[] DBL_OBESE_BOUNDARIES = {31.00D, 40.00D};
	
	/**
	 * Starke Adipositas BMI > Wert dieser Grenzwert ist bei Maenner und Frauen gleich
	 * daher ist er als Konstante definiert
	 */
	private static final double DBL_HEAVILY_OBESE_BOUNDARY = 40.00D;
	//endregion
	
	//region 1. Decl. and Init Attribute
	//endregion
	
	//region 2. Standard Konstruktor
	
	/**
	 * Privater Standard Konstruktor
	 * um die instanziierung ausserhalb
	 * dieser Klasse zu verhindern
	 */
	private BmiAnalyzer() {
		//Nichts zu tun ausser privat zu sein
	}
	
	//endregion
	
	
	//region 3. BMI-Berechnung
	
	/**
	 * Berechnet den BMI eines {@link Customer}s
	 *
	 * @param customer : {@link Customer} : Kunde dessen BMI berechnet und bewertet werden soll
	 */
	public static void calcBmi(Customer customer) {
		
		double dblBmi = -1D;
		
		//BMI berechnen = Gewicht / (Groesse in m)²
		dblBmi = (customer.getWeight() / (Math.pow(customer.getHeight(), 2)));
		
		//BMI bewerten, dann ausgeben
		System.out.println(customer.getFullName() + ":\n" + scoreBmi(customer.getSex(), customer.getAge(), dblBmi));
		
		
	}
	//endregion
	
	//region 4. Start BMI Scoring
	
	/**
	 * Bewertet den Bmi abnhaengig von den Parameter und
	 * gibt einen Bewertungstext zurueck
	 *
	 * @param iSex   : int : maennlich = {@link de.rhistel.settings.UserCommands#CMD_BMI_MALE}
	 *               weiblich = {@link de.rhistel.settings.UserCommands#CMD_BMI_FEMALE}
	 *               divers = {@link de.rhistel.settings.UserCommands#CMD_BMI_DIVERSE}
	 * @param iAge   : int : Alter des Kunden in ganzen Jahren
	 * @param dblBmi : double : Berechnete Bmi den es zu bewerten gilt
	 *
	 * @return strBmiScoring : {@link String} : Bewertung des BMI in Abhaengikeit mit Parametern
	 */
	private static String scoreBmi(int iSex, int iAge, double dblBmi) {
		return switch (iSex) {
			case CMD_BMI_MALE -> maleScoring(iAge, dblBmi);
			case CMD_BMI_FEMALE -> femaleScoring(iAge, dblBmi);
			case CMD_BMI_DIVERSE -> diverseScoring(iAge, dblBmi);
			default -> USER_MSG_CHOOSE_VALID_NO_SUCH_SEX_VALUE;
		};
		
	}
	//endregion
	
	//region 4. Male Scoring
	
	/**
	 * Bewertet maennliche Kunden
	 *
	 * @param iAge   : int : Alter in ganzen Jahren
	 * @param dblBmi : double : BMI
	 *
	 * @return strMaleScoring : {@link String} : Bewertung
	 */
	private static String maleScoring(int iAge, double dblBmi) {
		//Decl and Init
		String strMaleScoringText = BMI_TEXT + dblBmi + "\n";
		
		//Untergewicht < Wert
		final double dblUnderWeightBoundary = 20.00D;
		
		//Alle Grenzwerte Normalgewicht [0] = Startwert [1] Endwert
		final double[] dblNormalWeightBoundaries = {20.00D, 25.00D};
		
		//Grenze zwischen Normal und Uebergewicht > Erst Wert und < Zweitert Wert
		final double[] dblBoundaryBetweenNormalAndOverWeight = {25.00D, 26.00D};
		
		//Uebergewichtsgrenzen >= Erster Wert und <= Zweiter
		final double[] dblOverWeightBoundaries = {26.00D, 30.00D};
		
		//Zwischen Uebergewicht und Adipositas > Erster Wert und < Zweiter Wert
		final double[] dblBoundaryBetweenOverWeightAndObese = {30.00D, 31.00D};
		
		boolean needsOptimalBmiValuInfo = false;
		
		//Untergewichtscheck
		if (dblBmi < dblUnderWeightBoundary) {
			
			//Scoringtext festelegen
			strMaleScoringText += UNDER_WEIGHT_TEXT;
		}
		
		//Normalgewichtscheck
		if ((dblBmi >= dblNormalWeightBoundaries[BOUNDARY_START]) &&
				(dblBmi <= dblNormalWeightBoundaries[BOUNDARY_END])) {
			
			//Scoringtext festlegen
			strMaleScoringText += NORMAL_WEIGHT_TEXT;
		}
		
		//Grenze zwischen Normal und Uebergewicht
		if ((dblBmi > dblBoundaryBetweenNormalAndOverWeight[BOUNDARY_START]) &&
				(dblBmi < dblBoundaryBetweenNormalAndOverWeight[BOUNDARY_END])) {
			
			//Flag setzen um den Optimalen Bmi Wert mit auszugeben
			needsOptimalBmiValuInfo = true;
			
			//Scoringtext festlegen
			strMaleScoringText += BOUNDARY_NORMAL_WEIGHT_AND_OVER_WEIGHT_TEXT;
		}
		
		//Uebergewichtscheck
		if ((dblBmi >= dblOverWeightBoundaries[BOUNDARY_START]) &&
				(dblBmi <= dblOverWeightBoundaries[BOUNDARY_END])) {
			
			//Flag setzen um den Optimalen Bmi Wert mit auszugeben
			needsOptimalBmiValuInfo = true;
			
			//Scoringtext festlegen
			strMaleScoringText += OVER_WEIGHT_TEXT;
		}
		
		//Grenze zwischen Uebergewicht und Adipositas
		if ((dblBmi > dblBoundaryBetweenOverWeightAndObese[BOUNDARY_START]) &&
				(dblBmi < dblBoundaryBetweenOverWeightAndObese[BOUNDARY_END])) {
			
			//Flag setzen um den Optimalen Bmi Wert mit auszugeben
			needsOptimalBmiValuInfo = true;
			
			//Scoringtext festlegen
			strMaleScoringText += BOUNDARY_OVER_WEIGHT_AND_OBESE_TEXT;
		}
		
		//Adipositacheck
		if ((dblBmi >= DBL_OBESE_BOUNDARIES[BOUNDARY_START]) &&
				(dblBmi <= DBL_OBESE_BOUNDARIES[BOUNDARY_END])) {
			
			//Flag setzen um den Optimalen Bmi Wert mit auszugeben
			needsOptimalBmiValuInfo = true;
			
			//Scoringtext festlegen
			strMaleScoringText += OBESE_TEXT;
		}
		
		//Starke Adipositas check
		if (dblBmi > DBL_HEAVILY_OBESE_BOUNDARY) {
			
			//Flag setzen um den Optimalen Bmi Wert mit auszugeben
			needsOptimalBmiValuInfo = true;
			
			//Scoringtext festlegen
			strMaleScoringText += HEAVILY_OBESE_TEXT;
		}
		
		
		if (needsOptimalBmiValuInfo) {
			strMaleScoringText += getOptimalBmiValueInfo(iAge);
		}
		
		return strMaleScoringText;
	}
	
	//endregion
	
	//region 5. Female Scoring
	
	/**
	 * Bewertet weibliche Kunden
	 *
	 * @param iAge   : int : Alter in ganzen Jahren
	 * @param dblBmi : double : BMI
	 *
	 * @return strFemaleScoring : {@link String} : Bewertung
	 */
	private static String femaleScoring(int iAge, double dblBmi) {
		//Decl and Init
		String strFemaleScoringText = BMI_TEXT + dblBmi + "\n";
		
		//Untergewicht < Wert
		final double dblUnderWeightBoundary = 19.00D;
		
		//Alle Grenzwerte Normalgewicht [0] = Startwert [1] Endwert
		final double[] dblNormalWeightBoundaries = {19.00D, 24.00D};
		
		//Grenze zwischen Normal und Uebergewicht > Erst Wert und < Zweitert Wert
		final double[] dblBoundaryBetweenNormalAndOverWeight = {24.00D, 25.00D};
		
		//Uebergewichtsgrenzen >= Erster Wert und <= Zweiter
		final double[] dblOverWeightBoundaries = {25.00D, 30.00D};
		
		//Zwischen Uebergewicht und Adipositas > Erster Wert und < Zweiter Wert
		final double[] dblBoundaryBetweenOverWeightAndObese = {30.00D, 31.00D};
		
		boolean needsOptimalBmiValuInfo = false;
		//Untergewichtscheck
		if (dblBmi < dblUnderWeightBoundary) {
			//Scoringtext festelegen
			strFemaleScoringText += UNDER_WEIGHT_TEXT;
		}
		
		//Normalgewichtscheck
		if ((dblBmi >= dblNormalWeightBoundaries[BOUNDARY_START]) &&
				(dblBmi <= dblNormalWeightBoundaries[BOUNDARY_END])) {
			//Scoringtext festlegen
			strFemaleScoringText += NORMAL_WEIGHT_TEXT;
		}
		
		//Grenze zwischen Normal und Uebergewicht
		if ((dblBmi > dblBoundaryBetweenNormalAndOverWeight[BOUNDARY_START]) &&
				(dblBmi < dblBoundaryBetweenNormalAndOverWeight[BOUNDARY_END])) {
			
			//Flag setzen um den Optimalen Bmi Wert mit auszugeben
			needsOptimalBmiValuInfo = true;
			
			//Scoringtext festlegen
			strFemaleScoringText += BOUNDARY_NORMAL_WEIGHT_AND_OVER_WEIGHT_TEXT;
		}
		
		//Uebergewichtscheck
		if ((dblBmi >= dblOverWeightBoundaries[BOUNDARY_START]) &&
				(dblBmi <= dblOverWeightBoundaries[BOUNDARY_END])) {
			
			//Flag setzen um den Optimalen Bmi Wert mit auszugeben
			needsOptimalBmiValuInfo = true;
			
			//Scoringtext festlegen
			strFemaleScoringText += OVER_WEIGHT_TEXT;
		}
		
		//Grenze zwischen Uebergewicht und Adipositas
		if ((dblBmi > dblBoundaryBetweenOverWeightAndObese[BOUNDARY_START]) &&
				(dblBmi < dblBoundaryBetweenOverWeightAndObese[BOUNDARY_END])) {
			
			//Flag setzen um den Optimalen Bmi Wert mit auszugeben
			needsOptimalBmiValuInfo = true;
			
			//Scoringtext festlegen
			strFemaleScoringText += BOUNDARY_OVER_WEIGHT_AND_OBESE_TEXT;
		}
		
		//Adipositacheck
		if ((dblBmi >= DBL_OBESE_BOUNDARIES[BOUNDARY_START]) &&
				(dblBmi <= DBL_OBESE_BOUNDARIES[BOUNDARY_END])) {
			
			//Flag setzen um den Optimalen Bmi Wert mit auszugeben
			needsOptimalBmiValuInfo = true;
			
			//Scoringtext festlegen
			strFemaleScoringText += OBESE_TEXT;
		}
		
		//Starke Adipositas check
		if (dblBmi > DBL_HEAVILY_OBESE_BOUNDARY) {
			
			//Flag setzen um den Optimalen Bmi Wert mit auszugeben
			needsOptimalBmiValuInfo = true;
			
			//Scoringtext festlegen
			strFemaleScoringText += OBESE_TEXT;
		}
		
		if (needsOptimalBmiValuInfo) {
			strFemaleScoringText += getOptimalBmiValueInfo(iAge);
		}
		
		return strFemaleScoringText;
	}
	//endregion
	
	//region 6. Diverse Scoring
	
	/**
	 * Bewertet diverse Kunden
	 *
	 * @param iAge   : int : Alter in ganzen Jahren
	 * @param dblBmi : double : BMI
	 *
	 * @return strDiversScoring : {@link String} : Bewertung
	 */
	private static String diverseScoring(int iAge, double dblBmi) {
		return maleScoring(iAge, dblBmi);
	}
	//endregion
	
	//region 7. Optimal BMI Value
	
	/**
	 * Gibt einen Hinweis als String zurueck was der Optimale BMI-Wert
	 * passend zum mitgegeben alter ist. Wird in den Funktionen
	 * <ul>
	 *     <li>{@link BmiAnalyzer#maleScoring(int, double)}</li>
	 *     <li>{@link BmiAnalyzer#femaleScoring(int, double)}</li>
	 *     <li>{@link BmiAnalyzer#diverseScoring(int, double)}</li>
	 * </ul>
	 * genutzt. Und auch dann wenn der BMI aussagt das kein Normal oder Untergewicht vorliegt
	 *
	 * @param iAge : int : Alter der Person
	 *
	 * @return strOptimalBmiValueInfo : {@link String} : Satz welcher den optimalen BMI abhaengig vom alter ansagt
	 */
	private static String getOptimalBmiValueInfo(int iAge) {
		//Decl and Init
		
		String strOptimalBmiValueInfo = "";
		
		int[] iAgeBorderNineTeenToTwentyFour    = {19, 24};
		int[] iAgeBordersTwentyFiveToThirtyFour = {25, 34};
		int[] iAgeBordersThirtyFiveToFortyFour  = {35, 44};
		int[] iAgeBordersFortyFiveToFiftyFour   = {45, 54};
		int[] iAgeBordersFiftyFiveToSixtyFour   = {55, 64};
		int   iAgeBordersSixtySix               = 65;
		
		if ((iAge >= iAgeBorderNineTeenToTwentyFour[BOUNDARY_START]) &&
				(iAge <= iAgeBorderNineTeenToTwentyFour[BOUNDARY_END])) {
			
			//String Format
			strOptimalBmiValueInfo = String.format(OPTIMAL_BMI_VALUE_INFO_FORMATTER, iAge, "19 - 24");
		}
		
		if ((iAge >= iAgeBordersTwentyFiveToThirtyFour[BOUNDARY_START]) &&
				(iAge <= iAgeBordersTwentyFiveToThirtyFour[BOUNDARY_END])) {
			//String Format
			strOptimalBmiValueInfo = String.format(OPTIMAL_BMI_VALUE_INFO_FORMATTER, iAge, "20 - 25");
		}
		
		if ((iAge >= iAgeBordersThirtyFiveToFortyFour[BOUNDARY_START]) &&
				(iAge <= iAgeBordersThirtyFiveToFortyFour[BOUNDARY_END])) {
			
			//String Format
			strOptimalBmiValueInfo = String.format(OPTIMAL_BMI_VALUE_INFO_FORMATTER, iAge, "21 - 26");
		}
		
		if ((iAge >= iAgeBordersFortyFiveToFiftyFour[BOUNDARY_START]) &&
				(iAge <= iAgeBordersFortyFiveToFiftyFour[BOUNDARY_END])) {
			
			//String Format
			strOptimalBmiValueInfo = String.format(OPTIMAL_BMI_VALUE_INFO_FORMATTER, iAge, "22 - 27");
		}
		
		if ((iAge >= iAgeBordersFiftyFiveToSixtyFour[BOUNDARY_START]) &&
				(iAge <= iAgeBordersFiftyFiveToSixtyFour[BOUNDARY_END])) {
			//String Format
			strOptimalBmiValueInfo = String.format(OPTIMAL_BMI_VALUE_INFO_FORMATTER, iAge, "23 - 28");
		}
		
		if (iAge > iAgeBordersSixtySix) {
			//String Format
			strOptimalBmiValueInfo = String.format(OPTIMAL_BMI_VALUE_INFO_FORMATTER, iAge, "24 - 29");
		}
		
		return strOptimalBmiValueInfo;
		
	}
	//endregion
}
