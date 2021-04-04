package student_administration.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="firstenroll")
@NamedQuery(name="FirstEnroll.findAll", query="SELECT fe FROM FirstEnroll fe")
public class FirstEnroll extends Activity {

	private float pointsFromEntranceExam;
	
	private float highSchoolPoints;
	
	@ManyToOne
	private HighSchool highSchool;
	
	public FirstEnroll() {}

	public FirstEnroll(String note, StudentIndex index, float pointsFromEntranceExam, float highSchoolPoints, HighSchool highSchool) {
		super(note, index);
		this.pointsFromEntranceExam = pointsFromEntranceExam;
		this.highSchoolPoints = highSchoolPoints;
		this.highSchool = highSchool;
	}

	public float getPointsFromEntranceExam() {
		return pointsFromEntranceExam;
	}

	public void setPointsFromEntranceExam(float pointsFromEntranceExam) {
		this.pointsFromEntranceExam = pointsFromEntranceExam;
	}

	public float getHighSchoolPoints() {
		return highSchoolPoints;
	}

	public void setHighSchoolPoints(float highSchoolPoints) {
		this.highSchoolPoints = highSchoolPoints;
	}

	public HighSchool getHighSchool() {
		return highSchool;
	}

	public void setHighSchool(HighSchool highSchool) {
		this.highSchool = highSchool;
	}
	
	
}
