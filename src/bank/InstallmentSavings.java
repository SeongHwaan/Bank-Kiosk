package bank;

import java.util.Scanner;

public class InstallmentSavings extends Savings {
    public InstallmentSavings(String name, String userId, int calcType, double rate) {
        super(name, userId);
        this.calcType = calcType;
        this.rate = rate;
        if (calcType == 1)
            this.info = "단리예금";
        else
            this.info = "복리예금";
    }

    @Override
    public void read(Scanner scan) {
        super.read(scan);
        calcType = scan.nextInt();
        rate = scan.nextInt();
    }

    @Override
    public String getInfo() {
        if (calcType == 1) // 단리
            return  "상품설명: 해당 상품은 매달 5% 단리가 적용된~~ ";
        return "상품설명: 해당 상품은 매달 3% 복리가 적용된~~";
    }

    @Override
    public void print() {
        // [계좌] 3799672866, 100000,
        System.out.format("[%s적금 %d%%] %s, %f원\n", calcType == 1 ? "단리" : "복리", rate, number, cash);
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

    @Override
    public int calcInterest(int month) { // 이자계산
        if (calcType == 1) // 단리
            return (int) (cash * ((float) rate * month / 1200));
        else // 복리
            return (int) (cash * (Math.pow((100 + (float) rate) / 1200, month) - 1));
    }

    @Override
    public int calcEstimatedAmount(int month) { // 예상금액
        return cash + calcInterest(month);
    }
}