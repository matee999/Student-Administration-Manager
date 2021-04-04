package student_administration.models;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="titleofprofessor")
@NamedQuery(name="TitleOfProfessor.findAll", query="SELECT tp FROM TitleOfProfessor tp")
public class TitleOfProfessor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int titleOfProfessorId;
		
	private Date dateOfElection;
	
	private boolean active;
	
	private String scientificArea;
	
	@ManyToOne
	private AcademicTitle academicTitle;
	
	@ManyToOne
	private Professor professor;
	
	public TitleOfProfessor() {}

	public TitleOfProfessor(AcademicTitle academicTitle, Date dateOfElection, boolean active,
			String scientificArea, Professor professor) {
		super();
		this.academicTitle = academicTitle;
		this.dateOfElection = dateOfElection;
		this.active = active;
		this.scientificArea = scientificArea;
		this.professor = professor;
	}

	public int getTitleOfProfessorId() {
		return titleOfProfessorId;
	}

	public void setTitleOfProfessorId(int titleOfProfessorId) {
		this.titleOfProfessorId = titleOfProfessorId;
	}

	public Date getDateOfElection() {
		return dateOfElection;
	}

	public void setDateOfElection(Date dateOfElection) {
		this.dateOfElection = dateOfElection;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getScientificArea() {
		return scientificArea;
	}

	public void setScientificArea(String scientificArea) {
		this.scientificArea = scientificArea;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public AcademicTitle getAcademicTitle() {
		return academicTitle;
	}

	public void setAcademicTitle(AcademicTitle academicTitle) {
		this.academicTitle = academicTitle;
	}
	
	
}
