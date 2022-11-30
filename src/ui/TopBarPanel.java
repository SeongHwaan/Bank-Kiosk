package ui;

import bank.Bank;

import java.awt.BorderLayout;
import javax.swing.*;

public class TopBarPanel extends JPanel {
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

		mainButton.addActionListener(e -> WindowBuilder.card.show(WindowBuilder.bankingPane, "메인화면"));
		logout.addActionListener(e -> {
			WindowBuilder.mainFrame.dispose();
			javax.swing.SwingUtilities.invokeLater(LoginFrame::main);
			MyAccountList.selectedIndex = 0;
			Bank.loginUser = null;
			Bank.loginAccountList.clear();
		});
	}
}
