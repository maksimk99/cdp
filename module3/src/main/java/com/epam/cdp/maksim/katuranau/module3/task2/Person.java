package com.epam.cdp.maksim.katuranau.module3.task2;

public class Person {
    private String name;
    private Person father;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Person getFather() {
        return father;
    }

    public Person setFather(Person father) {
        this.father = father;
        return this;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", father='" + father.getName()
                + "'}";
    }
}
