package bai_tap_lon;

import java.util.ArrayList;
import java.util.List;

public class ExamRoom {
	private String roomID;
	private int capacity;
	private Student[][] seatMap;
	private int rows;
	private int cols;

	public ExamRoom(String roomID, int rows, int cols) {
		this.roomID = roomID;
		this.rows = rows;
		this.cols = cols;
		this.capacity = rows * cols;
		this.seatMap = new Student[rows][cols];
	}

	public String getRoomID() {
		return roomID;
	}

	public int getCapacity() {
		return capacity;
	}

	public Student[][] getSeatMap() {
		return seatMap;
	}

	// Gán sinh viên vào vị trí cụ thể
	public boolean assignStudent(Student s, int row, int col) {
		if (row < 0 || row >= rows || col < 0 || col >= cols)
			return false;
		if (seatMap[row][col] == null) {
			seatMap[row][col] = s;
			return true;
		}
		return false;
	}

	// Kiểm tra vị trí có trống không
	public boolean isSeatEmpty(int row, int col) {
		if (row < 0 || row >= rows || col < 0 || col >= cols)
			return false;
		return seatMap[row][col] == null;
	}

	// Lấy danh sách vị trí ghế trống
	public List<int[]> getAvailableSeats() {
		List<int[]> seats = new ArrayList<>();
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (seatMap[r][c] == null) {
					seats.add(new int[] { r, c });
				}
			}
		}
		return seats;
	}

	// Xuất sơ đồ chỗ ngồi
	public void exportLayout() {
		System.out.println("Room " + roomID + " seating layout:");
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (seatMap[r][c] != null) {
					System.out.print(seatMap[r][c].getStudentID() + "\t");
				} else {
					System.out.print("Empty\t");
				}
			}
			System.out.println();
		}
	}
}
