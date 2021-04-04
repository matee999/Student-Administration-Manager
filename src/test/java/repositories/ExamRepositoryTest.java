package repositories;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import student_administration.models.Student;
import student_administration.repositories.ExamRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {student_administration.StudentAdministrationApp.class})
public class ExamRepositoryTest {
	
	@Autowired
	ExamRepository examRepository;
	
	@Test
	public void getRegisteredStudentsByExam() throws Exception {
		System.out.println("---getRegisteredStudentsByExam---");
		List<Student> students = examRepository.getRegisteredStudentsForExam(1);
		
		for (Student student : students)
			System.out.println(student);
	}
	
	@Test
	public void getAverageGradeForExam() throws Exception {
		System.out.println("---getAverageGradeForExam---");
		Float grade = examRepository.getAverageGradeForExam(1);
		
		System.out.println(grade);
	}
	
	@Test
	public void getExamTakeCountForStudent() throws Exception {
		System.out.println("---getExamTakeCountForStudent---");
		int examTakeCount = examRepository.getExamTakeCountForStudent(1, 1);
		
		System.out.println(examTakeCount);
	}
	
	@Test
	public void getWonPreExamPointsForListenedSubject() throws Exception {
		System.out.println("---getWonPreExamPointsForListenedSubject---");
		Float preExamPoints = examRepository.getWonPreExamPointsForListenedSubject(1);
		
		System.out.println(preExamPoints);
	}
}
