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

	JPanel gblPanel = new JPanel();

	JTextField accountInput;
	JTextField cashInput;
	ButtonDesign transfer;

	JLabel name;
	JLabel amount;

	User transferUser;
	Savings transferAccount;

	public AccountTransferPanel() {
		setLayout(new GridBagLayout());
		gblPanel.setLayout(new GridBagLayout());

		for (int i = 0; i < 9; i++) {
			gbc[i] = new GridBagConstraints();
		}

		JLabel title = new JLabel("어떤 계좌로 보낼까요?");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("", Font.BOLD, 28));

		gbc[5].gridx = 0;
		gbc[5].gridy = 0;
		gbc[5].fill = GridBagConstraints.BOTH;
		gbc[5].ipady = 50;
		add(title, gbc[5]);

		transfer = new ButtonDesign("이체");
		transfer.addActionListener(this);

		name = new JLabel("계좌번호");
		accountInput = new JTextField("", 15);

		amount = new JLabel("이체금액");
		cashInput = new JTextField("", 15);

		gbc[0].gridx = 0;
		gbc[0].gridy = 1;
		gbc[0].gridwidth = 2;
		gbc[0].weightx = 1;
		gbc[0].fill = GridBagConstraints.BOTH;
		gblPanel.add(name, gbc[0]);

		gbc[1].gridx = 2;
		gbc[1].gridy = 1;
		gbc[1].weightx = 1;
		gbc[1].fill = GridBagConstraints.BOTH;
		gblPanel.add(accountInput, gbc[1]);

		gbc[2].gridx = 0;
		gbc[2].gridy = 2;
		gbc[2].gridwidth = 2;
		gbc[2].weightx = 1;
		gbc[2].fill = GridBagConstraints.BOTH;
		gblPanel.add(amount, gbc[2]);

		gbc[3].gridx = 2;
		gbc[3].gridy = 2;
		gbc[3].weightx = 1;
		gbc[3].fill = GridBagConstraints.BOTH;
		gblPanel.add(cashInput, gbc[3]);

		gbc[4].gridx = 0;
		gbc[4].gridy = 3;
		gbc[4].gridwidth = 3;
		gbc[4].weightx = 1;
		gbc[4].fill = GridBagConstraints.BOTH;
		gblPanel.add(transfer, gbc[4]);

		gbc[6].gridx = 0;
		gbc[6].gridy = 4;
		gbc[6].weighty = 2;
		add(gblPanel, gbc[6]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("이체")) {
			Savings selectedAccount = Bank.loginAccountList.get(MyAccountList.selectedIndex);

			if (selectedAccount.info.contains("적금")) {
				JOptionPane.showMessageDialog(null, "송금이 불가능한 계좌입니다.\n사유: 적금계좌", "오류", JOptionPane.WARNING_MESSAGE);
				return;
			}

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

			if (Integer.parseInt(cashInput.getText()) == 0) {
				JOptionPane.showMessageDialog(null, "0원을 입력하셨습니다.\n다시 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "송금이 완료되었습니다.", "완료", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
