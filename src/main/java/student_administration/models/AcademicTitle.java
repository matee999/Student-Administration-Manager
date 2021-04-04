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
@Table(name="academictitle")
@NamedQuery(name="AcademicTitle.findAll", query="SELECT at FROM AcademicTitle at")
public class AcademicTitle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int academicTitleId;
	
	@OneToMany(mappedBy = "academicTitle")
    private List<TitleOfProfessor> titleOfProfessors;
	
	private String title;
	
	public AcademicTitle() {}
	
	public AcademicTitle(String title) {
		this.title = title;
	}

	public int getAcademicTitleId() {
		return academicTitleId;
	}

	public void setAcademicTitleId(int academicTitleId) {
		this.academicTitleId = academicTitleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return title;
	}
	
}