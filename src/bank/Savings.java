package bank;

import java.util.ArrayList;
import java.util.Scanner;

public class Savings implements Manageable {

	public String name;
	int calcType; // 0: 무이자 1: 단리, 2: 복리
	public String number; // 계좌번호
	public String userId; // 사용자 아이디
	public int cash; // 계좌 금액
	public double rate;
	public String info;

	public ArrayList<History> historyList = new ArrayList<>();

	public Savings(String name, String userId) {
		this.name = name;
		generateNumber();
		this.userId = userId;
		this.calcType = 0;
		this.cash = 0;
		this.info = "일반예금";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getInfo() {
		return info;
	}

	public String getNumber() {
		return number;
	}

	public String[] getTexts() {
		return new String[] { "" + number, "" + cash };
	}

	public void generateNumber() {
		number = String.valueOf(Math.random());
		number = number.substring(8);
		if (Bank.accountMgr.matches(number))
			generateNumber();
	}

	public void createHistory(int type, String day, String desc, int cash) {
		History history = new History(type, day, desc, cash);
		historyList.add(history);
	}

	public void printHistory() {
		for (History history : historyList)
			history.print();
	}

	@Override
	public void read(Scanner scan) {
		name = scan.next();
		number = scan.next();
		userId = scan.next();
		cash = scan.nextInt();
	}

	@Override
	public void print() {
		// [계좌] 3799672866, 100000
		System.out.format("[예금] %s, %d원\n", number, cash);
		printHistory();
	}

	@Override
	public void printInfo(Scanner scan) {
		System.out.format("[예금] 입출금 무이자");
	}

	@Override
	public boolean matches(String kwd) {
		return kwd.equals(userId) || kwd.equals(number);
	}

	public int calcInterest(int m) {
		return 0;
	}

	public int calcEstimatedAmount(int m) {
		return 0;
	}

	public static class History {
		int type; // 거래 구분 1: 입금 2: 출금
		public String day; // 거래일자, 2022-10-30
		String desc;// 거래내역
		int cash; // 거래금

		public History(int type, String day, String desc, int cash) {
			this.type = type;
			this.day = day;
			this.desc = desc;
			this.cash = cash;
		}

		public String[] getTexts() {
			return new String[] { "" + (type == 1 ? "입금" : "출금"), day, "" + cash };
		}

		public void print() {
			// [입금] 2022-10-30, 김관식, 100원
			if (type == 1)
				System.out.print("\t[입금] ");
			else if (type == 2)
				System.out.print("\t[출금] ");

			System.out.println(day + ", " + desc + ", " + cash + "원");
		}
	}
}