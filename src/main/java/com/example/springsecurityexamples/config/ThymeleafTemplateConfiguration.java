package com.example.springsecurityexamples.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@RequiredArgsConstructor
public class ThymeleafTemplateConfiguration {

    protected final static String TEMPLATE_PREFIX = "/templates/";


    private final ThymeleafProperties properties;

//    @Bean
//    public TemplateEngine appTemplateEngine() {
//        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.addTemplateResolver(htmlTemplateResolver());
//        templateEngine.addTemplateResolver(txtTemplateResolver());
//        return templateEngine;
//    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(htmlTemplateResolver());
        return templateEngine;
    }

    private ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(1));
        templateResolver.setPrefix(TEMPLATE_PREFIX);
        templateResolver.setSuffix(ThymeleafProperties.DEFAULT_SUFFIX);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(properties.getEncoding().name());
        templateResolver.setCacheable(properties.isCache());
        return templateResolver;
    }

}
