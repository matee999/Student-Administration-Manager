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
import student_administration.models.Professor;
import student_administration.services.ProfessorService;

@Component
public class NewProfessorController {

	@FXML private TextField imeTf;
	@FXML private TextField prezimeTf;
	@FXML private TextField srednjeImeTf;
	@FXML private TextField emailTf;

	@Autowired
	MainViewManager mainViewManager;
	
	@Autowired
	ProfessorService professorService;
	
	private ObservableList<Professor> allProfessors;
	
	@FXML private TableView<Professor> professorTable;
	
	@FXML
	protected void initialize() {
		allProfessors = FXCollections.observableList(professorService.loadAll());
		professorTable.setItems(allProfessors);
		
		professorTable.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	mainViewManager.getData().put("profesor", professorTable.getSelectionModel().getSelectedItem());
		            mainViewManager.changeRoot("TitleOfProfessor");                   
		        }
		    }
		});
	}
	
	
	public void handleSaveProfessor(ActionEvent event) {
		Professor p = professorService.saveProfessor(imeTf.getText(), prezimeTf.getText(), srednjeImeTf.getText(), emailTf.getText());
		allProfessors.add(p);		
	}
}
