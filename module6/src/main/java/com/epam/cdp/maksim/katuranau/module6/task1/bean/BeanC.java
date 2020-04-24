package com.epam.cdp.maksim.katuranau.module6.task1.bean;

public abstract class BeanC {
    private String name;
    private int size;
    private BeanD beanD;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BeanD getBeanD() {
        return beanD;
    }

    public void setBeanD(BeanD beanD) {
        this.beanD = beanD;
    }

    public abstract BeanD createBeanD();
}
