package student_administration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import student_administration.models.Student;
import student_administration.models.EnrolledYear;
import student_administration.models.ListenSubject;
import student_administration.models.PassedSubject;
import student_administration.models.RenewedYear;

public interface StudentRepository extends CrudRepository<Student, Integer> {
	
	@Query("SELECT s FROM Student s WHERE s.studentId = "
			+ "(SELECT index.student.studentId FROM StudentIndex index WHERE index.number = :indexNumber AND index.department.shortName = :departmentName)")
	Student getStudentByIndexNumber(int indexNumber, String departmentName);
	
    @Query("SELECT ps FROM PassedSubject ps WHERE ps.passedSubjectId IN "
    		+ "(SELECT ls.passedSubject.passedSubjectId FROM ListenSubject ls WHERE ls.studentIndex.studentIndexId = "
    		+ "(SELECT index.studentIndexId FROM StudentIndex index WHERE index.studentIndexId = :indexId))")
    List<PassedSubject> getPassedSubjectsByIndex(int indexId);
    
    @Query("SELECT ey FROM EnrolledYear ey WHERE ey.studentIndex IN "
    		+ "(SELECT index.studentIndexId FROM StudentIndex index WHERE index.studentIndexId = :indexId)")
    List<EnrolledYear> getEnrolledYearsByIndex(int indexId);
    
    @Query("SELECT ry FROM RenewedYear ry WHERE ry.studentIndex IN "
    		+ "(SELECT index.studentIndexId FROM StudentIndex index WHERE index.studentIndexId = :indexId)")
    List<RenewedYear> getRenewedYearsByIndex(int indexId);
    
    @Query("SELECT s FROM Student s WHERE lower(s.name) LIKE lower(:name) OR lower(s.surname) LIKE lower(:surname)")
    List<Student> getStudentsByNameOrSurname(String name, String surname);
    
    @Query("SELECT s FROM Student s WHERE s.studentId IN "
    		+ "(SELECT index.student.studentId FROM StudentIndex index WHERE index.studentIndexId IN "
    		+ "(SELECT fe.studentIndex.studentIndexId FROM FirstEnroll fe WHERE fe.highSchool.highSchoolId = :highSchoolId))")
    List<Student> getStudentsByHighSchool(int highSchoolId);
    
    @Query("SELECT ls FROM ListenSubject ls WHERE ls.enrolledYear.schoolYear.schoolyearId = :schoolYear AND ls.studentIndex.studentIndexId = :studentIndexId")
    List<ListenSubject> getListenSubjectBySchoolYear(int schoolYear, int studentIndexId);
}
