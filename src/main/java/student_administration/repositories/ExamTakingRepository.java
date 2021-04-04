package student_administration.repositories;

import org.springframework.data.repository.CrudRepository;

import student_administration.models.ExamTaking;

public interface ExamTakingRepository extends CrudRepository<ExamTaking, Integer> {}
