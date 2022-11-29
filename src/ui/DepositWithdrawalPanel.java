package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

import bank.Bank;
import bank.Savings;

public class DepositWithdrawalPanel extends JPanel implements ActionListener {
	JTextField cashInput = new JTextField("", 20);
	JButton deposit = new JButton("입금");
	JButton withdrawal = new JButton("출금");
	Savings account;
	GridBagConstraints[] gbc = new GridBagConstraints[9];
	
	
	public DepositWithdrawalPanel() {
		setLayout(new GridBagLayout());

		for (int i = 0; i < 9; i++) {
			gbc[i] = new GridBagConstraints();
		}

		deposit.addActionListener(this);
		withdrawal.addActionListener(this);

		gbc[0].gridx = 0;
		gbc[0].gridy = 0;
		gbc[0].weightx =2;
		gbc[0].fill = GridBagConstraints.BOTH;
		add(cashInput, gbc[0]);

		gbc[1].gridx = 1;
		gbc[1].gridy = 0;
		gbc[1].weightx =1;
		gbc[1].fill = GridBagConstraints.BOTH;
		add(deposit, gbc[1]);

		gbc[2].gridx = 2;
		gbc[2].gridy = 0;
		gbc[2].weightx =1;
		gbc[2].fill = GridBagConstraints.BOTH;
		add(withdrawal, gbc[2]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("입금")) {
			try {
				account = Bank.loginAccountList.get(MyAccountList.selectedIndex);
				WindowBuilder.bank.deposit(account, cashInput.getText());
				cashInput.setText("");
				AccountManage.update();
			} catch (Exception e1) {
				System.out.print("계좌를 선택 및 금액을 입력하세요");
			}
		}
		if (e.getActionCommand().equals("출금")) {
			try {
				account = Bank.loginAccountList.get(MyAccountList.selectedIndex);
				WindowBuilder.bank.withdraw(account, cashInput.getText());
				cashInput.setText("");
				AccountManage.update();
			} catch (Exception e2) {
				System.out.print("계좌 선택 및 금액을 입력하세요");
			}
		}
	}

}