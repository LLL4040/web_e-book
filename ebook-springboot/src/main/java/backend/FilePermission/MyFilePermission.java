package backend.FilePermission;

import java.security.Permission;
import java.util.Arrays;
import java.util.List;

/**
 * 控制文件读写路径的权限类
 */
public class MyFilePermission extends Permission {
    private String action;
    /**
     * 构造函数
     * @param path 文件路径
     * @param action read or write
     */
    public MyFilePermission(String path, String action) {
        super(path);
        this.action = action;
    }

    @Override
    public String getActions() {
        return action;
    }

    public List<String> pathArray() {
        return Arrays.asList(getName().split("/"));
    }

    @Override
    public boolean equals(Object other) {
        if(other == null) {
            return false;
        }
        if(!getClass().equals(other.getClass())) {
            return false;
        }
        MyFilePermission o = (MyFilePermission) other;
        if(!action.equals(o.action)) {
            return false;
        }
        return getName().equals(o.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode() + action.hashCode();
    }

    @Override
    public boolean implies(Permission other) {
        if(!(other instanceof MyFilePermission)) {
            return false;
        }
        MyFilePermission o = (MyFilePermission) other;
        if(action.equals("read") && o.action.equals("read")) {
            List<String> path = pathArray(), oPath = o.pathArray();
            // "/tmp/*"不蕴含"/tmp/a/b.txt"
            if(path.size() != oPath.size()) {
                return false;
            }
            if(!path.get(path.size() - 1).equals("*") && !path.get(path.size() - 1).equals("-")) {
                return path.equals(oPath);
            }
            for(int i = 0; i < path.size() - 1; i++) {
                if(!path.get(i).equals(oPath.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
