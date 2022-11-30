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
	CreditPanel creditPanel = new CreditPanel();
	MyPagePanel myPagePanel = new MyPagePanel();
	CreateAccount creation;

	private void createAndShowMain() {
		mainFrame = new JFrame("KGU BANK");
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

		Dimension frameSize = mainFrame.getSize(); // 프레임 사이즈
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
		mainFrame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2); // 화면
	}

	private void setBankingPane() {
		bankingPane = new JPanel(new CardLayout());
		card = (CardLayout) WindowBuilder.bankingPane.getLayout(); // 상단바, 하단바에서 핸들링

		bankingPane.add(myAccount, "메인화면");
		bankingPane.add(check, "계좌관리");
		bankingPane.add(product, "은행상품");
		bankingPane.add(d, "입출금");
		bankingPane.add(t, "송금");
		bankingPane.add(myPagePanel, "마이페이지");
		bankingPane.add(creditPanel, "프로그램 정보");
		creation = new CreateAccount();
	}

	public static void main(String[] args) {
		bank = new Bank();
		bank.run();
		startLogin();
	}

	public static void startLogin() {
		javax.swing.SwingUtilities.invokeLater(LoginFrame::main);
	}

	public static void startGUI() {
		javax.swing.SwingUtilities.invokeLater(() -> {
			WindowBuilder main = new WindowBuilder();
			main.createAndShowMain();
		});
	}	
}
