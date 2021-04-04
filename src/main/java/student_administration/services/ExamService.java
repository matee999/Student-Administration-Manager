package student_administration.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_administration.models.Exam;
import student_administration.models.ExamReport;
import student_administration.models.ExamResult;
import student_administration.models.ExamTaking;
import student_administration.models.PassedSubject;
import student_administration.repositories.ExamRepository;
import student_administration.repositories.ExamTakingRepository;

@Service
public class ExamService {

	@Autowired
	ExamRepository examRepo;

	@Autowired
	ExamTakingRepository examTakingRepo;

	public Exam saveExam(Exam e) {
		return examRepo.save(e);
	}

	public List<Exam> loadAll() {
		Iterable<Exam> iter = examRepo.findAll();
		List<Exam> rez = new ArrayList<Exam>();
		iter.forEach(rez::add);
		return rez;
	}

	public List<Exam> loadAllExamOnExaminationPeriod(int examinationPeriodId) {
		Iterable<Exam> iter = examRepo.getExamsForExaminationPeriod(examinationPeriodId);
		List<Exam> rez = new ArrayList<Exam>();
		iter.forEach(rez::add);
		return rez;
	}

	public List<ExamResult> getExamResult(Exam e) {
		List<PassedSubject> ps = e.getPassedSubjects();
		List<ExamResult> er = new ArrayList<ExamResult>();
		for (PassedSubject p : ps) {
			ExamResult err = new ExamResult();
			err.setIndex(p.getListenSubject().getStudentIndex());
			err.setGrade(p.getGrade());

			er.add(err);
		}
		Iterable<ExamTaking> itertaking = examTakingRepo.findAll();
		List<ExamTaking> taking = new ArrayList<ExamTaking>();
		itertaking.forEach(taking::add);
		for (ExamTaking ett : taking) {
			for (ExamResult errr : er) {
				if (ett.getStudentIndex().getStudentIndexId() == errr.getIndex().getStudentIndexId()) {
					errr.setExamPoints(ett.getExamPoints());
				}
			}
		}
		return er;
	}

	public List<ExamReport> getReportData(Exam e) {
		List<ExamResult> results = getExamResult(e);
		List<ExamReport> data = new ArrayList<ExamReport>();

		for (ExamResult res : results) {
			ExamReport report = new ExamReport(res.getIndex().getReportName(),
					res.getIndex().getStudent().getName() + " " + res.getIndex().getStudent().getSurname(), "",
					res.getExamPoints(), res.getGrade());
			data.add(report);
		}
		
		return data;
	}
}
