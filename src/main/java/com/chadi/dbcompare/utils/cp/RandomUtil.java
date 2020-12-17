package com.chadi.dbcompare.utils.cp;

import java.util.Random;

/**
 * @ClassName RandomUtil
 * @Description TODO
 * @Author XuDong
 * @Date 2020/12/17 16:05
 * @Version 1.0
 **/
public class RandomUtil {

    public RandomUtil(){}

    public static String getRandom(){
        Random rnd = new Random();
        return String.format("%03d", rnd.nextInt(999));
    }

    public static String getRandom(int maxNum){
        Random rnd = new Random();
        return String.format("%03d", rnd.nextInt(maxNum));
    }

    public static int getIntRandom(){
        return new Random().nextInt(999);
    }

    public static int getIntRandom(int maxNum){
        return new Random().nextInt(maxNum);
    }

    public static void main(String[] args) {
        System.out.println(RandomUtil.getIntRandom(5444));
    }
}
