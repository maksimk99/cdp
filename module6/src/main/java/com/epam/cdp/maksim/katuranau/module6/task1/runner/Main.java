package com.epam.cdp.maksim.katuranau.module6.task1.runner;

import com.epam.cdp.maksim.katuranau.module6.task1.bean.BeanA;
import com.epam.cdp.maksim.katuranau.module6.task1.bean.BeanB;
import com.epam.cdp.maksim.katuranau.module6.task1.bean.BeanC;
import com.epam.cdp.maksim.katuranau.module6.task1.bean.BeanE;
import com.epam.cdp.maksim.katuranau.module6.task1.bean.BeanF;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        BeanA beanA = context.getBean("beanA", BeanA.class);
        BeanB beanB = context.getBean("beanB", BeanB.class);
        BeanC beanC = context.getBean("beanC", BeanC.class);
        System.out.println("Compare two instances of beanD: "
                + (beanC.createBeanD() == beanC.createBeanD() ? "equals" : "not equals"));
        BeanE beanE = context.getBean("beanE", BeanE.class);
        System.out.println("Result of replaced method: " + beanE.getInformation() + "\n");
        BeanF beanF = context.getBean("beanF", BeanF.class);
        context.close();
    }
}
