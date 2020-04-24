package backend;

import backend.Utils.HBaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HBaseApplication {
    private static final Logger hbaseLog = LoggerFactory.getLogger("HBase");

    public static void main(String[] args) throws Exception {
        testLog();
    }

    public static void testLog() {
        for (int i = 0; i < 1002; i++) {
            hbaseLog.info("test log");
        }
        System.out.println("成功！");
    }

    public static void deleteTable() throws IOException {
        HBaseUtils hbaseUtils = new HBaseUtils();
        hbaseUtils.deleteTable("log1");
        hbaseUtils.deleteTable("log2");
        hbaseUtils.deleteTable("log3");
        System.out.println("完成！");
    }

    public static void getRowCount() {
        HBaseUtils hbaseUtils = new HBaseUtils();
        int cnt = hbaseUtils.rowCountByScanFilter("log0");
        System.out.println("log0行数为：" + cnt);
    }

    public static void scanAll() {
        HBaseUtils hbaseUtils = new HBaseUtils();
        try {
            String str = hbaseUtils.scanAllRecord("log0");
            System.out.println("获取到log1的内容：" + str);
//            hbaseLog.info("读取成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        HBaseUtils hbaseUtils = new HBaseUtils();
        try {
            hbaseUtils.createTable("log3", "log");
            System.out.println("成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
