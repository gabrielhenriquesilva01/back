package com.senai.projetosa.sessao;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;

@Configuration
public class FiltroConfig {

    @Bean
    public FilterRegistrationBean<FiltroAutenticacao> filtroAutenticacao() {
        FilterRegistrationBean<FiltroAutenticacao> registration = new FilterRegistrationBean<>();
        registration.setFilter(new FiltroAutenticacao());
        registration.addUrlPatterns("/home/*", "/produtolista/*");
        registration.setOrder(1);
        return registration;
        }
    }