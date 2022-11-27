package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bank.Bank;
import bank.Savings;

public class AccountManage extends JPanel {

	static HistoryTable historyTable;
	JButton check;

	static JTextArea textArea = new JTextArea();
	static JTextArea textArea_1 = new JTextArea();

	JButton depwith;
	JButton transfer;

	static Savings account;

	public AccountManage() {
		setLayout(null);

		textArea.setBounds(114, 65, 200, 21);
		textArea.setColumns(10);
		textArea.setEditable(false);
		textArea.setBorder(null);
		textArea_1.setBounds(114, 105, 200, 21);
		textArea_1.setColumns(10);
		textArea_1.setEditable(false);
		textArea_1.setBorder(null);

		add(textArea);
		add(textArea_1);

		JLabel lblNewLabel = new JLabel("계좌번호");
		lblNewLabel.setBounds(39, 59, 127, 26);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("금액");
		lblNewLabel_1.setBounds(39, 95, 127, 39);
		add(lblNewLabel_1);

		depwith = new JButton("입출금");
		depwith.setBounds(39, 156, 139, 45);
		add(depwith);

		transfer = new JButton("송금");
		transfer.setBounds(207, 156, 139, 45);
		add(transfer);

		historyTable = new HistoryTable();
		JScrollPane bottom = new JScrollPane(historyTable.table);
		bottom.setBounds(0, 274, 385, 295);
		add(bottom);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 234, 51, 26);
		add(comboBox);
		

		depwith.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					WindowBuilder.card.show(WindowBuilder.bankingPane, "입출금");

			}
		});
		
		transfer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					WindowBuilder.card.show(WindowBuilder.bankingPane, "송금");

			}
		});

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
