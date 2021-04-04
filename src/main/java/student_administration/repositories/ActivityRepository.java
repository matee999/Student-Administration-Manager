package student_administration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import student_administration.models.Activity;
import student_administration.models.FirstEnroll;

public interface ActivityRepository extends CrudRepository<Activity, Integer> {
	

}
