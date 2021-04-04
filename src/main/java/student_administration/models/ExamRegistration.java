package student_administration.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name="examregistration")
@NamedQuery(name="ExamRegistration.findAll", query="SELECT er FROM ExamRegistration er")
public class ExamRegistration extends Activity {

	@ManyToOne
	private ListenSubject listenSubject;
	
	@ManyToOne
	private Exam exam;
	
	@OneToOne(mappedBy = "examRegistration")
	private ExamTaking examTaking;
	
	@Transient
	private StringProperty index = new SimpleStringProperty();
	
	@Transient
	private StringProperty name = new SimpleStringProperty();
	
	@Transient
	private StringProperty surname = new SimpleStringProperty();
	
	public ExamRegistration() {}

	public ExamRegistration(String note, StudentIndex index, Exam exam, ListenSubject listenSubject) {
		super(note, index);
		
		this.exam = exam;
		this.listenSubject = listenSubject;
	}

	public ListenSubject getListenSubject() {
		return listenSubject;
	}

	public void setListenSubject(ListenSubject listenSubject) {
		this.listenSubject = listenSubject;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
	@Transient
	public String getIndex() {
		return Integer.toString(this.getStudentIndex().getNumber());
	}
	
	@Transient
	public String getName() {
		return getStudentIndex().getStudent().getName();
	}
	
	@Transient
	public String getSurname() {
		return getStudentIndex().getStudent().getSurname();
	}
}
