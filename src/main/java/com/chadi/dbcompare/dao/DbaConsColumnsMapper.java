package com.chadi.dbcompare.dao;

import com.chadi.dbcompare.bean.DbaConsColumns;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DbaConsColumnsMapper extends Mapper {

    //通过compare.properties的固定列查询数据
    public List<DbaConsColumns> getDba_cons_columnsByPros(@Param("constantColMap") Map constantColMap,
                                                         @Param("notLikeMap") Map notLikeMap,
                                                         @Param("appendPlusList") List appendPlusList);

}
