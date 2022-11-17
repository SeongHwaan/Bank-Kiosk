package bank;
import java.util.ArrayList;
import java.util.Scanner;

public class Account implements Manageable {
    int type; // 계좌유형
    String interestType; // 이자구분
    int rate; // 이자율
    String number; // 계좌번호
    String userId; // 사용자 아이디
    int cash; // 계좌 금액

    ArrayList<History> historyList = new ArrayList<>();

    public void createHistory(int type, String day, String user, int cash) {
        History history = new History(type, day, user, cash);
        historyList.add(history);
    }

    public void printHistory() {
        for (History history : historyList)
            history.print();
    }

    @Override
    public void read(Scanner scan) {
        type = scan.nextInt();
        number = scan.next();
        userId = scan.next();
        cash = scan.nextInt();
        setInterest();
    }

    public void setInterest() {
        switch (type) {
            case 1 -> {
                // 단리 %
            }
            case 2 -> {
                // 단리 %
            }
            case 3 -> {
                // 복리 %
            }
            case 4 -> {
                // 복리 %
            }
        }
    }

    @Override
    public void print() {
        // [계좌] 3799672866, 100000
        System.out.format("[계좌: %d] %s %d %s %d%% %d원\n", type, number, interestType, rate, cash);
        printHistory();
    }

    @Override
    public boolean matches(String kwd) {
        return kwd.equals(userId) || kwd.equals(number);
    }

    static class History implements Manageable {
        int type; // 거래 구분 1 :입금 2: 출금
        String day; // 거래일자, 2022-10-30
        String user;// 상대 사용자
        int cash; // 거래금

        public History(int type, String day, String user, int cash) {
            this.type = type;
            this.day = day;
            this.user = user;
            this.cash = cash;
        }

        @Override
        public void read(Scanner scan) {
            type = scan.nextInt();
            day = scan.next();
            user = scan.next();
            cash = scan.nextInt();
        }

        @Override
        public void print() {
            // [입금] 2022-10-30, 김관식, 100원

            if (type == 1)
                System.out.print("\t[입금] ");
            else if (type == 2)
                System.out.print("\t[출금] ");

            System.out.println(day + ", " + user + ", " + cash + "원");
        }

        @Override
        public boolean matches(String kwd) {
            return false;
        }
    }
}
