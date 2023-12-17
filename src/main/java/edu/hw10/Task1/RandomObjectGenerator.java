package edu.hw10.Task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

public class RandomObjectGenerator {

    private static int incrementInt = 0;
    private static final String DEFAULT_STRING = "defaultName";

    public <T> T nextObject(Class<T> currentClass)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return nextObject(currentClass, "");
    }

    public <T> T nextObject(Class<T> currentClass, String constructor)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        return generateInstance(currentClass, constructor);

    }

    private <T> T generateInstance(Class<T> currentClass, String constructor)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?>[] constructors = currentClass.getDeclaredConstructors();

        Constructor<?> selectedConstructor = null;
        for (Constructor<?> c : constructors) {
            if (constructor.equals("") || c.getName().equals(constructor)) {
                selectedConstructor = c;
                break;
            }
        }

        if (selectedConstructor != null) {
            selectedConstructor.setAccessible(true);
            Class<?>[] parameterTypes = selectedConstructor.getParameterTypes();
            Object[] args = new Object[parameterTypes.length];

            for (int i = 0; i < parameterTypes.length; i++) {
                args[i] = generateValue(parameterTypes[i], selectedConstructor.getParameters()[i]);
            }
            return currentClass.cast(selectedConstructor.newInstance(args));
        } else {
            throw new NoSuchMethodException();
        }
    }

    private Object generateValue(Class<?> type, Parameter parameter) {
        Object value = generateDefaultValue(type);

        NotNull notNullAnnotation = parameter.getAnnotation(NotNull.class);

        if (notNullAnnotation != null && value == null) {
            throw new IllegalStateException();
        }

        Min minAnnotation = parameter.getAnnotation(Min.class);
        Max maxAnnotation = parameter.getAnnotation(Max.class);

        if (minAnnotation != null && maxAnnotation != null) {
            int minValue = Math.max(minAnnotation.value(), (int) value);
            int maxValue = Math.min(maxAnnotation.value(), (int) value);
            return Math.max(minValue, Math.min(maxValue, (int) value));
        } else if (minAnnotation != null) {
            return Math.max(minAnnotation.value(), (int) value);
        } else if (maxAnnotation != null) {
            return Math.min(maxAnnotation.value(), (int) value);
        } else {
            return value;
        }
    }

    private Object generateDefaultValue(Class<?> type) {

        if (type.equals(int.class)) {
            return incrementInt++;
        }
        if (type.equals(String.class)) {
            return DEFAULT_STRING + incrementInt;
        }

        return null;
    }

}
