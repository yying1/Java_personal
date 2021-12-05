//Frank Yue Ying (yying2)
package hw3;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@SuppressWarnings("serial")
public class DataException extends RuntimeException{
	DataException(int rejectedCaseCounter) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Data Error");
		alert.setContentText(rejectedCaseCounter + " cases rejected. " + "\n"+"The file must have cases with\n tab separated date, title, type, and case number!");
		alert.showAndWait();
	}
	
	DataException() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText("Case must have date, title, type, and number");
		alert.showAndWait();
	}
	
	DataException(String Casenumber) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText("Duplicate case number");
		alert.showAndWait();
	}
}
