//Frank Yue Ying (yying2)
package hw2;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModifyCaseView extends CaseView{

	ModifyCaseView(String header) {
		super(header);
	}

	@Override
	Stage buildView() {
		Scene scene = new Scene(updateCaseGridPane);
		stage.setScene(scene);
		return stage;
	}

}
