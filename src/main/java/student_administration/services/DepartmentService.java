package student_administration.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import student_administration.models.Department;
import student_administration.models.Subject;
import student_administration.repositories.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepo;
	
	public Department saveDepartment(String punNaziv, String skraceniNaziv){
		Department dp = new Department();
		dp.setName(punNaziv);
		dp.setShortName(skraceniNaziv);
		return departmentRepo.save(dp);				
	}
	
	public List<Department> loadAll(){
		Iterable<Department> iter = departmentRepo.findAll();
		List<Department> rez = new ArrayList<Department>();
		iter.forEach(rez :: add);
		return rez;
	}
	
	public List<Subject> loadSubjectOnDepartment(int departmentId){
		Iterable<Subject> iter = departmentRepo.getSubjectsByDepartmentId(departmentId);
		List<Subject> rez = new ArrayList<Subject>();
		iter.forEach(rez :: add);
		return rez;
	} 
}
