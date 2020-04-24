package com.epam.cdp.maksim.katuranau.module4.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        try {
            Random random = new Random();
            while (true)
                list.add(random.nextInt());
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
            Thread.sleep(10000);
        }
    }
}
