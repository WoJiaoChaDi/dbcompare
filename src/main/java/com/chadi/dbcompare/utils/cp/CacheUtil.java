package com.chadi.dbcompare.utils.cp;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName CacheUtil
 * @Description TODO
 * @Author XuDong
 * @Date 2020/12/17 15:42
 * @Version 1.0
 **/
public class CacheUtil {
    private static final Log log = LogFactory.getLog(CacheUtil.class);
    private static final Map<String, Class<?>> CLASS_CACHE = new ConcurrentHashMap<>(1000);
    private static final Map<String, Charset> CHARSET_CACHE = new HashMap<>();


    public static Class<?> getClassForName(String className) {
        Class<?> targetClass = (Class) CLASS_CACHE.get(className);
        if (targetClass == null) {
            try {
                targetClass = Class.forName(className);
                CLASS_CACHE.put(className, targetClass);
            } catch (Throwable var3) {
                throw new RuntimeException("无法找到指定的类型，className=" + className, var3);
            }
        }
        return targetClass;
    }

    public static Charset getCharset(String charsetName) {
        if (!StringUtils.hasText(charsetName)) {
            return null;
        }else{
            charsetName = charsetName.toUpperCase();
            Charset targetCharset = (Charset) CHARSET_CACHE.get(charsetName);
            if (targetCharset == null) {
                try {
                    targetCharset = Charset.forName(charsetName);
                    CHARSET_CACHE.put(charsetName, targetCharset);
                } catch (Exception var3) {
                    log.error("CacheUtil Method getCharset fail... charsetName:" + charsetName, var3);
                }
            }
            return targetCharset;
        }
    }
}
