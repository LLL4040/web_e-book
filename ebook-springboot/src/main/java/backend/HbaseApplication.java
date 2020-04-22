package backend;

import backend.Utils.HbaseUtils;

import java.io.IOException;

public class HbaseApplication {

    public static void main(String[] args) throws Exception {
        HbaseUtils hbaseUtils = new HbaseUtils();
        try {
            String str = hbaseUtils.scanAllRecord("test");
            System.out.println("获取到hbase的内容：" + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
