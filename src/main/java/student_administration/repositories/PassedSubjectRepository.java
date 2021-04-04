package student_administration.repositories;

import org.springframework.data.repository.CrudRepository;

import student_administration.models.PassedSubject;

public interface PassedSubjectRepository extends CrudRepository<PassedSubject, Integer> {}
