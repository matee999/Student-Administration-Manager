package student_administration.client.fxmlcontrollers;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import student_administration.client.MainViewManager;
import student_administration.models.Department;
import student_administration.models.Exam;
import student_administration.models.ExaminationPeriod;
import student_administration.models.HoldSubject;
import student_administration.models.Professor;
import student_administration.models.Student;
import student_administration.models.Subject;
import student_administration.services.ExamService;
import student_administration.services.ProfessorService;
import student_administration.services.SubjectService;

@Component
public class NewExamController {

	@Autowired
	ExamService examService;
	
	@Autowired
	MainViewManager mainViewManager;
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	SubjectService subjectsService;
	
	private ObservableList<Exam> allExams;
	
	@FXML private TableView<Exam> examTable;
	
	@FXML ComboBox<Professor> professorCb;
	@FXML ComboBox<HoldSubject> predmetCb;
	
	@FXML DatePicker datumDp;
	
	@FXML TextField vremeTf;
	
	private ExaminationPeriod examinationPeriod;
	
	@FXML
	protected void initialize() {
		examinationPeriod = (ExaminationPeriod)mainViewManager.getData().get("examination");
		List<Professor> professors = professorService.loadAll();
		professorCb.setItems(FXCollections.observableArrayList(professors));
		
		List<HoldSubject> subjects = subjectsService.loadAllHoldSubject();
		predmetCb.setItems(FXCollections.observableArrayList(subjects));
		
		examTable.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	mainViewManager.getData().put("exam", examTable.getSelectionModel().getSelectedItem());
		            mainViewManager.changeRoot("ExamRegister");;                   
		        }
		    }
		});
		allExams = FXCollections.observableList(examService.loadAllExamOnExaminationPeriod(examinationPeriod.getExaminationPeriodId()));
		examTable.setItems(allExams);
	}
	
	public void handleSaveExam(ActionEvent event) {
		Exam e = new Exam();
		e.setExaminationPeriod(examinationPeriod);
		e.setHoldSubject(predmetCb.getValue());
		e.setExamDate(Date.from(datumDp.getValue().atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant()));
		e.setProfessor(professorCb.getValue());
		e.setHourExam(Integer.parseInt(vremeTf.getText()));
		examService.saveExam(e);
		allExams.add(e);
		
	}
}
