package de.rhistel.gui.customermanagement;

import de.rhistel.gui.UiController;
import de.rhistel.logic.CsvFileHandler;
import de.rhistel.model.Customer;
import de.rhistel.model.EPronoun;
import de.rhistel.settings.ApplicationTexts;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static de.rhistel.settings.ApplicationTexts.USER_MSG_PLEASE_ENTER_EVERYTHING;
import static de.rhistel.settings.ApplicationTexts.USER_MSG_SAVED_SUCCESSFULLY;

/**
 * Nimmt die Events der GUI entgegen und
 * leitet die weitere Logik ein.
 * <p>
 */
public class CustomerManagementController implements Initializable {
	
	//region 0. Konstanten
	private static final int PRIMARY_STAGE_CUSTOMER_MANAGEMENT_WIDTH  = 1620;
	private static final int PRIMARY_STAGE_CUSTOMER_MANAGEMENT_HEIGHT = 800;
	
	private static final String CUSTOMER_MANAGEMENT_LAYOUT_PATH = "/customer_management_layout.fxml";
	//endregion
	
	//region 1. Decl. and Init Attribute
	@FXML
	private ListView<Customer> lvCustomers;
	
	/**
	 * In Spitzenklammern den Objekttyp angegben den die ComboBox anzeigt
	 * sprich den Typ der Eintraege
	 */
	@FXML
	private ComboBox<String> cboPronoun;
	
	@FXML
	private TextField txtFirstName;
	
	@FXML
	private TextField txtLastName;
	
	@FXML
	private TextField txtBday;
	
	@FXML
	private TextField txtAge;
	
	
	@FXML
	private TextField txtSex;
	
	@FXML
	private TextField txtHeight;
	
	@FXML
	private TextField txtWeight;
	
	@FXML
	private TextField txtPhoneNr;
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private TextField txtStreet;
	
	@FXML
	private TextField txtHouseNr;
	
	@FXML
	private TextField txtZip;
	
	@FXML
	private TextField txtCity;
	
	
	/**
	 * Enthaelt alle Kunden die zur Laufzeit
	 * gelesen, angelegt, geaendert oder geloescht werden.
	 * TODO List wird zu {@link ObservableList}
	 */
	private ObservableList<Customer> listOfAllCustomers;
	//endregion
	
	//region 2. Konstruktor
	
	/**
	 * Standardkonstruktor
	 * zum dirketen auslesen der Kunden aus der Dateie
	 */
	public CustomerManagementController() {
	}
	
	//endregion
	
	//region 3. Initalsieren
	
	/**
	 * Wird aufgerufen sobald der Controller verwendet werden kann,
	 * und befuellt Steuereleemente mit Standardwerten und/oder Text.
	 *
	 * @param location  The location used to resolve relative paths for the root object, or
	 *                  {@code null} if the location is not known.
	 * @param resources The resources used to localize the root object, or {@code null} if
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//1. Kombobox befuellen
		this.fillCboPronoun();
		
		//2. ListViewBefuellen
		this.initListView();
	}
	
	
	//endregion
	
	//region 4. Aufbau und Anzeige der Gui
	
	/**
	 * Baut das CustomerManangement auf. Wird aus dem {@link UiController} geqtriggert
	 *
	 * @param primaryStage    : {@link Stage} : Hauptfenster aus {@link de.rhistel.main.Main}
	 * @param urlToStyleSheet : {@link URL} : Link zur CSS
	 */
	
