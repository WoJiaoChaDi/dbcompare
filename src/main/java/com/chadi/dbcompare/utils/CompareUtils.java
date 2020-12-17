package com.chadi.dbcompare.utils;

import com.chadi.dbcompare.bean.DbBaseObj;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompareUtils {

    final static Logger logger = LoggerFactory.getLogger(CompareUtils.class);

    public final static String matchFlag_No_0 = "0"; //未匹配
    public final static String matchFlag_Yes_1 = "1"; //匹配成功
    public final static String matchFlag_Yes_2 = "2"; //匹配失败
    public final static String matchStatus = "matchStatus"; //匹配状态
    public final static String keyMatchStatus = "_matchStatus"; //key的匹配状态
    public final static String mainColStr = "*"; //主要字段起始符号（主要字段全部匹配，可以标记此字段匹配过了）

/**
 * @description: 根据compareCols的列名，对比两个List内的对象字段的值是否相同
 * @param baseList
 * @param targetList
 * @param compareCols
 * @return: java.util.Map<java.lang.String,java.util.List>
 * @author: XuDong
 * @time: 2020/12/16 16:37
 */
    public static Map<String, List> compareList(List<? extends DbBaseObj> baseList, List<? extends DbBaseObj> targetList, List<String> compareCols) {

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
                    compareCol = CommonUtils.strTrimLowlineAndRenameHump(compareCol);

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
                        baseObjMap.put(compareCol + keyMatchStatus, CompareUtils.matchFlag_Yes_1);
                        targetObjMap.put(compareCol + keyMatchStatus, CompareUtils.matchFlag_Yes_1);
                        matchNum++;
                    }else{

                        //主要字段匹配失败，跳过 匹配队列(第二个循环）的此对象
                        if(mainColFlag){
                            break;
                        }

                        baseObjMap.put(compareCol + keyMatchStatus, CompareUtils.matchFlag_Yes_2);
                        targetObjMap.put(compareCol + keyMatchStatus, CompareUtils.matchFlag_Yes_2);
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
                    compareCol = CommonUtils.strTrimLowlineAndRenameHump(compareCol);

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

    /**
     * @description: 根据List索引，比较两个List相同索引的对象的值是否相同
     * @param baseList
     * @param targetList
     * @param compareCols
     * @return: java.util.Map<java.lang.String,java.util.List>
     * @author: XuDong
     * @time: 2020/12/16 16:39
     */
    public static Map<String, List> compareListByLine(List<? extends DbBaseObj> baseList, List<? extends DbBaseObj> targetList, List<String> compareCols) throws IllegalAccessException {

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
        for (int i = 0; i < baseList.size(); i++) {
            DbBaseObj baseObj = baseList.get(i);
            //基础数据刷新，计数归零
            matchNum = 0;

            //基础数据库字段值
            Map baseObjMap = BeanMapUtils.beanToMap(baseObj);
            baseObjMap.put(CompareUtils.matchStatus, CompareUtils.matchFlag_No_0);

            //获取对应行数的targetObj
            DbBaseObj targetObj = null;
            if (i < targetList.size()) {
                targetObj = targetList.get(i);
            }

            //目标数据库字段值
            Map targetObjMap = BeanMapUtils.beanToMap(targetObj);
            targetObjMap.put(CompareUtils.matchStatus, CompareUtils.matchFlag_No_0);

            //循环所有需要匹配的列
            for (int j = 0; j < compareCols.size(); j++) {
                String compareCol = compareCols.get(j);
                compareCol = CommonUtils.strTrimLowlineAndRenameHump(compareCol);

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
                    baseObjMap.put(compareCol + keyMatchStatus, CompareUtils.matchFlag_Yes_1);
                    targetObjMap.put(compareCol + keyMatchStatus, CompareUtils.matchFlag_Yes_1);
                    matchNum++;
                }else{

                    //主要字段匹配失败，跳过 匹配队列(第二个循环）的此对象
                    if(mainColFlag){
                        break;
                    }

                    baseObjMap.put(compareCol + keyMatchStatus, CompareUtils.matchFlag_Yes_2);
                    targetObjMap.put(compareCol + keyMatchStatus, CompareUtils.matchFlag_Yes_2);
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
                //break;
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
                    compareCol = CommonUtils.strTrimLowlineAndRenameHump(compareCol);

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

    public static void soutResult(Map<String, List> resultMap, String type, List<String> compareCols) {

        String s1 = "------↓------未能匹配成功的" + type + "名------↓------";
        logger.info(s1);
        List<Map> baseMapNoMatchList = resultMap.get("baseMapNoMatchList");
        soutCompareResult(compareCols, baseMapNoMatchList);

        String s2 = "------↓------完全匹配成功的" + type + "名------↓------";
        logger.info(s2);
        List<Map> baseMapAllMatchList = resultMap.get("baseMapAllMatchList");
        soutCompareResult(compareCols, baseMapAllMatchList);

        String s3 = "------↓------未能完全匹配的" + type + "名，未匹配字段用【】框起来的------↓------";
        logger.info(s3);
        List<Map> baseMapPartMatchList = resultMap.get("baseMapPartMatchList");
        soutCompareResult(compareCols, baseMapPartMatchList);

        String s4 = "------↓------匹配队列多出的" + type + "名------↓------";
        logger.info(s4);
        List<Map> targetMapNoMatchList = resultMap.get("targetMapNoMatchList");
        soutCompareResult(compareCols, targetMapNoMatchList);
    }

    private static void soutCompareResult(List<String> compareCols, List<Map> baseMapNoMatchList) {

        String info = "";
        //输出表头
        if(!CollectionUtils.isEmpty(baseMapNoMatchList)){
            soutCompareColsSplitByTab(compareCols);
        }
        //拼装每列的数据
        for (Map<String, String> baseMapNoMatch : baseMapNoMatchList) {

            info += "| ";

            for (String compareCol : compareCols) {
                String col = strToHumpAndNoStar(compareCol);

                //判断字段匹配失败
                if(CompareUtils.matchFlag_Yes_2.equals(baseMapNoMatch.get(col + CompareUtils.keyMatchStatus))){
                    //匹配失败字段标记
                    info += "【";
                }
                info += baseMapNoMatch.get(col);
                if(CompareUtils.matchFlag_Yes_2.equals(baseMapNoMatch.get(col + CompareUtils.keyMatchStatus))){
                    //匹配失败字段标记
                    info += "】";
                }
                info += "\t";
            }

            info += " |" + "\n";
            logger.info(info);
            sysoutToText(info);
        }
    }

    public static String strToHumpAndNoStar(String compareCol) {
        String col = CommonUtils.strTrimLowlineAndRenameHump(compareCol);
        col = col.replace("*", "");
        return col;
    }

    private static void soutCompareColsSplitByTab(List<String> compareCols) {
        String cols = "||";
        for (String compareCol : compareCols) {
            cols += compareCol + "\t";
        }
        cols += "||";
        logger.info(cols + "\t");
    }

    public static void sysoutToText(String str){
        try {
            File f = new File("d:" + File.separator+"test.txt");
            OutputStream out=new FileOutputStream(f,true);//追加内容
            byte[] b=str.getBytes();
            for(int i=0;i<b.length;i++){
                out.write(b[i]);
            }
            out.close();
        } catch (Exception e) {
            logger.info("输出异常", e);
        }
    }

}
