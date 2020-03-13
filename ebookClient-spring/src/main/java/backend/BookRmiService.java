package backend;

import java.util.LinkedList;
import java.util.Map;

public interface BookRmiService {
    /**
     * RMI，根据书籍名查询详情
     */
    LinkedList<Map<Object, Object>> findAllByBookname(String name);
}
