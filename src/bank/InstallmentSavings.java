package bank;

import java.util.Scanner;

public class InstallmentSavings extends Savings {
    final int type = 2;
    int calcType; // 1: 단리, 2: 복리
    int rate;

    public void setInstallmentSavings(int calcType, int rate, String name, String userId) {
        super.setSavings(name, userId);
        this.calcType = calcType;
        this.rate = rate;
    }

    @Override
    public void read(Scanner scan) {
        super.read(scan);
        rate = scan.nextInt();
    }

    @Override
    public void printInfo() {
        System.out.format("[적금] 입금 %s 이자 %d%%", calcType == 1 ? "단리" : "복리", rate);
    }

    public int calcInterest(int month) { // 이자계산
        if (calcType == 1) // 단리
            return cash * (rate * month) / 100;
        else // 복리
            return cash * ((int) Math.pow((100 + rate) / 100, month) - 1);
    }

    public int calcEstimatedAmount(int month) { // 예상금액
        return cash + calcInterest(month);
    }
}