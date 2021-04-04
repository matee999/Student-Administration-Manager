package student_administration.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_administration.models.AcademicTitle;
import student_administration.models.Department;
import student_administration.models.Professor;
import student_administration.models.TitleOfProfessor;
import student_administration.repositories.AcademicTitleRepository;
import student_administration.repositories.TitleOfProfessorRepository;

@Service
public class TitleOfProfessorService {

	@Autowired
	TitleOfProfessorRepository titleOfProfessorRepo;
	
	@Autowired
	AcademicTitleRepository academicTitleRepo;
	
	public List<TitleOfProfessor> loadAll(){
		Iterable<TitleOfProfessor> iter = titleOfProfessorRepo.findAll();
		List<TitleOfProfessor> rez = new ArrayList<TitleOfProfessor>();
		iter.forEach(rez :: add);
		return rez;
	}
	
	public List<AcademicTitle> loadAllAcademicTitles(){
		Iterable<AcademicTitle> iter = academicTitleRepo.findAll();
		List<AcademicTitle> rez = new ArrayList<AcademicTitle>();
		iter.forEach(rez :: add);
		return rez;
	}

	public TitleOfProfessor saveTitleOfProfessor(AcademicTitle academicTitle, String area, LocalDate date, Professor professor) {
		
		return titleOfProfessorRepo.save(new TitleOfProfessor(academicTitle, Date.from(date.atStartOfDay()
      .atZone(ZoneId.systemDefault())
      .toInstant()), true, area, professor));
	}
	
	
}
