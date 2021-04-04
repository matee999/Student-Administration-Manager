package student_administration.models;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="examtaking")
@NamedQuery(name="ExamTaking.findAll", query="SELECT et FROM ExamTaking et")
public class ExamTaking extends Activity {

	@OneToOne
	private ExamRegistration examRegistration;
	
	private float examPoints;
		
	private boolean canceled;
	
	public ExamTaking() {}

	public ExamTaking(String note, StudentIndex index, ExamRegistration examRegistration, float examPoints, boolean canceled) {
		super(note, index);
		
		this.examRegistration = examRegistration;
		this.examPoints = examPoints;
		this.canceled = canceled;
	}

	public ExamRegistration getExamRegistration() {
		return examRegistration;
	}

	public void setExamRegistration(ExamRegistration examRegistration) {
		this.examRegistration = examRegistration;
	}

	public float getExamPoints() {
		return examPoints;
	}

	public void setExamPoints(float examPoints) {
		this.examPoints = examPoints;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
}
