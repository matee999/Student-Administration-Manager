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
import student_administration.models.EnrolledYear;
import student_administration.models.HoldSubject;
import student_administration.models.ListenSubject;
import student_administration.models.Professor;
import student_administration.models.RenewedYear;
import student_administration.models.SchoolYear;
import student_administration.models.Student;
import student_administration.models.StudentIndex;
import student_administration.models.Subject;
import student_administration.services.HoldSubjectService;
import student_administration.services.ProfessorService;
import student_administration.services.SchoolYearService;
import student_administration.services.SubjectService;

@Component
public class ObnovaGodineIzborPredmetaController {
	
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
	
	
	
	@FXML private TableView<HoldSubject> holdSubjectChoiceTable;
	private ObservableList<HoldSubject> allHoldSubjects;
	
	private StudentIndex is;
	private Student s;
	private RenewedYear renewedYear;
	
	
	@FXML private TableView<ListenSubject>listenSubjectListTable;
	private ObservableList<ListenSubject> allListenSubjects;

	
	@FXML
	public void initialize() {		
		
		s = (Student) mainViewManager.getData().get("student");
		for(StudentIndex si : s.getStudentIndexes()) {
			if(si.isActive())
				is = si; 
		}
		renewedYear = (RenewedYear) mainViewManager.getData().get("godinaObnove");

		
		holdSubjectChoiceTable.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	ListenSubject ls = holdSubjectService.saveListenSubject(holdSubjectChoiceTable.getSelectionModel().getSelectedItem(), is, renewedYear);
		        	allListenSubjects.add(ls);
		        	allHoldSubjects.remove(holdSubjectChoiceTable.getSelectionModel().getSelectedItem());
		        }
		    }
		});
		
		
		allHoldSubjects = FXCollections.observableList(holdSubjectService.loadAll());
		holdSubjectChoiceTable.setItems(allHoldSubjects);
		
		allListenSubjects = FXCollections.observableList(renewedYear.getRenewedSubjects());
		listenSubjectListTable.setItems(allListenSubjects);
	}
	
	
}
