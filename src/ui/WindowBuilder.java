package ui;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;

public class WindowBuilder {

	public static Bank bank;
	static JFrame mainFrame;
	static CardLayout card;
	static JPanel bankingPane;	
	
	TopBarPanel top;
	JPanel bottomPane;

	MyAccountList myAccount = new MyAccountList();
	
	void createAndShowMain() {
		mainFrame = new JFrame("경기은행");
		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
		top = new TopBarPanel();
		bottomPane = new JPanel();
		
		setupTopBarPane(bank.loginUser.name);
		setupBankingPane();
		setBottomPane();

		mainFrame.getContentPane().add(top, BorderLayout.NORTH);
		mainFrame.getContentPane().add(bankingPane, BorderLayout.CENTER);
		mainFrame.getContentPane().add(bottomPane, BorderLayout.SOUTH);
		
		mainFrame.setSize(400, 690);
		//mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(bankingPane);
		mainFrame.setVisible(true);
		//mainFrame.pack();
	}

	private void setupTopBarPane(String name) {
		top.setupTopBarPanel(name);
        top.mainButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
//        	WindowBuilder.card.show(bankingPane, "메인화면");
           }
        });
	}
	
	AccountTable accountTable = new AccountTable();
	
	AccountManage check = new AccountManage();
	BankProduct product = new BankProduct();
	
	AccountTransferPanel t = new AccountTransferPanel();
	
	DepositWithdrawalPanel d = new DepositWithdrawalPanel();
	
		
	private void setupBankingPane() {
		bankingPane = new JPanel(new CardLayout());
		
		bankingPane.add(myAccount, "메인화면");
		bankingPane.add(check, "계좌관리");
		bankingPane.add(product, "은행상품");
		bankingPane.add(d, "입출금");
		bankingPane.add(t, "송금");
	}
	
	private void setBottomPane() {
		bottomPane.setBorder(null);
		
		ButtonDesign manageButton = new ButtonDesign("계좌관리");
		ButtonDesign productButton = new ButtonDesign("은행상품");
		ButtonDesign createButton = new ButtonDesign("계좌개설");

		JScrollPane accountPanel = new JScrollPane(accountTable.table);
		accountPanel.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		
		bottomPane.add(manageButton);
		bottomPane.add(productButton);
		bottomPane.add(createButton);
		
		card =  (CardLayout) bankingPane.getLayout();
		
		manageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(bankingPane, "계좌관리");
			}
		});
		
		productButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(bankingPane, "은행상품");		
			}
		});
	}
	
	public static void main(String[] args) {
		bank = new Bank();
		bank.run();
		startLogin();
	}

	public static void startLogin() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LoginFrame.main();
			}
		});
	}
	
	public static void startGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				WindowBuilder main = new WindowBuilder();
				main.createAndShowMain();
			}
		});
	}
}
