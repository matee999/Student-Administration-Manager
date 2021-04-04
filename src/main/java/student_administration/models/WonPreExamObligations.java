package student_administration.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="wonpreexamobligations")
@NamedQuery(name="WonPreExamObligations.findAll", query="SELECT wpeo FROM WonPreExamObligations wpeo")
public class WonPreExamObligations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wonPreExamObligationsId;

	@ManyToOne
	private ListenSubject listenSubject;
		
	private float points;
	
	@ManyToOne
	private PreExamObligations preExamObligations;
	
	public WonPreExamObligations() {}

	public WonPreExamObligations(ListenSubject listenSubject, float points, PreExamObligations preExamObligations) {
		this.listenSubject = listenSubject;
		this.points = points;
		this.preExamObligations = preExamObligations;
	}

	public float getPoints() {
		return points;
	}

	public void setPoints(float points) {
		this.points = points;
	}

	public PreExamObligations getPreExamObligations() {
		return preExamObligations;
	}

	public void setPreExamObligations(PreExamObligations preExamObligations) {
		this.preExamObligations = preExamObligations;
	}

	public int getWonPreExamObligationsId() {
		return wonPreExamObligationsId;
	}

	public void setWonPreExamObligationsId(int wonPreExamObligationsId) {
		this.wonPreExamObligationsId = wonPreExamObligationsId;
	}

	public ListenSubject getListenSubject() {
		return listenSubject;
	}

	public void setListenSubject(ListenSubject listenSubject) {
		this.listenSubject = listenSubject;
	}
	
	
	
}
