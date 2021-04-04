package student_administration.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name="listensubject")
@NamedQuery(name="ListenSubject.findAll", query="SELECT ls FROM ListenSubject ls")
public class ListenSubject {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int listenSubjectId;

	@ManyToOne
	private StudentIndex studentIndex;
	
	@ManyToOne
	private HoldSubject holdSubject;
	
	@ManyToOne
	private EnrolledYear enrolledYear;
	
	@ManyToOne
	private RenewedYear renewedYear;
	
	@OneToMany(mappedBy = "listenSubject")
    private List<WonPreExamObligations> wonPreExamObligations;
	
	@OneToMany(mappedBy = "listenSubject")
	private List<ExamRegistration> examRegistrations;
	
	@OneToOne(mappedBy = "listenSubject")
	private PassedSubject passedSubject;
	
	@Transient
	private StringProperty subject = new SimpleStringProperty();
	@Transient
	private StringProperty professor = new SimpleStringProperty();

	
	public ListenSubject() {}

	public ListenSubject(StudentIndex studentIndex, HoldSubject holdSubject) {
		super();
		this.studentIndex = studentIndex;
		this.holdSubject = holdSubject;
	}
	
	public int getListenSubjectId() {
		return listenSubjectId;
	}

	public StudentIndex getStudentIndex() {
		return studentIndex;
	}

	public void setStudentIndex(StudentIndex studentIndex) {
		this.studentIndex = studentIndex;
	}

	public HoldSubject getHoldSubject() {
		return holdSubject;
	}

	public void setHoldSubject(HoldSubject holdSubject) {
		this.holdSubject = holdSubject;
	}

	public float getWonPreExamObligationsSum() {
		float sum=0;
		for(int i=0;i<wonPreExamObligations.size();i++) {
			sum=sum + wonPreExamObligations.get(i).getPoints();
		}
		return sum;
	}

	public void setWonPreExamObligations(List<WonPreExamObligations> wonPreExamObligations) {
		this.wonPreExamObligations = wonPreExamObligations;
	}
	
	public List<WonPreExamObligations> getWonPreExamObligations() {
		return wonPreExamObligations;
	}

	public List<ExamRegistration> getExamRegistrations() {
		return examRegistrations;
	}

	public void setExamRegistrations(List<ExamRegistration> examRegistrations) {
		this.examRegistrations = examRegistrations;
	}

	public void setListenSubjectId(int listenSubjectId) {
		this.listenSubjectId = listenSubjectId;
	}
	
	@Transient
	public String getSubject() {
		return holdSubject.getSubject().getName();
	}

	@Transient
	public String getProfessor() {
		return holdSubject.getProfessorOwner().getName() + " " + holdSubject.getProfessorOwner().getSurname();
	}
	
	
	
	public RenewedYear getRenewedYear() {
		return renewedYear;
	}

	public void setRenewedYear(RenewedYear renewedYear) {
		this.renewedYear = renewedYear;
	}

	public EnrolledYear getEnrolledYear() {
		return enrolledYear;
	}

	public void setEnrolledYear(EnrolledYear enrolledYear) {
		this.enrolledYear = enrolledYear;
	}

	@Override
	public String toString() {
		return "ListenSubject [holdSubject=" + holdSubject + "]";
	}
}
