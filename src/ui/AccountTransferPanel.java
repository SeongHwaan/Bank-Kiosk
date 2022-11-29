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
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("", Font.BOLD, 28));

		name = new JLabel("계좌번호");
		accountInput = new JTextField("", 10);

		amount = new JLabel("이체금액");
		cashInput = new JTextField("", 10);

		transfer = new JButton("이체");
		transfer.addActionListener(this);

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

		// gbc[5].gridx = 0;
		// gbc[5].gridy = 3;
		// gbc[5].weightx = 1;
		// gbc[5].fill = GridBagConstraints.BOTH;
		// gblAccount.add(checkAccount, gbc[5]);

		gbc[6].gridx = 1;
		gbc[6].gridy = 3;
		gbc[6].weightx = 1;
		gbc[6].fill = GridBagConstraints.BOTH;
		gblAccount.add(transfer, gbc[6]);

		gbc[0].gridx = 0;
		gbc[0].gridy = 0;
		gbc[0].weightx = 1;
		gbc[0].fill = GridBagConstraints.BOTH;
		gbc[0].ipady = 50;
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
