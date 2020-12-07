package com.chadi.dbcompare.utils;

import com.chadi.dbcompare.bean.DbBaseObj;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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

    public static Map<String, List> compareList(List<? extends DbBaseObj> baseList, List<? extends DbBaseObj> targetList, List<String> compareCols) throws IllegalAccessException {

        Map<String, List> resultMap = new HashMap<>();

        List<Map> baseMapList = new ArrayList<>();
        List<Map> targetMapList = new ArrayList<>();

        //期望匹配的列数
        int wishNum = compareCols.size();
        //匹配成功的列数
        int matchNum = 0;

        //循环基础数据库 的每一条数据
        for (DbBaseObj baseObj : baseList) {
            //基础数据库字段值
            Map baseObjMap = BeanMapUtils.beanToMap(baseObj);

            for (DbBaseObj targetObj : targetList) {
                //目标数据库字段值
                Map targetObjMap = BeanMapUtils.beanToMap(targetObj);

                //循环所有需要匹配的列
                for (String compareCol : compareCols) {
                    String baseCol = (String) baseObjMap.get(compareCol);
                    String targetCol = (String) targetObjMap.get(compareCol);
                    //匹配成功，记录数
                    if (strCompare(baseCol, targetCol)) {
                        baseObjMap.put(compareCol + "_CbFlag", true);
                        targetObjMap.put(compareCol + "_CbFlag", true);
                        matchNum++;
                    }else{
                        baseObjMap.put(compareCol + "_CbFlag", false);
                        targetObjMap.put(compareCol + "_CbFlag", false);
                    }
                }



                //如果匹配成功的列数 与 期望列数 一致，说明字段完全一致
                if (wishNum == matchNum) {
                    baseObj.setMatchFlag(true);
                    targetObj.setMatchFlag(true);
                    baseObjMap.put("AllCbFlag", true);
                    targetObjMap.put("AllCbFlag", true);
                }

                //添加目标map
                targetMapList.add(targetObjMap);
            }

            //添加基础map
            baseMapList.add(baseObjMap);
        }

        resultMap.put("baseList", baseList);
        resultMap.put("targetList", targetList);
        resultMap.put("baseMapList", baseMapList);
        resultMap.put("targetMapList", targetMapList);
        return resultMap;
    }









    public static boolean strCompare(String str1, String str2) {

        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null && str2 != null) {
            return false;
        }
        if (str1 != null && str2 == null) {
            return false;
        }
        if (str1 != null && str2 != null) {
            if (str1.equals(str2)) {
                return true;
            }
        }
        return false;
    }
}
