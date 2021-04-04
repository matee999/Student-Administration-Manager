package student_administration.client.fxmlcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import student_administration.client.MainViewManager;
import student_administration.models.HighSchool;
import student_administration.models.HoldSubject;
import student_administration.models.Professor;
import student_administration.models.SchoolYear;
import student_administration.models.Subject;
import student_administration.services.HoldSubjectService;
import student_administration.services.SifarniciService;

@Component
public class SchoolYearProfilController {

	@Autowired
	MainViewManager mainViewManager;
	
	private SchoolYear schoolYear;
	
	private HoldSubject holdSubject;
	
	@Autowired
	HoldSubjectService holdSubjectService;
	
	@Autowired
	SifarniciService sifarniciService;
	
	@FXML ComboBox<Professor> profesoriCb;
	
	@FXML ComboBox<Subject> predmetiCb;
	@FXML private Text actionTarget;
	
	@FXML
	public void initialize() {
		schoolYear = (SchoolYear) mainViewManager.getData().get("godina");
		List<Professor> professors = sifarniciService.getProfessors();
		profesoriCb.setItems(FXCollections.observableArrayList(professors));
		List<Subject> subjects = sifarniciService.getSubjects();
		predmetiCb.setItems(FXCollections.observableArrayList(subjects));
	}
	
	public void handleSaveHoldSubject(ActionEvent event) {
		if(profesoriCb.getValue()==null || predmetiCb.getValue()==null) {
			actionTarget.setText("Nisu uneti svi podaci!");
			return;
		}else
			holdSubjectService.saveHoldSubject(profesoriCb.getValue(), predmetiCb.getValue(),schoolYear);
	}
}
