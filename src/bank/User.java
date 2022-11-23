package bank;

import java.util.Scanner;

public class User implements Manageable {
    String id; // 사용자번호, 사용자를 구분하는
    String password;
    String name;
    String birthDay;
    String phone;
    String email;

    @Override
    public void read(Scanner scan) {
        id = scan.next();
        password = scan.next();
        name = scan.next();
        birthDay = scan.next();
        phone = scan.next();
        email = scan.next();
    }

    @Override
    public void print() {
        // [회원] 김관식, 2000-03-23, 010-1234-5678, abc@abc.com
        System.out.println("[회원] " + name + ", " + birthDay + ", " + phone + ", " + email);
    }

    @Override
    public boolean matches(String kwd) {
        return kwd.equals(id);
    }

    public String[] getTexts() {
        return new String[] { id, password, name, birthDay, phone, email };
    }

    @Override
    public void printInfo(Scanner scan) {
    }
}
