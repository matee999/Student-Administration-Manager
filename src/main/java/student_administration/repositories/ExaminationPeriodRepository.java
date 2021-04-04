package student_administration.repositories;

import org.springframework.data.repository.CrudRepository;

import student_administration.models.ExaminationPeriod;

public interface ExaminationPeriodRepository extends CrudRepository<ExaminationPeriod, Integer> {}
