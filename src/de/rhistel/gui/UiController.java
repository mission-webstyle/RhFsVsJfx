package de.rhistel.gui;

import de.rhistel.gui.customermanagement.CustomerManagementController;
import de.rhistel.gui.login.LoginController;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Kontrolliert das anzeigen aller
 * GUIs des Projektes
 */
public class UiController {
	
	//region 0.Konstanten
	
	private static final String STYLES_CSS_PATH = "/styles.css";
	//endregion
	
	//region 1. Decl. and Init
	private static UiController instance;
	
	/**
	 * Hauptbuehne von der Main urspruenglich uebergeben
	 * Somit haben wir ein Fenster was immer den
	 * passenden Inhalt anzeigt.
	 */
	private Stage primaryStage;
	
	private URL urlToStyleSheet;
	
	private LoginController              loginController;
	private CustomerManagementController customerManagementController;
	//endregion
	
	//region 2. Konstruktor
	
	/**
	 * Standardkonstruktor
	 */
	private UiController() {
		this.urlToStyleSheet = this.getClass().getResource(STYLES_CSS_PATH);
		
		this.loginController = new LoginController();
		
		this.customerManagementController = new CustomerManagementController();
	}
	
	//endregion
	
	//region 3. Getter und Setter
	
	/**
	 * Gibt die einzige Instanz (threadsicher, weil synchronized) dieses Kontrollers zurueck.
	 *
	 * @return instance : {@link UiController} : Einzige Instanz
	 */
	public static synchronized UiController getInstance() {
		if (instance == null) {
			instance = new UiController();
		}
		
		return instance;
	}
	
	/**
	 * Kann null sein, immer erst checken ob im {@link de.rhistel.main.Main} die primary
	 * Stage uebergebene worden ist sont hangelt es ein {@link NullPointerException}
	 *
	 * @return primaryStage : {@link Stage} : Hauptfenster kann null sein sollte dieser
	 * Getter verwendet werden bevor der Setter genutzt wurde(in der {@link de.rhistel.main.Main} um diese
	 * Referenz zu setzen. Es kann {@link NullPointerException}s geben
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	/**
	 * Setzt die Referenz auf die primaryStage. Diese Methode
	 * sollte am besten in der {@link de.rhistel.main.Main} genutzt werden
	 * bevor der {@link UiController#getPrimaryStage()} Getter genutzt wird.
	 * Sonst produziert dieser eine {@link NullPointerException}.
	 *
	 * @param primaryStage : {@link Stage} : Hauptbuehne aus der {@link de.rhistel.main.Main}
	 */
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	//endregion
	
	//region 4. GUIs
	
	public void showLogin() {
		this.loginController.openLoginScene(this.primaryStage, this.urlToStyleSheet);
	}
	
	public void showCustomerManagement() {
		this.customerManagementController.openCustomerManagementScene(this.primaryStage, this.urlToStyleSheet);
	}
	//endregion
	
}
