package com.gseeds.droneapp.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
//@EnableWebMvc
class MvcConfiguration implements WebMvcConfigurer{

    @Override
     void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/home.html");
        registry.addViewController("/foo.html");
    }

    @Override
    void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
    }
}
