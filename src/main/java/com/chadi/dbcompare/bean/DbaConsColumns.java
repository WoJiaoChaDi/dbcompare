package com.chadi.dbcompare.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbaConsColumns extends DbBaseObj{
    private String owner;
    private String constraintName;
    private String tableName;
    private String columnName;
    private String position;
}
