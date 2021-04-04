package student_administration.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_administration.models.Activity;
import student_administration.models.Department;
import student_administration.models.ListenSubject;
import student_administration.models.PassedSubject;
import student_administration.models.Student;
import student_administration.models.StudentIndex;
import student_administration.repositories.ActivityRepository;
import student_administration.repositories.SchoolYearRepository;
import student_administration.repositories.StudentIndexRepository;
import student_administration.repositories.StudentRepository;

@Service
public class StudentProfilService {

	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	StudentIndexRepository studentIndexRepo;
	
	@Autowired
	SchoolYearRepository schoolYearRepo;
	
	@Autowired
	ActivityRepository activityRepo;
	
	public List<PassedSubject> loadAllPassedSubject(int indexId){
		Iterable<PassedSubject> iter = studentRepo.getPassedSubjectsByIndex(indexId);
		List<PassedSubject> rez = new ArrayList<PassedSubject>();
		iter.forEach(rez :: add);
		return rez;
	}
	
	public void saveIndex(StudentIndex index) {
		studentIndexRepo.save(index);
	}
	
	public List<ListenSubject> loadAllListenSubject(StudentIndex index){
		Iterable<ListenSubject> iter = studentRepo.getListenSubjectBySchoolYear(schoolYearRepo.getActiveSchoolYear().getSchoolyearId(), index.getStudentIndexId());
		List<ListenSubject> rez = new ArrayList<ListenSubject>();
		iter.forEach(rez :: add);
		return rez;
	}
	
	public List<Activity> loadAllActivities(StudentIndex index){
		Iterable<Activity> iter = activityRepo.findAll();
		List<Activity> rez = new ArrayList<Activity>();
		for(Activity a : iter) {
			if(a.getStudentIndex().getStudentIndexId()==index.getStudentIndexId())
				rez.add(a);
		}
		return rez;
	}
	
	public List<StudentIndex> loadAll(){
		Iterable<StudentIndex> iter = studentIndexRepo.findAll();
		List<StudentIndex> rez = new ArrayList<StudentIndex>();
		iter.forEach(rez :: add);
		return rez;
	}
}
