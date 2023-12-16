package edu.hw10.task2;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class CacheHandler implements InvocationHandler {

    private final Object object;
    private final List<CacheMethod> cacheMethods;

    CacheHandler(Object object) {
        this.object = object;

        cacheMethods = new ArrayList<>();
        for (var method : object.getClass().getMethods()) {
            Cache cacheAnnotation = method.getDeclaredAnnotation(Cache.class);
            if (cacheAnnotation != null) {
                cacheMethods.add(new CacheMethod(method, cacheAnnotation.persist()));
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            for (var cacheMethod : cacheMethods) {
                if (cacheMethod.method.getName().equals(method.getName())
                    && Arrays.equals(cacheMethod.method.getParameterTypes(), method.getParameterTypes())) {
                    return cacheMethod.invoke(args);
                }
            }
        }

        return method.invoke(object, args);
    }

    class CacheMethod {

        final Method method;
        final boolean persist;
        List<Query> queries = new LinkedList<>();
        final int capacity = 10;
        final long timeLimitMillis = 1000;

        CacheMethod(Method method, boolean persist) {
            this.method = method;
            this.persist = persist;
        }

        public Object invoke(Object[] args) throws InvocationTargetException, IllegalAccessException, IOException {
            for (var query : queries) {
                if (Arrays.equals(query.args(), args)) {
                    if (System.currentTimeMillis() - query.time() < timeLimitMillis) {
                        return query.result();
                    }

                    var result = method.invoke(object, args);
                    Query newQuery = new Query(args, result, System.currentTimeMillis());
                    queries.remove(query);
                    queries.add(newQuery);

                    if (persist) {
                        newQuery.saveToDisc(Path.of(method.getName()));
                    }

                    return result;
                }
            }

            var result = method.invoke(object, args);
            var query = new Query(args, result, System.currentTimeMillis());
            queries.add(query);

            if (queries.size() > capacity) {
                queries.remove(0);
            }

            if (persist) {
                query.saveToDisc(Path.of(method.getName()));
            }

            return result;
        }
    }
}
