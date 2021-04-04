package student_administration.client.fxmlcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import student_administration.client.MainViewManager;
import student_administration.models.Department;
import student_administration.models.Subject;
import student_administration.services.DepartmentService;

@Component
public class SubjectsOnDepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	MainViewManager mainViewManager;
	
	private Department d;
	
	private ObservableList<Subject> allSubjects;
	
	@FXML private TableView<Subject> subjectsOnDepartmentTable;
	
	
	
	@FXML
	public void initialize() {
		d = (Department) mainViewManager.getData().get("department");
		
		allSubjects = FXCollections.observableList(departmentService.loadSubjectOnDepartment(d.getDepartmentId()));
		subjectsOnDepartmentTable.setItems(allSubjects);
	}
}
