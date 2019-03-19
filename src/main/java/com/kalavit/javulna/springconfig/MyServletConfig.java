/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.springconfig;

import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author peti
 */
@Configuration
public class MyServletConfig extends WebMvcConfigurerAdapter {

    public static final String[] ALLOWED_CORS_ORIGINS = {"http://localhost:3000", "http://localhost:4200"};
    public static final String CORS_MAPPNIG = "/**";
    public static final String[] ALLOWED_HEADERS = {"*"};

    @Bean
    public DozerBeanMapper dozerMapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<String> mappingFileUrls = new ArrayList<>();
        mappingFileUrls.add("dozer-bean-mappings.xml");
        mapper.setMappingFiles(mappingFileUrls);
        return mapper;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(CORS_MAPPNIG)
                .allowedOrigins(ALLOWED_CORS_ORIGINS)
                .allowedMethods("*")
                .allowedHeaders(ALLOWED_HEADERS)
                .allowCredentials(true);
    }
}
