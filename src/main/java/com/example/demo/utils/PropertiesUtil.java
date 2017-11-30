package com.example.demo.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.utils
 * @Date: 2017/11/30
 * @Time: 14:24
 */
public class PropertiesUtil {
    private final ResourceBundle resource;
    private final String fileName;

    /**
     * @Author: rogue
     * @Description: 构造函数实例化部分对象，获取文件资源对象
     * @ClassName: PropertiesUtil
     * @Date: 2017/11/30
     * @Time: 14:26
     */
    public PropertiesUtil(String fileName){
        this.fileName = fileName;
        Locale locale = new Locale("zh","CN");
        this.resource = ResourceBundle.getBundle(this.fileName,locale);
    }

    //根据传入的key获取对象的值
    public String getValue(String key){
        String value = this.resource.getString(key);
        return value;
    }
}
