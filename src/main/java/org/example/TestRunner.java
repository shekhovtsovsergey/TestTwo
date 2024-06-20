package org.example;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestRunner {

    public static void main(String[] args) {
        runTests(TestClass.class);
    }

    public static void runTests(Class<?> testClass) {
        try {
            Class.forName(testClass.getName());
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден: " + testClass.getName());
            e.printStackTrace();
            return;
        }
        if (!validateAnnotations(testClass)) {
            return;
        }
        executeMethods(testClass);
    }



    private static boolean validateAnnotations(Class<?> testClass) {
        long beforeSuiteCount = countMethodsWithAnnotation(testClass, BeforeSuite.class);
        long afterSuiteCount = countMethodsWithAnnotation(testClass, AfterSuite.class);

        if (beforeSuiteCount > 1) {
            System.err.println("Класс " + testClass.getName() + " содержит более одного метода с аннотацией @BeforeSuite");
            return false;
        }

        if (afterSuiteCount > 1) {
            System.err.println("Класс " + testClass.getName() + " содержит более одного метода с аннотацией @AfterSuite");
            return false;
        }
        return true;
    }

    private static void executeMethods(Class<?> testClass) {
        try {
            executeMethodWithAnnotation(testClass, BeforeSuite.class);
            List<Method> testMethods = findMethodsWithAnnotation(testClass, Test.class);
            testMethods.sort(Comparator.comparingInt((Method m) -> m.getAnnotation(Test.class).priority()).reversed());
            for (Method method : testMethods) {
                executeMethod(testClass, method);
            }
            executeMethodWithAnnotation(testClass, AfterSuite.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeMethodWithAnnotation(Class<?> testClass, Class<? extends java.lang.annotation.Annotation> annotationClass) throws Exception {
        Method method = findMethodWithAnnotation(testClass, annotationClass);
        if (method != null) {
            executeMethod(testClass, method);
        }
    }

    private static Method findMethodWithAnnotation(Class<?> testClass, Class<? extends java.lang.annotation.Annotation> annotationClass) {
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                return method;
            }
        }
        return null;
    }

    private static List<Method> findMethodsWithAnnotation(Class<?> testClass, Class<? extends java.lang.annotation.Annotation> annotationClass) {
        List<Method> methods = new ArrayList<>();
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                methods.add(method);
            }
        }
        return methods;
    }

    private static long countMethodsWithAnnotation(Class<?> testClass, Class<? extends java.lang.annotation.Annotation> annotationClass) {
        return findMethodsWithAnnotation(testClass, annotationClass).size();
    }

    private static void executeMethod(Class<?> testClass, Method method) throws Exception {
        Object instance = testClass.getDeclaredConstructor().newInstance();
        method.setAccessible(true);
        method.invoke(instance);
    }

}
