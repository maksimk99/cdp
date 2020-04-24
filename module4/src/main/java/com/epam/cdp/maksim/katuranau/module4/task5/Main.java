package com.epam.cdp.maksim.katuranau.module4.task5;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /*
    -XX:+UseG1GC -Xmx100m
    -XX:+UseG1GC -XX:+UseStringDeduplication -Xmx100m
     */
    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();
        for (int i = 0; ; i++) {
            for (int j = 0; j < 1000; j++) {
                String text = "Some spring";
                stringList.add(text + j);
            }
            System.out.println("Iteration " + i);
        }
    }

}
