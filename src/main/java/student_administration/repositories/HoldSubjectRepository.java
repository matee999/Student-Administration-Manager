package student_administration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import student_administration.models.HoldSubject;
import student_administration.models.Student;

public interface HoldSubjectRepository extends CrudRepository<HoldSubject, Integer> {
	
	@Query("SELECT hs FROM HoldSubject hs WHERE lower(hs.subject.name) LIKE lower(:name) AND hs.schoolYear.firstYear = :firstYear AND hs.schoolYear.secondYear = :secondYear")
    List<HoldSubject> findByName(String name, int firstYear, int secondYear);
}
