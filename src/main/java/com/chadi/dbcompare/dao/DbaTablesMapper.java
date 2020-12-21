package com.chadi.dbcompare.dao;

import com.chadi.dbcompare.bean.DbaTables;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DbaTablesMapper extends Mapper {


    //返回List
    public List<DbaTables> getDba_tablesByOwner(String owner);

    //通过compare.properties的固定列查询数据
    public List<DbaTables> getDba_tablesByPros(@Param("constantColMap") Map constantColMap,
                                               @Param("notLikeMap") Map notLikeMap,
                                               @Param("appendPlusList") List appendPlusList);
}
