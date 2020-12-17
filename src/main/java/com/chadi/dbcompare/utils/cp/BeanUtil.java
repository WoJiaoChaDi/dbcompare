package com.chadi.dbcompare.utils.cp;

import com.chadi.dbcompare.utils.cp.annotation.MsgNode;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.util.CollectionUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BeanUtil
 * @Description TODO
 * @Author XuDong
 * @Date 2020/12/17 9:50
 * @Version 1.0
 **/
public class BeanUtil {

    protected static final Log log = LogFactory.getLog(BeanUtil.class);
    private static ConfigurableConversionService conversionService = new DefaultConversionService();

    public BeanUtil() {
    }

    protected  static ConfigurableConversionService getConverter() {
        return conversionService;
    }

    /**
     * @description: 复制JavaBean,ignoreNull是否选择原对象的null值
     * @param source
* @param target
* @param ignoreNull
     * @return: void
     * @author: XuDong
     * @time: 2020/12/17 11:03
     */
    public static void copyBeanProperties(Object source, Object target, Boolean ignoreNull) {

        if (source != null) {
            Class sourceClz = source.getClass();
            Class targetClz = target.getClass();
            PropertyDescriptor[] scrPropertys = BeanUtils.getPropertyDescriptors(sourceClz);
            Object srcValue = null;
            log.debug("Bean转化开始:" + sourceClz.getName() + "-->" + targetClz.getName());
            PropertyDescriptor[] var7 = scrPropertys;
            int var8 = scrPropertys.length;

            for (int var9 = 0; var9 < var8; ++var9) {
                PropertyDescriptor srcProperty = var7[var9];

                String nodeName;
                try {
                    PropertyDescriptor desProperty = new PropertyDescriptor(srcProperty.getName(), targetClz);
                    Method setMethod = desProperty.getWriteMethod();
                    Method getMethod = desProperty.getReadMethod();
                    if (getMethod != null && setMethod != null) {
                        srcValue = getMethod.invoke(source);
                        if (srcValue == null) {
                            if (!ignoreNull) {
                                setMethod.invoke(target, srcValue);
                            }
                        } else if (srcProperty.getPropertyType() != desProperty.getPropertyType()) {
                            setMethod.invoke(target, convert(srcValue, desProperty.getPropertyType()));
                        } else {
                            setMethod.invoke(target, srcValue);
                        }
                    }
                } catch (IntrospectionException var14) {
                    log.error(var14);
                } catch (IllegalArgumentException var15) {
                    nodeName = getNodeName(srcProperty, sourceClz);
                    log.warn(String.format("Bean转化出错：%s属性[%s][%s]值%s-->%s,原因:%s", sourceClz.getName(), srcProperty.getName(), nodeName, srcValue, targetClz.getName(), var15.getMessage(), var15));
                    throw new RuntimeException(var15);
                } catch (Exception var16) {
                    nodeName = getNodeName(srcProperty, sourceClz);
                    log.warn(String.format("Bean转化出错：%s属性[%s][%s]值%s-->%s,原因:%s", sourceClz.getName(), srcProperty.getName(), nodeName, srcValue, targetClz.getName(), var16.getMessage(), var16));
                    throw new RuntimeException(var16);
                }
            }
        }
    }

