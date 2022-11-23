package bank;

import java.util.Scanner;

public interface Manageable {
    void read(Scanner scan);

    void print();

    boolean matches(String kwd);

    void printInfo(Scanner scan);
}
