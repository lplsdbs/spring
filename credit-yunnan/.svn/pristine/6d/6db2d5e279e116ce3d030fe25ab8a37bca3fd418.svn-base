package com.ccb.portal.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertiesUtil {
    public static  String getPropertierByKey(String key){
        File file=new File("./sysconfig/config.properties");
        try {
            FileInputStream in=new FileInputStream(file);
            Properties pr=new Properties();
            pr.load(in);
//            ResourceBundle conf=ResourceBundle.getBundle("config");
            return pr.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
