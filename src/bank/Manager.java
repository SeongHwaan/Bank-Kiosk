package bank;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager<T extends Manageable> {
    public ArrayList<T> list = new ArrayList<>();

    public void printAll() {
        for (T manageable : list) {
            manageable.print();
        }
    }

    Scanner openFile(String filename) {
        Scanner file = null;

        try {
            file = new Scanner(new File(filename));
        } catch (Exception e) {
            System.out.printf("[시스템] 파일 오픈 실패: %s\n", filename);
            System.exit(0);
        }
        return file;
    }

    public void readAll(String filename, Factory<T> fac) {
        Scanner fileIn = openFile(filename);

        while (fileIn.hasNext()) {
            T t = fac.create();
            t.read(fileIn);
            list.add(t);
        }

        fileIn.close();
    }

    boolean matches(String kwd) {
        for (T t : list) {
            if (t.matches(kwd))
                return true;
        }
        return false;
    }
}
