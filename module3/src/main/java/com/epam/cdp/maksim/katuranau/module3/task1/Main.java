package com.epam.cdp.maksim.katuranau.module3.task1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>() {{
            add(new Person("Petya", 24));
            add(new Person("Katya", 21));
            add(new Person("Andrei", 22));
            add(new Person("Sasha", 19));
        }};

        Comparator<Person> personComparatorByName = (person1, person2) -> person1.getName().compareTo(person2.getName());
        Comparator<Person> personComparatorByAge = (person1, person2) -> person1.getAge() - person2.getAge();
        System.out.println("Sort persons by name:");
        personList.stream().sorted(personComparatorByName).forEach(System.out::println);
        System.out.println("Sort persons by age:");
        personList.stream().sorted(personComparatorByAge).forEach(System.out::println);
    }
}
