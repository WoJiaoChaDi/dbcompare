package com.chadi.dbcompare.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserConstraints extends DbBaseObj {

    private String owner;
    private String constraintName;
    private String constraintType;
    private String tableName;
    private String searchCondition;
    private String rOwner;
    private String rConstraintName;
    private String deleteRule;
    private String status;
    private String deferrable;
    private String deferred;
    private String validated;
    private String generated;
    private String bad;
    private String rely;
    private String lastChange;
    private String indexOwner;
    private String indexName;
    private String invalid;
    private String viewRelated;

}
