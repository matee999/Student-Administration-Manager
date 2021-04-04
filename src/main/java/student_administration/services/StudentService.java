package student_administration.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_administration.models.Department;
import student_administration.models.EnrolledYear;
import student_administration.models.Exam;
import student_administration.models.ExamRegistration;
import student_administration.models.ExamTaking;
import student_administration.models.ExaminationPeriod;
import student_administration.models.FirstEnroll;
import student_administration.models.HoldSubject;
import student_administration.models.ListenSubject;
import student_administration.models.PassedSubject;
import student_administration.models.PreExamObligations;
import student_administration.models.RenewedYear;
import student_administration.models.SchoolYear;
import student_administration.models.Student;
import student_administration.models.StudentIndex;
import student_administration.models.Subject;
import student_administration.models.WonPreExamObligations;
import student_administration.repositories.DepartmentRepository;
import student_administration.repositories.EnrolledYearRepository;
import student_administration.repositories.ExamRegistrationRepository;
import student_administration.repositories.ExamRepository;
import student_administration.repositories.ExamTakingRepository;
import student_administration.repositories.ExaminationPeriodRepository;
import student_administration.repositories.FirstEnrollRepository;
import student_administration.repositories.HoldSubjectRepository;
import student_administration.repositories.ListenSubjectRepository;
import student_administration.repositories.PassedSubjectRepository;
import student_administration.repositories.PreExamObligationsRepository;
import student_administration.repositories.RenewedYearRepository;
import student_administration.repositories.SchoolYearRepository;
import student_administration.repositories.StudentIndexRepository;
import student_administration.repositories.StudentRepository;
import student_administration.repositories.SubjectRepository;
import student_administration.repositories.WonPreExamObligationsRepository;


