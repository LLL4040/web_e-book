package backend.ClassLoader;

import java.io.*;

/**
 * 自定义类加载器，对加密的类进行解密
 */
public class MyClassLoader extends ClassLoader{
    private String basePath;

    public MyClassLoader() {
        super();//让系统类加载器成为该类加载器的父加载器
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = loadClassBytes(name);
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    /**
     * 加载二进制文件
     * @param name 文件名（不包括扩展名）
     * @return
     */
    private byte[] loadClassBytes(String name) {
        name = name.replace(".", "/");
        name = basePath + name + ".class";
        byte[] data = null;
        try (InputStream in = new FileInputStream(new File(name))) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int ch = 0;
            while ((ch = in.read()) != -1) {
                ch = ch ^ 0xff;
                buffer.write(ch);
            }
            in.close();
            data = buffer.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
