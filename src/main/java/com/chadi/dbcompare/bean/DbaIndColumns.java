package com.chadi.dbcompare.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbaIndColumns {
    private String indexOwner;
    private String indexName;
    private String tableOwner;
    private String tableName;
    private String columnName;
    private String columnPosition;
    private String columnLength;
    private String charLength;
    private String descend;
}
