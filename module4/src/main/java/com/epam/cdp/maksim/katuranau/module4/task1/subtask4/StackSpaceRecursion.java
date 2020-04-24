package com.epam.cdp.maksim.katuranau.module4.task1.subtask4;

public class StackSpaceRecursion {
    public static void main(String[] args) {
        recursiveFunction(1);
    }

    private static Integer recursiveFunction(Integer number) {
        return number + recursiveFunction(number + 1);
    }
}
