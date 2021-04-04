package student_administration.models;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="examinationperiod")
@NamedQuery(name="ExaminationPeriod.findAll", query="SELECT ep FROM ExaminationPeriod ep")
public class ExaminationPeriod {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int examinationPeriodId;
	
	private String name;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	@OneToMany(mappedBy = "examinationPeriod")
	private List<Exam> exams;
	
	@ManyToOne
	private SchoolYear schoolYear;
	
	public ExaminationPeriod() {}

	public ExaminationPeriod(String name, LocalDate startDate, LocalDate endDate, List<Exam> exams) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.exams = exams;
	}
	
	

	public ExaminationPeriod(String name) {
		super();
		this.name = name;
	}
	
	

	public ExaminationPeriod(String name, SchoolYear schoolYear) {
		super();
		this.name = name;
		this.schoolYear = schoolYear;
	}

	public int getExaminationPeriodId() {
		return examinationPeriodId;
	}

	public void setExaminationPeriodId(int examinationPeriodId) {
		this.examinationPeriodId = examinationPeriodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	public SchoolYear getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(SchoolYear schoolYear) {
		this.schoolYear = schoolYear;
	}

	@Override
	public String toString() {
		return name;
	}
}
