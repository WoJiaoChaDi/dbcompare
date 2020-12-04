package com.chadi.dbcompare.dao;

import com.chadi.dbcompare.bean.Dba_tables;

import java.util.List;

public interface Dba_tablesMapper {


    //返回List
    public List<Dba_tables> getDba_tablesByOwner(String owner);

}
