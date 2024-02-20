package com.camelot.util;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

/**
 * @author Lord Camelot
 * @date 2024/2/7
 * @description
 */
public class PropertiesUtil {

    private static Properties properties = new Properties();

    static {
        YamlPropertiesFactoryBean yamlMapFactoryBean = new YamlPropertiesFactoryBean();
        // 可以加载多个yml文件
        yamlMapFactoryBean.setResources(new ClassPathResource("application.yml"));
        properties = yamlMapFactoryBean.getObject();
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultVale) {
        return properties.getProperty(key, defaultVale);
    }
}
