package student_administration.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name="holdsubject")
@NamedQuery(name="HoldSubject.findAll", query="SELECT hs FROM HoldSubject hs")
public class HoldSubject {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int holdSubjectId;

	@ManyToOne
	private Professor professorOwner;
	
	@ManyToMany
	private List<Professor> lecturers;
	
	@ManyToOne
	private Subject subject;
	
	@ManyToOne
	private SchoolYear schoolYear;
	
	@OneToMany(mappedBy = "holdSubject", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
    private List<ListenSubject> listenSubjectList;
	
	@OneToMany(mappedBy = "holdSubject")
	private List<PreExamObligations> preExamObligations;
	
	@OneToMany(mappedBy = "holdSubject")
	private List<Exam> exams;

	
	
	public HoldSubject() {}

	public HoldSubject(Professor professorOwner, Subject subject, SchoolYear schoolYear) {
		super();
		this.professorOwner = professorOwner;
		this.subject = subject;
		this.schoolYear = schoolYear;
	}
	
	public int getHoldSubjectId() {
		return holdSubjectId;
	}

	public Professor getProfessorOwner() {
		return professorOwner;
	}
	
	public void setProfessorOwner(Professor professorOwner) {
		this.professorOwner = professorOwner;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public SchoolYear getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(SchoolYear schoolYear) {
		this.schoolYear = schoolYear;
	}

	@Override
	public String toString() {
		return subject.getName();
	}

	public List<ListenSubject> getListenSubjectList() {
		if(listenSubjectList == null)
			listenSubjectList = new ArrayList<ListenSubject>();
		return listenSubjectList;
	}

	public void setListenSubjectList(List<ListenSubject> listenSubjectList) {
		this.listenSubjectList = listenSubjectList;
	}

	public List<PreExamObligations> getPreExamObligations() {
		return preExamObligations;
	}

	public void setPreExamObligations(List<PreExamObligations> preExamObligations) {
		this.preExamObligations = preExamObligations;
	}
	
	
}
