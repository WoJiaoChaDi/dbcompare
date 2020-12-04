package com.chadi.dbcompare.dao;

import com.chadi.dbcompare.bean.User_indexes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface User_indexesMapper extends Mapper {

    //通过compare.properties的固定列查询数据
    public List<User_indexes> getUser_indexesByPros(@Param("params") Map constantColMaps);

}
