package de.rhistel.gui.customermanagement;

import de.rhistel.model.Customer;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class LvCustomersCallback implements Callback <ListView<Customer>, ListCell<Customer>>{
	/**
	 * The <code>call</code> method is called when required, and is given a
	 * single argument of type P, with a requirement that an object of type R
	 * is returned.
	 *
	 * @param param The single argument upon which the returned value should be
	 *              determined.
	 *
	 * @return An object of type R that may be determined based on the provided
	 * parameter value.
	 */
	@Override
	public ListCell<Customer> call(ListView<Customer> param) {
		return new LvCustomersCell();
	}
}
