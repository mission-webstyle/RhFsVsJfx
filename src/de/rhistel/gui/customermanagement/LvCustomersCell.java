package de.rhistel.gui.customermanagement;

import de.rhistel.model.Customer;
import javafx.scene.control.ListCell;

public class LvCustomersCell extends ListCell<Customer> {
	@Override
	protected void updateItem(Customer customerToShowInCell, boolean empty) {
		
		//Superklasse Item zur weiterverabeitung mitgeben
		super.updateItem(customerToShowInCell, empty);
		
		//TODO Check ob leer -> Upatebug ist dann nicht da
		if ( empty || customerToShowInCell == null) {
			
			//Text der Stelle setzen
			this.setText(null);
			
			this.setGraphic(null);
		}else{
			this.setText(customerToShowInCell.getFullName() + customerToShowInCell.getBday());
		}
		
	}
}