    /**
     * @description: 复制JavaBean,ignoreNull是否选择原对象的null值，excludsArray 需要排除的属性
     * @param source
* @param target
* @param ignoreNull
     * @return: void
     * @author: XuDong
     * @time: 2020/12/17 11:03
     */
    public static void copyBeanPropertiesExclude(Object source, Object target, Boolean ignoreNull, String[] excludsArray) {
        List<String> excludesList = null;
        if (source != null) {

            if (excludsArray != null && excludsArray.length > 0) {
                excludesList = Arrays.asList(excludsArray);
            }

            Class sourceClz = source.getClass();
            Class targetClz = target.getClass();
            PropertyDescriptor[] scrPropertys = BeanUtils.getPropertyDescriptors(sourceClz);
            Object srcValue = null;
            log.debug("Bean转化开始:" + sourceClz.getName() + "-->" + targetClz.getName());
            PropertyDescriptor[] var7 = scrPropertys;
            int var8 = scrPropertys.length;

            for (int var9 = 0; var9 < var8; ++var9) {
                PropertyDescriptor srcProperty = var7[var9];

                String nodeName;
                try {
                    PropertyDescriptor desProperty = new PropertyDescriptor(srcProperty.getName(), targetClz);
                    Method setMethod = desProperty.getWriteMethod();
                    Method getMethod = desProperty.getReadMethod();
                    if (getMethod != null && setMethod != null) {
                        srcValue = getMethod.invoke(source);
                        if (srcValue == null) {
                            if (!ignoreNull) {
                                setMethod.invoke(target, srcValue);
                            }
                        } else if(excludesList == null || !excludesList.contains(srcProperty.getName())){
                            if (srcProperty.getPropertyType() != desProperty.getPropertyType()) {
                                setMethod.invoke(target, convert(srcValue, desProperty.getPropertyType()));
                            } else {
                                setMethod.invoke(target, srcValue);
                            }
                        }
                    }
                } catch (IntrospectionException var14) {
                    log.error(var14);
                } catch (IllegalArgumentException var15) {
                    nodeName = getNodeName(srcProperty, sourceClz);
                    log.warn(String.format("Bean转化出错：%s属性[%s][%s]值%s-->%s,原因:%s", sourceClz.getName(), srcProperty.getName(), nodeName, srcValue, targetClz.getName(), var15.getMessage(), var15));
                    throw new RuntimeException(var15);
                } catch (Exception var16) {
                    nodeName = getNodeName(srcProperty, sourceClz);
                    log.warn(String.format("Bean转化出错：%s属性[%s][%s]值%s-->%s,原因:%s", sourceClz.getName(), srcProperty.getName(), nodeName, srcValue, targetClz.getName(), var16.getMessage(), var16));
                    throw new RuntimeException(var16);
                }
            }
        }
    }


    private static String getNodeName(PropertyDescriptor srcProperty, Class sourceClz) {
        String nodeName = srcProperty.getName();
        Annotation annotation = srcProperty.getPropertyType().getAnnotation((MsgNode.class));
        if (annotation != null) {
            nodeName = ((MsgNode) annotation).name();
        } else {
            try {
                Field oneField = sourceClz.getDeclaredField(nodeName);
                if (oneField != null) {
                    Annotation fdAn = oneField.getAnnotation(MsgNode.class);
                    nodeName = ((MsgNode) fdAn).name();
                }
            } catch (SecurityException var6) {
                var6.printStackTrace();
            } catch (NoSuchFieldException var7) {
                var7.printStackTrace();
            }
        }
        return nodeName;
    }

    public static Object convert(Object obj, Class<?> clazz) {
        return getConverter().convert(obj, clazz);
    }

