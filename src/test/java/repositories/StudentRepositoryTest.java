package repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import student_administration.models.Department;
import student_administration.models.EnrolledYear;
import student_administration.models.Exam;
import student_administration.models.ExamRegistration;
import student_administration.models.ExamTaking;
import student_administration.models.ExaminationPeriod;
import student_administration.models.FirstEnroll;
import student_administration.models.HighSchool;
import student_administration.models.HoldSubject;
import student_administration.models.ListenSubject;
import student_administration.models.Student;
import student_administration.models.StudentIndex;
import student_administration.models.Subject;
import student_administration.models.WonPreExamObligations;
import student_administration.models.PassedSubject;
import student_administration.models.RenewedYear;
import student_administration.models.SchoolYear;
import student_administration.repositories.DepartmentRepository;
import student_administration.repositories.EnrolledYearRepository;
import student_administration.repositories.ExamRegistrationRepository;
import student_administration.repositories.ExamRepository;
import student_administration.repositories.ExamTakingRepository;
import student_administration.repositories.ExaminationPeriodRepository;
import student_administration.repositories.FirstEnrollRepository;
import student_administration.repositories.HighSchoolRepository;
import student_administration.repositories.HoldSubjectRepository;
import student_administration.repositories.ListenSubjectRepository;
import student_administration.repositories.PassedSubjectRepository;
import student_administration.repositories.RenewedYearRepository;
import student_administration.repositories.SchoolYearRepository;
import student_administration.repositories.StudentIndexRepository;
import student_administration.repositories.StudentRepository;
import student_administration.repositories.SubjectRepository;
import student_administration.repositories.WonPreExamObligationsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {student_administration.StudentAdministrationApp.class})
public class StudentRepositoryTest {
	
	@Autowired
	HighSchoolRepository highSchoolRepository;

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	HoldSubjectRepository holdSubjectRepository;
	
	@Autowired
	ListenSubjectRepository listenSubjectRepository;
	
	@Autowired
	PassedSubjectRepository passedSubjectRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	StudentIndexRepository studentIndexRepository;
	
	@Autowired
	FirstEnrollRepository firstEnrollRepository;
	
	@Autowired
	SchoolYearRepository schoolYearRepository;
	
	@Autowired
	EnrolledYearRepository enrolledYearRepository;
	
	@Autowired
	RenewedYearRepository renewedYearRepository;
	
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	ExamRegistrationRepository examRegistrationRepository;
	
	@Autowired
	ExaminationPeriodRepository examinationPeriodRepository;
	
	@Autowired
	ExamTakingRepository examTakingRepository;
	
	@Autowired
	WonPreExamObligationsRepository wonPreExamObligationsRepository;
	
	private static boolean setupIsDone = false;
	
