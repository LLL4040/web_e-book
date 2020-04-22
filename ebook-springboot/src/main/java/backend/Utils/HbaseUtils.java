package backend.Utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

@Component
public class HbaseUtils {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static Configuration conf = HBaseConfiguration.create();
    private static Connection connection = null;
    private static HbaseUtils instance = null;
    private static Admin admin = null;

    public HbaseUtils() {
        if (connection == null) {
            try {
                connection = ConnectionFactory.createConnection(conf);
                admin = connection.getAdmin();
            } catch (IOException e) {
                logger.error("HbaseUtils实例初始化失败！错误信息为：" + e.getMessage());
            }
        }
    }

    //简单单例方法，如果autowired自动注入就不需要此方法
    public static synchronized HbaseUtils getInstance(){
        if(instance == null){
            instance = new HbaseUtils();
        }
        return instance;
    }

    /**
     * 判断一张表是否存在
     * @param tableName
     * @return true or false
     */
    public boolean isExist(String tableName){
        //对表操作需要使用HbaseAdmin
        try {
            return admin.tableExists(TableName.valueOf(tableName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建表
     * @param tableName 表名
     * @param columnFamily 列族
     * @throws IOException
     */
    public void createTable(String tableName,String... columnFamily) throws IOException{
        TableName name = TableName.valueOf(tableName);
        //如果存在则写日志
        if (admin.tableExists(name)) {
            logger.error("create htable error! this table {} already exists!", name);
        } else {
            HTableDescriptor desc = new HTableDescriptor(name);
            for (String cf : columnFamily) {
                desc.addFamily(new HColumnDescriptor(cf));
            }
            admin.createTable(desc);
        }
    }

    /**
     * 插入记录（单行单列族-多列多值）
     * @param tableName 表名
     * @param row 行名
     * @param columnFamilies 列族名
     * @param columns 列名（数组）
     * @param values 值（数组）（且需要和列一一对应）
     * @throws IOException
     */
    public void insertRecords(String tableName, String row, String columnFamilies, String[] columns, String[] values) throws IOException {
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        Put put = new Put(Bytes.toBytes(row));
        for (int i = 0; i < columns.length; i++) {
            put.addColumn(Bytes.toBytes(columnFamilies), Bytes.toBytes(columns[i]), Bytes.toBytes(values[i]));
            table.put(put);
        }
    }

    /**
     * 插入记录（单行单列族-单列单值）
     * @param tableName 表名
     * @param row 行名
     * @param columnFamily 列族名
     * @param column 列名
     * @param value 值
     * @throws IOException
     */
    public void insertOneRecord(String tableName, String row, String columnFamily, String column, String value) throws IOException {
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        Put put = new Put(Bytes.toBytes(row));
        put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));
        table.put(put);
    }

    /**
     * 删除一行记录
     * @param tableName 表名
     * @param rowKey 行名
     * @throws IOException
     */
    public void deleteRow(String tableName, String rowKey) throws IOException {
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        Delete d = new Delete(rowKey.getBytes());
        table.delete(d);
    }

    /**
     * 删除单行单列族记录
     * @param tableName 表名
     * @param rowKey 行名
     * @param columnFamily 列族名
     * @throws IOException
     */
    public void deleteColumnFamily(String tableName, String rowKey, String columnFamily) throws IOException {
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        Delete d = new Delete(rowKey.getBytes()).addFamily(Bytes.toBytes(columnFamily));
        table.delete(d);
    }

    /**
     * 删除单行单列记录
     * @param tableName 表名
     * @param rowKey 行名
     * @param columnFamily 列族名
     * @param column 列名
     * @throws IOException
     */
    public void deleteColumn(String tableName, String rowKey, String columnFamily, String column) throws IOException {
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        Delete d = new Delete(rowKey.getBytes()).addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
        table.delete(d);
    }

    /**
     * 查找一行记录
     * @param tableName 表名
     * @param rowKey 行名
     * @return
     * @throws IOException
     */
    public static String selectRow(String tableName, String rowKey) throws IOException {
        String record = "";
        TableName name=TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        Get g = new Get(rowKey.getBytes());
        Result rs = table.get(g);
        NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> map = rs.getMap();
        for (Cell cell : rs.rawCells()) {
            StringBuffer stringBuffer = new StringBuffer()
                    .append(Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength())).append("\t")
                    .append(Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength())).append("\t")
                    .append(Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength())).append("\t")
                    .append(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength())).append("\n");
            String str = stringBuffer.toString();
            record += str;
        }
        return record;
    }

    /**
     * 查找单行单列记录
     * @param tableName 表名
     * @param rowKey 行名
     * @param columnFamily 列族名
     * @param column 列名
     * @return
     * @throws IOException
     */
    public static String selectValue(String tableName, String rowKey, String columnFamily, String column) throws IOException {
        TableName name=TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        Get g = new Get(rowKey.getBytes());
        g.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
        Result rs = table.get(g);
        return Bytes.toString(rs.value());
    }

    /**
     * 查询表中所有行（Scan方式）
     * @param tableName 表名
     * @return
     * @throws IOException
     */
    public String scanAllRecord(String tableName) throws IOException {
        String record = "";
        TableName name=TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        try {
            for(Result result : scanner){
                for (Cell cell : result.rawCells()) {
                    StringBuffer stringBuffer = new StringBuffer()
                            .append(Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength())).append("\t")
                            .append(Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength())).append("\t")
                            .append(Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength())).append("\t")
                            .append(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength())).append("\n");
                    String str = stringBuffer.toString();
                    record += str;
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return record;
    }

    /**
     * 根据rowKey关键字查询报告记录
     * @param tableName 表名
     * @param rowKeyword 关键字
     * @return
     * @throws IOException
     */
    public List scanReportDataByRowKeyword(String tableName, String rowKeyword) throws IOException {
        ArrayList<Object> list = new ArrayList<Object>();

        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();

        //添加行键过滤器，根据关键字匹配
        RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(rowKeyword));
        scan.setFilter(rowFilter);

        ResultScanner scanner = table.getScanner(scan);
        try {
            for (Result result : scanner) {
                //TODO 此处根据业务来自定义实现
                list.add(null);
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return list;
    }

    /**
     * 根据rowKey关键字和时间戳范围查询报告记录
     * @param tableName 表名
     * @param rowKeyword 关键字
     * @param minStamp 开始时间
     * @param maxStamp 结束时间
     * @return
     * @throws IOException
     */
    public List scanReportDataByRowKeywordTimestamp(String tableName, String rowKeyword, Long minStamp, Long maxStamp) throws IOException {
        ArrayList<Object> list = new ArrayList<Object>();

        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        //添加scan的时间范围
        scan.setTimeRange(minStamp, maxStamp);

        RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(rowKeyword));
        scan.setFilter(rowFilter);

        ResultScanner scanner = table.getScanner(scan);
        try {
            for (Result result : scanner) {
                //TODO 此处根据业务来自定义实现
                list.add(null);
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return list;
    }

    /**
     * 删除表
     * @param tableName 表名
     * @throws IOException
     */
    public void deleteTable(String tableName) throws IOException {
        TableName name=TableName.valueOf(tableName);
        if(admin.tableExists(name)) {
            admin.disableTable(name);
            admin.deleteTable(name);
        }
    }

}
