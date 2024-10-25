package com.luoxin.customclassloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * 自定义 ClassLoader，打破双亲委派机制，核心在于重载 loadClass 方法
 */
class CustomClassLoader extends ClassLoader {
    private String classPath;

    public CustomClassLoader(String classPath) {
        this.classPath = classPath;
    }

//    public MyClassLoader(ClassLoader parent, String classPath) {
//        super(parent);
//        this.classPath = classPath;
//    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();

                try {
                    // 核心的区别，在这里将 com.luoxin 开头的类打破双亲委派机制
                    if (this.getParent() != null && !name.startsWith("com.luoxin")) {
                        c = this.getParent().loadClass(name);
                    } else {
                        c = findClass(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes;
        try {
            bytes = loadClassBytes(name);
        } catch (IOException e) {
            throw new ClassNotFoundException("Failed to load class " + name, e);
        }
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassBytes(String className) throws IOException {
        String classFilePath = classPath + className.replace('.', '\\') + ".class";
        Path path = Paths.get(classFilePath);
        return Files.readAllBytes(path);
    }
}