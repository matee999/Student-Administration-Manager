package student_administration.client.fxmlcontrollers;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import student_administration.client.MainViewManager;
import student_administration.models.Department;
import student_administration.models.FirstEnroll;
import student_administration.models.HighSchool;
import student_administration.models.Student;
import student_administration.models.StudentIndex;
import student_administration.services.DepartmentService;
import student_administration.services.SifarniciService;
import student_administration.services.StudentService;

@Component
public class StudentController {
	
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	SifarniciService sifarniciService;
	
	@Autowired
	DepartmentService departmentService;
	
	
	@Autowired  
	MainViewManager mainViewManager;
	
	// forma ce se koristiti za unos novog i za azuriranje postojeceg studenta
	private Student s;
	private FirstEnroll fe;
	private StudentIndex si;

	@FXML private TextField imeTf;
	
	@FXML private TextField prezimeTf;
	@FXML private TextField srednjeImeTf;
	@FXML private TextField jmbgTf;
	@FXML private DatePicker datumRodjenjaDp;
	@FXML private Text actionTarget;

	@FXML private TextField emailTf;
	
	@FXML private ToggleGroup polGroup;
	
	@FXML TextField brojTelefonaTf;
	@FXML TextField adresaPrebivalistaTf;
	@FXML ComboBox<String> mestoPrebivalistaCb;
	@FXML TextField adresaStanovanjaTf;
	// TODO da li i mesta da idu preko sifarnika
	@FXML ComboBox<String> mestoStanovanjaCb;
	@FXML ComboBox<String> mestoRodjenjaCb;

	@FXML ComboBox<String> drzavaRodjenjaCb;

	@FXML ComboBox<String> drzavljanstvoCb;

	@FXML TextField nacionalnostTf;

	@FXML TextField brojLicneKarteTf;

	@FXML TextField licnuKartuIzdaoTf;
	
	// prvi upis

	@FXML ComboBox<HighSchool> srednjeSkolaCb;

	@FXML TextField strucnaSpremaTf;

	@FXML TextField uspehSrednjaSkolaTf;

	@FXML TextField uspehPrijemniTf;

	@FXML TextField godinaZavrsetkaSrednjeSkoleTf;

	@FXML TextField prelazSaVisokoskolskeUstanoveTf;

	@FXML TextField prethodnoZavrseneStudijeTf;

	@FXML TextField visokoskolskaUstanovaPrethodnihStudijaTf;

	@FXML TextField stecenoZvanjeTf;

	@FXML TextField prosecnaOcenaTf;

	@FXML DatePicker datumUpisaDp;

	@FXML TextArea napomenaTa;
	
	//indeks
	
	@FXML ComboBox<Department> departmentCb;
	
	@FXML TextField brojIndeksaTf;
	
	@FXML
    public void initialize() {		
		List<String> drzavaCodes = List.of("Republika Srbija","Crna Gora","Hrvatska");
		drzavaRodjenjaCb.setItems(FXCollections.observableArrayList(drzavaCodes));
		drzavaRodjenjaCb.setValue(new String("Republika Srbija"));
		drzavljanstvoCb.setItems(FXCollections.observableArrayList(drzavaCodes));
		drzavljanstvoCb.setValue(new String("Republika Srbija"));
		List<String> mestaCodes = List.of("Beograd","Leskovac","Vranje");
		ObservableList<String> mestaCodesObservableList = FXCollections.observableArrayList(mestaCodes);
		mestoPrebivalistaCb.setItems(mestaCodesObservableList);
		mestoRodjenjaCb.setItems(mestaCodesObservableList);
		mestoStanovanjaCb.setItems(mestaCodesObservableList);
		List<HighSchool> srednjeSkole = sifarniciService.getSrednjeSkole();
		srednjeSkolaCb.setItems(FXCollections.observableArrayList(srednjeSkole));
		
		List<Department> department = departmentService.loadAll();
		departmentCb.setItems(FXCollections.observableArrayList(department));
    }
	
