package backend.Controller;

import backend.Utils.HBaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/log")
public class LogController {
    @Autowired
    HBaseUtils hBaseUtils;

    private static final Logger hbaseLog = LoggerFactory.getLogger("HBase");

    @GetMapping("/scanAll")
    public void scanAll() throws IOException {
        String str = hBaseUtils.scanAllRecord("log0");
        System.out.println("log0中的内容为：" + str);
    }

    @GetMapping("/deleteTable")
    public void deleteTable() throws IOException {
        hBaseUtils.deleteTable("log0");
        hBaseUtils.deleteTable("log1");
        System.out.println("Done.");
    }

    @GetMapping("/test")
    public void test() {
        hbaseLog.info("test spring boot");
        System.out.println("Done.");
    }
}
