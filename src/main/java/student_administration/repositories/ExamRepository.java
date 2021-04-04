package student_administration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import student_administration.models.Exam;
import student_administration.models.Student;

public interface ExamRepository extends CrudRepository<Exam, Integer> {
	
	@Query("SELECT s FROM Student s WHERE s.studentId IN "
			+ "(SELECT si.student.studentId FROM StudentIndex si WHERE si.studentIndexId IN "
			+ "(SELECT er.studentIndex.studentIndexId FROM ExamRegistration er WHERE er.exam.examId = :examId))")
	List<Student> getRegisteredStudentsForExam(int examId);
	
	@Query("SELECT avg(ps.grade) FROM PassedSubject ps WHERE ps.listenSubject.listenSubjectId IN "
			+ "(SELECT er.listenSubject.listenSubjectId FROM ExamRegistration er WHERE er.exam.examId = :examId)")
	Float getAverageGradeForExam(int examId);
	
	@Query("SELECT s, sum(wpeo.points) + sum(et.examPoints) FROM Student s, WonPreExamObligations wpeo, ExamTaking et WHERE s.studentId IN "
			+ "(SELECT si.student.studentId FROM StudentIndex si WHERE si.studentIndexId IN "
			+ "(SELECT er.studentIndex.studentIndexId FROM ExamRegistration er WHERE er.exam.examId = :examId)) AND "
			+ "wpeo.listenSubject.passedSubject.exam.examId = :examId AND et.examRegistration.exam.examId = :examId")
	List<Object[]> getExamResults(int examId);
	
	@Query("SELECT count(et.activityId) FROM ExamTaking et WHERE et.examRegistration.listenSubject.holdSubject.subject.subjectId = :subjectId AND "
			+ "et.studentIndex.student.studentId = :studentId")
	int getExamTakeCountForStudent(int studentId, int subjectId);
	
	@Query("SELECT sum(wpeo.points) FROM WonPreExamObligations wpeo WHERE wpeo.listenSubject.listenSubjectId = :listenSubjectId")
	Float getWonPreExamPointsForListenedSubject(int listenSubjectId);
	
	@Query("SELECT e FROM Exam e WHERE e.examinationPeriod.examinationPeriodId = :examinationPeriod")
    List<Exam> getExamsForExaminationPeriod(int examinationPeriod);
}
