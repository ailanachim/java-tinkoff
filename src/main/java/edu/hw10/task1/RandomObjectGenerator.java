package edu.hw10.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class RandomObjectGenerator {

    public Object nextObject(Class<?> tClass, String methodName)
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        var methods = tClass.getMethods();
        for (var method : methods) {
            if (method.getName().equals(methodName)) {
                var paramTypes = method.getParameterTypes();
                var annotations = method.getParameterAnnotations();

                var args = new Object[paramTypes.length];
                for (int i = 0; i < args.length; i++) {
                    args[i] = getObject(paramTypes[i], annotations[i]);
                }

                return method.invoke(null, args);
            }
        }

        throw new NoSuchMethodException(methodName);
    }

    public Object nextObject(Class<?> tClass)
        throws InvocationTargetException, IllegalAccessException, InstantiationException {

        for (var constructor : tClass.getConstructors()) {
            if (canCreateInstance(constructor)) {
                return newInstance(constructor);
            }
        }

        if (tClass.isPrimitive()) {
            return getObject(tClass, new Annotation[] {});
        }

        throw new InstantiationException(tClass.toString());
    }

    private Object newInstance(Constructor<?> constructor)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        var types = constructor.getParameterTypes();
        var annotations = constructor.getParameterAnnotations();

        var args = new Object[types.length];
        for (int i = 0; i < args.length; i++) {
            args[i] = getObject(types[i], annotations[i]);
        }
        return constructor.newInstance(args);
    }

    private boolean canCreateInstance(Constructor<?> constructor) {
        var params = constructor.getParameterTypes();
        var annotations = constructor.getParameterAnnotations();
        for (int i = 0; i < params.length; i++) {
            if (!params[i].isPrimitive() && !hasNoArgsConstructor(params[i]) && hasNotNull(annotations[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean hasNoArgsConstructor(Class<?> tClass) {
        for (var constructor : tClass.getConstructors()) {
            if (constructor.getParameterCount() == 0) {
                return true;
            }
        }

        return false;
    }

    private boolean hasNotNull(Annotation[] annotations) {
        for (var annotation : annotations) {
            if (annotation instanceof NotNull) {
                return true;
            }
        }

        return false;
    }

    private Object getObject(Class<?> tClass, Annotation[] annotations)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {

        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        for (var annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = minAnnotation.value();
            } else if (annotation instanceof Max maxAnnotation) {
                max = maxAnnotation.value();
            }
        }

        if (tClass.isPrimitive()) {
            return generatePrimitive(tClass, min, max);
        }

        try {
            return tClass.getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    @SuppressWarnings("ReturnCount")
    private Object generatePrimitive(Class<?> tClass, int min, int max) {
        Random random = new Random();

        if (tClass.equals(int.class)) {
            return random.nextInt(min, max);
        } else if (tClass.equals(float.class)) {
            return random.nextFloat(min, max);
        } else if (tClass.equals(boolean.class)) {
            return random.nextBoolean();
        } else if (tClass.equals(long.class)) {
            return random.nextLong(min, max);
        } else if (tClass.equals(byte.class)) {
            return (byte) random.nextInt(Math.max(min, Byte.MIN_VALUE), Math.min(max, Byte.MAX_VALUE));
        } else if (tClass.equals(double.class)) {
            return random.nextDouble(min, max);
        } else if (tClass.equals(char.class)) {
            return (char) random.nextInt(Math.max(min, Character.MIN_VALUE), Math.min(max, Character.MAX_VALUE));
        } else {
            return (short) random.nextInt(Math.max(min, Short.MIN_VALUE), Math.min(max, Short.MAX_VALUE));
        }
    }
}
