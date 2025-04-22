package bai_tap_lon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatingArrangement {
	private List<ExamRoom> rooms;
	private Map<String, int[]> studentSeatMap; // lưu vị trí chỗ ngồi của sinh viên: key = studentID, value =
												// [roomIndex, row, col]

	public SeatingArrangement(List<ExamRoom> rooms) {
		this.rooms = rooms;
		this.studentSeatMap = new HashMap<>();
	}

	// Hàm kiểm tra sinh viên cùng lớp có ngồi gần nhau không
	private boolean isNeighborSameClass(ExamRoom room, int row, int col, String className) {
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			if (nr >= 0 && nr < room.getSeatMap().length && nc >= 0 && nc < room.getSeatMap()[0].length) {
				Student neighbor = room.getSeatMap()[nr][nc];
				if (neighbor != null && neighbor.getClassName().equals(className)) {
					return true;
				}
			}
		}
		return false;
	}

	// Thuật toán sắp xếp chỗ ngồi
	public void arrangeSeats(List<Student> students) {
		int roomIndex = 0;
		ExamRoom currentRoom = rooms.get(roomIndex);
		int rows = currentRoom.getSeatMap().length;
		int cols = currentRoom.getSeatMap()[0].length;

		for (Student s : students) {
			boolean seated = false;
			while (!seated) {
				List<int[]> availableSeats = currentRoom.getAvailableSeats();
				if (availableSeats.isEmpty()) {
					// Chuyển sang phòng tiếp theo nếu còn phòng
					roomIndex++;
					if (roomIndex >= rooms.size()) {
						System.out.println("Không đủ chỗ ngồi cho tất cả sinh viên.");
						return;
					}
					currentRoom = rooms.get(roomIndex);
					rows = currentRoom.getSeatMap().length;
					cols = currentRoom.getSeatMap()[0].length;
					continue;
				}
				// Tìm ghế phù hợp không có sinh viên cùng lớp ngồi cạnh
				boolean foundSeat = false;
				for (int[] seat : availableSeats) {
					int r = seat[0];
					int c = seat[1];
					if (!isNeighborSameClass(currentRoom, r, c, s.getClassName())) {
						currentRoom.assignStudent(s, r, c);
						studentSeatMap.put(s.getStudentID(), new int[] { roomIndex, r, c });
						foundSeat = true;
						seated = true;
						break;
					}
				}
				// Nếu không tìm được ghế thoả mãn, sẽ phải gán tạm ghế đầu tiên (bất chấp)
				if (!foundSeat) {
					int[] seat = availableSeats.get(0);
					currentRoom.assignStudent(s, seat[0], seat[1]);
					studentSeatMap.put(s.getStudentID(), new int[] { roomIndex, seat[0], seat[1] });
					seated = true;
				}
			}
		}
	}

	// Tìm vị trí chỗ ngồi của sinh viên theo MSSV
	public String findSeat(String studentID) {
		if (!studentSeatMap.containsKey(studentID)) {
			return "Không tìm thấy sinh viên có MSSV: " + studentID;
		}
		int[] info = studentSeatMap.get(studentID);
		ExamRoom room = rooms.get(info[0]);
		return "Sinh viên " + studentID + " ngồi phòng " + room.getRoomID() + " tại vị trí hàng " + info[1] + ", cột "
				+ info[2];
	}

	// Xuất sơ đồ chỗ ngồi cho tất cả phòng
	public void exportAllLayouts() {
		for (ExamRoom room : rooms) {
			room.exportLayout();
			System.out.println();
		}
	}
}
