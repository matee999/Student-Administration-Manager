package student_administration.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name="exam")
@NamedQuery(name="Exam.findAll", query="SELECT e FROM Exam e")
public class Exam {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int examId;
	
	private Date examDate;
	
	private int hourExam;
	
	private boolean locked;
	
	@ManyToOne
	private HoldSubject holdSubject;
	
	@ManyToOne
	private ExaminationPeriod examinationPeriod;

	@ManyToOne
	private Professor professor;
	
	@OneToMany(mappedBy="exam", fetch = FetchType.EAGER)
	List<ExamRegistration> examRegistrations;
	
	@OneToMany(mappedBy="exam", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	List<PassedSubject> passedSubjects;
	
	@Transient
	private StringProperty professorName = new SimpleStringProperty();
	
	@Transient
	private StringProperty subject = new SimpleStringProperty();
	
	public Exam() {}

	public Exam(Date examDate, int hourExam, boolean locked, HoldSubject holdSubject, ExaminationPeriod examinationPeriod,
			Professor professor) {
		super();
		this.examDate = examDate;
		this.hourExam = hourExam;
		this.locked = locked;
		this.holdSubject = holdSubject;
		this.examinationPeriod = examinationPeriod;
		this.professor = professor;
	}

	
	
	public Exam(HoldSubject holdSubject, ExaminationPeriod examinationPeriod) {
		super();
		this.holdSubject = holdSubject;
		this.examinationPeriod = examinationPeriod;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public int getHourExam() {
		return hourExam;
	}

	public void setHourExam(int hourExam) {
		this.hourExam = hourExam;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public HoldSubject getHoldSubject() {
		return holdSubject;
	}
	
	public void setHoldSubject(HoldSubject holdSubject) {
		this.holdSubject = holdSubject;
	}

	public ExaminationPeriod getExaminationPeriod() {
		return examinationPeriod;
	}

	public void setExaminationPeriod(ExaminationPeriod examinationPeriod) {
		this.examinationPeriod = examinationPeriod;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<ExamRegistration> getExamRegistrations() {
		if(examRegistrations == null)
			examRegistrations = new ArrayList<ExamRegistration>();
		return examRegistrations;
	}

	public void setExamRegistrations(List<ExamRegistration> examRegistrations) {
		this.examRegistrations = examRegistrations;
	}

	public List<PassedSubject> getPassedSubjects() {
		return passedSubjects;
	}

	public void setPassedSubjects(List<PassedSubject> passedSubjects) {
		this.passedSubjects = passedSubjects;
	}
	
	@Transient
	public String getProfessorName() {
		return professor.getName() + " " + professor.getSurname();
	}
	
	@Transient
	public String getSubject() {
		return holdSubject.getSubject().getName();
	}
	
}
