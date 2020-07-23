package de.rhistel.gui.login;

import de.rhistel.Encrypter;
import de.rhistel.gui.UiController;
import de.rhistel.logic.db.DbManager;
import de.rhistel.model.User;
import de.rhistel.settings.ApplicationTexts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Verwaltet die Loginevents.
 */
public class LoginController implements Initializable {
	
	//region 0.Konstanten
	private static final int PRIMARY_STAGE_LOGIN_WIDTH  = 420;
	private static final int PRIMARY_STAGE_LOGIN_HEIGHT = 275;
	
	private static final String LOGIN_LAYOUT_PATH = "/login_layout.fxml";
	
	
	//endregion
	
	//region 1. Decl. and Init
	
	
	@FXML
	private Label lblHeader;
	
	@FXML
	private TextField txtUserName;
	
	@FXML
	private PasswordField txtUserPw;
	
	//endregion
	
	//region 2. Konstruktor
	//endregion
	
	//region 3. Initilasierung der GUI
	
	/**
	 * Called to initialize a controller after its root element has been
	 * completely processed.
	 *
	 * @param location  The location used to resolve relative paths for the root object, or
	 *                  {@code null} if the location is not known.
	 * @param resources The resources used to localize the root object, or {@code null} if
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}
	
	//endregion
	
	//region 4. Aufbau und Anzeige der GUI
	
	/**
	 * Code zum offenen aus der Main kopiert
	 * und hier eingefuegt angepasst auf das LoginLayout
	 *
	 * @param primaryStage    : {@link Stage} : wird in der {@link de.rhistel.main.Main} gesetz
	 * @param urlToStyleSheet
	 */
	public void openLoginScene(Stage primaryStage, URL urlToStyleSheet) {
		try {
			//Code aus der Main kopiert hier eingefuegt mit neuer methode
			URL urlToLoginLayout = this.getClass().getResource(LOGIN_LAYOUT_PATH);
			
			Parent rootParent = FXMLLoader.load(urlToLoginLayout);
			
			Scene primaryScene = new Scene(rootParent, PRIMARY_STAGE_LOGIN_WIDTH,
			                               PRIMARY_STAGE_LOGIN_HEIGHT);
			
			if (urlToStyleSheet != null) {
				
				//Link als Text auslesen
				String strUrlToStylesheet = urlToStyleSheet.toExternalForm();
				
				// Styleangaben der Scene (Inhalt des Fensters zuweisen)
				primaryScene.getStylesheets().add(strUrlToStylesheet);
				
			}
			
			//4. Fenstertitel setzen
			primaryStage.setTitle(ApplicationTexts.APPLICATION_NAME);
			
			
			//5. Fensterinhalt setzen
			primaryStage.setScene(primaryScene);
			
			//6. Fenster im eigenen Prozess anzeigen
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//endregion
	
	//region 5. Login
	
	/**
	 * Macht den Login =)
	 */
	@FXML
	private void onClickLogin() {
		try {
			//Code auselsen von Gui
			String strUserName = this.txtUserName.getText();
			String strUserPw   = this.txtUserPw.getText();
			
			//Sollte nicht leer sein
			if ((!strUserPw.isEmpty()) | (!strUserPw.isBlank())) {
				
				
				//Eingabe verschluesseln
				final String strEncryptedUserPw = Encrypter.getInstance().
						encryptToSha256HashHexString(strUserPw);
				
				//Checken ob der User in der Db mit den eingebgeben Daten existiert
				User userFromDb = DbManager.getInstance().getUserByNameAndPw(strUserName, strEncryptedUserPw);
				
				//Checken ob die Eingabe richtig ist
				if (userFromDb != null) {
					
					/*
					 * User an SessionControler weitergeben.
					 *
					 * SessionController merkt sich den user und bietet natruerlich auch eine Moeglichkeit zum
					 * ausloggen. Und sorgt dafuer das beim Schliessen des Programmes der User auch ausgeloggt
					 * wird. Sollte die Session druch nicht nutzen des Programmes (abgelauferner Timer wie bei
					 * Bankingsides) ungeueltig werden, so wird der User auch ausgeloggt. Man koennte auch den
					 * SessionController komplett zum checken der Daten nutens und Ihne auch wenn man will
					 * UserController oder so nennen das kommt auf den Anwendungsfall an. Wir verzichten
					 *  hier auf eine "Session"
					 */
					//Naechste Maske anzeigen
					UiController.getInstance().showCustomerManagement();
				} else {
					
					//UserHinweis falscher Code
					showUserMsgOnHeader("Logindaten sind ungültig!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	//endregion
	
	
	//region Hilfsmethoden und Funktionen
	
	private void showUserMsgOnHeader(String strUserMsg) {
		this.lblHeader.setText(strUserMsg);
	}
	//endregion
}