	@Before
	public void setupData() {
		if (setupIsDone)
			return;
		
		HighSchool highSchool = new HighSchool("Test Srednja Skola", "Beograd", "Gimnazija");
		highSchoolRepository.save(highSchool);
		
		Student student = new Student();
		student.setName("Student");
		student.setSurname("Studentic");
		student.setEnrollementYear(2020);
		studentRepository.save(student);
		
		Department department = new Department();
		department.setName("Nauke");
		department.setShortName("RN");
		departmentRepository.save(department);
		
		StudentIndex index = new StudentIndex(3318, true, new Date(), student, department);
		studentIndexRepository.save(index);
		
		FirstEnroll firstEnroll = new FirstEnroll("Prvi upis", index, 40, 60, highSchool);
		firstEnrollRepository.save(firstEnroll);
		
		Subject subject = new Subject();
		subject.setName("UPINF");
		subject.setDepartment(department);
		subjectRepository.save(subject);
		
		Subject subject2 = new Subject();
		subject2.setName("ViS");
		subject2.setDepartment(department);
		subjectRepository.save(subject2);
		
		SchoolYear schoolYearOld = new SchoolYear(2019, 2020, false);
		schoolYearRepository.save(schoolYearOld);
		
		SchoolYear schoolYear = new SchoolYear(2020, 2021, true);
		schoolYearRepository.save(schoolYear);
		
		HoldSubject holdSubject = new HoldSubject(null, subject, schoolYear);
		holdSubjectRepository.save(holdSubject);
		
		HoldSubject holdSubject2 = new HoldSubject(null, subject2, schoolYearOld);
		holdSubjectRepository.save(holdSubject2);
		
		HoldSubject holdSubject3 = new HoldSubject(null, subject2, schoolYear);
		holdSubjectRepository.save(holdSubject3);
		
		ListenSubject listenSubject = new ListenSubject(index, holdSubject);
		listenSubjectRepository.save(listenSubject);
		
		ListenSubject listenSubject2 = new ListenSubject(index, holdSubject2);
		listenSubjectRepository.save(listenSubject2);
		
		ListenSubject listenSubject3 = new ListenSubject(index, holdSubject3);
		listenSubjectRepository.save(listenSubject3);
		
		WonPreExamObligations wonPreExamObligations = new WonPreExamObligations(listenSubject, 20, null);
		wonPreExamObligationsRepository.save(wonPreExamObligations);
		
		WonPreExamObligations wonPreExamObligations2 = new WonPreExamObligations(listenSubject, 10, null);
		wonPreExamObligationsRepository.save(wonPreExamObligations2);
		
		ExaminationPeriod examinationPeriod = new ExaminationPeriod();
		examinationPeriod.setName("Januarski");
		examinationPeriodRepository.save(examinationPeriod);
		
		Exam exam = new Exam();
		exam.setHoldSubject(holdSubject);
		exam.setExaminationPeriod(examinationPeriod);
		examRepository.save(exam);
		
		ExamRegistration examRegistration = new ExamRegistration("", index, exam, listenSubject);
		examRegistrationRepository.save(examRegistration);
		
		ExamTaking examTaking = new ExamTaking("", index, examRegistration, 30, false);
		examTakingRepository.save(examTaking);
		
		PassedSubject passedSubject = new PassedSubject(listenSubject, exam, false, 10);
		passedSubjectRepository.save(passedSubject);
		
		PassedSubject passedSubject2 = new PassedSubject(listenSubject2, null, false, 7); // 19-20
		passedSubjectRepository.save(passedSubject2);
		
		PassedSubject passedSubject3 = new PassedSubject(listenSubject3, null, false, 6); // 20-21
		passedSubjectRepository.save(passedSubject3);
		
		EnrolledYear enrolledYearOld = new EnrolledYear("", index, schoolYearOld, new ArrayList<ListenSubject>());
		enrolledYearRepository.save(enrolledYearOld);
		
		EnrolledYear enrolledYear = new EnrolledYear("", index, schoolYear, new ArrayList<ListenSubject>());
		enrolledYearRepository.save(enrolledYear);
		
		RenewedYear renewedYear = new RenewedYear("", index, schoolYear, new ArrayList<ListenSubject>());
		renewedYearRepository.save(renewedYear);
		
		
		// To run the setup only once, else it'd be called on every test method call
		setupIsDone = true;
	}
	
	@Test
	public void getStudentByIndex() throws Exception {
		System.out.println("---getStudentByIndex---");
		Student student = studentRepository.getStudentByIndexNumber(3318, "RN");
		System.out.println(student);
	}
	
	@Test
	public void getPassedExamsByIndex() throws Exception {
		System.out.println("---getPassedExamsByIndex---");
		List<PassedSubject> passedSubjects = studentRepository.getPassedSubjectsByIndex(1);
		
		for (PassedSubject ps : passedSubjects)
			System.out.println(ps);
	}
	
	@Test
	public void getEnrolledYearsByIndex() throws Exception {
		System.out.println("---getEnrolledYearsByIndex---");
		List<EnrolledYear> years = studentRepository.getEnrolledYearsByIndex(1);
		
		for (EnrolledYear year : years)
			System.out.println(year);
	}
	
	@Test
	public void getRenewedYearsByIndex() throws Exception {
		System.out.println("---getRenewedYearsByIndex---");
		List<RenewedYear> years = studentRepository.getRenewedYearsByIndex(1);
		
		for (RenewedYear year : years)
			System.out.println(year);
	}
	
	@Test
	public void getStudentsByNameOrSurname() throws Exception {
		System.out.println("---getStudentsByNameOrSurname---");
		List<Student> students = studentRepository.getStudentsByNameOrSurname("student", "studentic");
		
		for (Student student : students)
			System.out.println(student);
	}
	
	@Test
	public void getStudentsByHighSchool() throws Exception {
		System.out.println("---getStudentsByHighSchool---");
		
		HighSchool highSchool = highSchoolRepository.findById(1).get();
		List<Student> students = studentRepository.getStudentsByHighSchool(highSchool.getHighSchoolId());
		
		for (Student student : students)
			System.out.println(student);
	}
}
