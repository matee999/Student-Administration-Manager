package student_administration.repositories;

import org.springframework.data.repository.CrudRepository;

import student_administration.models.College;

public interface CollegeRepository extends CrudRepository<College, Integer> {}
