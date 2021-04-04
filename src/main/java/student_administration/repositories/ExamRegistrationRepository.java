package student_administration.repositories;

import org.springframework.data.repository.CrudRepository;

import student_administration.models.ExamRegistration;

public interface ExamRegistrationRepository extends CrudRepository<ExamRegistration, Integer> {}
