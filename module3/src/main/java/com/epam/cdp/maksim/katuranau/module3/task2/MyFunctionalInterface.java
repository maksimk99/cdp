package com.epam.cdp.maksim.katuranau.module3.task2;

public interface MyFunctionalInterface {
    void apply();

    default void print(String string) {
        System.out.println(string);
    }

    default int getLength(String string) {
        return string.length();
    }

    static boolean isWordHelloExist(String string) {
        return string.toLowerCase().contains("hello");
    }

    static int getSquaredNumber(int number) {
        return number * number;
    }
}
