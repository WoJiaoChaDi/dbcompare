package com.chadi.dbcompare.utils;

import com.chadi.dbcompare.bean.DbBaseObj;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompareUtils {

    final static Logger logger = LoggerFactory.getLogger(CompareUtils.class);

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

    /**
     * @description: 字符串去掉下划线_并变成驼峰命名
     * @param str
     * @return: java.lang.String
     * @author: XuDong
     * @time: 2020/12/7 14:17
     */
    public static String strTrimLowlineAndRenameHump(String str){
        if (StringUtils.isEmpty(str)) {
            logger.error("字符串为空！");
        }

        //去除首尾__
        while(str.startsWith("_")){
            str = str.substring(1, str.length());
        }
        while(str.endsWith("_")){
            str = str.substring(0, str.length() - 1);
        }

        //获取新的驼峰字符串
        str = str.toLowerCase();
        int index_ = str.indexOf("_");

        String newStr = "";
        String newStrStart = "";
        String newStrMiddle = "";
        String newStrEnd = "";

        while (index_ != -1) {
            newStrStart = str.substring(0, index_);
            newStrMiddle = str.substring(index_+1, index_ + 2).toUpperCase();
            newStrEnd = str.substring(index_ + 2, str.length());
            newStr = newStrStart + newStrMiddle + newStrEnd;
            index_ = newStr.indexOf("_");
        }

        return newStr;
    }

    public static List<? extends DbBaseObj> compareList(List<? extends DbBaseObj> baseList, List<? extends DbBaseObj> targetList, List<String> compareCols) throws IllegalAccessException {
        //循环基础数据库
        for (DbBaseObj baseObj : baseList) {

            //循环"比较字段"
            for (String compareCol : compareCols) {
                //将"比较字段"转为驼峰
                String matchCol = CompareUtils.strTrimLowlineAndRenameHump(compareCol);
                //"比较字段"的值
                String baseVal = "";
                String targetVal = "";

                // 获取对象属性
                Field[] baseFields = baseObj.getClass().getDeclaredFields();
                //循环获取 "比较字段"的值
                for(Field baseFile: baseFields){
                    if(matchCol.equals(baseFile.getName())){
                        baseFile.setAccessible(true); // 私有属性必须设置访问权限
                        baseVal = (String) baseFile.get(baseObj);
                        //logger.info("-----baseVal" + baseVal);
                        break;
                    }
                }

                for (DbBaseObj targetObj : targetList) {

                    //如果是之前匹配成功的，则跳过
                    if (targetObj.isMatchFlag()) {
                        continue;
                    }

                    // 获取对象属性
                    Field[] targetFields = targetObj.getClass().getDeclaredFields();
                    //循环获取 "比较字段"的值
                    for(Field targetField: targetFields){
                        if(matchCol.equals(targetField.getName())){
                            targetField.setAccessible(true); // 私有属性必须设置访问权限
                            targetVal = (String) targetField.get(baseObj);
                            //logger.info("-----targetVal" + targetVal);

                            //记录匹配成功
                            if(baseVal.equals(targetVal)){
                                targetObj.setMatchFlag(true);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return targetList;
    }
}
