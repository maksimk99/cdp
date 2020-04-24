package com.epam.cdp.maksim.katuranau.module6.task1.bean;

public class BeanB {
    private String name;
    private BeanA beanA;

    public BeanB(String name, BeanA beanA) {
        this.name = name;
        this.beanA = beanA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BeanA getBeanA() {
        return beanA;
    }

    public void setBeanA(BeanA beanA) {
        this.beanA = beanA;
    }
}
