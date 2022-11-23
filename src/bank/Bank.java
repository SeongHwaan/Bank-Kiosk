package bank;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    Scanner scan = new Scanner(System.in);

    public static Manager<User> userMgr = new Manager<>(); // 사용자 매니저
    public static Manager<Savings> accountMgr = new Manager<>(); // 계좌 매니저

    User loginUser = new User(); // 은행 시스템을 이용할 회원
    ArrayList<Savings> loginAccountList = new ArrayList<>(); // 로그인 회원 계좌 리스트

    void run() {
        setDatabase();

        while (true) {
            if (!login())
                System.exit(0);

            System.out.println("--- 뱅크 키오스크, 환영합니다!");
            System.out.println(loginUser.name + "님으로 정상적으로 로그인됐습니다.");

            while (true) {
                System.out.println("- 원하시는 메뉴를 입력해주세요.");
                System.out.println("(1) 입출금\t\t(2) 계좌이체\n(3) 자산관리\t\t(4) 조회\n(5) 개설\n(0) 로그아웃");
                int menu = scan.nextInt();

                switch (menu) {
                    case 0 -> { // 로그아웃
                        loginUser = null;
                        loginAccountList.clear();
                    }

                    case 1 -> deposit();    // 입금

                    case 2 -> transfer();   // 이체

                    case 3 -> manageAsset();    // 자산관리

                    case 4 -> {
                        // 조회()
                        for (Savings savings : loginAccountList)
                            savings.print();
                    }

                    case 5 -> createAccount();  // 계좌개설

                    default -> {
                        System.out.print("- 잘못된 입력입니다.\n\n");
                        continue;
                    }
                }

                System.out.print("--- 이용해주셔서 감사합니다!\n\n");
                if (menu == 0)
                    break;
            }
        }
    }

    // 혜택조회 *
    private void manageAsset() {
        final Savings useAccunt = selectAccount(); // 조회할 계좌 선택하면
        useAccunt.printInfo(); // 해당 계좌의 혜택을 출력
    }

    // 계좌 개설 *, 현재 rate 고정으로 되어 있음
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
                case 2 -> newAccount = new InstallmentSavings(1, 3, name, loginUser.id);
                // 복리 적금
                case 3 -> newAccount = new InstallmentSavings(1, 5, name, loginUser.id);

                default -> {
                    System.out.println("- 잘못된 입력입니다.");
                    continue;
                }
            }

            // 로그인 회원, 계좌리스트에 추가
            accountMgr.list.add(newAccount);
            loadLoginAccount();
            return;
        }
    }

    public void loadLoginAccount() {
        for (Savings account : accountMgr.list)
            if (account.matches(loginUser.name))
                loginAccountList.add(account);
    }

    // 이용할 계좌 선택 *
    private Savings selectAccount() {
        final int n = loginAccountList.size();
        int menu;

        while (true) {
            System.out.println("- 이용하실 계좌를 선택해주세요.");

            // 로그인 계좌 리스트에서 계좌 구분하여 출력하는
            // (1) 비상금
            // (2) 주택청약적금
            // (3) 비상금
            for (int i = 0; i < n; i++) {
                System.out.println("(" + (i + 1) + ") " + loginAccountList.get(i).name);
            }

            menu = scan.nextInt();

            // 정상적인 인덱스를 입력했을 경우 리턴
            if (menu <= n && menu > 0) {
                return loginAccountList.get(menu - 1);
            }
        }
    }

    // 현금 입금 *
    // Today 처리 구현해야함
    private void deposit() {
        final Savings useAccunt = selectAccount();

        System.out.print("금액: ");
        int cash = scan.nextInt();
        cash = Math.abs(cash); // 현금 입금은 음수가 될 수 없으므로 보정

        useAccunt.cash += cash;
        useAccunt.createHistory(1, "*today", "-", cash); // 거래내역 생성
        System.out.println("거래 후 잔고: " + useAccunt.cash);
    }

    // 현금 출금 *
    // Today 처리 구현해야함
    private int withdraw() {
        final Savings useAccunt = selectAccount();

        int cash = scan.nextInt();

        // 본인 잔고 확인하여, 정상적으로 입력 됐을 경우 패스
        while (cash > useAccunt.cash) {
            System.out.println("계좌 잔고가 부족합니다. " + (useAccunt.cash - cash) + "원 부족.");
            System.out.print("금액을 입력해주세요: ₩");
            cash = scan.nextInt();
        }

        useAccunt.cash -= cash;
        useAccunt.createHistory(2, "*today", "-", cash); // 거래내역 생성
        System.out.println("거래 후 잔고: " + useAccunt.cash);

        return cash; // 메서드 재사용 하기 위해 인출은 리턴값을 가진다.
    }

    // 이체, 타인 계좌에게 송금 *
    private void transfer() {
        Savings account;

        // 정상적인 계좌를 입력 받을 때 까지
        while (true) {
            System.out.println("계좌번호를 입력해주세요: ");
            account = findAccount(scan.next()); // 데이터베이스에서 계좌번호 찾기

            // 계좌가 정상적으로 조회됐을경우 탈출
            if (account != null)
                break;

            System.out.println("확인할 수 없는 계좌번호입니다. 다시입력해주세요.");
        }

        // 자신 계좌를 입력했을 경우 탈출
        if (account.userId.equals(loginUser.id)) {
            System.out.println("본인계좌에 계좌이체를 할 수 없습니다.");
            return;
        }

        // 해당 계좌를 사용하는 유저 찾기
        User user = findUser(account.userId);

        // 찾을 수 없으면 삭제
        if (user == null) {
            System.out.println("[시스템] 사용자를 찾을 수 없습니다.");
            return;
        }

        System.out.format("예금주: %s\n", user.name);
        System.out.print("금액을 입력해주세요: ₩");

        int cash = withdraw(); // 본인 계좌에서 인출 후
        account.cash += cash; // 상대 계좌로 전달
        account.createHistory(1, "*Today", loginUser.name, cash); // 상대 거래내역 생성
    }

    // 키오스크 이용자
    private boolean login() {
        for (int i = 0; i < 5; i++) {
            System.out.print("ID: ");
            String id = scan.next();
            if (id.contentEquals("quit")) // id에 quit 입력 시 프로그램 종료
                System.exit(0);

            System.out.print("PW: ");
            String pw = scan.next();

            // int id = 0; // 테스트용으로 사용자 0으로 로그인하여 시스템을 시연한다.
            loginUser = findUser(id);
            if (!isValidLogin(pw)) { // 유효하지 않은 로그인이면 다시 입력
                System.out.format("로그인 5회 실패시, 프로그램이 종료됩니다. (%d/5)\n", i + 1);
                continue;
            }

            loadLoginAccount();
            return true;
        }

        return false;
    }

    private boolean isValidLogin(String pw) {
        if (loginUser == null) {
            System.out.println("[시스템] 사용자를 찾을 수 없습니다.");
            return false;
        }
        if (!loginUser.password.contentEquals(pw)) {
            System.out.println("[시스템] 비밀번호가 잘못되었습니다.");
            loginUser = null;
            return false;
        }
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
        userMgr.readAll("src/input/user.txt", User::new); // 사용자 데이터
        accountMgr.readAll("src/input/account.txt", () -> new Savings(null, null)); // 계좌 데이터
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.run();
    }
}