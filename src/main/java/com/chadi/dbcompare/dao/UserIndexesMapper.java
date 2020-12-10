package com.chadi.dbcompare.dao;

import com.chadi.dbcompare.bean.UserIndexes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserIndexesMapper extends Mapper {

    //通过compare.properties的固定列查询数据
    public List<UserIndexes> getUser_indexesByPros(@Param("constantColMap") Map constantColMap,
                                                   @Param("notLikeMap") Map notLikeMap,
                                                   @Param("appendPlusList") List appendPlusList);

}
