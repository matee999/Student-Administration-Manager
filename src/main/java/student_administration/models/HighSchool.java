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
@Table(name="highschool")
@NamedQuery(name="HighSchool.findAll", query="SELECT hs FROM HighSchool hs")
public class HighSchool {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int highSchoolId;
	
	private String name;
	
	private String place;
	
	private String type;
	
	@OneToMany(mappedBy = "highSchool")
	private List<FirstEnroll> enrollments;
	
	public HighSchool() {}

	public HighSchool(String name, String place, String type) {
		super();
		this.name = name;
		this.place = place;
		this.type = type;
	}

	public int getHighSchoolId() {
		return highSchoolId;
	}

	public String getName() {
		return name;
	}

	public String getPlace() {
		return place;
	}

	public String getType() {
		return type;
	}

	public void setHighSchoolId(int highSchoolId) {
		this.highSchoolId = highSchoolId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return name + "," + place + "," + type;
	}

	
}
