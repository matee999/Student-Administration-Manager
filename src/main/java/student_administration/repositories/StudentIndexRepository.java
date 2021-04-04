package student_administration.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import student_administration.models.Student;
import student_administration.models.StudentIndex;

public interface StudentIndexRepository extends CrudRepository<StudentIndex, Integer> {
	@Query("SELECT si FROM StudentIndex si WHERE si.number = :indexNumber AND si.department.shortName = :departmentName")
	StudentIndex getStudentIndexByIndexNumber(int indexNumber, String departmentName);
}
