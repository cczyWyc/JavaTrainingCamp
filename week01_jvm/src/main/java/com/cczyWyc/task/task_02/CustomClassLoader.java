package com.cczyWyc.task.task_02;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * custom classloader
 * @author wangyc
 */
public class CustomClassLoader extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> customClass = new CustomClassLoader().loadClass("Hello");
        Method customClassMethod = customClass.getMethod("hello");
        customClassMethod.invoke(customClass.newInstance());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //load xlass
        Path path = Paths.get("week01_jvm/src/main/java/com/cczyWyc/task/task_02/Hello.xlass");
        byte[] bytes = new byte[256];
        try {
            bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        System.out.println(bytes.length);
        return defineClass(name, bytes, 0, bytes.length);
    }
}
