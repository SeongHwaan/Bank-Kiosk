package ui;

import java.awt.*;
import javax.swing.*;

import bank.Bank;
import bank.User;

public class GUIMain {

	static JFrame mainFrame;
	static CardLayout card;
	JPanel bankingPane;
	// TopBarPanel top = new TopBarPanel();
	public static Bank bank;
	User user;

	JPanel bottomPane;

	MyAccountList myAccount = new MyAccountList();

	// MyAccountList2 a = new MyAccountList2();
	// // MyAccountList2 a = new MyAccountList2();

	// private void createAndShowMain() {
	//
	// //user = (User) Bank.userMgr.list.get(1);
	//
	// //index bound error 발생
	// mainFrame = new JFrame("경기은행");
	// mainFrame.setLayout(new BorderLayout());
	// mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// mainFrame.setLocationRelativeTo(null);
	//
	// bottomPane = new JPanel();
	//
	//
	// setupTopBarPane();
	// setupBankingPane();
	// setBottomPane();
	//
	// mainFrame.add(top, BorderLayout.NORTH);
	// mainFrame.add(bankingPane, BorderLayout.CENTER);
	// mainFrame.add(bottomPane, BorderLayout.SOUTH);
	//
	//
	// //mainFrame.setSize(400, 700);
	// //mainFrame.setResizable(false);
	// mainFrame.setVisible(true);
	// mainFrame.pack();
	// }
	//
	// private void setupTopBarPane() {
	// top.setupTopBarPanel();
	//// top.button.addActionListener(new ActionListener() {
	//// public void actionPerformed(ActionEvent e) {
	//// GUIMain.card.show(bankingPane, "메인화면");
	//// }
	//// });
	//// }
	//
	// AccountTable accountTable = new AccountTable();
	// DepositWithdrawalPanel depwith = new DepositWithdrawalPanel(accountTable);
	// AccountTransferPanel transfer = new AccountTransferPanel(accountTable);
	//
	//
	// AccountManage check = new AccountManage();
	//
	//
	// AssetManagementPanel manage = new AssetManagementPanel();
	//
	//
	// private void setupBankingPane() {
	// bankingPane = new JPanel(new CardLayout());
	//
	// bankingPane.add(myAccount, "메인화면");
	// bankingPane.add(depwith, "입출금");
	// bankingPane.add(transfer, "계좌이체");
	// bankingPane.add(check, "계좌조회");
	// bankingPane.add(manage, "자산관리");
	//
	// }
	//
	// private void setBottomPane() {
	// bottomPane.setBorder(null);
	//
	// JButton depositWithdrawal = new JButton("입출금");
	// JButton accountTransfer = new JButton("계좌 이체");
	// JButton accountCheck = new JButton("계좌 조회");
	// JButton AssetManage = new JButton("자산 관리");
	//
	// JScrollPane accountPanel = new JScrollPane(accountTable.table);
	// accountPanel.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
	//
	// bottomPane.add(depositWithdrawal);
	// bottomPane.add(accountTransfer);
	// bottomPane.add(accountCheck);
	// bottomPane.add(AssetManage);
	//
	// card = (CardLayout) bankingPane.getLayout();
	//
	// depositWithdrawal.addActionListener(new ActionListener() {
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// card.show(bankingPane, "입출금");
	//
	// depwith.add(accountPanel, BorderLayout.NORTH);
	// }
	// });
	// accountTransfer.addActionListener(new ActionListener() {
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// card.show(bankingPane, "계좌이체");
	//
	// transfer.add(accountPanel, BorderLayout.NORTH);
	// }
	// });
	// accountCheck.addActionListener(new ActionListener() {
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// card.show(bankingPane, "계좌조회");
	//
	// }
	// });
	// AssetManage.addActionListener(new ActionListener() {
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// card.show(bankingPane, "자산관리");
	// }
	// });
	//
	// }
	//
	// public static void main(String[] args) {
	// bank = new Bank();
	// bank.run();
	// startGUI();
	// }
	//
	// public static void startGUI() {
	// javax.swing.SwingUtilities.invokeLater(new Runnable() {
	// public void run() {
	// GUIMain main = new GUIMain();
	// main.createAndShowMain();
	// }
	// });
	// }
}
