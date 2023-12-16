package edu.hw10.task2;

import java.lang.reflect.Proxy;

public class CacheProxy {
    private CacheProxy() {
    }

    public static <T> T create(Object object, Class<T> objectClass) {
        return (T) Proxy.newProxyInstance(
            objectClass.getClassLoader(),
            new Class[] {objectClass},
            new CacheHandler(object)
        );
    }
}
