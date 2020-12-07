package com.chadi.dbcompare.dao;

import com.chadi.dbcompare.bean.UserProcedures;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserProceduresMapper extends Mapper {

    //通过compare.properties的固定列查询数据
    public List<UserProcedures> getUser_proceduresByPros(@Param("params") Map constantColMaps);

}
