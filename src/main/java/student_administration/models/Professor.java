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
@Table(name="professor")
@NamedQuery(name="Professor.findAll", query="SELECT p FROM Professor p")
public class Professor{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int professorId;
	
	private String name;
	
	private String surname;
	
	private String middlename;
	
	private String email;
	
	@OneToMany(mappedBy = "professorOwner")
    private List<HoldSubject> holdSubjectList;
	
	@OneToMany(mappedBy = "professor")
	private List<TitleOfProfessor> titles;
	
	@OneToMany(mappedBy = "professor")
	private List<Exam> exams;
	
	@ManyToMany
	private List<College> colleges;
	
	@ManyToMany(mappedBy = "lecturers")
	private List<HoldSubject> givingLecturesList;

	public Professor() {}
	
	public Professor(String name, String surname, String middlename, String email) {
		super();
		this.name = name;
		this.surname = surname;
		this.middlename = middlename;
		this.email = email;
	}
	
	public void setColleges(List<College> colleges) {
		this.colleges = colleges;
	}
	
	public List<College> getColleges() {
		return colleges;
	}

	public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<TitleOfProfessor> getTitles() {
		return titles;
	}

	public void setTitles(List<TitleOfProfessor> titles) {
		this.titles = titles;
	}

	@Override
	public String toString() {
		return name + " " + surname;
	}
}
