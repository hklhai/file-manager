package com.hxqh.filemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;


/**
 * Created by Ocean lin on 2018/10/30.
 *
 * @author Ocean lin
 */
@Configuration
public class CustomerConfig {

    /**
     * 制定jsckson工具转换时对日期格式的处理
     *
     * @return
     */
    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return mapper;
    }
}