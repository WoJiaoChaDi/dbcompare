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

    public final static String matchFlag_No_0 = "0"; //未匹配
    public final static String matchFlag_Yes_1 = "1"; //匹配成功
    public final static String matchFlag_Yes_2 = "2"; //匹配失败
    public final static String matchStatus = "matchStatus"; //匹配状态
    public final static String mainColStr = "*"; //主要字段起始符号（主要字段全部匹配，可以标记此字段匹配过了）

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

        String newStr = str;
        String newStrStart = "";
        String newStrMiddle = "";
        String newStrEnd = "";

        while (index_ != -1) {
            newStrStart = newStr.substring(0, index_);
            newStrMiddle = newStr.substring(index_+1, index_ + 2).toUpperCase();
            newStrEnd = newStr.substring(index_ + 2, newStr.length());
            newStr = newStrStart + newStrMiddle + newStrEnd;
            index_ = newStr.indexOf("_");
        }

        return newStr;
    }

    public static Map<String, List> compareList(List<? extends DbBaseObj> baseList, List<? extends DbBaseObj> targetList, List<String> compareCols) throws IllegalAccessException {

        Map<String, List> resultMap = new HashMap<>();

        List<Map> baseMapList = new ArrayList<>();              //原始数据
        List<Map> baseMapAllMatchList = new ArrayList<>();      //全部匹配
        List<Map> baseMapPartMatchList = new ArrayList<>();     //部分匹配
        List<Map> baseMapNoMatchList = new ArrayList<>();       //不匹配
        List<Map> baseMapMatchList = new ArrayList<>();         //匹配成功(全部匹配+部分匹配)
        List<Map> baseMapFailMatchList = new ArrayList<>();     //匹配失败(部分匹配+不匹配)

        List<Map<String, Object>> targetMapList = new ArrayList<>();            //原始数据
        List<Map> targetMapAllMatchList = new ArrayList<>();                    //全部匹配
        List<Map> targetMapPartMatchList = new ArrayList<>();                   //部分匹配
        List<Map<String, Object>> targetMapNoMatchList = new ArrayList<>();     //不匹配
        List<Map> targetMapMatchList = new ArrayList<>();                       //匹配成功(全部匹配+部分匹配)
        List<Map> targetMapFailMatchList = new ArrayList<>();                   //匹配失败(部分匹配+不匹配)

        //期望匹配的列数
        int wishNum = compareCols.size();
        //匹配成功的列数
        int matchNum = 0;

        //循环基础数据库 的每一条数据
        for (DbBaseObj baseObj : baseList) {

            //基础数据刷新，计数归零
            matchNum = 0;

            //基础数据库字段值
            Map baseObjMap = BeanMapUtils.beanToMap(baseObj);
            baseObjMap.put(CompareUtils.matchStatus, CompareUtils.matchFlag_No_0);

            //循环 匹配队列
            for (DbBaseObj targetObj : targetList) {

                //如果这个已经匹配成功了，则退出目标循环
                //if(CompareUtils.matchFlag_Yes_1.equals(baseObjMap.get(CompareUtils.matchStatus))){
                //    break;
                //}

                //目标数据库字段值
                Map targetObjMap = BeanMapUtils.beanToMap(targetObj);
                targetObjMap.put(CompareUtils.matchStatus, CompareUtils.matchFlag_No_0);

                //循环所有需要匹配的列
                for (int i = 0; i < compareCols.size(); i++) {
                    String compareCol = compareCols.get(i);
                    compareCol = CompareUtils.strTrimLowlineAndRenameHump(compareCol);

                    boolean mainColFlag = false;
                    //如果是主要字段，进行标记
                    if(compareCol.startsWith(CompareUtils.mainColStr)){
                        mainColFlag = true;
                        compareCol = compareCol.substring(1, compareCol.length());
                    }

                    String baseCol = (String) baseObjMap.get(compareCol);
                    String targetCol = (String) targetObjMap.get(compareCol);

                    //匹配成功，记录每个属性的对比结果 与 匹配成功数
                    if (compareStr(baseCol, targetCol)) {
                        baseObjMap.put(compareCol + "_CbFlag", CompareUtils.matchFlag_Yes_1);
                        targetObjMap.put(compareCol + "_CbFlag", CompareUtils.matchFlag_Yes_1);
                        matchNum++;
                    }else{

                        //主要字段匹配失败，跳过 匹配队列(第二个循环）的此对象
                        if(mainColFlag){
                            break;
                        }

                        baseObjMap.put(compareCol + "_CbFlag", CompareUtils.matchFlag_Yes_2);
                        targetObjMap.put(compareCol + "_CbFlag", CompareUtils.matchFlag_Yes_2);
                    }
                }

                //有匹配成功过的添加进目标MapList，然后  退出 匹配队列，循环下一个基础队列
                if(matchNum > 0){
                    //完全匹配
                    //如果匹配成功的列数 与 期望列数 一致，说明字段完全一致
                    if (wishNum == matchNum) {
                        baseObj.setMatchFlag(CompareUtils.matchFlag_Yes_1);
                        targetObj.setMatchFlag(CompareUtils.matchFlag_Yes_1);
                        baseObjMap.put(CompareUtils.matchStatus, CompareUtils.matchFlag_Yes_1);
                        targetObjMap.put(CompareUtils.matchStatus, CompareUtils.matchFlag_Yes_1);

                        baseMapAllMatchList.add(baseObjMap);
                        targetMapAllMatchList.add(targetObjMap);
                    } else {
                        //部分匹配
                        baseObjMap.put(CompareUtils.matchStatus, CompareUtils.matchFlag_Yes_2);
                        targetObjMap.put(CompareUtils.matchStatus, CompareUtils.matchFlag_Yes_2);

                        baseMapPartMatchList.add(baseObjMap);
                        targetMapPartMatchList.add(targetObjMap);
                    }

                    targetMapMatchList.add(targetObjMap);
                    break;
                }
            }

            //如果 循环完 匹配队列后，也没有更改  匹配状态，说明此 对象 在 匹配队列中不存在。
            if(CompareUtils.matchFlag_No_0.equals(baseObjMap.get(CompareUtils.matchStatus))){
                baseMapNoMatchList.add(baseObjMap);
            }

            //添加基础map
            baseMapList.add(baseObjMap);
        }


        //分离出  匹配队列 中 多出来的对象
        List<Map> targetMapMatchTempList = new ArrayList<>();
        targetMapMatchTempList.addAll(targetMapMatchList);

        targetMapNoMatchList = BeanMapUtils.beansToMaps(targetList);
        for (int i = 0; i < targetMapNoMatchList.size(); ) {

            Map targetMap = targetMapNoMatchList.get(i);
            boolean moveMapFlag = false;
            for (Map targetMapMatch : targetMapMatchTempList) {
                //循环所有需要匹配的列
                for (int k = 0; k < compareCols.size(); k++) {
                    String compareCol = compareCols.get(k);
                    compareCol = CompareUtils.strTrimLowlineAndRenameHump(compareCol);

                    //如果是主要字段，进行标记
                    if(compareCol.startsWith(CompareUtils.mainColStr)){
                        compareCol = compareCol.substring(1, compareCol.length());

                        String targetMapStr = (String) targetMap.get(compareCol);
                        String targetMapMatchStr = (String) targetMapMatch.get(compareCol);
                        if(CompareUtils.compareStr(targetMapStr, targetMapMatchStr)){
                            moveMapFlag = true;
                            targetMapNoMatchList.remove(targetMap);
                            targetMapMatchTempList.remove(targetMapMatch);
                        }
                        break;
                    }
                }

                if (moveMapFlag == true) {
                    break;
                }
            }

            if (moveMapFlag == false) {
                i++;
            }
        }


        resultMap.put("baseList", baseList);
        resultMap.put("baseMapList", baseMapList);                          //原始数据
        resultMap.put("baseMapAllMatchList", baseMapAllMatchList);       //全部匹配
        resultMap.put("baseMapPartMatchList", baseMapPartMatchList);     //部分匹配
        resultMap.put("baseMapNoMatchList", baseMapNoMatchList);         //不匹配
        baseMapMatchList.addAll(baseMapAllMatchList);
        baseMapMatchList.addAll(baseMapPartMatchList);
        resultMap.put("baseMapMatchList", baseMapMatchList);                //匹配成功(全部匹配+部分匹配)
        baseMapFailMatchList.addAll(baseMapPartMatchList);
        baseMapFailMatchList.addAll(baseMapNoMatchList);
        resultMap.put("baseMapFailMatchList", baseMapFailMatchList);        //匹配失败(部分匹配+不匹配)

        resultMap.put("targetList", targetList);
        resultMap.put("targetMapList", targetMapList);                      //原始数据
        resultMap.put("targetMapAllMatchList", targetMapAllMatchList);   //全部匹配
        resultMap.put("targetMapPartMatchList", targetMapPartMatchList); //部分匹配
        resultMap.put("targetMapNoMatchList", targetMapNoMatchList);        //不匹配
        resultMap.put("targetMapMatchList", targetMapMatchList);            //匹配成功(全部匹配+部分匹配)
        targetMapFailMatchList.addAll(targetMapPartMatchList);
        targetMapFailMatchList.addAll(targetMapNoMatchList);
        resultMap.put("targetMapFailMatchList", targetMapFailMatchList);    //匹配失败(部分匹配+不匹配)

        return resultMap;
    }









    public static boolean compareStr(String str1, String str2) {

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
