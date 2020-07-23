package de.rhistel.main;

import de.rhistel.Encrypter;
import de.rhistel.gui.UiController;
import de.rhistel.logic.db.DbManager;
import de.rhistel.model.User;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Primarystage setzen und Login aufrufen
		//1. Hauptfenster an UiController uebergeben, so das dieser alle GUIs verwalten kann
		UiController.getInstance().setPrimaryStage(primaryStage);

		//2. Login aufrufen
		UiController.getInstance().showLogin();
		
//		System.out.println("VORHER:\n\n");
//		for (User u : DbManager.getInstance().getAllUsersFromDbTable()) {
//			System.out.print(u.getAllAttributesAsCsvLine());
//		}
//
////
////		String strUserName = "otto";
////		String strUserPwSha256HasValue = Encrypter.getInstance().encryptToSha256HashHexString("jaaaaa");
//
//		String strUserName             = "kasper";
//		String strUserPwSha256HasValue = Encrypter.getInstance().encryptToSha256HashHexString("le");
//
////		User userToInsert = new User(4,"otto",strUserPwSha256HasValue);
////		User userToUpdate = new User(3,"kasper",strUserPwSha256HasValue);
////		User userToDelete = new User(4,"otto",strUserPwSha256HasValue);
////
////		DbManager.getInstance().deleteUserById(userToDelete.getId());
//
//
//		User userFromDb = DbManager.getInstance().getUserByNameAndPw(strUserName, strUserPwSha256HasValue);
//
//		if (userFromDb != null) {
//			System.out.println("User gibt es: " + userFromDb.getUserName());
//		}else{
//			System.out.println("User gibt es nicht!");
//		}
		
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
