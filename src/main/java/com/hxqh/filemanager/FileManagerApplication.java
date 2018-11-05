package com.hxqh.filemanager;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lin
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.hxqh.filemanager.**.*")
public class FileManagerApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(FileManagerApplication.class, args);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        //定义一个转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //添加fastjson的配置信息 比如 ：是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        List<MediaType> fastMediaTypes = new ArrayList<>();

        // 处理中文乱码问题
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);

        //在转换器中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        //将转换器添加到converters中
        converters.add(fastConverter);
    }

}
