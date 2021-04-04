package student_administration.client.fxmlcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import student_administration.models.ExaminationPeriod;
import student_administration.services.ExaminationPeriodService;

@Component
public class ResultOnExamController {

	@Autowired
	ExaminationPeriodService examinationPeriodService;
	
	@FXML ComboBox<ExaminationPeriod> examinationPeriodCb;
	
	public void initialize() {
		examinationPeriodCb.setItems(FXCollections.observableArrayList(examinationPeriodService.loadAll()));
	}
	
	public void handleGenerisiRezultatePoRokovima(ActionEvent event) {
		
	}
}
