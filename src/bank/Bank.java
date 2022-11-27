package bank;

import java.util.ArrayList;
import java.util.Scanner;

import bank.InstallmentSavings;
import bank.Manager;
import bank.Savings;
import bank.User;

public class Bank {
	Scanner scan = new Scanner(System.in);

	public static Manager<User> userMgr = new Manager<>(); // 사용자 매니저
	public static Manager<Savings> accountMgr = new Manager<>(); // 계좌 매니저
	public static ArrayList<Savings> productList = new ArrayList<>();
	
	public static User loginUser = new User(); // 은행 시스템을 이용할 회원
	public static ArrayList<Savings> loginAccountList = new ArrayList<>(); // 로그인 회원 계좌 리스트
	
	Savings one;
	InstallmentSavings two;
	InstallmentSavings three;
	
	public void run() {
		setDatabase();
	}
	
	// 계좌 개설
	private void createAccount() {
		System.out.print("계좌 별칭: ");
		String name = scan.next();
		Savings newAccount;

		while (true) {
			System.out.println("- 원하시는 계좌상품을 입력해주세요.");
			System.out.println("(1) 예금\t\t(2) 단리적금\n(3) 복리적금");
			int menu = scan.nextInt();

			switch (menu) {
				// 예금
				case 1 -> newAccount = new Savings(name, loginUser.id);
				// 단리적금 개설
				case 2 -> newAccount = new InstallmentSavings(name, loginUser.id, 1, 3);
				// 복리 적금
				case 3 -> newAccount = new InstallmentSavings(name, loginUser.id, 2, 3);

				default -> {
					System.out.println("- 잘못된 입력입니다.");
					continue;
				}
			}

			// 로그인 회원, 계좌리스트에 추가
			accountMgr.list.add(newAccount);
			loginAccountList.add(newAccount);
			return;
		}
	}

	// 여러 계좌에서 하나 선택
	private Savings selectAccount() {
		final int n = loginAccountList.size() + 1;
		int menu = 0;

		while (true) {
			System.out.println("- 사용하실 계좌를 선택해주세요.");

			// 로그인 계좌 리스트에서 계좌 구분하여 출력하는
			for (int i = 1; i < n; i++) {
				System.out.print("(" + i + ")");
			}

			menu = scan.nextInt();

			// 정상적인 인덱스를 입력했을 경우 리턴
			if (menu <= n && menu > 0) {
				System.out.print("--- 이용해주셔서 감사합니다!\n\n");
				return loginAccountList.get(menu);
			}
		}
	}

	// 현금 입금
	public void deposit(Savings useAccount, String kwd) {

		int cash = Math.abs(Integer.parseInt(kwd)); // 현금 입금은 음수가 될 수 없으므로 보정
		useAccount.cash += cash;
		useAccount.createHistory(1, "*Today", "-", cash); // 거래내역 생성
		System.out.println("거래 후 잔고: " + useAccount.cash);
	}

	// 현금 출금
	public int withdraw(Savings useAccount, String kwd) {
		int cash = Integer.parseInt(kwd);
		// 본인 잔고 확인하여, 정상적으로 입력 됐을 경우 패쓰
		try {
			if(cash > useAccount.cash)
				throw new Exception();
			useAccount.cash -= cash;
			useAccount.createHistory(2, "*Today", "-", cash); // 거래내역 생성
			System.out.println("거래 후 잔고: " + useAccount.cash);
		} catch (Exception e) {
			System.out.println("no money계좌 잔고가 부족합니다. " + (useAccount.cash - cash) + "원 부족.");
		}
		return cash; // 메서드 재사용 하기 위해 인출은 리턴값을 가진다.
	}

	// 이체, 타인 계좌에게 송금
	public void transfer(Savings myAccount, Savings transAccount, String kwd) {
		int cash = Integer.parseInt(kwd);
		withdraw(myAccount, kwd); // 본인 계좌에서 인출 후
		transAccount.cash += cash; // 상대 계좌로 전달
		transAccount.createHistory(1, "*Today", loginUser.name, cash); // 상대 거래내역 생성
	}

	// 키오스크 이용자
	public boolean login(String usertext, String passwordtext) {
		loginUser = findUser(usertext);
		// 유효하지 않은 로그인이면 다시 입력
		return isValidLogin(passwordtext);
	}

	public void loadLoginAccount() {
		for (Savings account : accountMgr.list)
			if (account.matches(loginUser.id))
				loginAccountList.add(account);
	}

	private boolean isValidLogin(String pw) {
		if (loginUser == null)
			return false;
		if (!loginUser.password.contentEquals(pw))
			return false;
		loadLoginAccount();
		return true;
	}

	// 리스트에서 유저 찾기
	public User findUser(String id) {
		for (User user : userMgr.list)
			if (user.matches(id))
				return user;
		return null;
	}

	// 리스트에서 계좌 찾기
	public Savings findAccount(String number) {
		for (Savings account : accountMgr.list)
			if (account.matches(number))
				return account;
		return null;
	}

	// 데이터 마운트
	private void setDatabase() {
		userMgr.readAll("src/input/users.txt", User::new); // 사용자 데이터
		accountMgr.readAll("src/input/savings.txt", () -> new Savings(null, null)); // 예금 계좌 데이터
		accountMgr.readAll("src/input/installmentsavings.txt", () -> new InstallmentSavings(null, null, 0, 0)); // 적금 계좌 데이터
		
		one = new Savings("일반예금", null);
		two = new InstallmentSavings("단리적금", null, 1, 5.0);
		three = new InstallmentSavings("복리적금", null, 2, 3.0);
		
		productList.add(one);
		productList.add(two);
		productList.add(three);
		
		
	}

	public static void main(String[] args) {
		Bank bank = new Bank();
		bank.run();
	}
}