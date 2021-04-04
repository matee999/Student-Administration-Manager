package student_administration.client.fxmlcontrollers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import student_administration.client.MainViewManager;
import student_administration.client.importer.CSVPoeniImporter;

@Component
public class ImportController {
	
	@FXML Label nazivFajlaLabel;
	
	@FXML Label izvestajImportLabel;
	
	@Autowired
	MainViewManager mainViewManager;
	
	@Autowired
	CSVPoeniImporter csvPoeniImporter;
	
	private File fajl;
	
	public void izaberiFajl(ActionEvent ecent) {	
		fajl = mainViewManager.openFileChooser();
		if(fajl == null)
			return;
		nazivFajlaLabel.setText(fajl.getName());
	}
	
	public void zapocniImport(ActionEvent ecent) {		
		String poruka = csvPoeniImporter.importCSV(fajl);
		izvestajImportLabel.setText(poruka);
	}
	

}
