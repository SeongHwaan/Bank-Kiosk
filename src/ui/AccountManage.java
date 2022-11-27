package ui;

import javax.swing.*;
import java.awt.*;
import bank.Bank;
import bank.Savings;

public class AccountManage extends JPanel {
	static JPanel infoPanel = new JPanel();

	static HistoryTable historyTable;
	JButton check;

	static JTextArea textArea = new JTextArea();
	static JTextArea textArea_1 = new JTextArea();

	JButton depwith;
	JButton transfer;

	static Savings account;

	public AccountManage() {
		setLayout(new GridBagLayout());
		infoPanel.setLayout(new GridLayout(0, 2));
		GridBagConstraints[] gbc = new GridBagConstraints[3];

		for (int i = 0; i < 3; i++) {
			/* GridBagConstraints 초기화 */
			gbc[i] = new GridBagConstraints();
		}

		JLabel title = new JLabel("통장");
		title.setFont(new Font("", Font.BOLD, 28));

		JLabel lblNewLabel = new JLabel("계좌번호");
		lblNewLabel.setBounds(39, 59, 127, 26);

		textArea.setEditable(false);

		JLabel lblNewLabel_1 = new JLabel("금액");
		lblNewLabel_1.setBounds(39, 95, 127, 39);

		textArea_1.setEditable(false);

		depwith = new JButton("입출금");
		depwith.setBounds(39, 156, 139, 45);

		transfer = new JButton("송금");
		transfer.setBounds(207, 156, 139, 45);

		infoPanel.add(lblNewLabel);
		infoPanel.add(textArea);
		infoPanel.add(lblNewLabel_1);
		infoPanel.add(textArea_1);
		infoPanel.add(depwith);
		infoPanel.add(transfer);

		gbc[0].gridx = 0;
		gbc[0].gridy = 0;
		gbc[0].weightx = 1;
		gbc[0].fill = GridBagConstraints.BOTH;
		add(title, gbc[0]);

		gbc[1].gridx = 0;
		gbc[1].gridy = 1;
		gbc[1].weightx = 1;
		gbc[1].weighty = 1;
		gbc[1].fill = GridBagConstraints.BOTH;
		add(infoPanel, gbc[1]); // 부모 패널에 추가

		historyTable = new HistoryTable();
		JScrollPane bottom = new JScrollPane(historyTable.table);
		bottom.setBounds(0, 274, 385, 295);

		gbc[2].gridx = 0;
		gbc[2].gridy = 2;
		gbc[2].weightx = 1;
		gbc[2].weighty = 3;
		gbc[2].fill = GridBagConstraints.BOTH;
		add(bottom, gbc[2]);

		depwith.addActionListener(e -> WindowBuilder.card.show(WindowBuilder.bankingPane, "입출금"));
		transfer.addActionListener(e -> WindowBuilder.card.show(WindowBuilder.bankingPane, "송금"));

	}

	public static void update() {
		try {
			account = Bank.accountMgr.list.get(MyAccountList.selectedIndex);
			textArea.setText("");
			textArea.append(account.number);
			textArea_1.setText("");
			textArea_1.append("" + account.cash);
			historyTable.update();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
