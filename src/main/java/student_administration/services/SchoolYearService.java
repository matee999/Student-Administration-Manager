package student_administration.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_administration.models.SchoolYear;
import student_administration.repositories.SchoolYearRepository;

@Service
public class SchoolYearService {

	@Autowired
	SchoolYearRepository schoolYearRepo;
	
	private List<SchoolYear> allSchoolYears;
	
	public SchoolYear saveSchoolYear(String pocetna, String zavrsna){
		SchoolYear sy = new SchoolYear();
		sy.setFirstYear(Integer.parseInt(pocetna));
		sy.setSecondYear(Integer.parseInt(zavrsna));
		sy.setActive(true);
		allSchoolYears = new ArrayList<SchoolYear>();
		allSchoolYears = (List<SchoolYear>) schoolYearRepo.findAll();
		for(SchoolYear s: allSchoolYears) {
			s.setActive(false);
		}
		schoolYearRepo.saveAll(allSchoolYears);
		return schoolYearRepo.save(sy);				
	}
	
	public List<SchoolYear> loadAll(){
		Iterable<SchoolYear> iter = schoolYearRepo.findAll();
		List<SchoolYear> rez = new ArrayList<SchoolYear>();
		iter.forEach(rez :: add);
		return rez;
	}
}
