package com.chadi.dbcompare.dao;

import com.chadi.dbcompare.bean.Dba_tables;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Dba_tablesMapper extends Mapper {


    //返回List
    public List<Dba_tables> getDba_tablesByOwner(String owner);

    public List<Dba_tables> getDba_tablesByPros(@Param("params") Map constantColMaps);

}
