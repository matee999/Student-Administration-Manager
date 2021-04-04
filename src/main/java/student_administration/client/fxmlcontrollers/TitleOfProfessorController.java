package student_administration.client.fxmlcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import student_administration.client.MainViewManager;
import student_administration.models.AcademicTitle;
import student_administration.models.Department;
import student_administration.models.Professor;
import student_administration.models.SchoolYear;
import student_administration.models.StudentIndex;
import student_administration.models.TitleOfProfessor;
import student_administration.services.TitleOfProfessorService;

@Component
public class TitleOfProfessorController {

	@Autowired
	MainViewManager mainViewManager;
	
	private Professor professor;
	
	@Autowired
	TitleOfProfessorService titleOfProfessorService;
	
	@FXML private ComboBox<AcademicTitle> akademskoZvanjeCb;
	@FXML private TextField naucnaOblastTf;
	@FXML private DatePicker datumUpisaDp;

	private ObservableList<TitleOfProfessor> allTitlesOfProfessor;
	
	@FXML private TableView<TitleOfProfessor> titleOfProfessorTable;
	
	@FXML
	public void initialize() {
		List<AcademicTitle> titles = titleOfProfessorService.loadAllAcademicTitles();
		akademskoZvanjeCb.setItems(FXCollections.observableArrayList(titles)); 
		
		allTitlesOfProfessor = FXCollections.observableList(titleOfProfessorService.loadAll());
		titleOfProfessorTable.setItems(allTitlesOfProfessor);

		
		professor = (Professor) mainViewManager.getData().get("profesor");
		
	}
	
	public void handleSaveTitleOfProfessor(ActionEvent event) {
		TitleOfProfessor tp = titleOfProfessorService.saveTitleOfProfessor(akademskoZvanjeCb.getValue(), naucnaOblastTf.getText(), datumUpisaDp.getValue(), professor);
		allTitlesOfProfessor.add(tp);		
	}
	
}
