package com.epam.cdp.maksim.katuranau.module5.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@Configuration
@Import(GeneralConfiguration.class)
@ComponentScan(basePackages = "com.epam.cdp.maksim.katuranau.module5", excludeFilters={
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value=JavaConfig.class)})
public class AnnotationConfig {
}
