package com.chadi.dbcompare.dao;

import com.chadi.dbcompare.bean.UserTriggers;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserTriggersMapper extends Mapper {

    //通过compare.properties的固定列查询数据
    public List<UserTriggers> getUser_triggersByPros(@Param("params") Map constantColMaps);

}
