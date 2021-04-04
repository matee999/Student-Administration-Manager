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
import student_administration.models.ListenSubject;
import student_administration.models.PassedSubject;
import student_administration.models.Student;
import student_administration.models.StudentIndex;
import student_administration.services.StudentProfilService;

@Component
public class ListenSubjectController {

	@Autowired
	MainViewManager mainViewManager;
	
	@Autowired
	StudentProfilService studentProfilService;

	private Student student;

	@FXML private Text imeListenSubjectTf;
	@FXML private TableView<ListenSubject> listenSubjectTable;
	private ObservableList<ListenSubject> allListenSubjects;
	private StudentIndex indexNumber;
	
	
	
	@FXML
	public void initialize() {
		student = (Student)mainViewManager.getData().get("student");
	
		//Polozeni predmeti
		
		imeListenSubjectTf.setText(student.getName()+ " " + student.getSurname());
		List<StudentIndex> indexs = new ArrayList<StudentIndex>();
		indexs= student.getStudentIndexes();		
		for(StudentIndex i : indexs) {
			if(i.isActive()) {
				indexNumber = i;
			}		
		}
		allListenSubjects = FXCollections.observableList(studentProfilService.loadAllListenSubject(indexNumber));
		listenSubjectTable.setItems(allListenSubjects);
	}
}
