//Frank Yue Ying (yying2)
package hw2;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CyberCop extends Application{

	public static final String DEFAULT_PATH = "data"; //folder name where data files are stored
	public static final String DEFAULT_HTML = "/CyberCop.html"; //local HTML
	public static final String APP_TITLE = "Cyber Cop"; //displayed on top of app

	CCView ccView = new CCView();
	CCModel ccModel = new CCModel();

	CaseView caseView; //UI for Add/Modify/Delete menu option

	GridPane cyberCopRoot;
	Stage stage;

	static Case currentCase; //points to the case selected in TableView.

	public static void main(String[] args) {
		launch(args);
	}

	/** start the application and show the opening scene */
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		primaryStage.setTitle("Cyber Cop");
		cyberCopRoot = ccView.setupScreen();  
		setupBindings();
		Scene scene = new Scene(cyberCopRoot, ccView.ccWidth, ccView.ccHeight);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		ccView.webEngine.load(getClass().getResource(DEFAULT_HTML).toExternalForm());
		primaryStage.show();
	}

	/** setupBindings() binds all GUI components to their handlers.
	 * It also binds disableProperty of menu items and text-fields 
	 * with ccView.isFileOpen so that they are enabled as needed
	 */
	void setupBindings() {
		//write your code here
		if (ccView.isFileOpen.get() == false) {
			ccView.closeFileMenuItem.setDisable(true);
			ccView.addCaseMenuItem.setDisable(true);
			ccView.modifyCaseMenuItem.setDisable(true);
			ccView.deleteCaseMenuItem.setDisable(true);
		}
		ccView.caseTableView.setOnMouseClicked(new CurrentCaseHandler());
		ccView.openFileMenuItem.setOnAction(new OpenMenuItemHandler());
		ccView.closeFileMenuItem.setOnAction(new CloseFileMenuItemHandler());
		ccView.exitMenuItem.setOnAction(new ExitMenuItemHandler());
		ccView.searchButton.setOnAction(new SearchButtonHandler());
		ccView.clearButton.setOnAction(new ClearButtonHandler());
		ccView.addCaseMenuItem.setOnAction(new CaseMenuItemHandler());
		ccView.modifyCaseMenuItem.setOnAction(new CaseMenuItemHandler());
		ccView.deleteCaseMenuItem.setOnAction(new CaseMenuItemHandler());
		//The following is some helper code to display web-pages. It is commented out to start with
		//Uncomment it to plug it in your code as needed.  

		
	}
	
	class OpenMenuItemHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			FileChooser filechooser = new FileChooser();
			filechooser.setTitle("Select a case file");
			filechooser.setInitialDirectory(new File(DEFAULT_PATH));
			File file = null;
			String filename = "";
			if ((file = filechooser.showOpenDialog(stage)) !=null) {
				filename = file.getAbsolutePath();
			}
			ccModel.readCases(filename);
			ccModel.buildYearMapAndList();
			ccView.caseTableView.setItems(ccModel.caseList);
			stage.setTitle("Cyber Cop: " + file.getName());
			ccView.messageLabel.setText(String.valueOf(ccModel.caseList.size())+" cases");
			ccView.caseTableView.getSelectionModel().select(0);
			currentCase = ccView.caseTableView.getSelectionModel().getSelectedItem();
			ccView.titleTextField.setText(currentCase.getCaseTitle());
			ccView.caseTypeTextField.setText(currentCase.getCaseType());
			ccView.yearComboBox.setItems(ccModel.yearList);
			ccView.yearComboBox.getSelectionModel().select(currentCase.getCaseDate().split("-")[0].trim());
			ccView.caseNumberTextField.setText(currentCase.getCaseNumber());
			ccView.caseNotesTextArea.setText(currentCase.getCaseNotes());
			ccView.isFileOpen.set(true);
			ccView.closeFileMenuItem.setDisable(false);
			ccView.openFileMenuItem.setDisable(true);
			ccView.addCaseMenuItem.setDisable(false);
			ccView.modifyCaseMenuItem.setDisable(false);
			ccView.deleteCaseMenuItem.setDisable(false);
		}
		
	}
	
	class CloseFileMenuItemHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			ccView.isFileOpen.set(false);
			ccView.titleTextField.clear();
			ccView.caseTypeTextField.clear();
			ccView.yearComboBox.getSelectionModel().select("");
			ccView.yearComboBox.getItems().clear();
			ccView.caseNumberTextField.clear();
			ccView.caseNotesTextArea.clear();
			ccView.caseTableView.getItems().clear();
			ccView.openFileMenuItem.setDisable(false);
			ccView.closeFileMenuItem.setDisable(true);
			ccView.addCaseMenuItem.setDisable(true);
			ccView.modifyCaseMenuItem.setDisable(true);
			ccView.deleteCaseMenuItem.setDisable(true);
		}
		
	}
	
	class ExitMenuItemHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
		    stage.close();
		}
		
	}

	class SearchButtonHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			List<Case> search_result = new ArrayList<>();
			search_result = ccModel.searchCases(ccView.titleTextField.getText(), ccView.caseTypeTextField.getText(), ccView.yearComboBox.getValue(), ccView.caseNumberTextField.getText());
			ObservableList<Case> caseListresult = FXCollections.observableArrayList();
			for (Case c:search_result) {
				caseListresult.add(c);
			}
			ccView.caseTableView.setItems(caseListresult);
			ccView.messageLabel.setText(String.valueOf(caseListresult.size())+" cases");
			ccView.caseTableView.getSelectionModel().select(0);
			if (ccView.caseTableView.getSelectionModel().getSelectedItem() != null) {
				currentCase = ccView.caseTableView.getSelectionModel().getSelectedItem();
				ccView.titleTextField.setText(currentCase.getCaseTitle());
				ccView.caseTypeTextField.setText(currentCase.getCaseType());
				ccView.yearComboBox.getSelectionModel().select(currentCase.getCaseDate().split("-")[0].trim());
				ccView.caseNumberTextField.setText(currentCase.getCaseNumber());
				ccView.caseNotesTextArea.setText(currentCase.getCaseNotes());
			}
		}
	}
		
	class ClearButtonHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			ccView.titleTextField.clear();
			ccView.caseTypeTextField.clear();
			ccView.yearComboBox.getSelectionModel().select("");
			ccView.caseNumberTextField.clear();
		}
		
	}
	
	class CaseMenuItemHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			MenuItem selecteditem = (MenuItem) arg0.getTarget();
			currentCase = ccView.caseTableView.getSelectionModel().getSelectedItem();
			switch (selecteditem.getText()) {
			case "Add case":
				caseView = new AddCaseView(selecteditem.getText());
				caseView.buildView();
				caseView.stage.show();
				caseView.updateButton.setOnAction(new AddButtonHandler());
				break;
			case "Modify case":
				caseView = new ModifyCaseView(selecteditem.getText());
				caseView.titleTextField.setText(currentCase.getCaseTitle());
				caseView.caseTypeTextField.setText(currentCase.getCaseType());
				caseView.caseNumberTextField.setText(currentCase.getCaseNumber());
				caseView.categoryTextField.setText(currentCase.getCaseCategory());
				caseView.caseLinkTextField.setText(currentCase.getCaseLink());
				caseView.caseNotesTextArea.setText(currentCase.getCaseNotes());
				caseView.caseDatePicker.setValue(LocalDate.parse(currentCase.getCaseDate()));
				caseView.buildView();
				caseView.stage.show();
				caseView.updateButton.setOnAction(new ModifyButtonHandler());
				break;
			case "Delete case":
				caseView = new DeleteCaseView(selecteditem.getText());
				caseView.titleTextField.setText(currentCase.getCaseTitle());
				caseView.caseTypeTextField.setText(currentCase.getCaseType());
				caseView.caseNumberTextField.setText(currentCase.getCaseNumber());
				caseView.categoryTextField.setText(currentCase.getCaseCategory());
				caseView.caseLinkTextField.setText(currentCase.getCaseLink());
				caseView.caseNotesTextArea.setText(currentCase.getCaseNotes());
				caseView.caseDatePicker.setValue(LocalDate.parse(currentCase.getCaseDate()));
				caseView.buildView();
				caseView.stage.show();
				caseView.updateButton.setOnAction(new DeleteButtonHandler());
				break;
			}
			caseView.updateButton.setText(selecteditem.getText());
			caseView.clearButton.setOnAction(new CVclearButtonHandler());
			caseView.closeButton.setOnAction(new EventHandler<ActionEvent> (){
				@Override
				public void handle(ActionEvent event) {
					caseView.stage.close();
				}});
			}
	}

	class AddButtonHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			Case addedcase = new Case(
					caseView.caseDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyy-MM-dd")), 
					caseView.titleTextField.getText(), 
					caseView.caseTypeTextField.getText(), 
					caseView.caseNumberTextField.getText(), 
					caseView.caseLinkTextField.getText(), 
					caseView.categoryTextField.getText(), 
					caseView.caseNotesTextArea.getText());
			ccModel.caseList.add(addedcase);
			ccView.caseTableView.setItems(ccModel.caseList);
			ccView.messageLabel.setText(String.valueOf(ccModel.caseList.size())+" cases");
			ccView.caseTableView.getSelectionModel().select(addedcase);
			currentCase = ccView.caseTableView.getSelectionModel().getSelectedItem();
			ccView.titleTextField.setText(currentCase.getCaseTitle());
			ccView.caseTypeTextField.setText(currentCase.getCaseType());
			ccView.yearComboBox.setItems(ccModel.yearList);
			ccView.yearComboBox.getSelectionModel().select(currentCase.getCaseDate().split("-")[0].trim());
			ccView.caseNumberTextField.setText(currentCase.getCaseNumber());
			ccView.caseNotesTextArea.setText(currentCase.getCaseNotes());
		}
	}
	
	class ModifyButtonHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			currentCase.setCaseTitle(caseView.titleTextField.getText());
			currentCase.setCaseType(caseView.caseTypeTextField.getText());
			currentCase.setCaseNumber(caseView.caseNumberTextField.getText());
			currentCase.setCaseCategory(caseView.categoryTextField.getText());
			currentCase.setCaseLink(caseView.caseLinkTextField.getText());
			currentCase.setCaseNotes(caseView.caseNotesTextArea.getText());
			currentCase.setCaseDate(caseView.caseDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyy-MM-dd")));
			ccView.titleTextField.setText(currentCase.getCaseTitle());
			ccView.caseTypeTextField.setText(currentCase.getCaseType());
			ccView.yearComboBox.setItems(ccModel.yearList);
			ccView.yearComboBox.getSelectionModel().select(currentCase.getCaseDate().split("-")[0].trim());
			ccView.caseNumberTextField.setText(currentCase.getCaseNumber());
			ccView.caseNotesTextArea.setText(currentCase.getCaseNotes());
		}
		
	}
	
	class DeleteButtonHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			ccModel.caseList.remove(currentCase);
			ccModel.caseMap.remove(currentCase.getCaseNumber());
			ccView.caseTableView.setItems(ccModel.caseList);
			ccView.messageLabel.setText(String.valueOf(ccModel.caseList.size())+" cases");
			ccView.caseTableView.getSelectionModel().select(ccView.caseTableView.getSelectionModel().getSelectedIndex());
			currentCase = ccView.caseTableView.getSelectionModel().getSelectedItem();
			ccView.titleTextField.setText(currentCase.getCaseTitle());
			ccView.caseTypeTextField.setText(currentCase.getCaseType());
			ccView.yearComboBox.setItems(ccModel.yearList);
			ccView.yearComboBox.getSelectionModel().select(currentCase.getCaseDate().split("-")[0].trim());
			ccView.caseNumberTextField.setText(currentCase.getCaseNumber());
			ccView.caseNotesTextArea.setText(currentCase.getCaseNotes());
		}
		
	}
	
	class CVclearButtonHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			caseView.titleTextField.clear();
			caseView.caseTypeTextField.clear();
			caseView.caseNumberTextField.clear();
			caseView.categoryTextField.clear();
			caseView.caseLinkTextField.clear();
			caseView.caseNotesTextArea.clear();
			caseView.caseDatePicker.setValue(LocalDate.now());
		}
		
	}
	
	class CurrentCaseHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent arg0) {
			currentCase = ccView.caseTableView.getSelectionModel().getSelectedItem();
			ccView.titleTextField.setText(currentCase.getCaseTitle());
			ccView.caseTypeTextField.setText(currentCase.getCaseType());
			ccView.yearComboBox.setItems(ccModel.yearList);
			ccView.yearComboBox.getSelectionModel().select(currentCase.getCaseDate().split("-")[0].trim());
			ccView.caseNumberTextField.setText(currentCase.getCaseNumber());
			ccView.caseNotesTextArea.setText(currentCase.getCaseNotes());
			if (currentCase.getCaseLink() == null || currentCase.getCaseLink().isBlank()) {  //if no link in data
				URL url = getClass().getClassLoader().getResource(DEFAULT_HTML);  //default html
				if (url != null) ccView.webEngine.load(url.toExternalForm());
			} else if (currentCase.getCaseLink().toLowerCase().trim().startsWith("http")){  //if external link
				ccView.webEngine.load(currentCase.getCaseLink());
			} else {
				URL url = getClass().getClassLoader().getResource(currentCase.getCaseLink().trim());  //local link
				if (url != null) ccView.webEngine.load(url.toExternalForm());
			}
		}
		
	}
}

