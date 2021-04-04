package student_administration.models;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="departmentchange")
@NamedQuery(name="DepartmentChange.findAll", query="SELECT dc FROM DepartmentChange dc")
public class DepartmentChange extends Activity {

	@OneToOne
	private StudentIndex newIndex;
	
	public DepartmentChange() {}

	public DepartmentChange(String note, StudentIndex currentIndex, StudentIndex newIndex) {
		super(note, currentIndex);
		
		this.newIndex = newIndex;
	}

	public StudentIndex getNewIndex() {
		return newIndex;
	}

	public void setNewIndex(StudentIndex newIndex) {
		this.newIndex = newIndex;
	}	
	
}
