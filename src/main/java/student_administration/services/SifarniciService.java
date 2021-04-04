package student_administration.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_administration.models.HighSchool;
import student_administration.models.Professor;
import student_administration.models.Subject;
import student_administration.repositories.HighSchoolRepository;
import student_administration.repositories.ProfessorRepository;
import student_administration.repositories.SubjectRepository;

@Service
public class SifarniciService {
	
	@Autowired
	HighSchoolRepository highSchoolRepo;
	
	@Autowired
	ProfessorRepository professorRepo;
	
	@Autowired
	SubjectRepository subjectRepo;
	
	public List<HighSchool> getSrednjeSkole(){		
		Iterable<HighSchool> iter = highSchoolRepo.findAll();
		List<HighSchool> rez = new ArrayList<HighSchool>();		
		iter.forEach(rez::add);
		return rez;		
	}
	
	public HighSchool saveHighSchool(HighSchool ss) {
		return highSchoolRepo.save(ss);
	}
	
	public List<Professor> getProfessors(){		
		Iterable<Professor> iter = professorRepo.findAll();
		List<Professor> rez = new ArrayList<Professor>();		
		iter.forEach(rez::add);
		return rez;		
	}
	
	public List<Subject> getSubjects(){		
		Iterable<Subject> iter = subjectRepo.findAll();
		List<Subject> rez = new ArrayList<Subject>();		
		iter.forEach(rez::add);
		return rez;		
	}
}
