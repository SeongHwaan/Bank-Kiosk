package ui;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import bank.Bank;
import bank.Savings;
import bank.User;

public class AccountTransferPanel extends JPanel implements ActionListener {

	JPanel bottomPane;
	JPanel textPane;
	JPanel buttonPane;
	
	JTextField accountInput;
	JTextField cashInput;

	JButton transfer;

	JLabel name;
	JLabel amount;
	
	User transferUser;
	Savings transferAccount;
	
	public AccountTransferPanel() {

		setLayout(new BorderLayout());

		bottomPane = new JPanel(new BorderLayout());
		textPane = new JPanel(new GridLayout(2,1));// 테스트
		buttonPane = new JPanel(new FlowLayout());

		
		name = new JLabel("계좌명");
		amount = new JLabel("이체금액");
		
		accountInput = new JTextField("",10);
		cashInput = new JTextField("", 10);

		transfer = new JButton("이체");

		transfer.addActionListener(this);

		textPane.add(name);
		textPane.add(amount);
		textPane.add(accountInput);
		textPane.add(cashInput);

		buttonPane.add(transfer);

		bottomPane.add(textPane, BorderLayout.WEST);
		bottomPane.add(buttonPane, BorderLayout.EAST);

		add(bottomPane, BorderLayout.SOUTH);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("이체")) {
			Savings selectedAccount = Bank.loginAccountList.get(MyAccountList.selectedIndex);

			try {
				transferAccount = WindowBuilder.bank.findAccount(accountInput.getText());
				transferUser = WindowBuilder.bank.findUser(transferAccount.userId);
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null,
						"계좌번호 또는 사용자를 찾을 수 없습니다.",
						"오류", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (transferAccount == selectedAccount) {
				JOptionPane.showMessageDialog(null,
						"출금계좌와 동일한 계좌를 입력했습니다.",
						"오류", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (cashInput.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"금액을 입력해주세요.",
						"오류", JOptionPane.ERROR_MESSAGE);
				return;
			}

			WindowBuilder.bank.transfer(selectedAccount, transferAccount, cashInput.getText());
			accountInput.setText("");
			cashInput.setText("");
			AccountManage.update();
		}
	}
}