	public void handleOpenModalSrednjeSkole(ActionEvent ae) {
		// TODO kreirati modal window za dodavanje nove srednje skole, mozda i brisanje i promena postojećih ?? strani ključ
		mainViewManager.openModal("addSrednjaSkola");		
	}
	
	public void updateSrednjeSkole() {
		List<HighSchool> srednjeSkole = sifarniciService.getSrednjeSkole();
		srednjeSkolaCb.setItems(FXCollections.observableArrayList(srednjeSkole));
	}
	
	public void addNewStudent(ActionEvent event) {
		if(imeTf.getText().equals("") || prezimeTf.getText().equals("")) {
			actionTarget.setText("Nisu uneti svi podaci, student nije sacuvan!");
			return;
		}
		if(s==null) {
			s= new Student();
		}
		if(si==null) {
			si= new StudentIndex();
		}
		if(fe==null) {
			fe= new FirstEnroll();
		}
		if(imeTf.getText().length()!=0) s.setName(imeTf.getText());
		if(prezimeTf.getText().length()!=0) s.setSurname(prezimeTf.getText());
		s.setMiddlename(srednjeImeTf.getText());
		s.setJmbg(jmbgTf.getText());
		s.setBirthday(datumRodjenjaDp.getValue());
		s.setEmail(emailTf.getText());
		s.setPlaceOfBirth(mestoRodjenjaCb.getValue());
		s.setCountryOfBirth(drzavaRodjenjaCb.getValue());
		RadioButton selectedRadioButton = (RadioButton) polGroup.getSelectedToggle();
		String pol = selectedRadioButton.getText();
		s.setGender(pol);
		
		s.setNationality(nacionalnostTf.getText());
		s.setiDNumber(brojLicneKarteTf.getText());
		s.setIssuedAnIDCard(licnuKartuIzdaoTf.getText());
		s.setCitizenship(drzavljanstvoCb.getValue());
		s.setMobileNumber(brojTelefonaTf.getText());
		s.setResidentalAddress(adresaStanovanjaTf.getText());
		s.setResidentalPlase(mestoStanovanjaCb.getValue());
		s.setAddressOfResidence(adresaPrebivalistaTf.getText());
		s.setPlaceOfResidence(mestoPrebivalistaCb.getValue());
		
		s=studentService.saveStudent(s);		//ovo gore su nam podaci potrebni da napravimo studenta
												//dalje ce se obavljati skupljanje podataka za prvi upis
		
		si.setNumber(Integer.parseInt(brojIndeksaTf.getText()));
		si.setDepartment(departmentCb.getValue());
		si.setActive(true);
		si.setStudent(s);
		si.setEnrollmentDate(Date.from(datumUpisaDp.getValue().atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant()));
		
		si = studentService.saveStudentIndeks(si);		//napravljen je i novi indeks za gornjeg studenta
		
		 fe.setHighSchool(srednjeSkolaCb.getValue());


		 fe.setHighSchoolPoints(Float.parseFloat(uspehSrednjaSkolaTf.getText()));

		 fe.setPointsFromEntranceExam(Float.parseFloat(uspehPrijemniTf.getText()));
		 
		 fe.setStudentIndex(si);
		 
		 fe.setNote(napomenaTa.getText());
		 
		 fe.setDate(Date.from(datumUpisaDp.getValue().atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant()));
		 fe = studentService.saveFirstEnroll(fe);
		 
//		 strucnaSpremaTf;
//
//		 godinaZavrsetkaSrednjeSkoleTf;
//
//		 prelazSaVisokoskolskeUstanoveTf;
//
//		 prethodnoZavrseneStudijeTf;
//
//		 visokoskolskaUstanovaPrethodnihStudijaTf;
//
//		 stecenoZvanjeTf;
//
//		 prosecnaOcenaTf;
//
//		 datumUpisaDp;
//
//		 napomenaTa;
		
	}
}
