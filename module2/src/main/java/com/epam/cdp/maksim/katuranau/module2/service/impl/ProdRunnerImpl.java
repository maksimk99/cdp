package com.epam.cdp.maksim.katuranau.module2.service.impl;

import com.epam.cdp.maksim.katuranau.module2.annotation.ProdCode;
import com.epam.cdp.maksim.katuranau.module2.service.ProdRunner;
import com.epam.cdp.maksim.katuranau.module2.service.Square;

public class ProdRunnerImpl implements ProdRunner {

    @Deprecated
    public void firstMethod() {
        System.out.println("First method is invoked");
    }

    @ProdCode
    public void secondMethod() {
        System.out.println("Second method is invoked");
    }

    public void thirdMethod() {
        System.out.println("Third method is invoked");
    }

    public void fourthMethod() {
        System.out.println("Fourth method is invoked");
    }

    @ProdCode
    public void fifthMethod() {
        System.out.println("Fifth method is invoked");
    }

    @ProdCode
    public void sixthMethod() {
        System.out.println("Sixth method is invoked");
        int a = 5;
        Square s = (int x)->x*x;
        System.out.println("Square of five: " + s.calculate(a));
    }
}

