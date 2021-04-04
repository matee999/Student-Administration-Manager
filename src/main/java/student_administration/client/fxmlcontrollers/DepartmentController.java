package student_administration.client.fxmlcontrollers;

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
import student_administration.client.MainViewManager;
import student_administration.models.Department;
import student_administration.services.DepartmentService;

@Component
public class DepartmentController {

	@Autowired
	MainViewManager mainViewManager;
	
	@Autowired
	DepartmentService departmentService;
	
	@FXML private TextField nazivTf;
	@FXML private TextField skraceniNazivTf;
	
	private ObservableList<Department> allDepartments;
	
	@FXML private TableView<Department> departmentTable;
	
	@FXML
	protected void initialize() {
		allDepartments = FXCollections.observableList(departmentService.loadAll());
		departmentTable.setItems(allDepartments);
		
		departmentTable.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	mainViewManager.getData().put("department", departmentTable.getSelectionModel().getSelectedItem());
		            mainViewManager.openModal("SubjectsOnDepartment");;                   
		        }
		    }
		});
	}
	
	public void handleSaveDepartment(ActionEvent event) {
		Department dp = departmentService.saveDepartment(nazivTf.getText(), skraceniNazivTf.getText());
		allDepartments.add(dp);		
	}
}
