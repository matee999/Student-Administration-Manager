package student_administration.client.fxmlcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import student_administration.client.MainViewManager;
import student_administration.models.Department;
import student_administration.models.SchoolYear;
import student_administration.services.SchoolYearService;

@Component
public class SchoolYearController {

	@Autowired
	SchoolYearService schoolYearService;
	
	@FXML private TextField pocetnaTf;
	@FXML private TextField zavrsnaTf;
	
	@Autowired
	MainViewManager mainViewManager;
	
	private ObservableList<SchoolYear> allSchoolYear;
	
	@FXML private TableView<SchoolYear> schoolYearTable;
	
	@FXML
	protected void initialize() {
		allSchoolYear = FXCollections.observableList(schoolYearService.loadAll());
		schoolYearTable.setItems(allSchoolYear);
		
		schoolYearTable.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	mainViewManager.getData().put("godina", schoolYearTable.getSelectionModel().getSelectedItem());
		            mainViewManager.changeRoot("SchoolYearProfil");                   
		        }
		    }
		});
	}
	
	public void handleSaveSchoolYear(ActionEvent event) {
		SchoolYear sy = schoolYearService.saveSchoolYear(pocetnaTf.getText(), zavrsnaTf.getText());
		allSchoolYear.add(sy);	
		schoolYearTable.refresh();
	}
}
