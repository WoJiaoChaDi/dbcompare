package com.chadi.dbcompare.utils.cp;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;

import java.util.Iterator;
import java.util.List;

/**
 * @ClassName StringUtils
 * @Description TODO
 * @Author XuDong
 * @Date 2020/12/17 16:12
 * @Version 1.0
 **/
public class StringUtil {
    
    public StringUtil(){}

    
    /**
     * @description: null 或者 去掉空格后
     * @param str
     * @return: boolean
     * @author: XuDong
     * @time: 2020/12/17 16:13
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * @description: 判断两个字符串是否相同
     * @param str1
     * @param str2
     * @return: boolean
     * @author: XuDong
     * @time: 2020/12/17 16:19
     */
    public static boolean isEquals(String str1, String str2) {

        if (isEmpty(str1) && isEmpty(str2)) {
            return true;
        }else{
            return !isEmpty(str1) && !isEmpty(str2) && str1.trim().equals(str2.trim());
        }
    }

    /**
     * @description: null 或者 去掉空格后
     * @param str
     * @return: boolean
     * @author: XuDong
     * @time: 2020/12/17 16:13
     */
    public static boolean isEmptyWithTrim(String str) {
        return null == str || StringUtils.isEmpty(str.trim());
    }

    /**
     * @description: 判断两个字符串是否相同（自动忽略两边空格）
     * @param str1
* @param str2
     * @return: boolean
     * @author: XuDong
     * @time: 2020/12/17 16:19
     */
    public static boolean isEqualsWithTrim(String str1, String str2) {

        if (isEmptyWithTrim(str1) && isEmptyWithTrim(str2)) {
            return true;
        }else{
            return !isEmptyWithTrim(str1) && !isEmptyWithTrim(str2) && str1.trim().equals(str2.trim());
        }
    }

    /**
     * @description: 判断是否纯字母
     * @param str
     * @return: boolean
     * @author: XuDong
     * @time: 2020/12/17 16:21
     */
    public static boolean isCharacter(String str) {
        return str.matches("[a-zA-Z]+");
    }

    /**
     * @description: 判断是否只有字母和数字
     * @param str
     * @return: boolean
     * @author: XuDong
     * @time: 2020/12/17 16:23
     */
    public static boolean isCharacterDigit(String str) {
        String regex = "^[a-z0-9A-Z]+$";
        return str.matches(regex);
    }

    /**
     * @description: 是否数字（包含小数）
     * @param string
     * @return: boolean
     * @author: XuDong
     * @time: 2020/12/17 16:24
     */
    public static boolean isDigit(String string) {
        return string.matches("[\\d]+(\\.[\\d]+)?");
    }

    /**
     * @description: 修剪两边空格，如果是null，返回空字符串
     * @param str
     * @return: java.lang.String
     * @author: XuDong
     * @time: 2020/12/17 16:26
     */
    public static String trim(String str) {
        if (str != null) {
            str = str.trim();
        } else {
            str = "";
        }
        return str;
    }

    /**
     * @description: 只修剪空格，不修剪其他的空白字符
     * @param str
     * @return: java.lang.String
     * @author: XuDong
     * @time: 2020/12/17 16:28
     */
    public static String trimWhitespace(String str) {
        return org.springframework.util.StringUtils.trimWhitespace(str);
    }

    /**
     * @description: 截取字符串，无需判断end是否大于start
     * @param str
* @param start
* @param end
     * @return: java.lang.String
     * @author: XuDong
     * @time: 2020/12/17 16:29
     */
    public static String substring(String str, int start, int end) {
        return StringUtils.substring(str, start, end);
    }

    /**
     * @description: 判断起始字符，无需判断null
     * @param str
* @param prefix
     * @return: boolean
     * @author: XuDong
     * @time: 2020/12/17 16:31
     */
    public static boolean startWith(String str, String prefix) {
        if (str == null && prefix == null) {
            return true;
        }else{
            return str != null && prefix != null ? str.startsWith(prefix) : false;
        }
    }
    /**
     * @description: 判断结束字符，无需判断null
     * @param str
    * @param suffix
     * @return: boolean
     * @author: XuDong
     * @time: 2020/12/17 16:31
     */
    public static boolean endWith(String str, String suffix) {
        if (str == null && suffix == null) {
            return true;
        }else{
            return str != null && suffix != null ? str.endsWith(suffix) : false;
        }
    }

    /**
     * @description: 拼接字符串,如归strList是null，返回""
     * @param strList
* @param separator
     * @return: java.lang.String
     * @author: XuDong
     * @time: 2020/12/17 16:37
     */
    public static String joinListStr(List<String> strList, String separator) {
        if (strList == null) {
            return null;
        } else if (strList.size() <= 0) {
            return "";
        } else {
            Iterator<String> iterator = strList.iterator();
            String first = (String) iterator.next();
            if (!iterator.hasNext()) {
                return first;
            } else {
                StrBuilder buf = new StrBuilder(256);
                if (first != null) {
                    buf.append(first);
                }

                do {
                    if (separator != null) {
                        buf.append(separator);
                    }

                    String str = (String) iterator.next();
                    if (str != null) {
                        buf.append(str);
                    }
                } while (iterator.hasNext());

                return buf.toString();
            }
        }
    }


}
