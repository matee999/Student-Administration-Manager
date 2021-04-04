package student_administration.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="schoolyear")
@NamedQuery(name="SchoolYear.findAll", query="SELECT sy FROM SchoolYear sy")
public class SchoolYear {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int schoolyearId;
	
	private int firstYear;
	
	private int secondYear;
	
	private boolean active;
	
	@OneToMany(mappedBy = "schoolYear")
    private List<HoldSubject> holdSubjectList;
	
	@OneToMany(mappedBy = "schoolYear")
	private List<ExaminationPeriod> examinationPeriods;
	
	@ManyToMany()
	private List<StudentIndex> studentIndexes;
	
	@OneToMany(mappedBy = "schoolYear")
	private List<EnrolledYear> enrollments;
	
	@OneToMany(mappedBy = "schoolYear")
	private List<RenewedYear> renewals;
	
	public SchoolYear() {}

	public SchoolYear(int firstYear, int secondYear, boolean active) {
		super();
		this.firstYear = firstYear;
		this.secondYear = secondYear;
		this.active = active;
	}

	public int getSchoolyearId() {
		return schoolyearId;
	}

	public void setSchoolyearId(int schoolyearId) {
		this.schoolyearId = schoolyearId;
	}

	public boolean isActive() {
		return active;
	}

	
	
	public void setFirstYear(int firstYear) {
		this.firstYear = firstYear;
	}

	public void setSecondYear(int secondYear) {
		this.secondYear = secondYear;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<ExaminationPeriod> getExaminationPeriods() {
		return examinationPeriods;
	}

	public void setExaminationPeriods(List<ExaminationPeriod> examinationPeriods) {
		this.examinationPeriods = examinationPeriods;
	}

	public List<StudentIndex> getStudentIndexes() {
		return studentIndexes;
	}

	public void setStudentIndexes(List<StudentIndex> studentIndexes) {
		this.studentIndexes = studentIndexes;
	}
	
	public int getFirstYear() {
		return firstYear;
	}
	
	public int getSecondYear() {
		return secondYear;
	}

	@Override
	public String toString() {
		return firstYear + "/" + secondYear;
	}
}
