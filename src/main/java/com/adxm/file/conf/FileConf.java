package com.adxm.file.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConf implements WebMvcConfigurer {

    @Value("${file.file-host}")
    private String fileHost;

    @Value("${file.file-path}")
    private String filePath;

    @Value("${file.file-folder-mapping}")
    private String fileFolderMapping;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(fileFolderMapping + "**").addResourceLocations("file:" + filePath);
    }

}