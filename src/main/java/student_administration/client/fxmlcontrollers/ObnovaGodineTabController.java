package student_administration.client.fxmlcontrollers;

import java.util.ArrayList;
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
import student_administration.models.Professor;
import student_administration.models.RenewedYear;
import student_administration.models.SchoolYear;
import student_administration.models.Student;
import student_administration.services.SchoolYearService;
import student_administration.services.StudentService;

@Component
public class ObnovaGodineTabController {

	@Autowired
	MainViewManager mainViewManager;
	
	@FXML private ComboBox<SchoolYear> godinaCb;
	
	@Autowired
	SchoolYearService schoolYearService;
	
	@Autowired
	StudentService studentService;
	
	private ObservableList<RenewedYear> allSchoolYears;
	@FXML private TableView<RenewedYear> godinaObnoveTable;
	
	private Student s;
	
	@FXML
	public void initialize() {
		List<SchoolYear> years = schoolYearService.loadAll();
		godinaCb.setItems(FXCollections.observableArrayList(years));		//da li da ovde prikazuje samo aktivnu godinu ili kao sada sve
	
		allSchoolYears = FXCollections.observableList(studentService.loadAllRenewedYear());
		godinaObnoveTable.setItems(allSchoolYears);

		s = (Student) mainViewManager.getData().get("student");
		
		godinaObnoveTable.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	mainViewManager.getData().put("godinaObnove", godinaObnoveTable.getSelectionModel().getSelectedItem());
		            mainViewManager.changeRoot("PredmetiObnova");;                   
		        }
		    }
		});
	}
	
	public void handleObnovaGodine(ActionEvent event) {
		if(godinaCb.getValue()!=null) {
			RenewedYear ry = studentService.addRenewedYear(godinaCb.getValue(), s);
			allSchoolYears.add(ry);
		}else 
			return;
//			mainViewManager.changeRoot("PredmetiObnova");
	}
}
