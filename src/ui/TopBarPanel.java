package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopBarPanel extends JPanel {
	JLabel name;
	JButton bankIcon;
	JButton logout;
	JButton mainButton;
	JButton logoutButton;

	// ImageIcon bankLogo = new ImageIcon("src/images/bankLogo.png");

	void setup() {

		// 왼쪽
		mainButton = new JButton("main");

		// 오른쪽
		JPanel rightPane = new JPanel();
		JLabel userName = new JLabel("홍길동");
		logoutButton = new JButton("로그아웃");

		setLayout(new BorderLayout(0, 0));
		add(mainButton, BorderLayout.WEST);
		add(rightPane, BorderLayout.EAST);

		rightPane.add(userName);
		rightPane.add(logoutButton);

		// setBackground(new Color(32, 32, 44));

		mainButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowBuilder.card.show(WindowBuilder.bankingPane, "메인화면");
			}
		});

		// Image img = bankLogo.getImage();
		// Image changeImg = img.getScaledInstance(50,50, Image.SCALE_SMOOTH);
		// ImageIcon changeIcon = new ImageIcon(changeImg);
		//
		//
		//
		// bankIcon = new JButton( changeIcon );
		// bankIcon.setBorderPainted(false);
		// bankIcon.setOpaque(false);
		// bankIcon.setVerticalTextPosition(bankIcon.BOTTOM);
		// logout = new JButton("로그아웃");
		// name = new JLabel("홍길동님");
		//

	}
}
