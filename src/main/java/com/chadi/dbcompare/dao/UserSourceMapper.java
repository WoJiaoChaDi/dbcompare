package com.chadi.dbcompare.dao;

import com.chadi.dbcompare.bean.UserSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSourceMapper extends Mapper {

    //通过compare.properties的固定列查询数据
    public List<UserSource> getUser_sourceByNameAndType(@Param("name") String name,
                                                @Param("type") String type);

}
