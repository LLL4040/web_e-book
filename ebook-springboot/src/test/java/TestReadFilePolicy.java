import backend.FilePermission.MyFilePermission;

/**
 * 测试自定义的readFile policy文件
 */
public class TestReadFilePolicy {
    public static void main(String[] args){
        try {
            MyFilePermission fp = new MyFilePermission("C:/tmp/1.txt", "read");
            SecurityManager manager = System.getSecurityManager();
            if(manager != null) {
                manager.checkPermission(fp);
            }
            fp = new MyFilePermission("D:/*", "read");
            if(manager != null) {
                manager.checkPermission(fp);
            }
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }
    }
}
