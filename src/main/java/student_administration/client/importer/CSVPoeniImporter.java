package student_administration.client.importer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import student_administration.models.Exam;
import student_administration.models.ExamTaking;
import student_administration.models.ExaminationPeriod;
import student_administration.models.HoldSubject;
import student_administration.models.ListenSubject;
import student_administration.models.PreExamObligations;
import student_administration.models.SchoolYear;
import student_administration.models.StudentIndex;
import student_administration.models.Subject;
import student_administration.models.WonPreExamObligations;
import student_administration.repositories.SchoolYearRepository;
import student_administration.services.StudentService;



@Component
public class CSVPoeniImporter {
	
	@Autowired
	StudentService studService;
	
	// vraca poruku
	public String importCSV(File f) {
		Scanner sc = null;
		StringBuilder poruka = new StringBuilder();
		
		try {
			sc = new Scanner(f,"UTF-8");
			String line1 = sc.nextLine();
			String[] delovi1 = line1.split(",");
			String subjectName = delovi1[0];
			
			line1 = sc.nextLine();
			delovi1 = line1.split(",");
			String year = delovi1[0];
			String[] firstAndSecond = year.split("/");
			int first = Integer.parseInt(firstAndSecond[0]);
			int second = Integer.parseInt(firstAndSecond[1]);
			
			HoldSubject hs = studService.findHoldSubjectByName(subjectName, first, second);
			if(hs == null) {
				Subject subject = studService.findSubjectByName(subjectName);
				if(subject==null)
					subject = studService.saveSubject(subjectName);
				SchoolYear schoolYear = studService.findSchoolYear(first, second);
				if(schoolYear==null)
					schoolYear = studService.saveSchoolYear(first,second);
				hs = studService.saveHoldSubject(subject, schoolYear);	
			}
			
			line1 = sc.nextLine();
			delovi1 = line1.split(",");
			String preExamObligationName;
			List<PreExamObligations> obligations = new ArrayList<PreExamObligations>();
			for(int i = 0; i < 3; i++) {
				preExamObligationName = delovi1[5+i];
				obligations.add(studService.savePreExamObligation(hs, preExamObligationName));
			}
			
			String examinationPeriodName;
			List<Exam> exams = new ArrayList<Exam>();
			for(int i = 0; i < 4; i++) {
				examinationPeriodName = delovi1[8+i];
				ExaminationPeriod examinationPeriod = studService.saveExaminationPeriod(examinationPeriodName, hs.getSchoolYear());	
				exams.add(studService.saveExam(hs, examinationPeriod));
			}
			
			int brojSacuvanihStudenata = 0;
			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] delovi = line.split(",", -1);
				String studProgram = delovi[0];
				int broj = Integer.parseInt(delovi[1]);		
				int godina = Integer.parseInt(delovi[2]);	
				int index = (godina%100)+broj*100;			
				String prezime = delovi[3];
				String ime = delovi[4];
				
				
				StudentIndex si = studService.findStudentByIndex(index , studProgram);
				if(si==null)
					si = studService.saveStudentAndIndex(ime, prezime, studProgram, index, godina);
				
				ListenSubject listenSubject = studService.findListenSubjectByIndexAndHoldSubject(si, hs);
				if(listenSubject==null)
					listenSubject = studService.saveListenSubject(si,hs);
				
				List<WonPreExamObligations> wonPreExamObligations = new ArrayList<WonPreExamObligations>();
				for(int i = 0; i < obligations.size(); i++) {
					wonPreExamObligations.add(studService.saveWonPreExamObligation(listenSubject, delovi[5+i], obligations.get(i)));	
				}
				
				ExamTaking et = null;
				float poeni = 0;
				for(int i = 8; i < 12; i++) {
					if(!delovi[i].isEmpty()) {		
						poeni = 0;
						et = studService.registrationAndTakingAnExam(si,exams.get(i-8),listenSubject,delovi[i]);
						for(int j = 0; j<wonPreExamObligations.size(); j++) {
							poeni+=wonPreExamObligations.get(j).getPoints();
						}
						poeni+=et.getExamPoints();
					}
				}
				if(et!=null && poeni>=51) {
					int grade=6;
					if(poeni>=61 && poeni<71)
						grade=7;
					if(poeni>=71 && poeni<81)
						grade=8;
					if(poeni>=81 && poeni<91)
						grade=9;
					if(poeni>=91)
						grade=10;
					studService.savePassedSubject(listenSubject, et.getExamRegistration().getExam(), grade);
				}			
				brojSacuvanihStudenata++;
			}
			poruka.append("Broj sacuvanih studenata: ");
			poruka.append(brojSacuvanihStudenata);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sc.close();
		
		}
		return poruka.toString();
	}

}
