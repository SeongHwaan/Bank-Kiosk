package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bank.Bank;

public class WindowBuilder {

	public static Bank bank;
	static JFrame mainFrame;
	static CardLayout card;
	static JPanel bankingPane;

	TopBarPanel topBar;
	BottomBarPanel bottomBar;

	MyAccountList myAccount = new MyAccountList();

	private void createAndShowMain() {
		mainFrame = new JFrame("경기은행");
		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		topBar = new TopBarPanel();
		bottomBar = new BottomBarPanel();

		setTopBar();
		setBankingPane();
		setBottomBar();

		mainFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		mainFrame.getContentPane().add(bankingPane, BorderLayout.CENTER);
		mainFrame.getContentPane().add(bottomBar, BorderLayout.SOUTH);

		mainFrame.setSize(480, 720); // 2:3 비율
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(bankingPane);
		mainFrame.setVisible(true);
	}

	private void setTopBar() {
		topBar.setup();
	}

	AccountTable accountTable = new AccountTable();
	AccountManage check = new AccountManage();
	BankProduct product = new BankProduct();
	AccountTransferPanel t = new AccountTransferPanel();
	DepositWithdrawalPanel d = new DepositWithdrawalPanel();

	private void setBankingPane() {
		bankingPane = new JPanel(new CardLayout());

		bankingPane.add(myAccount, "메인화면");
		bankingPane.add(check, "계좌관리");
		bankingPane.add(product, "은행상품");
		bankingPane.add(d, "입출금");
		bankingPane.add(t, "송금");
	}

	private void setBottomBar() {
		bottomBar.setup();
	}

	public static void main(String[] args) {
		bank = new Bank();
		bank.run();
		startGUI();
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
