package bank;

import java.util.Scanner;

public class InstallmentSavings extends Savings {
    int calcType; // 1: 단리, 2: 복리
    int rate;

    public InstallmentSavings(String name, String userId, int calcType, int rate) {
        super(name, userId);
        this.calcType = calcType;
        this.rate = rate;
    }

    @Override
    public void read(Scanner scan) {
        super.read(scan);
        calcType = scan.nextInt();
        rate = scan.nextInt();
    }

    @Override
    public void print() {
        // [계좌] 3799672866, 100000,
        System.out.format("[%s적금 %d%%] %s, %d원\n", calcType == 1 ? "단리" : "복리", rate, number, cash);
        printHistory();
    }

    @Override
    public void printInfo(Scanner scan) {
        int month;
        System.out.format("[적금] 입금 %s 이자 %d%%\n", calcType == 1 ? "단리" : "복리", rate);
        System.out.print("목표 기간을 입력하세요. (단위: 개월) -> ");
        month = scan.nextInt();
        System.out.format("%d개월 후 예상금액: %d원\n(원금을 제외한)이자: %d원\n", month, calcEstimatedAmount(month), calcInterest(month));
    }

    public int calcInterest(int month) { // 이자계산
        if (calcType == 1) // 단리
            return (int) (cash * ((float) rate * month / 100));
        else // 복리
            return (int) (cash * (Math.pow((100 + (float) rate) / 100, month) - 1));
    }

    public int calcEstimatedAmount(int month) { // 예상금액
        return cash + calcInterest(month);
    }
}