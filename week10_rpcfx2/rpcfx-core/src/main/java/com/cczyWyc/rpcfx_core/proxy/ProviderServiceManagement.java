package com.cczyWyc.rpcfx_core.proxy;

import com.cczyWyc.rpcfx_core.annotation.ProviderService;
import com.cczyWyc.rpcfx_core.api.RpcRequest;
import com.cczyWyc.rpcfx_core.discovery.DiscoveryServer;
import com.google.common.base.Joiner;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * provider service manager
 * init provider service, put instance to map
 *
 * @author wangyc
 */
public class ProviderServiceManagement {

    /** proxy map.
     * key:service:group:version
     * value:class */
    private static Map<String, Object> proxyMap = new HashMap<>();

    public static void init(String packageName, int port) throws IOException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        System.out.println("-----Load rpc provider class start-----");

        DiscoveryServer serviceRegister = new DiscoveryServer();
        Class[] classes = getClasses(packageName);
        for (Class c : classes) {
            ProviderService annotation = (ProviderService) c.getAnnotation(ProviderService.class);
            if (annotation == null) {
                continue;
            }
            String group = annotation.group();
            String version = annotation.version();
            List<String> tags = Arrays.asList(annotation.tags().split(","));
            String provider = Joiner.on(":").join(annotation.service(), group, version);
            int weight = annotation.weight();
            proxyMap.put(provider, c.newInstance());
        }
    }

    /**
     * provider service
     *
     * @param rpcRequest rpc request
     * @return interface implement instance
     */
    public static Object getProviderService(RpcRequest rpcRequest) {
        String group = "default";
        String version = "default";
        String className = rpcRequest.getServiceClass();
        if (rpcRequest.getGroup() != null) {
            group = rpcRequest.getGroup();
        }
        if (rpcRequest.getVersion() != null) {
            version = rpcRequest.getVersion();
        }
        return proxyMap.get(Joiner.on(":").join(className, group, version));
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName the base package
     * @return the classes
     * @throws IOException exception
     * @throws ClassNotFoundException exception
     */
    private static Class[] getClasses(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            dirs.add(new File(url.getFile()));
        }
        List<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[0]);
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory the base directory
     * @param packageName the package name for classes found inside the base directory
     * @return the classes
     * @throws ClassNotFoundException exception
     */
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
