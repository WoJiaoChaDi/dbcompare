package com.chadi.dbcompare.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTriggers {
    private String triggerName;
    private String triggerType;
    private String triggeringEvent;
    private String tableOwner;
    private String baseObjectType;
    private String tableName;
    private String columnName;
    private String referencingNames;
    private String whenClause;
    private String status;
    private String description;
    private String actionType;
    private String triggerBody;
    private String crossedition;
    private String beforeStatement;
    private String beforeRow;
    private String afterRow;
    private String afterStatement;
    private String insteadOfRow;
    private String fireOnce;
    private String applyServerOnly;

}
