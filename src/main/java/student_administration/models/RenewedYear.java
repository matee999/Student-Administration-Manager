package student_administration.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="renewedyear")
@NamedQuery(name="RenewedYear.findAll", query="SELECT ry FROM RenewedYear ry")
public class RenewedYear extends Activity {

	@ManyToOne
	private SchoolYear schoolYear;
	
	@OneToMany(mappedBy = "renewedYear", fetch = FetchType.EAGER)
	private List<ListenSubject> renewedSubjects;
	
	public RenewedYear() {}

	public RenewedYear(String note, StudentIndex index, SchoolYear schoolYear, List<ListenSubject> renewedSubjects) {
		super(note, index);
		
		this.schoolYear = schoolYear;
		this.renewedSubjects = renewedSubjects;
	}

	public SchoolYear getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(SchoolYear schoolYear) {
		this.schoolYear = schoolYear;
	}

	public List<ListenSubject> getRenewedSubjects() {
		if(renewedSubjects==null)
			renewedSubjects = new ArrayList<ListenSubject>();
		return renewedSubjects;
	}

	public void setRenewedSubjects(List<ListenSubject> renewedSubjects) {
		this.renewedSubjects = renewedSubjects;
	}

	
	
	@Override
	public String toString() {
		return "RenewedYear [schoolYear=" + schoolYear + "]";
	}
}
