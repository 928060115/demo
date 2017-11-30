package com.example.demo.configurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: rogue
 * @Description:  配置拦截器，json转换器
 * @Package: com.example.demo.configurer
 * @Date: 2017/11/30
 * @Time: 13:34
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{

    /**
     * @Author: rogue
     * @Description: 修改自定义消息转换器
     * @ClassName: FastJsonConfiguration
     * @Date: 2017/11/30
     * @Time: 10:32
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        /**
         * @MethodName: configureMessageConverters
         * @Param: [converters] 消息转换器列表
         */
        //调用父类的配置
        super.configureMessageConverters(converters);
        //创建fastjson消息转换器
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //修改配置返回内容的过滤
        /**
         * WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
         * WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
         * DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
         * WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
         * WriteMapNullValue：是否输出值为null的字段,默认为false。
         */
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero
        );
        //处理中文乱码问题
        List<MediaType> fastMediaType = new ArrayList<>();
        fastMediaType.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaType);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //将fastjson添加到视图消息转换器列表
        converters.add(fastJsonHttpMessageConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/*");
    }
}
