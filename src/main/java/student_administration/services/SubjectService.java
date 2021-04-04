package student_administration.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_administration.models.Department;
import student_administration.models.HoldSubject;
import student_administration.models.Professor;
import student_administration.models.Subject;
import student_administration.repositories.HoldSubjectRepository;
import student_administration.repositories.ProfessorRepository;
import student_administration.repositories.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	SubjectRepository subjectRepo;
	
	@Autowired
	HoldSubjectRepository holdSubjectRepo;
	
	public Subject saveSubject(String name, String espb, String code, String numberOfLectures, String numberOfExercises, String semester, Department department, String description){
		Subject s = new Subject();
		s.setCode(Integer.parseInt(code));
		s.setDepartment(department);
		s.setName(name);
		s.setDescription(description);
		s.setEspb(Integer.parseInt(espb));
		s.setNumberOfLectures(Integer.parseInt(numberOfLectures));
		s.setNumberOfExercises(Integer.parseInt(numberOfExercises));
		s.setSemester(Integer.parseInt(semester));
		return subjectRepo.save(s);				
	}
	
	public List<Subject> loadAll(){
		Iterable<Subject> iter = subjectRepo.findAll();
		List<Subject> rez = new ArrayList<Subject>();
		iter.forEach(rez :: add);
		return rez;
	}
	
	public List<HoldSubject> loadAllHoldSubject(){
		Iterable<HoldSubject> iter = holdSubjectRepo.findAll();
		List<HoldSubject> rez = new ArrayList<HoldSubject>();
		iter.forEach(rez :: add);
		return rez;
	}
}
