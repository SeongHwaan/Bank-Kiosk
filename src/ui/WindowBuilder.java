package ui;

import javax.swing.*;
import java.awt.*;
import bank.Bank;

public class WindowBuilder {
	public static Bank bank;
	static JFrame mainFrame;
	static JPanel bankingPane;
	static CardLayout card;

	TopBarPanel topBar = new TopBarPanel(Bank.loginUser.name);
	BottomBarPanel bottomBar = new BottomBarPanel();
	MyAccountList myAccount = new MyAccountList();

	AccountManage check = new AccountManage();
	BankProduct product = new BankProduct();
	AccountTransferPanel t = new AccountTransferPanel();
	DepositWithdrawalPanel d = new DepositWithdrawalPanel();
	SetupPanel setupPanel = new SetupPanel();

	private void createAndShowMain() {
		mainFrame = new JFrame("경기뱅크");
		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBankingPane();

		mainFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		mainFrame.getContentPane().add(bankingPane, BorderLayout.CENTER);
		mainFrame.getContentPane().add(bottomBar, BorderLayout.SOUTH);

		mainFrame.setLocationRelativeTo(bankingPane);

		mainFrame.setSize(480, 720); // 2:3 비율
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}

	private void setBankingPane() {
		bankingPane = new JPanel(new CardLayout());
		card = (CardLayout) WindowBuilder.bankingPane.getLayout(); // 상단바, 하단바에서 핸들링

		bankingPane.add(myAccount, "메인화면");
		bankingPane.add(check, "계좌관리");
		bankingPane.add(product, "은행상품");
		bankingPane.add(d, "입출금");
		bankingPane.add(t, "송금");
		bankingPane.add(setupPanel, "설정");
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
		javax.swing.SwingUtilities.invokeLater(() -> {
			WindowBuilder main = new WindowBuilder();
			main.createAndShowMain();
		});
	}
}
