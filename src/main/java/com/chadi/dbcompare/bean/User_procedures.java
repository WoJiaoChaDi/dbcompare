package com.chadi.dbcompare.bean;

public class User_procedures {
    private String OBJECT_NAME;
    private String PROCEDURE_NAME;
    private String OBJECT_ID;
    private String SUBPROGRAM_ID;
    private String OVERLOAD;
    private String OBJECT_TYPE;
    private String AGGREGATE;
    private String PIPELINED;
    private String IMPLTYPEOWNER;
    private String IMPLTYPENAME;
    private String PARALLEL;
    private String INTERFACE;
    private String DETERMINISTIC;
    private String AUTHID;

    public User_procedures() {

    }

    public User_procedures(String OBJECT_NAME, String PROCEDURE_NAME, String OBJECT_ID, String SUBPROGRAM_ID, String OVERLOAD, String OBJECT_TYPE, String AGGREGATE, String PIPELINED, String IMPLTYPEOWNER, String IMPLTYPENAME, String PARALLEL, String INTERFACE, String DETERMINISTIC, String AUTHID) {
        this.OBJECT_NAME = OBJECT_NAME;
        this.PROCEDURE_NAME = PROCEDURE_NAME;
        this.OBJECT_ID = OBJECT_ID;
        this.SUBPROGRAM_ID = SUBPROGRAM_ID;
        this.OVERLOAD = OVERLOAD;
        this.OBJECT_TYPE = OBJECT_TYPE;
        this.AGGREGATE = AGGREGATE;
        this.PIPELINED = PIPELINED;
        this.IMPLTYPEOWNER = IMPLTYPEOWNER;
        this.IMPLTYPENAME = IMPLTYPENAME;
        this.PARALLEL = PARALLEL;
        this.INTERFACE = INTERFACE;
        this.DETERMINISTIC = DETERMINISTIC;
        this.AUTHID = AUTHID;
    }

    public String getOBJECT_NAME() {
        return OBJECT_NAME;
    }

    public void setOBJECT_NAME(String OBJECT_NAME) {
        this.OBJECT_NAME = OBJECT_NAME;
    }

    public String getPROCEDURE_NAME() {
        return PROCEDURE_NAME;
    }

    public void setPROCEDURE_NAME(String PROCEDURE_NAME) {
        this.PROCEDURE_NAME = PROCEDURE_NAME;
    }

    public String getOBJECT_ID() {
        return OBJECT_ID;
    }

    public void setOBJECT_ID(String OBJECT_ID) {
        this.OBJECT_ID = OBJECT_ID;
    }

    public String getSUBPROGRAM_ID() {
        return SUBPROGRAM_ID;
    }

    public void setSUBPROGRAM_ID(String SUBPROGRAM_ID) {
        this.SUBPROGRAM_ID = SUBPROGRAM_ID;
    }

    public String getOVERLOAD() {
        return OVERLOAD;
    }

    public void setOVERLOAD(String OVERLOAD) {
        this.OVERLOAD = OVERLOAD;
    }

    public String getOBJECT_TYPE() {
        return OBJECT_TYPE;
    }

    public void setOBJECT_TYPE(String OBJECT_TYPE) {
        this.OBJECT_TYPE = OBJECT_TYPE;
    }

    public String getAGGREGATE() {
        return AGGREGATE;
    }

    public void setAGGREGATE(String AGGREGATE) {
        this.AGGREGATE = AGGREGATE;
    }

    public String getPIPELINED() {
        return PIPELINED;
    }

    public void setPIPELINED(String PIPELINED) {
        this.PIPELINED = PIPELINED;
    }

    public String getIMPLTYPEOWNER() {
        return IMPLTYPEOWNER;
    }

    public void setIMPLTYPEOWNER(String IMPLTYPEOWNER) {
        this.IMPLTYPEOWNER = IMPLTYPEOWNER;
    }

    public String getIMPLTYPENAME() {
        return IMPLTYPENAME;
    }

    public void setIMPLTYPENAME(String IMPLTYPENAME) {
        this.IMPLTYPENAME = IMPLTYPENAME;
    }

    public String getPARALLEL() {
        return PARALLEL;
    }

    public void setPARALLEL(String PARALLEL) {
        this.PARALLEL = PARALLEL;
    }

    public String getINTERFACE() {
        return INTERFACE;
    }

    public void setINTERFACE(String INTERFACE) {
        this.INTERFACE = INTERFACE;
    }

    public String getDETERMINISTIC() {
        return DETERMINISTIC;
    }

    public void setDETERMINISTIC(String DETERMINISTIC) {
        this.DETERMINISTIC = DETERMINISTIC;
    }

    public String getAUTHID() {
        return AUTHID;
    }

    public void setAUTHID(String AUTHID) {
        this.AUTHID = AUTHID;
    }

    @Override
    public String toString() {
        return "User_procedures{" +
                "OBJECT_NAME='" + OBJECT_NAME + '\'' +
                ", PROCEDURE_NAME='" + PROCEDURE_NAME + '\'' +
                ", OBJECT_ID='" + OBJECT_ID + '\'' +
                ", SUBPROGRAM_ID='" + SUBPROGRAM_ID + '\'' +
                ", OVERLOAD='" + OVERLOAD + '\'' +
                ", OBJECT_TYPE='" + OBJECT_TYPE + '\'' +
                ", AGGREGATE='" + AGGREGATE + '\'' +
                ", PIPELINED='" + PIPELINED + '\'' +
                ", IMPLTYPEOWNER='" + IMPLTYPEOWNER + '\'' +
                ", IMPLTYPENAME='" + IMPLTYPENAME + '\'' +
                ", PARALLEL='" + PARALLEL + '\'' +
                ", INTERFACE='" + INTERFACE + '\'' +
                ", DETERMINISTIC='" + DETERMINISTIC + '\'' +
                ", AUTHID='" + AUTHID + '\'' +
                '}';
    }
}
