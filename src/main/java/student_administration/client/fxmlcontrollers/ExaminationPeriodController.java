package student_administration.client.fxmlcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import student_administration.client.MainViewManager;
import student_administration.models.Department;
import student_administration.models.ExaminationPeriod;
import student_administration.services.DepartmentService;
import student_administration.services.ExaminationPeriodService;

@Component
public class ExaminationPeriodController {

	@Autowired
	MainViewManager mainViewManager;
	
	@Autowired
	ExaminationPeriodService examinationPeriodService;
	
	@FXML private TextField nazivTf;
	@FXML private DatePicker datumPocetkaDp;
	@FXML private DatePicker datumZavrsetkaDp;

	
	private ObservableList<ExaminationPeriod> allExaminationPeriod;
	
	@FXML private TableView<ExaminationPeriod> examinationPeriodTable;
	
	@FXML
	protected void initialize() {
		allExaminationPeriod = FXCollections.observableList(examinationPeriodService.loadAll());
		examinationPeriodTable.setItems(allExaminationPeriod);
		
		examinationPeriodTable.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	mainViewManager.getData().put("examination", examinationPeriodTable.getSelectionModel().getSelectedItem());
		            mainViewManager.changeRoot("NewExam");;                   
		        }
		    }
		});
	}
	
	public void handleSaveExaminationPeriod(ActionEvent event) {
		ExaminationPeriod ep = examinationPeriodService.saveExaminationPeriod(nazivTf.getText(), datumPocetkaDp.getValue(), datumZavrsetkaDp.getValue());
		allExaminationPeriod.add(ep);		
	}
}
