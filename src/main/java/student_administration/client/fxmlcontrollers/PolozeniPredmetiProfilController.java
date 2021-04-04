package student_administration.client.fxmlcontrollers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import student_administration.client.MainViewManager;
import student_administration.models.Department;
import student_administration.models.PassedSubject;
import student_administration.models.Student;
import student_administration.models.StudentIndex;
import student_administration.services.DepartmentService;
import student_administration.services.StudentProfilService;

@Component
public class PolozeniPredmetiProfilController {

	@Autowired
	MainViewManager mainViewManager;
	
	@Autowired
	StudentProfilService studentProfilService;

	private Student student;

	@FXML private Text imePolozeniTf;
	@FXML private TableView<PassedSubject> passedSubjectTable;
	private ObservableList<PassedSubject> allPassedSubjects;
	private int indexNumber;
	
	
	
	@FXML
	public void initialize() {
		student = (Student)mainViewManager.getData().get("student");
		
		
		
		
		//Polozeni predmeti
		
		imePolozeniTf.setText(student.getName()+ " " + student.getSurname());
		List<StudentIndex> indexs = new ArrayList<StudentIndex>();
		indexs= student.getStudentIndexes();		
		for(StudentIndex i : indexs) {
			if(i.isActive()) {
				indexNumber = i.getStudentIndexId();
			}		
		}
		allPassedSubjects = FXCollections.observableList(studentProfilService.loadAllPassedSubject(indexNumber));
		passedSubjectTable.setItems(allPassedSubjects);
	}
	
	
}
