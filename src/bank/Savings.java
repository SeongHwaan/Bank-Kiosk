package bank;

import java.util.ArrayList;
import java.util.Scanner;

public class Savings implements Manageable {
    final int type = 1;
    String name; // 별칭
    String number; // 계좌번호
    String userId; // 사용자 아이디
    int cash; // 계좌 금액

    ArrayList<History> historyList = new ArrayList<>();

    public void setSavings(String name, String userId) {
        this.name = name;
        generateNumber();
        this.userId = userId;
        this.cash = 0;
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
        number = scan.next();
        userId = scan.next();
        cash = scan.nextInt();
    }

    @Override
    public void print() {
        // [계좌] 3799672866, 100000
        System.out.format("[계좌] %s, %d원\n", number, cash);
        printHistory();
    }

    @Override
    public void printInfo() {
        System.out.format("[예금] 입출금 무이자");
    }

    @Override
    public boolean matches(String kwd) {
        return kwd.equals(userId) || kwd.equals(number);
    }

    static class History {
        int type; // 거래 구분 1 :입금 2: 출금
        String day; // 거래일자, 2022-10-30
        String desc;// 거래내역
        int cash; // 거래금

        public History(int type, String day, String desc, int cash) {
            this.type = type;
            this.day = day;
            this.desc = desc;
            this.cash = cash;
        }

        public void read(Scanner scan) {
            type = scan.nextInt();
            day = scan.next();
            desc = scan.next();
            cash = scan.nextInt();
        }

        public void print() {
            // [입금] 2022-10-30, 김관식, 100원
            if (type == 1)
                System.out.print("\t[입금] ");
            else if (type == 2)
                System.out.print("\t[출금] ");

            System.out.println(day + ", " + desc + ", " + cash + "원");
        }

        public boolean matches(String kwd) {
            return false;
        }
    }
}