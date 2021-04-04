package student_administration.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name="enrolledyear")
@NamedQuery(name="EnrolledYear.findAll", query="SELECT ey FROM EnrolledYear ey")
public class EnrolledYear extends Activity {

	@ManyToOne
	private SchoolYear schoolYear;
	
	@OneToMany(mappedBy = "enrolledYear", fetch = FetchType.EAGER)
	private List<ListenSubject> transferedSubjects;
	
	@Transient
	StringProperty yearName = new SimpleStringProperty();
	
	public EnrolledYear() {}

	public EnrolledYear(String note, StudentIndex index, SchoolYear schoolYear, List<ListenSubject> transferedSubjects) {
		super(note, index);
		
		this.schoolYear = schoolYear;
		this.transferedSubjects = transferedSubjects;
	}

	public SchoolYear getEnrolledYear() {
		return schoolYear;
	}

	public void setSchoolYear(SchoolYear schoolYear) {
		this.schoolYear = schoolYear;
	}

	public List<ListenSubject> getTransferedSubjects() {
		if(transferedSubjects == null)
			transferedSubjects = new ArrayList<ListenSubject>();
		return transferedSubjects;
	}

	public void setTransferedSubjects(List<ListenSubject> transferedSubjects) {
		this.transferedSubjects = transferedSubjects;
	}
	
	@Transient
	public String getYearName() {
		return schoolYear.toString();
	}

	@Override
	public String toString() {
		return "EnrolledYear [schoolYear=" + schoolYear + "]";
	}
}
