package student_administration.models;

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
@Table(name="preexamobligations")
@NamedQuery(name="PreExamObligations.findAll", query="SELECT peo FROM PreExamObligations peo")
public class PreExamObligations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int preExamObligationsId;
	
	@ManyToOne
	private HoldSubject holdSubject;

	private String type;
	
	private float maxPoints;
	
	@OneToMany(mappedBy = "preExamObligations")
    private List<WonPreExamObligations> wonPreExamObligations;
	
	public PreExamObligations() {}
	
	public PreExamObligations(HoldSubject holdSubject, String type) {
		super();
		this.holdSubject = holdSubject;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(float maxPoints) {
		this.maxPoints = maxPoints;
	}	
	
}
