package com.hxqh.filemanager.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
/**
 * Created by Ocean lin on 2018/10/15.
 * <p>
 * response.setHeader("Access-Control-Max-Age", "3600");
 * response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
 *
 * @author Ocean lin
 */

@Configuration
public class CorsConfig  {
    final static org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CorsConfig.class);

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }


}
