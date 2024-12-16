package org.example.lesson4;

public class Employee {

    String name;
    int age;
    String job;

    public Employee(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getJob() {
        return job;
    }
}