	public void openCustomerManagementScene(Stage primaryStage, URL urlToStyleSheet) {
		try {
			//0. PrimayStage ist bereits geneeriert und stellt das Fenster da in dem Inhalt angezeigt wird
			
			//1. Universal Resource Link zum Main Layout aus Resourceordner und Layoutfile generrieren
			URL urlToCustomerMangementLayout = this.getClass().getResource(CUSTOMER_MANAGEMENT_LAYOUT_PATH);
			
			//2. Layout setzen
			Parent rootParent = FXMLLoader.load(urlToCustomerMangementLayout);
			
			
			//3. Mit Layoutinformationen vom rootParent und Groessenangaben Fensterinhalt genrieren lassen
			Scene primaryScene = new Scene(rootParent, PRIMARY_STAGE_CUSTOMER_MANAGEMENT_WIDTH,
			                               PRIMARY_STAGE_CUSTOMER_MANAGEMENT_HEIGHT);
			
			
			if (urlToStyleSheet != null) {
				
				//Link als Text auslesen
				String strUrlToStylesheet = urlToStyleSheet.toExternalForm();
				
				// Styleangaben der Scene (Inhalt des Fensters zuweisen)
				primaryScene.getStylesheets().add(strUrlToStylesheet);
			}
			
			//4. Fenstertitel und Postion setzen
			primaryStage.setTitle(ApplicationTexts.APPLICATION_NAME);
			primaryStage.setX(10D);
			primaryStage.setY(10D);
			
			
			//5. Fensterinhalt setzen
			primaryStage.setScene(primaryScene);
			
			//6. Fenster im eigenen Prozess anzeigen
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//endregion
	
	//region 5. Kunde speichern
	
	/**
	 * Liest alle Kunden aus checkt ob diese valide sind
	 * und speichert den Kunden in eine Datei
	 */
	@FXML
	private void onClickSubmitCustomer() {
//
		//Alle Kundendaten auslesen bei Falscheingabe ist der Rueckgabewert null
		Customer customerFromUi = this.getCustomerDataFromUi();

		//Checken ob alle Daten richtig waren
		if (customerFromUi != null) {

			//Einen neuen Kunden zur Liste hinzufuegenA
			this.listOfAllCustomers.add(customerFromUi);

			//Veraenderung in die Dateis speichern TODO speater durch DBAufrufersetzen
			CsvFileHandler.getInstance().saveCustomersToCsvFile(this.listOfAllCustomers);

			//DEBUG
			System.out.println(USER_MSG_SAVED_SUCCESSFULLY);

			resetCustomerDataTextFields();

			//TODO updateListView loeschen
		} else {
			//DEBUG
			System.out.println("Nicht gespeichert");
		}
	}
	//endregion
	
	//region Hilfsmethoden und Funktionen
	
	/**
	 * Update ListView befuellt die ListView
	 */
	private void initListView() {
		
		//TODO Kundenliste in lokale Variable speichern
		List<Customer> customersFromFile = CsvFileHandler.getInstance().readCustomersFromFile();
		
		//TODO Eine FxCollection Observable List fromen und em Attribut zuweisen
		this.listOfAllCustomers = FXCollections.observableList(customersFromFile);
		
		
		this.lvCustomers.getItems().clear();
		this.lvCustomers.setItems(this.listOfAllCustomers);
		
		this.lvCustomers.setOrientation(Orientation.VERTICAL);
		
		//CellFacotry generieren und setzen
		LvCustomersCallback lvCustomersCallback = new LvCustomersCallback();
		this.lvCustomers.setCellFactory(lvCustomersCallback);
		
		
		//Listener der ListView zu weisen
		this.lvCustomers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
			@Override
			public void changed(ObservableValue<? extends Customer> observableCustomers,
			                    Customer prevCustomer, Customer currentCustomer) {
				
				//Checken ob was genau selktiert ist und nur bei richtiger Selektierung weiter machen
				if ((currentCustomer != null) && (!currentCustomer.equals(prevCustomer))) {
					
					//Anzeigen des aktuellen Kunden mit neuer erstellter Methode
					fillCustomerDataInGui(currentCustomer);
				}
				
			}
		});
	}
	
