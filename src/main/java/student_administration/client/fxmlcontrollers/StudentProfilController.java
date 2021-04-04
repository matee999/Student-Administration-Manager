package student_administration.client.fxmlcontrollers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import student_administration.client.MainViewManager;
import student_administration.models.Department;
import student_administration.models.PassedSubject;
import student_administration.models.Student;
import student_administration.models.StudentIndex;
import student_administration.services.DepartmentService;
import student_administration.services.StudentProfilService;

@Controller
public class StudentProfilController {

	@Autowired
	MainViewManager mainViewManager;
	
	@Autowired
	StudentProfilService studentProfilService;
	
	@Autowired
	DepartmentService departmentService;
	
	private Student student;
	private Student studentI;
	
	@FXML private TextField imeTf;
	
	@FXML private TextField prezimeTf;
	@FXML private TextField srednjeImeTf;
	@FXML private TextField jmbgTf;
	@FXML private DatePicker datumRodjenjaDp;
	@FXML ComboBox<String> mestoRodjenjaCb;
	@FXML ComboBox<String> drzavaRodjenjaCb;
	@FXML private ToggleGroup polGroup;
	@FXML ComboBox<String> drzavljanstvoCb;
	@FXML TextField brojLicneKarteTf;
	@FXML TextField licnuKartuIzdaoTf;

	@FXML private TextField emailTf;
	@FXML private TextField brojTelefonaTf;
	@FXML private TextField adresaPrebivalistaTf;
	@FXML private TextField adresaStanovanjaTf;
	
	@FXML ComboBox<String> mestoStanovanjaCb;
	@FXML ComboBox<String> mestoPrebivalistaCb;
	
	@FXML private Text imePolozeniTf;
	@FXML private TableView<PassedSubject> passedSubjectTable;
	private ObservableList<PassedSubject> allPassedSubjects;
	private int indexNumber;
	
	@FXML private TextField brojIndeksaTf;
	@FXML ComboBox<Department> departmentCbIndex;
	@FXML private Text actionTarget;
	
	@FXML
	public void initialize() {
		student = (Student)mainViewManager.getData().get("student");
		//mainViewManager.getData().clear();
		//System.out.println(student.getName());
		imeTf.setText(student.getName());
		imeTf.setEditable(false);
		prezimeTf.setText(student.getSurname());
		prezimeTf.setEditable(false);
		srednjeImeTf.setText(student.getMiddlename());
		srednjeImeTf.setEditable(false);
		jmbgTf.setText(student.getJmbg());
		jmbgTf.setEditable(false);
		datumRodjenjaDp.setValue(student.getBirthday());
		datumRodjenjaDp.setEditable(false);
		mestoRodjenjaCb.setValue(student.getPlaceOfBirth());
		mestoRodjenjaCb.setEditable(false);
		drzavaRodjenjaCb.setValue(student.getCountryOfBirth());
		drzavaRodjenjaCb.setEditable(false);
		drzavljanstvoCb.setValue(student.getNationality());
		drzavljanstvoCb.setEditable(false);
		brojLicneKarteTf.setText(student.getIdCardNumber());
		brojLicneKarteTf.setEditable(false);
		licnuKartuIzdaoTf.setText(student.getIdCardIssuer());
		licnuKartuIzdaoTf.setEditable(false);
		
		emailTf.setText(student.getEmail());
		emailTf.setEditable(false);
		brojTelefonaTf.setText(student.getMobileNumber());
		brojTelefonaTf.setEditable(false);
		adresaPrebivalistaTf.setText(student.getAddressOfResidence());
		adresaPrebivalistaTf.setEditable(false);
		adresaStanovanjaTf.setText(student.getResidentalAddress());;
		adresaStanovanjaTf.setEditable(false);
		mestoStanovanjaCb.setValue(student.getResidentalPlase());
		mestoStanovanjaCb.setEditable(false);
		mestoPrebivalistaCb.setValue(student.getPlaceOfResidence());
		mestoPrebivalistaCb.setEditable(false);
		
		List<Department> department = departmentService.loadAll();
		System.out.println("cb " + departmentCbIndex);
		departmentCbIndex.setItems(FXCollections.observableArrayList(department));
		
		
	}
	
	public void changeIndex(ActionEvent event) {
		studentI = (Student)mainViewManager.getData().get("student");
		if(brojIndeksaTf.getText().length()!=0 && departmentCbIndex.getValue()!=null) {
			List<StudentIndex> indexs = new ArrayList<StudentIndex>();
			indexs= studentI.getStudentIndexes();
			for(StudentIndex i: indexs) {
				i.setActive(false);
				studentProfilService.saveIndex(i);
			}
			StudentIndex si = new StudentIndex();
			si.setActive(true);
			si.setDepartment(departmentCbIndex.getValue());
			si.setNumber(Integer.parseInt(brojIndeksaTf.getText()));
			si.setStudent(studentI);
			si.setEnrollmentDate(new Date());
			studentProfilService.saveIndex(si);
		}else {
			actionTarget.setText("Niste uneli podatke potrebne za promenu indeksa!");
			return;
		}
	}
}
