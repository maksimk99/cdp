package com.epam.cdp.maksim.katuranau.module6.task2.config;

import com.epam.cdp.maksim.katuranau.module6.task2.service.RandomNumberParserFactory;
import com.epam.cdp.maksim.katuranau.module6.task2.spel.RandomNumberFromOneValueSpelExpressionParser;
import com.epam.cdp.maksim.katuranau.module6.task2.spel.RandomNumberFromTwoValuesSpelExpressionParser;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RandomNumberParserConfig {

    @Bean(name = "randomNumberFromTwoValuesParser")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RandomNumberFromTwoValuesSpelExpressionParser randomNumberFromOneValueParser() {
        return new RandomNumberFromTwoValuesSpelExpressionParser();
    }

    @Bean(name = "randomNumberFromOneValueParser")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RandomNumberFromOneValueSpelExpressionParser randomNumberFromTwoValuesParser() {
        return new RandomNumberFromOneValueSpelExpressionParser();
    }

    @Bean
    public FactoryBean serviceLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(RandomNumberParserFactory.class);
        return factoryBean;
    }
}
