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

import bank.Bank;
import bank.Savings;
import bank.User;

public class AccountTransferPanel extends JPanel implements ActionListener {

	JPanel bottomPane;
	JPanel textPane;
	JPanel buttonPane;
	
	JTextField accountInput;
	JTextField cashInput;

	AccountTable accountTable;
	
	JButton checkAccount;
	JButton transfer;

	JLabel name;
	JLabel amount;
	
	User transferUser;
	Savings transferAccount;
	
	public AccountTransferPanel(AccountTable accountTable) {

		setLayout(new BorderLayout());

		bottomPane = new JPanel(new BorderLayout());
		textPane = new JPanel(new GridLayout(2,1));// 테스트
		buttonPane = new JPanel(new FlowLayout());

		this.accountTable = accountTable;
		
		name = new JLabel("계좌명");
		amount = new JLabel("이체금액");
		
		accountInput = new JTextField("", 25);
		cashInput = new JTextField("", 25);

		checkAccount = new JButton("계좌확인");
		transfer = new JButton("이체");

		checkAccount.addActionListener(this);
		transfer.addActionListener(this);

		textPane.add(name);
		textPane.add(amount);
		textPane.add(accountInput);
		textPane.add(cashInput);

		buttonPane.add(checkAccount);
		buttonPane.add(transfer);

		bottomPane.add(textPane, BorderLayout.WEST);
		bottomPane.add(buttonPane, BorderLayout.EAST);

		add(bottomPane, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("이체")) {
			Savings selectedAccount = Bank.accountMgr.list.get(AccountTable.selectedIndex);
			try {
				GUIMain.bank.transfer(selectedAccount, transferAccount, cashInput.getText());
				accountInput.setText("");
				cashInput.setText("");
				accountTable.update();
					
			}catch (Exception e1){
				System.out.println("계좌 확인 및 금액을 입력해주세요.");
			}
		}

		if (e.getActionCommand().equals("계좌확인")) {
			try {
				transferAccount = GUIMain.bank.findAccount(accountInput.getText());
				transferUser = GUIMain.bank.findUser(transferAccount.userId);
				//dialog로 사용자 이름 출력 기능 추가 
				
			} catch (NullPointerException e1) {
				System.out.println("can't find알 수 없는 계좌번호 또는 사용자를 찾을 수 없습니다. 다시 입력해주세요.");
			} 
		}
	}
}
