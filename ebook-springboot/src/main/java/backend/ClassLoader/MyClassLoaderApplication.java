package backend.ClassLoader;

/**
 * 负责启动MyClassLoader
 */
public class MyClassLoaderApplication {
    public static void main(String[] args) throws Exception {
        System.out.println(args.length);
        if(args.length != 2) {
            System.out.println("USAGE: java MyClassLoaderApplication basePath classname");
            return;
        }
        MyClassLoader loader = new MyClassLoader();
        loader.setBasePath(args[0]);
        Class<?> clazz = loader.loadClass(args[1]);
        System.out.println(clazz.getClassLoader());
    }
}
