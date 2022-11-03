import java.util.Scanner;

public class User implements Manageable {
    String code; // 아이디, 사용자를 구분하는
    String name;
    String birthDay;
    String phone;
    String email;

    @Override
    public void read(Scanner scan) {
        code = scan.next();
        name = scan.next();
        birthDay = scan.next();
        phone = scan.next();
        email = scan.next();
    }

    @Override
    public void print() {
        // [회원] 김관식, 2000-03-23, 010-1234-5678, abc@abc.com
        System.out.println("[회원] " + name + ", " + birthDay + ", " + phone + ", " + email + " " + code);
    }

    @Override
    public boolean matches(String kwd) {
        return kwd.equals(code);
    }
}
