package student_administration.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the studProgram database table.
 * 
 */
@Entity
@Table(name="department")
@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int departmentId;

	private String name;

	private String shortName;
	
	private int yearOfAccreditation;
	
	private String title;
	
	private int durationInSemesters;
	
	@ManyToOne
	private TypeOfStudy typeOfStudy;
	
	@OneToMany(mappedBy="department")
	private List<Subject> subjects;

	@OneToMany(mappedBy = "department")
	private List<StudentIndex> studentIndexes;
	
	public Department() {}
	
	public Department(String name, String shortName) {
		super();
		this.name = name;
		this.shortName = shortName;
	}

	public Department(String name, String shortName, int yearOfAccreditation, String title, int durationInSemesters,
			TypeOfStudy typeOfStudy, List<Subject> subjects, List<StudentIndex> studentIndexes) {
		super();
		this.name = name;
		this.shortName = shortName;
		this.yearOfAccreditation = yearOfAccreditation;
		this.title = title;
		this.durationInSemesters = durationInSemesters;
		this.typeOfStudy = typeOfStudy;
		this.subjects = subjects;
		this.studentIndexes = studentIndexes;
	}



	public int getDepartmentId() {
		return departmentId;
	}

	public String getName() {
		return name;
	}

	public String getShortName() {
		return shortName;
	}

	public int getYearOfAccreditation() {
		return yearOfAccreditation;
	}

	public String getTitle() {
		return title;
	}

	public int getDurationInSemesters() {
		return durationInSemesters;
	}

	public TypeOfStudy getTypeOfStudy() {
		return typeOfStudy;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public void setYearOfAccreditation(int yearOfAccreditation) {
		this.yearOfAccreditation = yearOfAccreditation;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDurationInSemesters(int durationInSemesters) {
		this.durationInSemesters = durationInSemesters;
	}

	public void setTypeOfStudy(TypeOfStudy typeOfStudy) {
		this.typeOfStudy = typeOfStudy;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<StudentIndex> getStudentIndexes() {
		return studentIndexes;
	}

	public void setStudentIndexes(List<StudentIndex> studentIndexes) {
		this.studentIndexes = studentIndexes;
	}	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return shortName;
	}

/*
	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setDepartment(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setDepartment(null);

		return student;
	}
*/

}