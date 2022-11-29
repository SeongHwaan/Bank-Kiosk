package ui;

import bank.Bank;

import java.awt.BorderLayout;
import javax.swing.*;

public class TopBarPanel extends JPanel {
	// JLabel name;
	// JButton bankIcon;
	JButton logout;
	JButton mainButton = new JButton("Logo");

	TopBarPanel(String name) {

		mainButton = new JButton("Logo");
		JPanel rightPane = new JPanel();
		JLabel userName = new JLabel(name);
		logout = new JButton("로그아웃");

		setLayout(new BorderLayout(0, 0));

		add(mainButton, BorderLayout.WEST);
		add(rightPane, BorderLayout.EAST);

		rightPane.add(userName);
		rightPane.add(logout);

		// setBackground(new Color(32, 32, 44));

		mainButton.addActionListener(e -> WindowBuilder.card.show(WindowBuilder.bankingPane, "메인화면"));
		logout.addActionListener(e -> {
			javax.swing.SwingUtilities.invokeLater(LoginFrame::main);
			Bank.loginUser = null;
			Bank.loginAccountList.clear();
			WindowBuilder.mainFrame.setVisible(false);
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
		// name = new JLabel("홍길동님");
		//

	}
}
