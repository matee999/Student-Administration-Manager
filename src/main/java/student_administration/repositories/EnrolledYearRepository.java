package student_administration.repositories;

import org.springframework.data.repository.CrudRepository;

import student_administration.models.EnrolledYear;

public interface EnrolledYearRepository extends CrudRepository<EnrolledYear, Integer> {}
