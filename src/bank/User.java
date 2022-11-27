package bank;
import java.util.Scanner;

public class User implements Manageable {
    public String id; // 사용자번호, 사용자를 구분하는
    public String password;
    public String name;
    String birthDay;
    String phone;
    public String email;

    @Override
    public String getInfo() {
        return null;
    }
    @Override
    public String getName() {
        return null;
    }

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

    @Override
    public void printInfo(Scanner scan) {
    }
}
