package com.epam.cdp.maksim.katuranau.module6.task1.bean;

public class BeanF {
    private String name;
    private int size;

    public BeanF(String name) {
        System.out.println("Bean BeanF constructor is invoked");
        this.name = name;
    }

    public void setSize(int size) {
        System.out.println("Bean BeanF setter is invoked");
        this.size = size;
    }

    public void initializeMe() {
        System.out.println("Bean BeanF initialization phase");
    }

    public void destroyMe() {
        System.out.println("Bean BeanF destroy phase");
    }
}
