package student_administration.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_administration.models.Department;
import student_administration.models.EnrolledYear;
import student_administration.models.HoldSubject;
import student_administration.models.ListenSubject;
import student_administration.models.Professor;
import student_administration.models.RenewedYear;
import student_administration.models.SchoolYear;
import student_administration.models.StudentIndex;
import student_administration.models.Subject;
import student_administration.repositories.HoldSubjectRepository;
import student_administration.repositories.ListenSubjectRepository;
import student_administration.repositories.ProfessorRepository;

@Service
public class HoldSubjectService {

	@Autowired
	HoldSubjectRepository holdSubjectRepo;
	
	@Autowired
	ListenSubjectRepository listenSubjectRepo;
	
	public HoldSubject saveHoldSubject(Professor professor, Subject subject, SchoolYear schoolYear){
		HoldSubject hs = new HoldSubject();
		hs.setProfessorOwner(professor);
		hs.setSubject(subject);
		hs.setSchoolYear(schoolYear);
		return holdSubjectRepo.save(hs);				
	}
	
	public List<HoldSubject> loadAll(){
		Iterable<HoldSubject> iter = holdSubjectRepo.findAll();
		List<HoldSubject> rez = new ArrayList<HoldSubject>();
		iter.forEach(rez :: add);
		return rez;
	}

	public ListenSubject saveListenSubject(HoldSubject holdSubject, StudentIndex value) {
		return listenSubjectRepo.save(new ListenSubject(value, holdSubject));
	}
	
	public ListenSubject saveListenSubject(HoldSubject holdSubject, StudentIndex value, EnrolledYear enrolled) {
		ListenSubject ls = new ListenSubject();
		ls.setHoldSubject(holdSubject);
		ls.setStudentIndex(value);
		ls.setEnrolledYear(enrolled);
		return listenSubjectRepo.save(ls);
	}
	
	public ListenSubject saveListenSubject(HoldSubject holdSubject, StudentIndex value, RenewedYear enrolled) {
		ListenSubject ls = new ListenSubject();
		ls.setHoldSubject(holdSubject);
		ls.setStudentIndex(value);
		ls.setRenewedYear(enrolled);
		return listenSubjectRepo.save(ls);
	}
}
