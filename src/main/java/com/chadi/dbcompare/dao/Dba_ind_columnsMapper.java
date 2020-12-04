package com.chadi.dbcompare.dao;

import com.chadi.dbcompare.bean.Dba_ind_columns;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Dba_ind_columnsMapper extends Mapper {

    //通过compare.properties的固定列查询数据
    public List<Dba_ind_columns> getDba_ind_columnsByPros(@Param("params") Map constantColMaps);

}
