package com.chadi.dbcompare.utils;

import net.sf.ehcache.util.PropertyUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropertyUtils {
    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private static Properties props;
    static{
        loadProps();
    }

    synchronized static private void loadProps(){
        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
//<!--第一种，通过类加载器进行获取properties文件流-->
                    in = PropertyUtil.class.getClassLoader().getResourceAsStream("compare.properties");
//<!--第二种，通过类进行获取properties文件流-->
                    //in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
                    props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("jdbc.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("jdbc.properties文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成...........");
        logger.info("properties文件内容：" + props);
    }

    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }

    /**
     * @description: 获取含有=的配置文件属性
     * @param key
     * @return: java.util.Map
     * @author: XuDong
     * @time: 2020/12/7 14:09
     */
    public static Map getPropertyToMap(String key){

        Map map = new HashMap();
        String property = PropertyUtils.getProperty(key);

        if (StringUtils.isEmpty(property)) {
            logger.error("未获取到" + key + "属性");
        }

        String[] split = property.split(",");

        for (String s : split) {

            String[] split1 = s.split("=");
            if(split1.length == 2){
                map.put(split1[0], split1[1]);
            }
        }
        return map;
    }

    /**
     * @description: 获取,分隔的配置文件属性
     * @param key
     * @return: java.util.Map
     * @author: XuDong
     * @time: 2020/12/7 14:09
     */
    public static List getPropertyToList(String key){

        Map map = new HashMap();
        String property = PropertyUtils.getProperty(key);

        if (StringUtils.isEmpty(property)) {
            logger.error("未获取到" + key + "属性");
        }

        String[] split = property.split(",");

        return Arrays.asList(split);
    }
}
