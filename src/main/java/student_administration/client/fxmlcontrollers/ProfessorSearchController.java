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
import student_administration.models.Professor;
import student_administration.services.ProfessorService;

@Component
public class ProfessorSearchController {

	@Autowired
	MainViewManager mainViewManager;
	
	@FXML private TextField imeTf;
	@FXML private TextField prezimeTf;
	
	@FXML private Text actionTarget;
	
	@Autowired
	ProfessorService professorService;
	
	private ObservableList<Professor> allProfessor;	
	@FXML private TableView<Professor> professorTable;
	
	@FXML
	protected void initialize() {
		allProfessor = FXCollections.observableList(new ArrayList<Professor>());
		professorTable.setItems(allProfessor);
		
		professorTable.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	mainViewManager.getData().put("professorSearch", professorTable.getSelectionModel().getSelectedItem());
		            mainViewManager.changeRoot("ProfessorSearchData");                   
		        }
		    }
		});
	}
	
	public void handleFindProfessor(ActionEvent event) {
		if(imeTf.getText().isEmpty()&&prezimeTf.getText().isEmpty()) {
			actionTarget.setText("Niste uneli podatke za pretragu");
		}else
			allProfessor.addAll(professorService.findProfessorbyName(imeTf.getText(), prezimeTf.getText()));
	}
}
