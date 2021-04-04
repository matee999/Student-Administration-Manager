package student_administration.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import student_administration.models.SchoolYear;

public interface SchoolYearRepository extends CrudRepository<SchoolYear, Integer> {
	
	@Query("SELECT sy FROM SchoolYear sy WHERE sy.active is true")
    SchoolYear getActiveSchoolYear();
	
	@Query("SELECT sy FROM SchoolYear sy WHERE sy.firstYear = :first AND sy.secondYear = :second")
    SchoolYear findSchoolYear(int first, int second);
}
