package student_administration.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_administration.models.Department;
import student_administration.models.Professor;
import student_administration.repositories.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	ProfessorRepository professorRepo;
	
	public Professor saveProfessor(String name, String surname, String middlename, String email){
		Professor p = new Professor();
		p.setName(name);
		p.setSurname(surname);
		p.setMiddlename(middlename);
		p.setEmail(email);
		return professorRepo.save(p);				
	}
	
	public List<Professor> loadAll(){
		Iterable<Professor> iter = professorRepo.findAll();
		List<Professor> rez = new ArrayList<Professor>();
		iter.forEach(rez :: add);
		return rez;
	}

	public List<Professor> findProfessorbyName(String name, String surname) {
		return professorRepo.getProfessorsByNameOrSurname(name, surname);
	}
}
