package com.chadi.dbcompare.utils.cp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName JsonUtil
 * @Description TODO
 * @Author XuDong
 * @Date 2020/12/17 15:39
 * @Version 1.0
 **/
public class JsonUtil {

    public JsonUtil() {

    }

    public static Object jsonToBean(String jsonStr, String className) {
        Class<?> claz = CacheUtil.getClassForName(className);
        return JSON.parseObject(jsonStr, claz);
    }

    public static <T> T jsonToBean(String jsonStr, Class<T> targetClass) {
        return JSON.parseObject(jsonStr, targetClass);
    }

    public static String beanToJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static JSONObject parseObject(String jsonStr) {
        return JSON.parseObject(jsonStr);
    }
}
