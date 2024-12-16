package org.example.lesson4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(5,2,10,9,4,3,10,1,13);
        List<String> words = Arrays.asList("hello", "world", "this", "is", "a", "test");
        String[][] strings = {{"apple", "banana", "mate", "date", "sport"},
                {"grape", "honeydew", "kiwi", "lemon", "mango"},
                {"nectarine", "orange", "papaya", "quince", "raspbery"}};
        List<Employee> employees = Arrays.asList(
                new Employee("Наташа", 30, "Тестировщица"),
                new Employee("Света", 28, "Менеджер"),
                new Employee("Марина", 31, "Тестировщица"),
                new Employee("Настя", 35, "Тестировщица"),
                new Employee("Оля", 25, "Стажер")
        );


        System.out.println("1. Удаление дубликатов из листа:");
        System.out.println(numbers.stream().distinct().collect(Collectors.toList()));


        System.out.println("2. 3-е наибольшее число:");
        System.out.println(numbers.stream().sorted(Comparator.reverseOrder()).skip(2).findFirst().orElse(0));


        System.out.println("3. 3-е наибольшее уникальное число:");
        System.out.println(numbers.stream().sorted(Comparator.reverseOrder()).distinct().skip(2).findFirst().orElse(0));


        System.out.println("4. 3 самых старших Тестировщицы:");
        System.out.println(employees.stream()
                .filter(e -> e.getJob().equals("Тестировщица"))
                .sorted(Comparator.comparingInt(Employee::getAge).reversed())
                .limit(3)
                .map(Employee::getName)
                .collect(Collectors.joining(", ")));


        System.out.println("5. Средний возраст Тестировщиц:");
        System.out.println(employees.stream().filter(e -> e.getJob().equals("Тестировщица")).mapToInt(Employee::getAge).average().orElse(0));


        System.out.println("6. Самое длинное слово:");
        System.out.println(words.stream().max(Comparator.comparingInt(String::length)).orElse(""));


        System.out.println("7. HashMap и счетчик:");
        System.out.println(words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));


        System.out.println("8. Печать строк в порядке длины и алфавита:");
        words.stream().sorted(Comparator.comparingInt(String::length).thenComparing(String::compareTo)).forEach(System.out::println);


        System.out.println("9. Самое длинное слово в массиве строк:");
        System.out.println(Arrays.stream(strings).flatMap(Arrays::stream).max(Comparator.comparingInt(String::length)).orElse(""));

    }

}
