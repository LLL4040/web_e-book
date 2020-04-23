package backend;

import backend.Utils.HBaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HBaseApplication {
    private static final Logger hbaseLog = LoggerFactory.getLogger("HBase");

    public static void main(String[] args) throws Exception {
        getRowCount();
    }

    public static void getRowCount() {
        HBaseUtils hbaseUtils = new HBaseUtils();
        int cnt = hbaseUtils.rowCountByScanFilter("log1");
        System.out.println("log1行数为：" + cnt);
    }

    public static void scanAll() {
        HBaseUtils hbaseUtils = new HBaseUtils();
        try {
            String str = hbaseUtils.scanAllRecord("log1");
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