    /**
     * @description: 比较两个类的差异
     * @param classOne
     * @param classTwo
     * @return: void
     * @author: XuDong
     * @time: 2020/12/17 10:13
     */
    public static void diffClassBetween(Class<?> classOne, Class<?> classTwo) {
        List<String> sameProperties = new ArrayList();
        List<String> diffOneProperties = new ArrayList();
        List<String> diffTwoProperties = new ArrayList();
        Field[] oneFields = classOne.getDeclaredFields();
        Field[] twoFields = classTwo.getDeclaredFields();
        Field[] var7 = twoFields;
        int var8 = twoFields.length;

        for(int var9 = 0; var9< var8; ++ var9) {
            Field tempField = var7[var9];
            diffTwoProperties.add(tempField.getName());
        }

        Boolean success = false;

        for (int i = 0; i < oneFields.length; ++i) {
            String oneFieldName = oneFields[i].getName();
            success = false;

            for(int j=0; j<twoFields.length; ++j) {
                String twoFieldName = twoFields[j].getName();
                if (oneFieldName.equals(twoFieldName)) {
                    sameProperties.add(twoFieldName);
                    diffTwoProperties.remove(twoFieldName);
                    success = true;
                    break;
                }
            }

            if (!success) {
                diffOneProperties.add(oneFieldName);
            }
        }

        log.info("--------相同字段-------");
        Integer i = 1;

        String temp;
        Iterator var20;
        for(var20 = sameProperties.iterator(); var20.hasNext(); i=i+1) {
            temp = (String) var20.next();
            log.info(i.toString() + ":" + temp);
        }

        i = 1;
        log.info("----类：" + classOne.getSimpleName() + "将有字段----");

        for(var20 = diffOneProperties.iterator(); var20.hasNext(); i=i+1) {
            temp = (String) var20.next();
            log.info(i.toString() + ":" + temp);
            System.out.println();
        }

        i = 1;
        log.info("----类：" + classTwo.getSimpleName() + "将有字段----");

        for(var20 = diffTwoProperties.iterator(); var20.hasNext(); i=i+1) {
            temp = (String) var20.next();
            log.info(i.toString() + ":" + temp);
        }
    }

    /**
     * @description: Map转JavaBean
     * @param cla
* @param map
     * @return: E
     * @author: XuDong
     * @time: 2020/12/17 11:03
     */
    public static <E> E mapToBean(Class<E> cla, Map<String, Object> map) {

        Object obj = null;
        try {
            obj = cla.newInstance();
            if (obj == null) {
                throw new RuntimeException("实例化" + cla.getName() + "失败");
            }
        } catch (Exception var1) {
            log.error("类型实例化对象失败，类型：" + cla, var1);
            return null;
        }

        Map<String, Object> newMap = new HashMap();
        Iterator var4 = map.entrySet().iterator();

        while (var4.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry) var4.next();
            newMap.put("set" + ((String) entry.getKey().trim().replaceAll("_", "").toLowerCase()), entry.getValue());
        }

        Method[] ms = cla.getMethods();
        Method[] var16 = ms;
        int var6 = ms.length;

        for(int var7=0; var7<var6; ++var7) {
            Method method = var16[var7];
            String mname = method.getName().toLowerCase();
            if (mname.startsWith("set")) {
                Class[] clas = method.getParameterTypes();
                Object v = newMap.get(mname);
                if (v != null && clas.length == 1) {
                    try {
                        method.invoke(obj, v);
                    } catch (Exception var13) {
                        log.error("属性值装入失败，装入方法：" + cla + "." + method.getName() + ".可装入类型" + clas[0] + ";欲装入值的类型：" + v.getClass());
                    }
                }
            }
        }

