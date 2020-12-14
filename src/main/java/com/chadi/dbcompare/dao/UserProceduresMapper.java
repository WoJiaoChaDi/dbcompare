package com.chadi.dbcompare.dao;

import com.chadi.dbcompare.bean.UserProcedures;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserProceduresMapper extends Mapper {

    public List<UserProcedures> getUser_proceduresByPros(@Param("constantColMap") Map constantColMap,
                                                   @Param("notLikeMap") Map notLikeMap,
                                                   @Param("appendPlusList") List appendPlusList);

}
