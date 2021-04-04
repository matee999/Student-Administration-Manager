package student_administration.client.fxmlcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import student_administration.client.MainViewManager;
import student_administration.models.Professor;
import student_administration.models.Student;

@Component
public class ProfessorProfilController {
	
	@Autowired
	MainViewManager mainViewManager;
	
	@FXML private TextField imeTf;
	
	@FXML private TextField prezimeTf;
	@FXML private TextField srednjeImeTf;
	
	@FXML private TextField emailTf;
	
	private Professor p;
	
	@FXML
	public void initialize() {
		p = (Professor)mainViewManager.getData().get("professorSearch");
		
		imeTf.setText(p.getName());
		imeTf.setEditable(false);
		prezimeTf.setText(p.getSurname());
		prezimeTf.setEditable(false);
		srednjeImeTf.setText(p.getMiddlename());
		srednjeImeTf.setEditable(false);
		emailTf.setText(p.getEmail());
		emailTf.setEditable(false);
	}
}
