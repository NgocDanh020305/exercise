
package bai_tap_lon;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SeatingArrangementGUI extends JFrame {
	private SeatingArrangement arrangement;
	private JTextArea outputArea;
	private JTextField mssvField;

	public SeatingArrangementGUI() {
		setTitle("Exam Seating Arrangement System");
		setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

// Sample Data
		List<ExamRoom> rooms = Arrays.asList(new ExamRoom("P101", 3, 4), new ExamRoom("P102", 2, 5));

		List<Student> students = Arrays.asList(new Student("SV001", "Nguyen Van A", "CTK42"),
				new Student("SV002", "Tran Thi B", "CTK42"), new Student("SV003", "Le Van C", "CTK43"),
				new Student("SV004", "Pham Thi D", "CTK44"), new Student("SV005", "Hoang Van E", "CTK43"),
				new Student("SV006", "Do Thi F", "CTK44"), new Student("SV007", "Vu Van G", "CTK42"),
				new Student("SV008", "Bui Thi H", "CTK45"), new Student("SV009", "Ngo Van I", "CTK45"),
				new Student("SV010", "Hoang Thi K", "CTK46"));

		arrangement = new SeatingArrangement(rooms);
		arrangement.arrangeSeats(students);

// Top panel: MSSV search
		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("Nhập MSSV:"));
		mssvField = new JTextField(10);
		JButton searchButton = new JButton("Tìm chỗ ngồi");
		topPanel.add(mssvField);
		topPanel.add(searchButton);

// Center panel: Output
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(outputArea);

// Bottom panel: Show layout
		JButton showLayoutButton = new JButton("Hiển thị sơ đồ chỗ ngồi");

// Action listeners
		searchButton.addActionListener(e -> {
			String mssv = mssvField.getText().trim();
			String result = arrangement.findSeat(mssv);
			outputArea.setText(result);
		});

		showLayoutButton.addActionListener(e -> {
			outputArea.setText("");
			for (ExamRoom room : rooms) {
				outputArea.append("Phòng " + room.getRoomID() + ":\n");
				Student[][] map = room.getSeatMap();
				for (int r = 0; r < map.length; r++) {
					for (int c = 0; c < map[0].length; c++) {
						if (map[r][c] != null) {
							outputArea.append(map[r][c].getStudentID() + "\t");
						} else {
							outputArea.append("Empty\t");
						}
					}
					outputArea.append("\n");
				}
				outputArea.append("\n");
			}
		});
		add(topPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(showLayoutButton, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			SeatingArrangementGUI gui = new SeatingArrangementGUI();
			gui.setVisible(true);
		});
	}
}
