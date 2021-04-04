package student_administration.models;


import javafx.beans.property.SimpleStringProperty;

public class ExamResult {

	private StudentIndex index;
	
	private float examPoints;
	
	private int grade;
	
	private SimpleStringProperty date = new SimpleStringProperty();
	
	

	public ExamResult() {
		super();
	}

	public ExamResult(StudentIndex index, float examPoints, int grade) {
		super();
		this.index = index;
		this.examPoints = examPoints;
		this.grade = grade;
	}

	public StudentIndex getIndex() {
		return index;
	}

	public void setIndex(StudentIndex index) {
		this.index = index;
	}

	public float getExamPoints() {
		return examPoints;
	}

	public void setExamPoints(float examPoints) {
		this.examPoints = examPoints;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public SimpleStringProperty getDate() {
		return date;
	}
	
}
