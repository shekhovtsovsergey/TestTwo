package org.example;

public class TestClass {

    @BeforeSuite
    public static void beforeSuite() {
        System.out.println("Запуск метода с аннотацией @BeforeSuite");
    }

    @Test(priority = 1)
    public void testMethod1() {
        System.out.println("Запуск тестового метода 1 (приоритет 1)");
    }

    @Test(priority = 10)
    public void testMethod2() {
        System.out.println("Запуск тестового метода 2 (приоритет 10)");
    }

    @Test
    public void testMethod3() {
        System.out.println("Запуск тестового метода 3 (приоритет по умолчанию)");
    }

    @AfterSuite
    public static void afterSuite() {
        System.out.println("Запуск метода с аннотацией @AfterSuite");
    }
}
