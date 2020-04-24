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
    private int curTN;
    private HBaseUtils hBaseUtils;

    @Override
    public void start() {
        try {
            hBaseUtils = new HBaseUtils();
            curTN = 0;
            curTable = "log" + curTN;
            curCF = "log";
            curCol = "content";
            if (hBaseUtils.isExist(curTable)) {
                curRow = hBaseUtils.rowCountByScanFilter(curTable) + 1;
            } else {
                hBaseUtils.createTable(curTable, curCF);
                curRow = 1;
            }
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
            if (!log.isEmpty()) {
                if (curRow > 1000) {
                    curTN = (curTN + 1) % 3;
                    curTable = "log" + curTN;
                    if (hBaseUtils.isExist(curTable)) {
                        hBaseUtils.deleteTable(curTable);
                    }
                    hBaseUtils.createTable(curTable, curCF);
                    curRow = 0;
                }
                hBaseUtils.insertOneRecord(curTable, String.valueOf(curRow), curCF, curCol, log);
                curRow++;
            }
        } catch (Exception e) {
            addStatus(new ErrorStatus("日志写入HBase出错", this, e));
        }
    }
}
