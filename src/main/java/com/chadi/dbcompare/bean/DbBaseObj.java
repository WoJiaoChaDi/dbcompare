package com.chadi.dbcompare.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName DbBaseObj
 * @Description DB基础类，封装一些公用属性
 * @Author XuDong
 * @Date 2020/12/7 14:45
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class DbBaseObj {

    String matchFlag;

}
