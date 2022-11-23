package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bank.Bank;
import bank.Savings;
import bank.User;

public class GUIMain {

	static JFrame mainFrame = new JFrame("경기은행");
	static CardLayout card;
	JPanel topBarPane;
	JPanel bankingPane;	
	
	public static Bank bank;
	User user;
	
	private void createAndShowMain() {

		//user = Bank.userMgr.list.get(1);
		//index bound error 발생
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel(new BorderLayout());		
		setupTopBarPane();
		setupBankingPane();

		mainPanel.add(topBarPane,BorderLayout.NORTH);
		mainPanel.add(bankingPane,BorderLayout.CENTER);

		mainFrame.getContentPane().add(mainPanel);
		//mainFrame.pack();
		mainFrame.setSize(900,600);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);

	}

	TopBarPanel top = new TopBarPanel();
	private void setupTopBarPane() {
		topBarPane = new JPanel();
		top.setupTopBarPanel();
        top.bankIcon.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	GUIMain.card.show(bankingPane, "메인화면");
           }
        });
        topBarPane.add(top);
	}
	
	AccountTable accountTable = new AccountTable();
	DepositWithdrawalPanel depwith = new DepositWithdrawalPanel(accountTable);
	AccountTransferPanel transfer = new AccountTransferPanel(accountTable);
	AccountCheckPanel check = new AccountCheckPanel();
	AssetManagementPanel manage = new AssetManagementPanel();
	
	private void setupBankingPane() {
		bankingPane = new JPanel(new CardLayout());
		JPanel mainScreenPanel = new JPanel(new GridLayout(2,1));
		
		JScrollPane accountPanel = new JScrollPane(accountTable.table);
		
		JButton depositWithdrawal = new JButton("입출금");
		JButton accountTransfer = new JButton("계좌 이체");
		JButton accountCheck = new JButton("계좌 조회");
		JButton AssetManage = new JButton("자산 관리");
		
		mainScreenPanel.add(depositWithdrawal);
		mainScreenPanel.add(accountTransfer);
		mainScreenPanel.add(accountCheck);
		mainScreenPanel.add(AssetManage);
		
		bankingPane.add(mainScreenPanel, "메인화면");
		bankingPane.add(depwith, "입출금");
		bankingPane.add(transfer, "계좌이체");
		bankingPane.add(check, "계좌조회");
		bankingPane.add(manage, "자산관리");
		
		card =  (CardLayout) bankingPane.getLayout();
		
		depositWithdrawal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(bankingPane, "입출금");

				depwith.add(accountPanel, BorderLayout.NORTH);
			}
		});
		accountTransfer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(bankingPane, "계좌이체");

				transfer.add(accountPanel, BorderLayout.NORTH);
			}
		});
		accountCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(bankingPane, "계좌조회");
				
				check.add(accountPanel, BorderLayout.NORTH);
			}
		});
		AssetManage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(bankingPane, "자산관리");		
			}
		});
	}
	
	public static void main(String[] args) {
		bank = new Bank();
		bank.run();
		startGUI();
	}

	public static void startGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUIMain main = new GUIMain();
				main.createAndShowMain();
			}
		});
	}
}
