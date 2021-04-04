package student_administration.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.scene.chart.PieChart.Data;
import student_administration.models.Department;
import student_administration.models.ExaminationPeriod;
import student_administration.models.SchoolYear;
import student_administration.repositories.DepartmentRepository;
import student_administration.repositories.ExaminationPeriodRepository;
import student_administration.repositories.SchoolYearRepository;

@Service
public class ExaminationPeriodService {

	@Autowired
	ExaminationPeriodRepository examinationPeriodRepo;
	
	@Autowired
	SchoolYearRepository schoolYearRepo;
	
	public ExaminationPeriod saveExaminationPeriod(String punNaziv, LocalDate startDate, LocalDate endDate){
		ExaminationPeriod dp = new ExaminationPeriod();
		dp.setName(punNaziv);
		dp.setStartDate(startDate);
		dp.setEndDate(endDate);
		
		
		List<SchoolYear> years = (List<SchoolYear>) schoolYearRepo.findAll();
		for(SchoolYear sy: years) {
			if(sy.isActive())
				dp.setSchoolYear(sy);
		}
		
		return examinationPeriodRepo.save(dp);				
	}
	
	public List<ExaminationPeriod> loadAll(){
		Iterable<ExaminationPeriod> iter = examinationPeriodRepo.findAll();
		List<ExaminationPeriod> rez = new ArrayList<ExaminationPeriod>();
		iter.forEach(rez :: add);
		return rez;
	}
}
