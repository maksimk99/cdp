package com.epam.cdp.maksim.katuranau.module1.task2;

public class Main {
    public static void main(String[] args) {
        DynamicDoubleArray array = new DynamicDoubleArray();
        array.add(2.3);
        array.add(7.5);
        array.add(2.6);
        array.add(8.9);
        array.add(4.1);
        System.out.println("Item with id = 3: " + array.get(3));
        System.out.println(array);
        array.remove(3);
        System.out.println(array);
    }
}
