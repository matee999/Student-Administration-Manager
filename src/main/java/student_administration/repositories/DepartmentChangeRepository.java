package student_administration.repositories;

import org.springframework.data.repository.CrudRepository;

import student_administration.models.DepartmentChange;

public interface DepartmentChangeRepository extends CrudRepository<DepartmentChange, Integer> {}
