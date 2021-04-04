package student_administration.client.fxmlcontrollers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import student_administration.client.MainViewManager;

@Component
public class MenuBarController { 
	
	 
	@Autowired
	MainViewManager mainViewManager;
	
		
	@FXML
	private MenuBar menuBar;
	
	
	public void openDepartment(ActionEvent event) {
		mainViewManager.changeRoot("studProgrami");
	}
	
	public void openNewStudent(ActionEvent event) {
		mainViewManager.changeRoot("newStudent");
	}
	
	public void openStudentSearch(ActionEvent event) {
		mainViewManager.changeRoot("StudentSearch");
	}
	
	public void openSchoolYear(ActionEvent event) {
		mainViewManager.changeRoot("SchoolYear");
	}
	
	public void openNewProfessor(ActionEvent event) {
		mainViewManager.changeRoot("NewProfessor");
	}
	
	public void openNewSubject(ActionEvent event) {
		mainViewManager.changeRoot("NewSubject");
	}
	
	public void openExaminationPeriod(ActionEvent event) {
		mainViewManager.changeRoot("NewExamionationPeriod");
	}
	
	public void openExam(ActionEvent event) {
		mainViewManager.changeRoot("NewExam");
	}
	
	public void openSubject(ActionEvent event) {
		mainViewManager.changeRoot("HoldSubject");
	}
	
	public void openProfessorSearch(ActionEvent event) {
		mainViewManager.changeRoot("ProfessorSearch");
	}
	
	public void openImport(ActionEvent ecent) {		
		mainViewManager.changeRoot("importData");
	}
	
	public void openResultOnExam(ActionEvent ecent) {		
		mainViewManager.changeRoot("resultOnExam");
	}
}
