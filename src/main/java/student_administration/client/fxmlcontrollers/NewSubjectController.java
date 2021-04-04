package student_administration.client.fxmlcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import student_administration.models.Department;
import student_administration.models.Professor;
import student_administration.models.Subject;
import student_administration.services.DepartmentService;
import student_administration.services.ProfessorService;
import student_administration.services.SubjectService;

@Component
public class NewSubjectController {

	@FXML private TextField imeTf;
	@FXML private TextField espbTf;
	@FXML private TextField codeTf;
	@FXML private TextField brojPredavanjaTf;
	@FXML private TextField brojVezbiTf;
	@FXML ComboBox<String> semesterCb;
	@FXML ComboBox<Department> departmentCb;
	@FXML TextArea descriptionTa;


	@Autowired
	SubjectService subjectService;
	
	@Autowired
	DepartmentService departmentService;
	
	private ObservableList<Subject> allSubjects;
	
	@FXML private TableView<Subject> subjectTable;
	
	@FXML
	protected void initialize() {
		List<String> semestarValue = List.of("1","2","3","4","5","6","7","8");
		semesterCb.setItems(FXCollections.observableArrayList(semestarValue));
		
		List<Department> department = departmentService.loadAll();
		departmentCb.setItems(FXCollections.observableArrayList(department));
		
		allSubjects = FXCollections.observableList(subjectService.loadAll());
		subjectTable.setItems(allSubjects);
	}
	
	
	public void handleSaveSubject(ActionEvent event) {
		Subject s = subjectService.saveSubject(imeTf.getText(), espbTf.getText(), codeTf.getText(), brojPredavanjaTf.getText(), brojVezbiTf.getText(), semesterCb.getValue(), departmentCb.getValue(), descriptionTa.getText());
		allSubjects.add(s);		
	}
}
