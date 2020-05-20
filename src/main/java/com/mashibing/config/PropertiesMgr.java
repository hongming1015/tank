package com.mashibing.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesMgr {

    private PropertiesMgr() {
    }

    Properties properties = new Properties();

    static class Instance{
        private static PropertiesMgr instance = new PropertiesMgr();
    }

    public static PropertiesMgr getInstance() {
        return Instance.instance;
    }

    {
        try {
            InputStream resourceAsStream = PropertiesMgr.class.getClassLoader().getResourceAsStream("config/application.properties");
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getString(String key) {
        Object pro = properties.get(key);
        if (pro == null) {
            throw new RuntimeException("没有获取到键" + key);
        }
        return pro.toString();
    }

    public Integer getInt(String key) {
        Object pro = properties.get(key);
        if (pro == null) {
            throw new RuntimeException("没有获取到键" + key);
        }
        return new Integer(pro.toString());
    }

}
