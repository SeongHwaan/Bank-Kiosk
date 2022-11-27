package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import bank.Bank;
import bank.Savings;

public class DepositWithdrawalPanel extends JPanel implements ActionListener {

	JPanel bottomPane;
	JPanel buttonPanel;
	JTextField cashInput;
	JButton deposit;
	JButton withdrawal;

	public DepositWithdrawalPanel() {

		setLayout(new BorderLayout(20,20));

		bottomPane = new JPanel(new BorderLayout());


		cashInput = new JTextField("", 20);
		deposit = new JButton("입금");
		withdrawal = new JButton("출금");

		// 하단 레이아웃 배치
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(deposit);
		buttonPanel.add(withdrawal);

		deposit.addActionListener(this);
		withdrawal.addActionListener(this);

		bottomPane.add(cashInput, BorderLayout.WEST);
		bottomPane.add(buttonPanel, BorderLayout.EAST);

		add(bottomPane, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("입금")) {
			try {
				Savings selectedAccount = (Savings) Bank.loginAccountList.get(MyAccountList.selectedIndex);
				WindowBuilder.bank.deposit(selectedAccount, cashInput.getText());
				cashInput.setText("");
				AccountManage.update();
			} catch (Exception e1) {
				System.out.print("계좌를 선택 및 금액을 입력하세요");
			}
		}
		if (e.getActionCommand().equals("출금")) {
			try {
				Savings selectedAccount = (Savings) Bank.loginAccountList.get(MyAccountList.selectedIndex);
				WindowBuilder.bank.withdraw(selectedAccount, cashInput.getText());
				cashInput.setText("");
				AccountManage.update();
			} catch (Exception e2) {
				System.out.print("계좌 선택 및 금액을 입력하세요");
			}
		}
	}

}