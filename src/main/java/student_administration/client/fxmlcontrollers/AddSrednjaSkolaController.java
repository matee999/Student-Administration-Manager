package student_administration.client.fxmlcontrollers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import student_administration.models.HighSchool;
import student_administration.services.SifarniciService;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

@Component
public class AddSrednjaSkolaController {
	
	@Autowired
	SifarniciService sifarniciService;
	
	@Autowired
	StudentController  studentController;

	@FXML TextField nazivNoveSrednjeSkoleTf;
	@FXML ComboBox<String> mestoNoveSrednjeSkoleCb;
	@FXML ComboBox<String> tipNoveSrednjeSkoleCb;
	
	@FXML public void addSrednjaSkola(ActionEvent event) {
		HighSchool ss = new HighSchool();
		if(mestoNoveSrednjeSkoleCb.getValue()!=null) ss.setPlace(mestoNoveSrednjeSkoleCb.getValue());
		ss.setName(nazivNoveSrednjeSkoleTf.getText());
		if(tipNoveSrednjeSkoleCb.getValue()!=null) ss.setType(tipNoveSrednjeSkoleCb.getValue());
		sifarniciService.saveHighSchool(ss);		
		studentController.updateSrednjeSkole();
		closeStage(event);	
	}
	
	@FXML
    	public void initialize() {		
		List<String> tipSrednjeSkoleCodes = List.of("Gimnazija","Srednje strucna skola");
		tipNoveSrednjeSkoleCb.setItems(FXCollections.observableArrayList(tipSrednjeSkoleCodes));
		List<String> mestaCodes = List.of("Beograd", "Kruševac", "Niš");
		mestoNoveSrednjeSkoleCb.setItems(FXCollections.observableArrayList(mestaCodes));
	}
	
	private void closeStage(ActionEvent event) {
        Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
