package com.epam.cdp.maksim.katuranau.module3.task2;

import java.util.Objects;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        implementsJavaStandardLibraryFunctionalInterfaces();
        MyFunctionalInterface myFunctionalInterfaceImplUsingAnonymousClass = new MyFunctionalInterface() {
            @Override
            public void apply() {
                System.out.println("Executing method of my functional interface using inner anonymous class...");
            }
        };
        MyFunctionalInterface myFunctionalInterfaceImplUsingLambdaExpression =
                () -> System.out.println("Executing method of my functional interface using lambda expression...");

        myFunctionalInterfaceImplUsingAnonymousClass.apply();
        myFunctionalInterfaceImplUsingLambdaExpression.apply();

        myFunctionalInterfaceImplUsingAnonymousClass.print("Print text using default method!");
        System.out.println("Get length of text using default method: "
                + myFunctionalInterfaceImplUsingLambdaExpression.getLength("Return length of string"));

        System.out.println("Check or word 'hello' exist: "
                + MyFunctionalInterface.isWordHelloExist("Hello World!"));
        System.out.println("Get Square of number 5: " + MyFunctionalInterface.getSquaredNumber(5));
    }

    private static void implementsJavaStandardLibraryFunctionalInterfaces() {
        Function<String, String> functionalInterfaceFunction = "string is "::concat;
        Predicate functionalInterfacePredicate = Objects::nonNull;
        UnaryOperator<Person> functionalInterfaceUnaryOperator = (person) -> person.setName("New Name");
        BinaryOperator<Person> functionalInterfaceBinaryOperator = Person::setFather;
        Supplier<Integer> functionalInterfaceSupplier = () -> (int) (Math.random() * 1000);
        Consumer<String> functionalInterfaceConsumer = System.out::println;
        System.out.println();
        System.out.println(functionalInterfaceFunction.apply("Hello World!!!"));
        System.out.println(functionalInterfacePredicate.test(new Person("Sasha")));
        System.out.println(functionalInterfaceUnaryOperator.apply(new Person().setFather(new Person("Igor"))));
        System.out.println(functionalInterfaceBinaryOperator.apply(new Person("Sasha"),
                new Person("Andrei")));
        System.out.println(functionalInterfaceSupplier.get());
        functionalInterfaceConsumer.accept("Hello World!!!");
    }
}