        return (E) obj;
    }

    /**
     * @description: Object属性转化成Map
     * @param bean
     * @return: java.util.Map
     * @author: XuDong
     * @time: 2020/12/17 11:02
     */
    public static Map beanToMap(Object bean) {

        Class type = bean.getClass();
        HashMap returnMap = new HashMap();

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (int i = 0; i < propertyDescriptors.length; ++i) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean);
                    if (result != null) {
                        returnMap.put(propertyName, result);
                    } else {
                        returnMap.put(propertyName, "");
                    }
                }
            }
        } catch (IntrospectionException var10) {
            log.error("Java BeanToMap,方法convertBean出错，" + var10.getMessage(), var10);
        } catch (IllegalArgumentException var11) {
            log.error("Java BeanToMap,方法convertBean出错，" + var11.getMessage(), var11);
        } catch (IllegalAccessException var12) {
            log.error("Java BeanToMap,方法convertBean出错，" + var12.getMessage(), var12);
        } catch (InvocationTargetException var13) {
            log.error("Java BeanToMap,方法convertBean出错，" + var13.getMessage(), var13);
        }

        return returnMap;
    }

    /**
     * @description: Map转化成对应Object，支持 int long double float boolean Date
     * @param type
* @param map
     * @return: java.lang.Object
     * @author: XuDong
     * @time: 2020/12/17 10:58
     */
    public static Object convertMap(Class type, Map map) {

        BeanInfo beanInfo = null;
        Object obj = null;

        try {
            beanInfo = Introspector.getBeanInfo(type);
            obj = type.newInstance();
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (int i = 0; i < propertyDescriptors.length; ++i) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                Class propertyType = descriptor.getPropertyType();
                if (map.containsKey(propertyName)) {
                    String value = String.valueOf(map.get(propertyName));
                    Object[] args = new Object[1];
                    if (!"java.lang.Long".equals(propertyType.getName()) && propertyType != Long.TYPE) {
                        if (!"long".equals(propertyType.getName()) && propertyType != Long.TYPE) {
                            if (!"java.lang.Integer".equals(propertyType.getName()) && propertyType != Integer.TYPE) {
                                if (!"int".equals(propertyType.getName()) && propertyType != Integer.TYPE) {
                                    if (!"java.lang.Double".equals(propertyType.getName()) && propertyType != Double.TYPE) {
                                        if (!"double".equals(propertyType.getName()) && propertyType != Double.TYPE) {
                                            if (!"java.lang.Boolean".equals(propertyType.getName()) && propertyType != Boolean.TYPE) {
                                                if (!"boolean".equals(propertyType.getName()) && propertyType != Boolean.TYPE) {
                                                    if (!"java.lang.Float".equals(propertyType.getName()) && propertyType != Float.TYPE) {
                                                        if (!"float".equals(propertyType.getName()) && propertyType != Float.TYPE) {
                                                            if (!"java.lang.Date".equals(propertyType.getName()) && propertyType != Date.class) {
                                                                args[0] = value;
                                                            } else {
                                                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                                                                try {
                                                                    args[0] = sdf.parse(value);
                                                                } catch (ParseException var15) {
                                                                    log.error("MapToJava，Method convertMap Error, ParseException" + var15.getMessage(), var15);
                                                                }
                                                            }
                                                        } else {
                                                            args[0] = Float.parseFloat(value);
                                                        }
                                                    } else {
                                                        args[0] = Float.parseFloat(value);
                                                    }
                                                } else {
                                                    args[0] = Boolean.getBoolean(value);
                                                }
                                            } else {
                                                args[0] = Boolean.getBoolean(value);
                                            }
                                        } else {
                                            args[0] = Double.valueOf(value);
                                        }
                                    } else {
                                        args[0] = Double.valueOf(value);
                                    }
                                } else {
                                    args[0] = Integer.valueOf(value);
                                }
                            } else {
                                args[0] = Integer.valueOf(value);
                            }
                        } else {
                            args[0] = Long.valueOf(value);
                        }
                    } else {
                        args[0] = Long.valueOf(value);

                    }

                    try {
                        descriptor.getWriteMethod().invoke(obj, args);
                    } catch (IllegalArgumentException var13) {
                        log.error("MapToJava，Method convertMap Error, IllegalArgumentException" + var13.getMessage(), var13);
                    } catch (InvocationTargetException var14) {
                        log.error("MapToJava，Method convertMap Error, InvocationTargetException" + var14.getMessage(), var14);
                    }
                }
            }
        } catch (IntrospectionException var16) {
            log.error("MapToJava，Method convertMap Error, IntrospectionException" + var16.getMessage(), var16);
        } catch (InstantiationException var17) {
            log.error("MapToJava，Method convertMap Error, InstantiationException" + var17.getMessage(), var17);
        } catch (IllegalAccessException avr18) {
            log.error("MapToJava，Method convertMap Error, IllegalAccessException" + avr18.getMessage(), avr18);
        }

        return obj;
    }

    /**
     * @description: 深度复制List
     * @param src
     * @return: java.util.List<?>
     * @author: XuDong
     * @time: 2020/12/17 11:12
     */
    public static List<?> deepListCopy(List<?> src) {

        List dest = null;
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            dest = (List) in.readObject();
        } catch (IOException var6) {
            log.error("===IOException===BeanUtil Method deepListCopy Fail:" + var6.getMessage(), var6);
        } catch (ClassNotFoundException var7) {
            log.error("===ClassNotFoundException===BeanUtil Method deepListCopy Fail:" + var7.getMessage(), var7);
        }
        return dest;
    }

    /**
     * @description: 根据类、方法名、参数，获取方法
     * @param targetClass
    * @param methodId
    * @param actualParameterTypes
     * @return: java.lang.reflect.Method
     * @author: XuDong
     * @time: 2020/12/17 11:20
     */
    public static Method getAppropriateMethod(Class<?> targetClass, String methodId, Class<?>[] actualParameterTypes) {
        Method method = null;
        if (null != actualParameterTypes && actualParameterTypes.length > 0) {
            boolean flag = true;
            Method[] methods = targetClass.getDeclaredMethods();
            Method[] var6 = methods;
            int var7 = methods.length;

            for(int var8 = 0; var8<var7; ++var8) {
                Method m = var6[var8];
                if (m.getName().equals(methodId)) {
                    Class<?>[] methodParmTypes = m.getParameterTypes();
                    if (actualParameterTypes.length == methodParmTypes.length) {
                        flag = true;

                        for(int i=0; i< actualParameterTypes.length; ++i) {
                            Class<?> parm = actualParameterTypes[i];
                            if (!methodParmTypes[i].isAssignableFrom(parm)) {
                                flag = false;
                                break;
                            }
                        }

                        if (flag) {
                            method = m;
                            break;
                        }
                    }
                }
            }
        }else{
            try {
                method = targetClass.getMethod(methodId, actualParameterTypes);
            } catch (Exception var13) {
                log.warn("获取方法" + targetClass.getName() + "." + methodId + "出现异常", var13);
                method = null;
            }
        }
        return method;
    }

    public static List<String> getNonEmptyFieldValues(List<Object> beans, String fieldName) {
        if (CollectionUtils.isEmpty(beans)) {
            return null;
        }else{
            List<String> fieldValues = new ArrayList(beans.size());
            Iterator var3 = beans.iterator();

            while (var3.hasNext()) {
                Object bean = var3.next();
                Object value = getFieldValue(bean, fieldName);
                String valueStr = value == null ? null : value.toString();
                if (!StringUtil.isEmpty(valueStr)) {
                    fieldValues.add(valueStr);
                }
            }

            return fieldValues;
        }
    }


    /**
     * @description: 根据对象与字段名，获取字段值
     * @param bean
     * @param fieldName
     * @return: java.lang.Object
     * @author: XuDong
     * @time: 2020/12/17 15:12
     */
    public static Object getFieldValue(Object bean, String fieldName) {

        Field[] fields = bean.getClass().getDeclaredFields();
        Field.setAccessible(fields, true);
        Object obj = null;
        Field[] var4 = fields;
        int var5 = fields.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];
            if (fieldName.equals(field.getName())) {
                try {
                    obj = field.get(bean);
                } catch (IllegalArgumentException var9) {
                    log.warn(var9.getMessage());
                } catch (IllegalAccessException var10) {
                    log.warn(var10.getMessage());
                }
            }
        }
        return obj;
    }



    /**
     * @description: 判断两个class，是否是同一个类
     * @param classes1
     * @param classes2
     * @return: boolean
     * @author: XuDong
     * @time: 2020/12/17 14:56
     */
    //public static final boolean isMoreSpecific(Class[] classes1, Class[] classes2) {
    //
    //    int index = 0;
    //
    //    for(int count= classes1.length; index < count; ++index) {
    //        Class c1 = classes1[index];
    //        Class c2 = classes2[index];
    //        //先判断是否同一个对象
    //        if (c1 != c2) {
    //            //判断是否基本类型 或 void，因为是基本对象的话，c1==c2 -> true
    //            if (c1.isPrimitive()) {
    //                return true;
    //            }
    //
    //            //判断  c1 是 c2 父类或者父接口    或者  c1 和 c2 是同一个类或者同一个接口
    //            if (c1.isAssignableFrom(c2)) {
    //                return false;
    //            }
    //
    //            if (c2.isAssignableFrom(c1)) {
    //                return true;
    //            }
    //        }
    //    }
    //    return false;
    //}

    /**
     * @description: 返回参数的8大基本类型的封装，如果不是基本类型，则返回参数本身的Class
     * @param arg
     * @return: java.lang.Class
     * @author: XuDong
     * @time: 2020/12/17 11:29
     */
    public static final Class getArgClass(Object arg) {

        if (arg == null) {
            return null;
        }else{
            Class c = arg.getClass();
            if (c == Boolean.class) {
                return Boolean.TYPE;
            }else{
                if (c.getSuperclass() == Number.class) {
                    if (c == Integer.class) {
                        return Integer.TYPE;
                    }
                    if (c == Double.class) {
                        return Double.TYPE;
                    }
                    if (c == Byte.class) {
                        return Byte.TYPE;
                    }
                    if (c == Long.class) {
                        return Long.TYPE;
                    }
                    if (c == Float.class) {
                        return Float.TYPE;
                    }
                    if (c == Short.class) {
                        return Short.TYPE;
                    }
                } else if (c == Character.class) {
                    return Character.TYPE;
                }

                return c;
            }
        }
    }



    /**
     * @description: 判断object是否是c类型
     * @param object
* @param c
     * @return: boolean
     * @author: XuDong
     * @time: 2020/12/17 11:35
     */
    public static final boolean isTypeCompatible(Object object, Class c) {

        boolean result = true;
        if (object != null) {
            //判断是否基本类型与void
            if (c.isPrimitive()) {
                //判断是否基本类型
                if (getArgClass(object) != c) {
                    result = false;
                }
            } else if (!c.isInstance(object)) {//判断是否两者类型是否相同
                result = false;
            }
        }
        return result;
    }

    static {
        //Converter<String, Date> sdConverter = new BeanUtil.Strin
    }

    protected static class StringToTimestampConverter implements Converter<String, Timestamp> {
        protected StringToTimestampConverter(){

        }

        public Timestamp convert(String source) {
            try {
                SimpleDateFormat sdfDt;
                if (source.length() == "yyyyMMddHHmmssSSS".length()) {
                    sdfDt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                    return new Timestamp(sdfDt.parse(source).getTime());
                } else if (source.length() == "yyyyMMddHHmmss".length()) {
                    sdfDt = new SimpleDateFormat("yyyyMMddHHmmss");
                    return new Timestamp(sdfDt.parse(source).getTime());
                } else {
                    throw new IllegalArgumentException(String.format("类型转换失败，需要格式[yyyyMMddHHmmssSSS]或者[yyyyMMddHHmmss]，但是输入的是[%s]", source));
                }
            } catch (ParseException var3) {
                throw new IllegalArgumentException(String.format("类型转换失败，需要格式[yyyyMMddHHmmssSSS]或者[yyyyMMddHHmmss]，但是输入的是[%s]", source));
            }
        }
    }

    protected static class DateToStringConverter implements Converter<Date, String> {
        protected DateToStringConverter(){}

        public String convert(Date source) {
            SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
            return sdfDt.format(source);
        }
    }

    protected static class StringToDateConverter implements Converter<String, Date> {
        protected StringToDateConverter(){}

        public Date convert(String source) {
            SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
            Date d = null;

            try {
                d = sdfDt.parse(source);
                return d;
            } catch (ParseException var5) {
                throw new IllegalArgumentException(String.format("类型转换失败，需要格式[yyyyMMdd]，但输入是[%s]", source));
            }
        }
    }
}
