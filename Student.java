package bai_tap_lon;

public class Student {
	private String studentID;
	private String name;
	private String className;

	public Student(String studentID, String name, String className) {
		this.studentID = studentID;
		this.name = name;
		this.className = className;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
