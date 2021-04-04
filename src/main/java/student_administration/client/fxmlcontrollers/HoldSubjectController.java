package student_administration.client.fxmlcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import student_administration.client.MainViewManager;
import student_administration.models.HoldSubject;
import student_administration.models.ListenSubject;
import student_administration.models.Professor;
import student_administration.models.SchoolYear;
import student_administration.models.Subject;
import student_administration.services.HoldSubjectService;
import student_administration.services.ProfessorService;
import student_administration.services.SchoolYearService;
import student_administration.services.SubjectService;

@Component
public class HoldSubjectController {

	@Autowired
	MainViewManager mainViewManager;
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	HoldSubjectService holdSubjectService;
	
	@Autowired
	SchoolYearService schoolYearService;
	
	@FXML ComboBox<Professor> professorCb;
	@FXML ComboBox<Subject> predmetHoldCb;
	@FXML ComboBox<SchoolYear> schoolYearCb;
	
	@FXML private TableView<HoldSubject> holdSubjectTable;
	private ObservableList<HoldSubject> allHoldSubjects;
	
	@FXML
	public void initialize() {
		List<Professor> professors = professorService.loadAll();
		professorCb.setItems(FXCollections.observableArrayList(professors));
		
		List<Subject> subjects = subjectService.loadAll();
		predmetHoldCb.setItems(FXCollections.observableArrayList(subjects));
		
		List<SchoolYear> schoolYears = schoolYearService.loadAll();
		schoolYearCb.setItems(FXCollections.observableArrayList(schoolYears));
		
		holdSubjectTable.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	mainViewManager.getData().put("holdsubject", holdSubjectTable.getSelectionModel().getSelectedItem());
		            mainViewManager.changeRoot("HoldSubjectDetail");                  
		        }
		    }
		});
		
		allHoldSubjects = FXCollections.observableList(holdSubjectService.loadAll());
		holdSubjectTable.setItems(allHoldSubjects);
	}
	
	public void handleSaveHoldSubject(ActionEvent event) {	
		HoldSubject hs = holdSubjectService.saveHoldSubject(professorCb.getValue(), predmetHoldCb.getValue(), schoolYearCb.getValue());
		allHoldSubjects.add(hs);
	}
	

}
