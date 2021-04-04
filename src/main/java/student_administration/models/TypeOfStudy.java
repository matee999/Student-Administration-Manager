package student_administration.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="typeofstudy")
@NamedQuery(name="TypeOfStudy.findAll", query="SELECT ts FROM TypeOfStudy ts")
public class TypeOfStudy {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int typeOfStudyId;
	
	private String name;
	
	private String shortName;
	
	@OneToMany(mappedBy="typeOfStudy")
	private List<Department> departments;

	public TypeOfStudy() {}
	
	public TypeOfStudy(String name, String shortName, List<Department> departments) {
		super();
		this.name = name;
		this.shortName = shortName;
		this.departments = departments;
	}

	public int getTypeOfStudyId() {
		return typeOfStudyId;
	}

	public void setTypeOfStudyId(int typeOfStudyId) {
		this.typeOfStudyId = typeOfStudyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	
}
