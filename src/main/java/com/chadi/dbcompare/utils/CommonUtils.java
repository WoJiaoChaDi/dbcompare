package com.chadi.dbcompare.utils;

import net.sf.ehcache.util.PropertyUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName CommonUtils
 * @Description TODO
 * @Author XuDong
 * @Date 2020/12/9 9:48
 * @Version 1.0
 **/
public class CommonUtils {

    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);

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
}
