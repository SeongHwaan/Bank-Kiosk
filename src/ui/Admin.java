package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Admin extends JFrame {
	static SearchPane top;
	static UserTable middle;
	static BottomPane bottom;

	public Admin() {
		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout()); // 전체를 관리할 컨텐트팬

		bottom = new BottomPane();
		bottom.init();
		middle = new UserTable();
		middle.init();
		top = new SearchPane(middle);
		top.init();

		// 상단 검색창
		pane.add(top, BorderLayout.NORTH);

		// 중간 테이블
		JScrollPane center = new JScrollPane(middle.table);
		pane.add(center, BorderLayout.CENTER);

		// 하단 관리창
		pane.add(bottom, BorderLayout.SOUTH);

		setTitle("사용자 계정 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(pane);

		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(Admin::new);
	}
}
