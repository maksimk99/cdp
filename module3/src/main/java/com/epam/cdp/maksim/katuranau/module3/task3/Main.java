package com.epam.cdp.maksim.katuranau.module3.task3;

public class Main {
    public static void main(String[] args) {
        ThreeFunction<String, Integer, Integer, String> threeFunctionMultiplyNumbers =
                (string, number1, number2) -> string.toUpperCase() + ", " + number1 + "*" + number2
                        + " = " + number1 * number2;
        System.out.println(threeFunctionMultiplyNumbers.apply("Maksim", 23, 17));

        ThreeFunction<Integer, Double, Integer, Double> threeFunctionComplexCalculation =
                (range, number1, number2) -> ((Math.random() * range) / number2) * number1;

        System.out.println("Result of complex calculation: "
                + threeFunctionComplexCalculation.apply(1000, 0.2, 10));
    }
}