@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	StudentIndexRepository studentIndeksRepo;
	
	@Autowired
	FirstEnrollRepository firstEnrollRepo;
	
	@Autowired
	EnrolledYearRepository enrolledYearRepo;
	
	@Autowired
	RenewedYearRepository renewedYearRepo;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	SchoolYearRepository schoolYearRepository;
	
	@Autowired
	HoldSubjectRepository holdSubjectRepo;
	
	@Autowired
	PreExamObligationsRepository preExamObligationRepo;
	
	@Autowired
	ExaminationPeriodRepository examinationPeriodRepo;
	
	@Autowired
	ExamRepository examRepo;
	
	@Autowired
	DepartmentRepository departmentRepo;
	
	@Autowired
	ListenSubjectRepository listenSubjectRepo;
	
	@Autowired
	WonPreExamObligationsRepository wonPreExamObligationRepo;
	
	@Autowired
	ExamRegistrationRepository examRegistrationRepo;
	
	@Autowired
	ExamTakingRepository examTakingRepo;
	
	@Autowired
	StudentIndexRepository studentIndexRepo;

	@Autowired
	PassedSubjectRepository passedSubjectRepo;
	
	public Student saveStudent(Student s) {
		return studentRepo.save(s);
	}
	
	public StudentIndex saveStudentIndeks(StudentIndex si) {
		return studentIndeksRepo.save(si);
	}
	
	public FirstEnroll saveFirstEnroll(FirstEnroll fe) {
		return firstEnrollRepo.save(fe);
	}

	public RenewedYear addRenewedYear(SchoolYear value,Student s) {
		RenewedYear ry = new RenewedYear();
		StudentIndex is = null;
		ry.setSchoolYear(value);
		ry.setNote("Obnova godine");
		ry.setDate(new Date());
		for(StudentIndex si : s.getStudentIndexes()) {
			if(si.isActive())
				is = si; 
		}
		ry.setStudentIndex(is);
		return renewedYearRepo.save(ry);
		
	}

	public EnrolledYear addEnrolledYear(SchoolYear value, Student s) {
		EnrolledYear ry = new EnrolledYear();
		StudentIndex is = null;
		ry.setSchoolYear(value);
		ry.setNote("Upis godine");
		ry.setDate(new Date());
		for(StudentIndex si : s.getStudentIndexes()) {
			if(si.isActive())
				is = si; 
		}
		ry.setStudentIndex(is);
		return enrolledYearRepo.save(ry);
	}
	
	public List<EnrolledYear> loadAllEnrolledYear(){
		Iterable<EnrolledYear> iter = enrolledYearRepo.findAll();
		List<EnrolledYear> rez = new ArrayList<EnrolledYear>();
		iter.forEach(rez :: add);
		return rez;
	}
	
	public List<RenewedYear> loadAllRenewedYear(){
		Iterable<RenewedYear> iter = renewedYearRepo.findAll();
		List<RenewedYear> rez = new ArrayList<RenewedYear>();
		iter.forEach(rez :: add);
		return rez;
	}

	public HoldSubject findHoldSubjectByName(String subjectName, int first, int second) {
		List<HoldSubject> list = holdSubjectRepo.findByName(subjectName,first, second);
		if(list!=null && list.size()>0)
			return list.get(0);
		return null;
	}
	
	public Subject saveSubject(String subjectName) {
		Subject subject = new Subject();
		subject.setName(subjectName);
		return subjectRepository.save(subject);
	}

	public SchoolYear saveSchoolYear(int first, int second) {
		SchoolYear schoolYearOld = new SchoolYear(first, second, true);
		return schoolYearRepository.save(schoolYearOld);
		
	}

	public HoldSubject saveHoldSubject(Subject subject, SchoolYear schoolYear) {
		HoldSubject holdSubject = new HoldSubject(null, subject, schoolYear);
		return holdSubjectRepo.save(holdSubject);
	}

	public PreExamObligations savePreExamObligation(HoldSubject holdSubject, String predispitnaObeveza) {
		PreExamObligations preExamObligation = new PreExamObligations(holdSubject , predispitnaObeveza);
		return preExamObligationRepo.save(preExamObligation);
	}

	public ExaminationPeriod saveExaminationPeriod(String examinationPeriodName, SchoolYear sy) {
		ExaminationPeriod examinationPeriod = new ExaminationPeriod(examinationPeriodName, sy);
		return examinationPeriodRepo.save(examinationPeriod);
	}

	public Exam saveExam(HoldSubject holdSubject, ExaminationPeriod examinationPeriod) {
		Exam exam = new Exam(holdSubject, examinationPeriod);
		return examRepo.save(exam);
	}
	
	public StudentIndex saveStudentAndIndex(String ime, String prezime, String studProgram, int x, int godinaUpisa) {
		
		Student s = new Student(ime,prezime);
		s = studentRepo.save(s);
		Department sp = departmentRepo.getStudProgramBySkraceniNaziv(studProgram);
		StudentIndex si = new StudentIndex(x, new GregorianCalendar(godinaUpisa, 0, 0).getTime(),sp);
		si.setActive(true);
		si.setStudent(s);
		return studentIndeksRepo.save(si);

	}

	public ListenSubject saveListenSubject(StudentIndex si, HoldSubject holdSubject) {
		ListenSubject listenSubject = new ListenSubject(si, holdSubject);
		return listenSubjectRepo.save(listenSubject);
	}

	public WonPreExamObligations saveWonPreExamObligation(ListenSubject listenSubject, String points,
			PreExamObligations preExamObligations) {
		WonPreExamObligations wonPreExamObligations = new WonPreExamObligations(listenSubject, points.isEmpty() ? 0: Float.parseFloat(points), preExamObligations);
		return wonPreExamObligationRepo.save(wonPreExamObligations);
	}

	public ExamTaking registrationAndTakingAnExam(StudentIndex si, Exam exam, ListenSubject listenSubject, String poeni) {
		ExamRegistration examRegistration = new ExamRegistration("Prijava ispita", si, exam, listenSubject);
		examRegistrationRepo.save(examRegistration);
		
		ExamTaking examTaking = new ExamTaking("Izlazak na ispit", si, examRegistration, poeni.isEmpty() ? 0 : Float.parseFloat(poeni), false);
		return examTakingRepo.save(examTaking);
		
	}

	public StudentIndex findStudentByIndex(int index, String studProgram) {
		return studentIndexRepo.getStudentIndexByIndexNumber(index, studProgram);		
	}

	public ListenSubject findListenSubjectByIndexAndHoldSubject(StudentIndex si, HoldSubject hs) {
		return listenSubjectRepo.getListenSubjectByStudentAndHoldSubject(si.getStudentIndexId(), hs.getHoldSubjectId());
	}

	public void savePassedSubject(ListenSubject listenSubject, Exam exam, int grade) {
		PassedSubject ps = new PassedSubject(listenSubject, exam, false ,grade);
		passedSubjectRepo.save(ps);
		
	}

	public Subject findSubjectByName(String subjectName) {
		return subjectRepository.findSubjectByName(subjectName);
	}

	public SchoolYear findSchoolYear(int first, int second) {
		return schoolYearRepository.findSchoolYear(first, second);
	}
	
}
