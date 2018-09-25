package com.shimo.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 配置文件读取工具类
 *
 * @author Renta
 */

public class PropertiesUtils {

	/**
	 * 获取属性值
	 * 
	 * @param file
	 * @param key
	 * @return
	 */
    public static String getProperty(String file, String key) {
        Configuration config = null;
        try {
            config = new PropertiesConfiguration(file);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        
        if(config.getProperty(key) == null) {
        	return null;
        }else {
        	return String.valueOf(config.getProperty(key));
        }

    }

    /**
     * 更新属性值
     *
     * @param key
     * @param value
     * @param file 文件的完整路径
     */
    public static void writeProperty(String key, String value, String file) {
        
    	Properties props = new Properties();
        try {
            props.load(new FileInputStream(file));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        OutputStream output = null;
        try {
            output = new FileOutputStream(file);
            Enumeration<?> e = props.propertyNames();
            if(!e.hasMoreElements()) {
            	props.setProperty(key, value);
            }else {
            	ArrayList<?> list = Collections.list(e);
            	List<String> fList = list.stream().map(ele -> (String)ele).filter(l-> key.equals(l)).collect(Collectors.toList());
            	
            	if(fList.size() == 0) {
            		props.setProperty(key, value);
            	}else{
            		fList.forEach((v) ->{
            			props.setProperty(v, value);
            		});
            	}
            	
            }

            props.store(output, "modified" + new Date().toString() +"\n");      
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 删除指定的key
     * 
     * @param key
     * @param value
     * @param file
     */
    public static void eraseProperty(String key, String value, String file) {
    	
    	Properties props = new Properties();
    	try {
    		props.load(new FileInputStream(file));
    	} catch (FileNotFoundException e1) {
    		e1.printStackTrace();
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    	
    	OutputStream output = null;
    	try {
    		output = new FileOutputStream(file);
    		Enumeration<?> e = props.propertyNames();
    		if(e.hasMoreElements()) {
    			ArrayList<?> list = Collections.list(e);
    			List<String> fList = list.stream().map(ele -> (String)ele).filter(l-> key.equals(l)).collect(Collectors.toList());
    			if(fList.size() > 0) {
    				props.remove(key);
    			}
    		}
    		
    		props.store(output, "modified" + new Date().toString() +"\n");       // 保存键值对到文件中
    	} catch (IOException io) {
    		io.printStackTrace();
    	} finally {
    		if (output != null) {
    			try {
    				output.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
    
}
