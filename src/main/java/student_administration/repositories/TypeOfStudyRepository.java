package student_administration.repositories;

import org.springframework.data.repository.CrudRepository;

import student_administration.models.TypeOfStudy;

public interface TypeOfStudyRepository extends CrudRepository<TypeOfStudy, Integer> {}
