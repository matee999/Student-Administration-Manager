package student_administration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import student_administration.models.Professor;
import student_administration.models.Student;

public interface ProfessorRepository extends CrudRepository<Professor, Integer> {
	
	@Query("SELECT p FROM Professor p WHERE lower(p.name) LIKE lower(:name) OR lower(p.surname) LIKE lower(:surname)")
    List<Professor> getProfessorsByNameOrSurname(String name, String surname);
}
