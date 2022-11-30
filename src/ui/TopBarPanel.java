package ui;

import bank.Bank;

import java.awt.BorderLayout;
import javax.swing.*;

public class TopBarPanel extends JPanel {
	// JLabel name;
	// JButton bankIcon;
	ButtonDesign logout;
	ButtonDesign mainButton;

	TopBarPanel(String name) {
		mainButton = new ButtonDesign("KGU");
		JPanel rightPane = new JPanel();
		JLabel userName = new JLabel(name);
		logout = new ButtonDesign("로그아웃");

		setLayout(new BorderLayout(0, 0));

		add(mainButton, BorderLayout.WEST);
		add(rightPane, BorderLayout.EAST);

		rightPane.add(userName);
		rightPane.add(logout);

		// setBackground(new Color(32, 32, 44));

		mainButton.addActionListener(e -> WindowBuilder.card.show(WindowBuilder.bankingPane, "메인화면"));
		logout.addActionListener(e -> {
			WindowBuilder.mainFrame.dispose();
			javax.swing.SwingUtilities.invokeLater(LoginFrame::main);
			Bank.loginUser = null;
			Bank.loginAccountList.clear();
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
