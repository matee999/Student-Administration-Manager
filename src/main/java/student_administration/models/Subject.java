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
@Table(name="subject")
@NamedQuery(name="Subject.findAll", query="SELECT su FROM Subject su")
public class Subject {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int subjectId;
	
	private int code;
	
	private String name;
	
	private String description;
	
	private int espb;
	
	private int semester; 
	
	private int numberOfExercises;
	
	private int numberOfLectures;
	
	@ManyToOne
	private Department department;
	
	@OneToMany(mappedBy = "subject")
    private List<HoldSubject> holdSubjectList;
	
	public Subject() {}
	
	public Subject(int code, String name, String description, int espb, int semester,
			int numberOfExercises, int numberOfLectures, Department department) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.espb = espb;
		this.semester = semester;
		this.numberOfExercises = numberOfExercises;
		this.numberOfLectures = numberOfLectures;
		this.department = department;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getEspb() {
		return espb;
	}

	public int getSemester() {
		return semester;
	}

	public int getNumberOfExercises() {
		return numberOfExercises;
	}

	public int getNumberOfLectures() {
		return numberOfLectures;
	}

	public Department getDepartment() {
		return department;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEspb(int espb) {
		this.espb = espb;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public void setNumberOfExercises(int numberOfExercises) {
		this.numberOfExercises = numberOfExercises;
	}

	public void setNumberOfLectures(int numberOfLectures) {
		this.numberOfLectures = numberOfLectures;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return name;
	}
}
