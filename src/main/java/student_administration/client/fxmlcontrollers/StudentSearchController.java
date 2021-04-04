package student_administration.client.fxmlcontrollers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import student_administration.client.MainViewManager;
import student_administration.models.Department;
import student_administration.models.Student;
import student_administration.services.StudentSearchService;

@Component
public class StudentSearchController {

	@Autowired
	StudentSearchService studentSearchService;
	
	@Autowired
	MainViewManager mainViewManager;
	
	@FXML private TextField imeTf;
	@FXML private TextField prezimeTf;
	
	@FXML private TextField brojIndeksaTf;
	
	@FXML private TextField departmentTf;
	
	private ObservableList<Student> allStudents;
	
	@FXML private TableView<Student> studentTable;

	@FXML
	protected void initialize() {
		allStudents = FXCollections.observableList(new ArrayList<Student>());
		studentTable.setItems(allStudents);
		
		//System.out.println(studentTable.getSelectionModel().getSelectedItem());
		studentTable.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	mainViewManager.getData().put("student", studentTable.getSelectionModel().getSelectedItem());
		            mainViewManager.changeRoot("StudentSearchData");                   
		        }
		    }
		});
	}

	public void handleFindStudent(ActionEvent event) {
		allStudents.clear();
		if(brojIndeksaTf.getText().isEmpty()) {
			allStudents.addAll(studentSearchService.findStudents(imeTf.getText(), prezimeTf.getText()));
		}else
			allStudents.addAll(studentSearchService.findStudentbyIndex(brojIndeksaTf.getText(),departmentTf.getText()));		
	}
	
	
}
