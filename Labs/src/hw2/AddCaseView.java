//Frank Yue Ying (yying2)
package hw2;

import java.time.LocalDate;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddCaseView extends CaseView{

	AddCaseView(String header) {
		super(header);
	}

	@Override
	Stage buildView() {
		caseDatePicker.setValue(LocalDate.now());
		Scene scene = new Scene(updateCaseGridPane);
		stage.setScene(scene);
		return stage;
	}

}
