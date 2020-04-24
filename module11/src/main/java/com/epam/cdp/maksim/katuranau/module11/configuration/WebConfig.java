package com.epam.cdp.maksim.katuranau.module11.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

@Configuration
@EnableWebMvc
@PropertySource("classpath:sql-query.properties")
@ComponentScan("com.epam.cdp.maksim.katuranau.module11")
public class WebConfig implements WebMvcConfigurer {

    private static final String STATIC_PATH_MATCHING = "/static/**";
    private static final String STATIC_PATH = "/static/";

    private LocaleChangeInterceptor localeChangeInterceptor;
    private ThemeChangeInterceptor themeChangeInterceptor;

    @Autowired
    public WebConfig(final LocaleChangeInterceptor localeChangeInterceptor,
                     final ThemeChangeInterceptor themeChangeInterceptor) {
        this.localeChangeInterceptor = localeChangeInterceptor;
        this.themeChangeInterceptor = themeChangeInterceptor;
    }

    /**
     * Create MultipartResolver to upload files in html form.
     *
     * @return multipartResolver
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10000000);
        return multipartResolver;
    }

    /**
     * Local validator factory bean validator.
     * Enable bean validation
     *
     * @return the validator
     */
    @Bean
    public Validator getValidator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Gets rest template.
     * This class used to work with rest api
     *
     * @return the rest template
     */
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler(STATIC_PATH_MATCHING)
                .addResourceLocations(STATIC_PATH);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(themeChangeInterceptor);
    }
}

