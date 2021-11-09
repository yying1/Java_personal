//Frank Yue Ying (yying2)
package hw2;

import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Case implements Comparable<Case>{
	private StringProperty caseDate = new SimpleStringProperty();
	private StringProperty caseTitle = new SimpleStringProperty();
	private StringProperty caseType = new SimpleStringProperty();
	private StringProperty caseNumber = new SimpleStringProperty();
	private StringProperty caseLink = new SimpleStringProperty();
	private StringProperty caseCategory = new SimpleStringProperty();
	private StringProperty caseNotes = new SimpleStringProperty();
	
	Case(String caseDate, String caseTitle, String caseType, String caseNumber, String caseLink,String caseCategory,String caseNotes){
		this.caseDate.set(caseDate);
		this.caseTitle.set(caseTitle);
		this.caseType.set(caseType);
		this.caseNumber.set(caseNumber);
		this.caseLink.set(caseLink);
		this.caseCategory.set(caseCategory);
		this.caseNotes.set(caseNotes);
	}
	
	public String getCaseDate() {return this.caseDate.get();}
	public void setCaseDate(String date) {this.caseDate.set(date);}
	public final StringProperty caseDateProperty() {return caseDate;}
	public String getCaseTitle() {return this.caseTitle.get();}
	public void setCaseTitle(String title) {this.caseTitle.set(title);}
	public final StringProperty caseTitleProperty() {return caseTitle;}
	public String getCaseType() {return this.caseType.get();}
	public void setCaseType(String type) {this.caseType.set(type);}
	public final StringProperty caseTypeProperty() {return caseType;}
	public String getCaseNumber() {return this.caseNumber.get();}
	public void setCaseNumber(String number) {this.caseNumber.set(number);}
	public final StringProperty caseNumberProperty() {return caseNumber;}
	public String getCaseLink() {return this.caseLink.get();}
	public void setCaseLink(String link) {this.caseLink.set(link);}
	public final StringProperty caseLinkProperty() {return caseLink;}
	public String getCaseCategory() {return this.caseCategory.get();}
	public void setCaseCategory(String category) {this.caseCategory.set(category);}
	public final StringProperty caseCategoryProperty() {return caseCategory;}
	public String getCaseNotes() {return this.caseNotes.get();}
	public void setCaseNotes(String notes) {this.caseNotes.set(notes);}
	public final StringProperty CaseNotesProperty() {return caseNotes;}
	
	@Override
	public String toString(){
		return this.caseNumber.get();
	}
	
	@Override
	public int compareTo(Case o) {
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-mm-dd");
		Date date1 = new Date();
		Date date2 = new Date();
		try {
			date1 = dformat.parse(this.caseDate.get());
			date2 =dformat.parse(o.caseDate.get());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date1.compareTo(date2);
	}

}
