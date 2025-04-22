package bai_tap_lon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamSession {
	private String subject;
	private Date dateTime;
	private List<Student> students;

	public ExamSession(String subject, Date dateTime) {
		this.subject = subject;
		this.dateTime = dateTime;
		this.students = new ArrayList<>();
	}

	public void addStudent(Student s) {
		students.add(s);
	}

	public List<Student> getStudentList() {
		return students;
	}
}
