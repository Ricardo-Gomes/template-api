package com.oliveira.templateapi.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean(){
      //  List<String> all = Arrays.asList("*");
    	List<String> metodos = Arrays.asList("GET","POST","PUT","DELETE","OPTIONS");
        List<String> origem = Arrays.asList("http://localhost:4200");
        List<String> headers = Arrays.asList("Content-Type", "Authorization");
        
        

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(origem);
        corsConfiguration.setAllowedMethods(metodos);
        corsConfiguration.setAllowedHeaders(headers);
        corsConfiguration.setAllowedHeaders(headers);
       // corsConfiguration.setAllowedOriginPatterns(origem);
        
        

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        CorsFilter corsFilter = new CorsFilter(source);
        FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<>(corsFilter);
        filter.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return filter;
    }
}
