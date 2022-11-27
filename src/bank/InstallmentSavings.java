package bank;

import java.util.Scanner;

public class InstallmentSavings extends Savings {
    final int type = 2;
    int calcType; // 1: 단리, 2: 복리

    //은행상품등록용
    public void setInstallmentSavings(String name, int calcType, double rate) {
        super.setSavings(name);
        this.calcType = calcType;
        this.rate = rate;
        if(calcType == 1)
        	info = "단리예금";
        else
        	info = "복리예금";
        
    }
    
    public void setInstallmentSavings(int calcType, double rate, String name, String userId) {
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
    public String printInfo() {
        if (calcType == 1) // 단리
            return  "상품설명: 해당 상품은 매달 5% 단리가 적용된~~ ";
        return "상품설명: 해당 상품은 매달 3% 복리가 적용된~~";
    }

    @Override
    public int calcInterest(int cash, int month) { // 이자계산
    	if (calcType == 1) // 단리
            return (int) (cash * (rate * month) / 100);
        else // 복리
            return (int) (cash * (Math.pow((100 + rate) / 100, month) - 1));
    }

    @Override
    public int calcEstimatedAmount(int cash, int month) { // 예상금액
        return cash + calcInterest(cash, month);
    }
}