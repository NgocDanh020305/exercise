package bai_tap_lon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		// Tạo danh sách phòng thi
		ExamRoom room1 = new ExamRoom("P101", 3, 4); // 3 hàng, 4 cột
		ExamRoom room2 = new ExamRoom("P102", 2, 5);

		List<ExamRoom> rooms = Arrays.asList(room1, room2);

		// Tạo danh sách sinh viên
		List<Student> students = new ArrayList<>();
		students.add(new Student("SV001", "Nguyen Van A", "CTK42"));
		students.add(new Student("SV002", "Tran Thi B", "CTK42"));
		students.add(new Student("SV003", "Le Van C", "CTK43"));
		students.add(new Student("SV004", "Pham Thi D", "CTK44"));
		students.add(new Student("SV005", "Hoang Van E", "CTK43"));
		students.add(new Student("SV006", "Do Thi F", "CTK44"));
		students.add(new Student("SV007", "Vu Van G", "CTK42"));
		students.add(new Student("SV008", "Bui Thi H", "CTK45"));
		students.add(new Student("SV009", "Ngo Van I", "CTK45"));
		students.add(new Student("SV010", "Hoang Thi K", "CTK46"));

		// Khởi tạo SeatingArrangement
		SeatingArrangement seatingArrangement = new SeatingArrangement(rooms);

		// Sắp xếp chỗ ngồi
		seatingArrangement.arrangeSeats(students);

		// Xuất sơ đồ chỗ ngồi
		seatingArrangement.exportAllLayouts();

		// Tìm chỗ ngồi của sinh viên
		System.out.println(seatingArrangement.findSeat("SV005"));
	}
}
