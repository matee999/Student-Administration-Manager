package student_administration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_administration.repositories.StudentRepository;

@Service
public class StudentSearchDataService {

	@Autowired
	StudentRepository studentRepo;
}
