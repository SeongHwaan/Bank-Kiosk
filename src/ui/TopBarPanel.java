package ui;

import java.awt.BorderLayout;
import javax.swing.*;

public class TopBarPanel extends JPanel {
	// JLabel name;
	// JButton bankIcon;
	// JButton logout;
	JButton mainButton = new JButton("Logo");
	JPanel rightPane = new JPanel();
	JLabel userName = new JLabel("홍길동");

	TopBarPanel(String name) {

		mainButton = new JButton("Logo");
		JPanel rightPane = new JPanel();
		JLabel userName = new JLabel(name);

		setLayout(new BorderLayout(0, 0));

		add(mainButton, BorderLayout.WEST);
		add(rightPane, BorderLayout.EAST);

		rightPane.add(userName);

		// setBackground(new Color(32, 32, 44));

		mainButton.addActionListener(e -> WindowBuilder.card.show(WindowBuilder.bankingPane, "메인화면"));

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
