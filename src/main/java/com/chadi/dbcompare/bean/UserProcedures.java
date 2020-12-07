package com.chadi.dbcompare.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProcedures extends DbBaseObj {
    private String objectName;
    private String procedureName;
    private String objectId;
    private String subprogramId;
    private String overload;
    private String objectType;
    private String aggregate;
    private String pipelined;
    private String impltypeowner;
    private String impltypename;
    private String parallel;
    //private String interface;
    private String deterministic;
    private String authid;

}
