package backend.Log;

import backend.Utils.HBaseUtils;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.status.ErrorStatus;

public class HBaseAppender extends AppenderBase<LoggingEvent> {
    private String curTable;
    private String curCF;
    private String curCol;
    private int curRow;
    private HBaseUtils hBaseUtils;

    @Override
    public void start() {
        try {
            hBaseUtils = new HBaseUtils();
            curTable = "log1";
            curCF = "log";
            curCol = "content";
            curRow = hBaseUtils.rowCountByScanFilter(curTable);
        } catch (Exception e) {
            addStatus(new ErrorStatus("Failed to initialize HBase", this, e));
            return;
        }
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    protected void append(LoggingEvent event) {
        try {
            String log = event.getMessage();
            System.out.println(log);
            if (!log.isEmpty()) {
                hBaseUtils.insertOneRecord(curTable, String.valueOf(curRow), curCF, curCol, log);
            }
        } catch (Exception e) {
            addStatus(new ErrorStatus("日志写入HBase出错", this, e));
        }
    }
}
