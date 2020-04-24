package com.epam.cdp.maksim.katuranau.module1.task3;

public class Main {
    public static void main(String[] args) {
        DynamicArrayWithGenerics<String> array = new DynamicArrayWithGenerics<>(3);
        array.add("Pasha");
        array.add("Petya");
        System.out.println(array);
        array.add("Kolya");
        array.add("Vadim");
        array.add("Andrei");
        System.out.println("Item with id = 3: " + array.get(3));
        System.out.println(array);
        System.out.println(array.remove(3));
        System.out.println(array);
    }
}
