//Frank Yue Ying (yying2)
package hw2;

public class CaseReaderFactory {
	
	CaseReader createReader(String filename){
		CaseReader Reader;
		if (filename.endsWith(".tsv")){
			Reader = new TSVCaseReader(filename);
		} else {
			Reader = new CSVCaseReader(filename);
		}
		return Reader;
		
	}
	
}
