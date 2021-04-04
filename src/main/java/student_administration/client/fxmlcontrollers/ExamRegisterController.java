package student_administration.client.fxmlcontrollers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import student_administration.client.MainViewManager;
import student_administration.client.reports.ReportsManager;
import student_administration.models.Exam;
import student_administration.models.ExamRegistration;
import student_administration.models.ExamReport;
import student_administration.models.ExamResult;
import student_administration.services.DepartmentService;
import student_administration.services.ExamService;

@Component
public class ExamRegisterController {
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	MainViewManager mainViewManager;
	
	@Autowired
	ExamService examService;
	
	@Autowired
	ReportsManager reportsManager;
	
	private Exam e;
	
	private ObservableList<ExamRegistration> allRegistrations;
	@FXML private TableView<ExamRegistration> studentOnExamTable;
	
	@FXML private TableView<ExamResult> examResultTable;
	private ObservableList<ExamResult> allExamResults;
	
	@FXML
	public void initialize() {
		e = (Exam) mainViewManager.getData().get("exam");
		
		allRegistrations = FXCollections.observableList(e.getExamRegistrations());
		studentOnExamTable.setItems(allRegistrations);
		
		allExamResults = FXCollections.observableList(examService.getExamResult(e));
		examResultTable.setItems(allExamResults);
		
		
	}
	
	public void createReport(ActionEvent event) {
		List<ExamReport> retVal = examService.getReportData(e);
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("examinationPeriod", e.getExaminationPeriod().getName());
        params.put("subject", e.getSubject());
        params.put("schoolYear", e.getHoldSubject().getSchoolYear().toString());
        reportsManager.openReport(retVal, params, "rezultatiIspitaPoIspitnimRokovima");
	}
}
