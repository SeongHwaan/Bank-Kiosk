package bank;

import java.util.Scanner;

public class InstallmentSavings extends Savings {
    final int type = 2;
    int rate;

    @Override
    public void read(Scanner scan) {
        super.read(scan);
        rate = scan.nextInt();
    }

    public int calcInterest(int calcType, int month) { // 이자계산
        if (calcType == 1) // 단리
            return cash * (rate * month) / 100;
        else // 복리
            return cash * ((int) Math.pow((100 + rate) / 100, month) - 1);
    }

    public int calcEstimatedAmount(int calcType, int month) { // 예상금액
        return cash + calcInterest(calcType, month);
    }
}