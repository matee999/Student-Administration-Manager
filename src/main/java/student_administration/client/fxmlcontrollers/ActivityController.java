package student_administration.client.fxmlcontrollers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import student_administration.client.MainViewManager;
import student_administration.models.Activity;
import student_administration.models.ListenSubject;
import student_administration.models.Student;
import student_administration.models.StudentIndex;
import student_administration.services.StudentProfilService;

@Component
public class ActivityController {
	@Autowired
	MainViewManager mainViewManager;
	
	@Autowired
	StudentProfilService studentProfilService;

	private Student student;

	//@FXML private Text imeActivityTf;
	@FXML private TableView<Activity> activityTable;
	private ObservableList<Activity> allActivities;
	private StudentIndex indexNumber;
	
	
	
	@FXML
	public void initialize() {
		student = (Student)mainViewManager.getData().get("student");
	
		
		//imeActivityTf.setText(student.getName()+ " " + student.getSurname());
		List<StudentIndex> indexs = new ArrayList<StudentIndex>();
		indexs= student.getStudentIndexes();		
		for(StudentIndex i : indexs) {
			if(i.isActive()) {
				indexNumber = i;
			}		
		}
		allActivities = FXCollections.observableList(studentProfilService.loadAllActivities(indexNumber));
		activityTable.setItems(allActivities);
	}
}