	private void fillCboPronoun() {
		//1. Eine Liste von Eintragen die gewaehlt werden koennen solle generieren
		List<String> strPronounList = new ArrayList<>();
		
		//2. Eintrage und Reihnefolge festelegen
		strPronounList.add(EPronoun.DIVERSE.getDescription());
		strPronounList.add(EPronoun.MR.getDescription());
		strPronounList.add(EPronoun.MRS.getDescription());
		
		//3. Aus einfacher ArrayList Observable Liste fuer Fx-Steuereleemnte generieren mit Factoryfunktion
		ObservableList<String> observablePronounList = FXCollections.observableList(strPronounList);
		
		//4. Observable Liste zur ComboBox hinzufugen das diese die Eintraege anezeigen kann
		this.cboPronoun.setItems(observablePronounList);
		
		//5. Standardselektion auswaehlen und anzeigen
		this.cboPronoun.getSelectionModel().clearAndSelect(EPronoun.DIVERSE.ordinal());
		
	}
	
	
	/**
	 * Liest alle Kundendaten von der Ui (Konsole)
	 * aus und generiert ein neuen {@link Customer}, sollten
	 * die Eingabedaten valide (hier in userem Fall nur nicht leer) sein.
	 * und gibt diesen befuellt zurueck. Sind die Eingabedaten nicht valide
	 * (minimum eine Eingabe war leer oder bestand nur aus Leerzeichen) dann
	 * wird null zurueck gegeben. Es gibt nur einen Kunden mit richtigen Daten
	 *
	 * @return customerFromUi : {@link Customer} : Neuer befuellter Kunde bei richtigen Eingaben
	 * bei <b>Falscheingabe</b> wird <b> >null< </b> zurueck gegeben.
	 */
	private Customer getCustomerDataFromUi() {
		//Decl and Init
		Customer customerFromUi   = null;
		boolean  isUserInputValid = true;
		
		//Eingabedaten auslesen
		String   strPronounDescription = this.cboPronoun.getSelectionModel().getSelectedItem();
		EPronoun ePronoun              = EPronoun.getEPronounByDescription(strPronounDescription);

//		EPronoun ePronoun = this.cboPronoun.getSelectionModel().getSelectedItem();
		
		String strFirstName = this.txtFirstName.getText();
		String strLastName  = this.txtLastName.getText();
		
		String strBday = this.txtBday.getText();
		
		int iAge = -1;
		int iSex = -1;
		
		Double dblHeight = -1D;
		Double dblWeight = -1D;
		
		try {
			iAge = Integer.parseInt(this.txtAge.getText());
			iSex = Integer.parseInt(this.txtSex.getText());
			
			dblHeight = Double.parseDouble(this.txtHeight.getText());
			dblWeight = Double.parseDouble(this.txtWeight.getText());
		} catch (NumberFormatException nfex) {
			nfex.printStackTrace();
			isUserInputValid = false;
		}
		
		String strPhoneNr = this.txtPhoneNr.getText();
		String strEmail   = this.txtEmail.getText();
		
		String strStreet  = this.txtStreet.getText();
		String strHouseNr = this.txtHouseNr.getText();
		String strZip     = this.txtZip.getText();
		String strCity    = this.txtCity.getText();
		
		//Alle Eingaben in ein Array packen um Sie auf einmal zu checken
		String[] strUserInput = {
				strFirstName,
				strLastName,
				strBday,
				strPhoneNr,
				strEmail,
				strStreet,
				strHouseNr,
				strZip,
				strCity
		};
		
		
		//Alle Eingabedaten durchlaufen
		for (String strValue : strUserInput) {
			
			//Wenn etwas leer ist gilt es als nicht korrekt / valide
			if (strValue.isEmpty() || strValue.isBlank()) {
				isUserInputValid = false;
			}
		}
		
		//Checken ob es falsch Eingaben gab
		if (isUserInputValid) {
			
			//Neues Kundenobjekt anlegen/generieren/instanziieren
			customerFromUi = new Customer();
			
			
			//Alle gecheckten Eingabedaten per setter dem Kundenobjekt geben
			customerFromUi.setEPronoun(ePronoun);
			customerFromUi.setFirstName(strFirstName);
			customerFromUi.setLastName(strLastName);
			
			customerFromUi.setBday(strBday);
			
			customerFromUi.setAge(iAge);
			customerFromUi.setSex(iSex);
			
			customerFromUi.setHeight(dblHeight);
			customerFromUi.setWeight(dblWeight);
			
			customerFromUi.setPhoneNr(strPhoneNr);
			customerFromUi.setEmail(strEmail);
			
			//Schon vorhandenes Blankoaddresskaertchen vom Formular ausfuellen
			customerFromUi.getCustomerAddress().setStreet(strStreet);
			customerFromUi.getCustomerAddress().setHouseNr(strHouseNr);
			customerFromUi.getCustomerAddress().setZip(strZip);
			customerFromUi.getCustomerAddress().setCity(strCity);
			
			
		} else {
			//Userhinweis ausgeben
			System.out.println(USER_MSG_PLEASE_ENTER_EVERYTHING);
		}
		
		return customerFromUi;
	}
	
	/**
	 * Nimmt einen Kunden entgegen und traegt dessen Daten in
	 * die Steuerelemente ein
	 *
	 * @param customerToShowInGui : {@link Customer} : Kunde der angezeigt werden soll
	 */
	private void fillCustomerDataInGui(Customer customerToShowInGui) {
		
		this.cboPronoun.getSelectionModel().clearAndSelect(customerToShowInGui.getEPronoun().ordinal());
		
		this.txtFirstName.setText(customerToShowInGui.getFirstName());
		this.txtLastName.setText(customerToShowInGui.getLastName());
		
		this.txtBday.setText(customerToShowInGui.getBday());
		this.txtAge.setText(customerToShowInGui.getAge().toString());
		this.txtSex.setText(customerToShowInGui.getSex().toString());
		
		this.txtHeight.setText(customerToShowInGui.getHeight().toString());
		this.txtWeight.setText(customerToShowInGui.getWeight().toString());
		
		this.txtPhoneNr.setText(customerToShowInGui.getPhoneNr());
		this.txtEmail.setText(customerToShowInGui.getEmail());
		
		this.txtStreet.setText(customerToShowInGui.getCustomerAddress().getStreet());
		this.txtHouseNr.setText(customerToShowInGui.getCustomerAddress().getHouseNr());
		this.txtZip.setText(customerToShowInGui.getCustomerAddress().getZip());
		this.txtCity.setText(customerToShowInGui.getCustomerAddress().getCity());
		
	}
	
	
	/**
	 * Alle Eingabefelder fuer Kundendaten
	 * leeren
	 */
	private void resetCustomerDataTextFields() {
		this.cboPronoun.getSelectionModel().clearAndSelect(EPronoun.DIVERSE.ordinal());
		
		//Eingabefocus auf Anredefeld setzen
		this.cboPronoun.requestFocus();
		
		this.txtFirstName.setText("");
		this.txtLastName.setText("");
		
		this.txtBday.setText("");
		this.txtAge.setText("");
		
		this.txtSex.setText("");
		this.txtHeight.setText("");
		this.txtWeight.setText("");
		
		this.txtPhoneNr.setText("");
		this.txtEmail.setText("");
		
		this.txtStreet.setText("");
		this.txtHouseNr.setText("");
		this.txtZip.setText("");
		this.txtCity.setText("");
	}
	
	
	//endregion
}
