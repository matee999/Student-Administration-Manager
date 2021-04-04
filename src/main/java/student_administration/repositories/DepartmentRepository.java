package student_administration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import student_administration.models.Department;
import student_administration.models.Subject;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
	
	@Query("SELECT s FROM Subject s WHERE s.department.departmentId = :departmentId")
	List<Subject> getSubjectsByDepartmentId(int departmentId);
	
	@Query("SELECT avg(ps.grade) FROM PassedSubject ps WHERE ps.passedSubjectId IN " +
            "(SELECT ls.passedSubject.passedSubjectId FROM ListenSubject ls WHERE ls.holdSubject.holdSubjectId IN " +
			"(SELECT hs.holdSubjectId FROM HoldSubject hs WHERE hs.subject.subjectId = :subjectId AND " +
			"hs.schoolYear.firstYear BETWEEN :from AND (:to - 1) AND hs.schoolYear.secondYear BETWEEN (:from + 1) AND :to))")
    Float getAverageGradeForSubjectInYearInterval(int subjectId, int from, int to);
	
	@Query("SELECT sp from Department sp where sp.shortName like :shortName")
	Department getStudProgramBySkraceniNaziv(String shortName);
}
