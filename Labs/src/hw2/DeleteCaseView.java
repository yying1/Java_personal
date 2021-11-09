//Frank Yue Ying (yying2)
package hw2;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class DeleteCaseView extends CaseView{

	DeleteCaseView(String header) {
		super(header);
	}

	@Override
	Stage buildView() {
		Scene scene = new Scene(updateCaseGridPane);
		stage.setScene(scene);
		return stage;
	}

}
