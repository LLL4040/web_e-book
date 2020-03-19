package backend.ClassLoader;

import java.io.*;

/**
 * 加密类
 */
public class MyEncryptUtil {
    public static void main(String[] args) throws Exception {
        if(args.length != 2) {
            System.out.println("USAGE: java MyEncryptUtil in out");
            return;
        }
        File src = new File(args[0]);
        File dist = new File(args[1]);
        encrypt(src, dist);
    }

    /**
     * 自定义加密算法，使用异或
     * @param src 源文件
     * @param dist 加密后的文件
     * @throws Exception
     */
    public static void encrypt(File src, File dist) throws Exception {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dist);

        int ch;
        while((ch = in.read()) != -1) {
            ch = ch ^ 0xff;
            out.write(ch);
        }
        in.close();
        out.close();
    }
}
