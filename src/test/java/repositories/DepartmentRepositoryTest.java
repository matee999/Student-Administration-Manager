package repositories;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import student_administration.models.Subject;
import student_administration.repositories.DepartmentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {student_administration.StudentAdministrationApp.class})
public class DepartmentRepositoryTest {

	@Autowired
	DepartmentRepository departmentRepository;
	
	@Test
	public void getSubjectsForDepartment() throws Exception{
		System.out.println("---getSubjectsForDepartment---");
		List<Subject> subjects = departmentRepository.getSubjectsByDepartmentId(1);
		
		for(Subject s : subjects) {
			System.out.println(s);
		}
	}
	
	@Test
	public void getAverageGradeForSubjectInYearInterval() throws Exception{
		System.out.println("---getAverageGradeForSubjectInYearInterval---");
		Float averageGrade = departmentRepository.getAverageGradeForSubjectInYearInterval(2, 2019, 2021);
		
		System.out.println(averageGrade);
	}
}
