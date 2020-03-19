package FilePermission;

import backend.FilePermission.MyFilePermission;

/**
 * 测试自定义FilePermission类
 */
public class TestFilePermission {

    public static void main(String[] args) throws Exception{
        testGetActions();
        testEquals();
        testImplies();
    }

    public static void testGetActions() {
        MyFilePermission fp = new MyFilePermission("C:/test.txt", "read");

        // the canonical string representation of the action
        String s = fp.getActions();
        System.out.println("Action: " + s);
    }

    public static void testEquals() {
        MyFilePermission fp = new MyFilePermission("C:/test.txt", "read");
        MyFilePermission fp1 = new MyFilePermission("C:/test1.txt", "read");
        MyFilePermission fp2 = new MyFilePermission("C:/test.txt", "write");
        MyFilePermission fp3 = new MyFilePermission("C:/test.txt", "read");

        boolean bool = fp.equals(fp1);
        System.out.println(bool);
        bool = fp.equals(fp2);
        System.out.println(bool);
        bool = fp.equals(fp3);
        System.out.println(bool);
    }

    public static void testImplies() {
        MyFilePermission fp = new MyFilePermission("C:/a/b/*", "read");
        MyFilePermission fp1 = new MyFilePermission("C:/a/b/test.txt", "read");
        MyFilePermission fp2 = new MyFilePermission("C:/a/b/c/test.txt", "read");
        MyFilePermission fp3 = new MyFilePermission("C:/a/test.txt", "read");

        boolean bool = fp.implies(fp1);
        System.out.println(bool);
        bool = fp.implies(fp2);
        System.out.println(bool);
        bool = fp.implies(fp3);
        System.out.println(bool);
    }
}
