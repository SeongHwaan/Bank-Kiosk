package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bank.Bank;
import bank.Savings;
import bank.User;

public class AccountTransferPanel extends JPanel implements ActionListener {
	GridBagConstraints[] gbc = new GridBagConstraints[9];

	JPanel gblAccount = new JPanel();

	JTextField accountInput;
	JTextField cashInput;

	JButton checkAccount;
	JButton transfer;

	JLabel name;
	JLabel amount;

	User transferUser;
	Savings transferAccount;

	public AccountTransferPanel() {
		setLayout(new GridBagLayout());
		gblAccount.setLayout(new GridBagLayout());

		for (int i = 0; i < 9; i++) {
			gbc[i] = new GridBagConstraints();
		}

		JLabel title = new JLabel("어떤 계좌로 보낼까요?");
		title.setFont(new Font("", Font.BOLD, 28));

		name = new JLabel("계좌번호");
		accountInput = new JTextField("", 10);

		amount = new JLabel("이체금액");
		cashInput = new JTextField("", 10);

		checkAccount = new JButton("계좌확인");
		transfer = new JButton("이체");

		gbc[1].gridx = 0;
		gbc[1].gridy = 1;
		gblAccount.add(name, gbc[1]);

		gbc[2].gridx = 1;
		gbc[2].gridy = 1;
		gbc[2].weightx = 1;
		gbc[2].fill = GridBagConstraints.BOTH;
		gblAccount.add(accountInput, gbc[2]);

		gbc[3].gridx = 0;
		gbc[3].gridy = 2;
		gblAccount.add(amount, gbc[3]);

		gbc[4].gridx = 1;
		gbc[4].gridy = 2;
		gbc[4].weightx = 1;
		gbc[4].fill = GridBagConstraints.BOTH;
		gblAccount.add(cashInput, gbc[4]);

		gbc[5].gridx = 0;
		gbc[5].gridy = 3;
		gbc[5].weightx = 1;
		gbc[5].fill = GridBagConstraints.BOTH;
		gblAccount.add(checkAccount, gbc[5]);

		gbc[6].gridx = 1;
		gbc[6].gridy = 3;
		gbc[6].weightx = 1;
		gbc[6].fill = GridBagConstraints.BOTH;
		gblAccount.add(transfer, gbc[6]);

		gbc[0].gridx = 0;
		gbc[0].gridy = 0;
		gbc[0].weightx = 1;
		gbc[0].fill = GridBagConstraints.BOTH;
		add(title, gbc[0]);

		gbc[7].gridx = 0;
		gbc[7].gridy = 1;
		gbc[7].weightx = 1;
		gbc[7].weighty = 2;
		gbc[7].fill = GridBagConstraints.BOTH;
		add(gblAccount, gbc[7]);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("이체")) {
			Savings selectedAccount = Bank.accountMgr.list.get(MyAccountList.selectedIndex);
			try {
				WindowBuilder.bank.transfer(selectedAccount, transferAccount, cashInput.getText());
				accountInput.setText("");
				cashInput.setText("");
				AccountManage.update();

			} catch (Exception e1) {
				System.out.println("계좌 확인 및 금액을 입력해주세요.");
			}
		}

		if (e.getActionCommand().equals("계좌확인")) {
			try {
				transferAccount = WindowBuilder.bank.findAccount(accountInput.getText());
				transferUser = WindowBuilder.bank.findUser(transferAccount.userId);
				// dialog로 사용자 이름 출력 기능 추가

			} catch (NullPointerException e1) {
				System.out.println("알 수 없는 계좌번호 또는 사용자를 찾을 수 없습니다. 다시 입력해주세요.");
			}
		}
	}
}
