package com.chadi.dbcompare.dao;

import com.chadi.dbcompare.bean.DbaIndColumns;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DbaIndColumnsMapper extends Mapper {

    //通过compare.properties的固定列查询数据
    public List<DbaIndColumns> getDba_ind_columnsByPros(@Param("params") Map constantColMaps);

}
