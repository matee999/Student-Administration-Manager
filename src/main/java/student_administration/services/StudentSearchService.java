package student_administration.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_administration.models.Department;
import student_administration.models.Student;
import student_administration.repositories.StudentRepository;

@Service
public class StudentSearchService {

	@Autowired
	private StudentRepository studentRepo;
	
	public List<Student> loadAll(){
		Iterable<Student> iter = studentRepo.findAll();
		List<Student> rez = new ArrayList<Student>();
		iter.forEach(rez :: add);
		return rez;
	}
	
	public List<Student> findStudents(String ime, String prezime){
		return studentRepo.getStudentsByNameOrSurname(ime, prezime);				
	}
	
	public Student findStudentbyIndex(String index, String departmentName){
		return studentRepo.getStudentByIndexNumber(Integer.parseInt(index), departmentName);				
	}
}
