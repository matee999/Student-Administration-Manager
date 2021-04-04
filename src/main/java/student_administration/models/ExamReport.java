package student_administration.models;

public class ExamReport {

	private String index;
	private String name;
	private String note;
	
	private Float points;
	
	private Integer grade;
	
	public ExamReport(String index, String name, String note, Float points, Integer grade) {
		this.index = index;
		this.name = name;
		this.note = note;
		this.points = points;
		this.grade = grade;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Float getPoints() {
		return points;
	}
	public void setPoints(Float points) {
		this.points = points;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
}
