package student_administration.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import student_administration.models.ListenSubject;

public interface ListenSubjectRepository extends CrudRepository<ListenSubject, Integer> {
	@Query("SELECT ls FROM ListenSubject ls WHERE ls.studentIndex.studentIndexId = :index AND ls.holdSubject.holdSubjectId = :holdSubject")
    ListenSubject getListenSubjectByStudentAndHoldSubject(int index, int holdSubject);
	
}
