package com.epam.cdp.maksim.katuranau.module6.task2.bean_post_processor;

import com.epam.cdp.maksim.katuranau.module6.task2.model.Salary;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if (o instanceof Salary) {
            Salary salary = (Salary) o;
            salary.setSalaryAmount(salary.getSalaryAmount().subtract(BigDecimal.TEN));
        }
        return o;
    }
}
