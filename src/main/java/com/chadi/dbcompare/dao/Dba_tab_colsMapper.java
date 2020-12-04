package com.chadi.dbcompare.dao;

import com.chadi.dbcompare.bean.Dba_tab_cols;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Dba_tab_colsMapper extends Mapper {


    //返回List
    public List<Dba_tab_cols> getDba_tab_colsByOwner(String owner);

    //通过compare.properties的固定列查询数据
    public List<Dba_tab_cols> getDba_tab_colsByPros(@Param("params") Map constantColMaps);

}
