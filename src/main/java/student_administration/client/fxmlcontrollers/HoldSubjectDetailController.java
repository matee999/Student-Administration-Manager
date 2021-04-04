package student_administration.client.fxmlcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import student_administration.client.MainViewManager;
import student_administration.models.HoldSubject;
import student_administration.models.ListenSubject;
import student_administration.models.Professor;
import student_administration.models.Student;
import student_administration.models.StudentIndex;
import student_administration.services.HoldSubjectService;
import student_administration.services.StudentProfilService;

@Component
public class HoldSubjectDetailController {
	
	@Autowired
	MainViewManager mainViewManager;
	
	@Autowired
	StudentProfilService studentProfilService;
	
	@FXML ComboBox<StudentIndex> indexCb;
	
	@FXML private TableView<ListenSubject> listenSubjectTable;
	private ObservableList<ListenSubject> allListenSubjects;
	
	private HoldSubject holdSubject;
	
	@Autowired
	HoldSubjectService holdSubjectService;
	
	@FXML
	public void initialize() {
		holdSubject = (HoldSubject)mainViewManager.getData().get("holdsubject");
		
		List<StudentIndex> indexs = studentProfilService.loadAll();
		indexCb.setItems(FXCollections.observableArrayList(indexs));
		
		allListenSubjects = FXCollections.observableList(holdSubject.getListenSubjectList());
		listenSubjectTable.setItems(allListenSubjects);
	}
	
	public void handleSaveListenSubject(ActionEvent event) {
		ListenSubject ls = holdSubjectService.saveListenSubject(holdSubject, indexCb.getValue());
		allListenSubjects.add(ls);
	}
}
