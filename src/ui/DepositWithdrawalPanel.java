package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

import bank.Bank;
import bank.Savings;

public class DepositWithdrawalPanel extends JPanel implements ActionListener {
	JTextField cashInput = new JTextField("", 20);
	ButtonDesign deposit = new ButtonDesign("입금");
	ButtonDesign withdrawal = new ButtonDesign("출금");
	Savings account;
	JPanel gblPanel = new JPanel();
	GridBagConstraints[] gbc = new GridBagConstraints[9];

	public DepositWithdrawalPanel() {
		setLayout(new GridBagLayout());
		gblPanel.setLayout(new GridBagLayout());

		for (int i = 0; i < 9; i++) {
			gbc[i] = new GridBagConstraints();
		}

		JLabel title = new JLabel("얼마나 보낼까요?");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("", Font.BOLD, 28));

		gbc[3].gridx = 0;
		gbc[3].gridy = 0;
		gbc[3].fill = GridBagConstraints.BOTH;
		gbc[3].ipady = 50;
		add(title, gbc[3]);

		deposit.addActionListener(this);
		withdrawal.addActionListener(this);

		gbc[0].gridx = 0;
		gbc[0].gridy = 0;
		gbc[0].weightx = 2;
		gbc[0].fill = GridBagConstraints.BOTH;
		gblPanel.add(cashInput, gbc[0]);

		gbc[1].gridx = 1;
		gbc[1].gridy = 0;
		gbc[1].weightx = 1;
		gbc[1].fill = GridBagConstraints.BOTH;
		gblPanel.add(deposit, gbc[1]);

		gbc[2].gridx = 2;
		gbc[2].gridy = 0;
		gbc[2].weightx = 1;
		gbc[2].fill = GridBagConstraints.BOTH;
		gblPanel.add(withdrawal, gbc[2]);

		gbc[4].gridx = 0;
		gbc[4].gridy = 1;
		gbc[4].weighty = 2;
		add(gblPanel, gbc[4]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("입금") || e.getActionCommand().equals("출금")) {
			try {
				account = Bank.loginAccountList.get(MyAccountList.selectedIndex);
				WindowBuilder.bank.deposit(account, cashInput.getText());
				cashInput.setText("");
				AccountManage.update();
			} catch (Exception e1) {
				System.out.print("계좌를 선택 및 금액을 입력하세요");
			}
		}
	}
}