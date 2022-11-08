package ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BottomPane extends JPanel implements ActionListener {
	JTextField[] editBox;

	void init() {
		// 위아래 설정
		editBox = new JTextField[6];
		setLayout(new GridLayout(2, 1));
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		for (int i = 0; i < 6; i++) {
			editBox[i] = new JTextField("", 10);
			top.add(editBox[i]);
		}

		// 상단 레이아웃 배치
		JButton editBtn = new JButton("수정");
		editBtn.setActionCommand("수정");
		editBtn.addActionListener(this);
		top.add(editBtn);
		add(top);

		// 하단 레이아웃 배치
		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout());
		JButton[] buttons = new JButton[2];
		String[] btnTexts = { "추가", "삭제" };
		for (int i = 0; i < 2; i++) {
			buttons[i] = new JButton(btnTexts[i]);
			buttons[i].addActionListener(this);
			bottom.add(buttons[i]);
		}
		add(bottom);
	}

	// 튜플 클릭 시 하단 편집창에 데이터 표현
	void fillDataToBox(String[] rowTexts) {
		for (int i = 0; i < rowTexts.length; i++)
			editBox[i].setText(rowTexts[i]);
	}

	// 버튼 클릭 액션 미구현
	public void actionPerformed(ActionEvent e) {
		// if (e.getActionCommand().equals("수정")) { }
		// if (e.getActionCommand().equals("추가")) { }
		// if (e.getActionCommand().equals("삭제")) { }
	}
}