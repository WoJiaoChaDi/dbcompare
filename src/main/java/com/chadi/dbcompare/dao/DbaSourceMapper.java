package com.chadi.dbcompare.dao;

import com.chadi.dbcompare.bean.DbaSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DbaSourceMapper extends Mapper {

    //通过compare.properties的固定列查询数据
    public List<DbaSource> getDba_sourceByPros(@Param("constantColMap") Map constantColMap,
                                               @Param("notLikeMap") Map notLikeMap,
                                               @Param("appendPlusList") List appendPlusList);

}
