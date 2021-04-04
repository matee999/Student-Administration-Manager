package student_administration.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import student_administration.models.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {
	@Query("SELECT s FROM Subject s WHERE lower(s.name) like lower(:name)")
    Subject findSubjectByName(String name);
}
