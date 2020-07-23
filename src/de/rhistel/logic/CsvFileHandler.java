package de.rhistel.logic;

import de.rhistel.model.Customer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvFileHandler {
	
	//region 0. Konstanten
	private static final String FILE_PATH      = "src/de/rhistel/customers/";
	private static final String FILE_TYPE_CSV  = ".csv";
	private static final String FILE_TYPE_TXT  = ".txt";
	private static final String FILE_NAME      = "/customers" + FILE_TYPE_CSV;
	private static final String FULL_FILE_PATH = FILE_PATH + FILE_NAME;
	//endregion
	
	//region 1.  Decl. and Init Attribute
	private static CsvFileHandler instance;
	//endregion
	
	//region 2. Konstruktor
	
	/**
	 * Private Konstruktor
	 */
	private CsvFileHandler() {
		//Nichts zu tu nausser privat zu sein
	}
	
	//endregion
	
	//region 3. Getter un0d Setter
	
	/**
	 * Gibt die einzige synchronisiert(Threadsichere) Instanz zurueck
	 * dies jemals geben wird und generiert diese bei ersten Aufruf
	 *
	 * @return instance: {@link CsvFileHandler} : Einzige Instance Singleton ftw
	 */
	public static synchronized CsvFileHandler getInstance() {
		if (instance == null) {
			instance = new CsvFileHandler();
		}
		
		return instance;
	}
	//endregion
	
	//region 4. Speichern
	
	/**
	 * Speichert den uebergebenen Kunden in eine Datei
	 *
	 * @param customersToSave : {@link Customer} : Kunden zum speichern
	 */
	public void saveCustomersToCsvFile(List<Customer> customersToSave) {
		
		//Decl. and Init Schreibgeraetschaften
		File myNewFolder  = null;
		File myFileToSave = null;
		
		FileOutputStream   fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter     out = null;
		
		
		try {
			
			//Neuer Ordner definieren
			myNewFolder = new File(FILE_PATH);
			
			//Checken ob bereits exisitiert
			if (!myNewFolder.exists()) {
				myNewFolder.mkdir();
			}
			
			//1.) Neue Datei definieren
			myFileToSave = new File(myNewFolder.getAbsolutePath() + FILE_NAME);
			
			
			/*
			 * 2.)
			 * Kann verwendet werden wenn es der Inhalt schon vorher passend bearbeitet
			 * wurde und alle Zeichen und Umbruecke etc. festethen rausschreiben nur mit
			 * bytes heisst es wird exakt genau so rausgeschrieben wie es ist
			 * Alle Ausgaben mussen zu ersten ein String sein der dann das byteArray zurueck
			 * gibtt
			 * fos.write(strLinesToSave.get(0).getBytes(StandardCharsets.US_ASCII));
			 *
			 * Inhalt wir direkt in die Datei geschrieben
			 * Datenstrom, bzw. die "Bruecke" zur Datei auf Basis der Infos des File-Objektes nur bytes schreiben
			 */
			
			fos = new FileOutputStream(myFileToSave, false);
			
			/*
			 * 3.)
			 * OutputStreamWriter generieren, fos kann nur ints und bytes schreiben
			 * Setz aber nur einmalig den Zeichensatz und nicht wie beim fos wo
			 * fuer jeden Inhaltsblock der Zeichesatu direkt noch einmal mit angegeben
			 * werden muss. Inhalt wir direkt in die Datei geschrieben.
			 */
			osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			
			
			/*
			 * 4.)
			 *
			 * BufferedWriter generieren um ohne Probleme Strings schreiben zu können
			 * Fuer große Dateien der Inhalt noch bearbeitet/verbessert werden musss
			 * bevor der Inhalt in die Datei geschrieben wird. Da der BufferedWriter
			 * den osw und fos beinhaltet hat alle Informationen zur Datei zum Anghaeng(Append)
			 * verhalten so wie ein einmalig gesezten Zeichesatz. Wie der Name wir der Inhalt
			 * gepuffert was bei großen Datenmengen seher effizient ist. und erst mit dem out.close()
			 * wird der Inhalt in die Datei geschrieben. Hohe Flexibiliaet was den Inhalt angeleangt
			 * Auch Zeilenumbrueche und aehnlichs sind schnell eingeplegt.
			 */
			out = new BufferedWriter(osw);
			
			for (Customer c : customersToSave) {
				out.write(c.getAllAttributesAsCsvLine());
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			// Alles schliess was geoffnet wurde um die Datei nicht zu blockieren.
			try {
				if (out != null) {
					out.close();
				}
				
				if (osw != null) {
					osw.close();
				}
				
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//endregion
	
	//region 5. Lesen
	
	/**
	 * Liest die Kunden aus einer Datei und gibt diesen als Liste zurueck
	 *
	 * @return strReadLines : {@link Customer} : Ausgelesene Kunden der Datei.
	 */
	public List<Customer> readCustomersFromFile() {
		//Decl anbd Init
		List<Customer> customersFromFile = new ArrayList<>();
		
		File myNewFolder  = null;
		File myFileToRead = null;
		
		FileInputStream   fis = null;
		InputStreamReader isr = null;
		BufferedReader    in  = null;
		
		try {
			
			//Neuer Ordner definieren
			myNewFolder = new File(FILE_PATH);
			
			//1.) Neue Datei definieren
			myFileToRead = new File(myNewFolder.getAbsolutePath() + FILE_NAME);
			
			if (myFileToRead.exists()) {
				
				/**
				 * 1.) Datenstrom zur Datei nur zum Lesen
				 * hat keinen Zeichensatz und liest nur einzelne Byts die
				 * int Bloecken zuruekgegebe werden oder alle Bytes der Datei
				 * auf einmal.
				 */
				fis = new FileInputStream(myFileToRead);
				
				/*
				 * 2.) Datenstrom leser welcher den dis entegene nimmt und+
				 * wieder damit alle Informationen bezueglich der Datei und
				 * Ihren Speicher ort hat. Ebenfalls wird ein Zeichensatz gesetzt
				 * um das richtige Auslesen von Umlauten etc. zu gewaehrliesten.
				 */
				isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
				
				
				/*
				 * 3.) Gepufferter Leserling nimmt den Datenstromleser isr entgegen
				 * und hat damit alle Informationen bezeuglich der Datei, Ihren Speicherort,
				 * sowie den Zeichensat der angewendet werden soll. Liest Zeilenweise aus,
				 * bis das Ende der Datei erreicht wird, was mit null kund getan wird.
				 */
				in = new BufferedReader(isr);
				
				
				//[E]nd [o]f [File]
				boolean eof = false;
				
				while (!eof) {
					
					//1. Zeile lesen
					String strReadCsvLine = in.readLine();
					
					//2. Checken ob das Ende der Datei / Streams erreicht wurd
					if (strReadCsvLine == null) {
						eof = true;
					} else {
						//1. Neuer Kunde anlegen
						Customer singleCustomerFromFile = new Customer();
						
						//2. Gelesen Zeile mit Attributen weitergbeen
						singleCustomerFromFile.setAllAttributesFromCsvLine(strReadCsvLine);
						
						//3. Kunden zur Liste hinzufuegen
						customersFromFile.add(singleCustomerFromFile);
					}
					
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				
				if (isr != null) {
					fis.close();
				}
				
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return customersFromFile;
	}
	//endregion
}
