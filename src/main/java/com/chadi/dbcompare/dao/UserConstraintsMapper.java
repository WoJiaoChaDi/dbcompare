package com.chadi.dbcompare.dao;

import com.chadi.dbcompare.bean.UserConstraints;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserConstraintsMapper extends Mapper {

    //通过compare.properties的固定列查询数据
    public List<UserConstraints> getUser_ConstraintsByPros(@Param("constantColMap") Map constantColMap,
                                                           @Param("notLikeMap") Map notLikeMap,
                                                           @Param("appendPlusList") List appendPlusList);

}
