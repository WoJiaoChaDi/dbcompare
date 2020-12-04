package com.chadi.dbcompare.bean;

public class User_Constraints {

    private String OWNER;
    private String CONSTRAINT_NAME;
    private String CONSTRAINT_TYPE;
    private String TABLE_NAME;
    private String SEARCH_CONDITION;
    private String R_OWNER;
    private String R_CONSTRAINT_NAME;
    private String DELETE_RULE;
    private String STATUS;
    private String DEFERRABLE;
    private String DEFERRED;
    private String VALIDATED;
    private String GENERATED;
    private String BAD;
    private String RELY;
    private String LAST_CHANGE;
    private String INDEX_OWNER;
    private String INDEX_NAME;
    private String INVALID;
    private String VIEW_RELATED;

    public User_Constraints() {
    }

    public User_Constraints(String OWNER, String CONSTRAINT_NAME, String CONSTRAINT_TYPE, String TABLE_NAME, String SEARCH_CONDITION, String r_OWNER, String r_CONSTRAINT_NAME, String DELETE_RULE, String STATUS, String DEFERRABLE, String DEFERRED, String VALIDATED, String GENERATED, String BAD, String RELY, String LAST_CHANGE, String INDEX_OWNER, String INDEX_NAME, String INVALID, String VIEW_RELATED) {
        this.OWNER = OWNER;
        this.CONSTRAINT_NAME = CONSTRAINT_NAME;
        this.CONSTRAINT_TYPE = CONSTRAINT_TYPE;
        this.TABLE_NAME = TABLE_NAME;
        this.SEARCH_CONDITION = SEARCH_CONDITION;
        R_OWNER = r_OWNER;
        R_CONSTRAINT_NAME = r_CONSTRAINT_NAME;
        this.DELETE_RULE = DELETE_RULE;
        this.STATUS = STATUS;
        this.DEFERRABLE = DEFERRABLE;
        this.DEFERRED = DEFERRED;
        this.VALIDATED = VALIDATED;
        this.GENERATED = GENERATED;
        this.BAD = BAD;
        this.RELY = RELY;
        this.LAST_CHANGE = LAST_CHANGE;
        this.INDEX_OWNER = INDEX_OWNER;
        this.INDEX_NAME = INDEX_NAME;
        this.INVALID = INVALID;
        this.VIEW_RELATED = VIEW_RELATED;
    }

    public String getOWNER() {
        return OWNER;
    }

    public void setOWNER(String OWNER) {
        this.OWNER = OWNER;
    }

    public String getCONSTRAINT_NAME() {
        return CONSTRAINT_NAME;
    }

    public void setCONSTRAINT_NAME(String CONSTRAINT_NAME) {
        this.CONSTRAINT_NAME = CONSTRAINT_NAME;
    }

    public String getCONSTRAINT_TYPE() {
        return CONSTRAINT_TYPE;
    }

    public void setCONSTRAINT_TYPE(String CONSTRAINT_TYPE) {
        this.CONSTRAINT_TYPE = CONSTRAINT_TYPE;
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public void setTABLE_NAME(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    public String getSEARCH_CONDITION() {
        return SEARCH_CONDITION;
    }

    public void setSEARCH_CONDITION(String SEARCH_CONDITION) {
        this.SEARCH_CONDITION = SEARCH_CONDITION;
    }

    public String getR_OWNER() {
        return R_OWNER;
    }

    public void setR_OWNER(String r_OWNER) {
        R_OWNER = r_OWNER;
    }

    public String getR_CONSTRAINT_NAME() {
        return R_CONSTRAINT_NAME;
    }

    public void setR_CONSTRAINT_NAME(String r_CONSTRAINT_NAME) {
        R_CONSTRAINT_NAME = r_CONSTRAINT_NAME;
    }

    public String getDELETE_RULE() {
        return DELETE_RULE;
    }

    public void setDELETE_RULE(String DELETE_RULE) {
        this.DELETE_RULE = DELETE_RULE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getDEFERRABLE() {
        return DEFERRABLE;
    }

    public void setDEFERRABLE(String DEFERRABLE) {
        this.DEFERRABLE = DEFERRABLE;
    }

    public String getDEFERRED() {
        return DEFERRED;
    }

    public void setDEFERRED(String DEFERRED) {
        this.DEFERRED = DEFERRED;
    }

    public String getVALIDATED() {
        return VALIDATED;
    }

    public void setVALIDATED(String VALIDATED) {
        this.VALIDATED = VALIDATED;
    }

    public String getGENERATED() {
        return GENERATED;
    }

    public void setGENERATED(String GENERATED) {
        this.GENERATED = GENERATED;
    }

    public String getBAD() {
        return BAD;
    }

    public void setBAD(String BAD) {
        this.BAD = BAD;
    }

    public String getRELY() {
        return RELY;
    }

    public void setRELY(String RELY) {
        this.RELY = RELY;
    }

    public String getLAST_CHANGE() {
        return LAST_CHANGE;
    }

    public void setLAST_CHANGE(String LAST_CHANGE) {
        this.LAST_CHANGE = LAST_CHANGE;
    }

    public String getINDEX_OWNER() {
        return INDEX_OWNER;
    }

    public void setINDEX_OWNER(String INDEX_OWNER) {
        this.INDEX_OWNER = INDEX_OWNER;
    }

    public String getINDEX_NAME() {
        return INDEX_NAME;
    }

    public void setINDEX_NAME(String INDEX_NAME) {
        this.INDEX_NAME = INDEX_NAME;
    }

    public String getINVALID() {
        return INVALID;
    }

    public void setINVALID(String INVALID) {
        this.INVALID = INVALID;
    }

    public String getVIEW_RELATED() {
        return VIEW_RELATED;
    }

    public void setVIEW_RELATED(String VIEW_RELATED) {
        this.VIEW_RELATED = VIEW_RELATED;
    }

    @Override
    public String toString() {
        return "User_Constraints{" +
                "OWNER='" + OWNER + '\'' +
                ", CONSTRAINT_NAME='" + CONSTRAINT_NAME + '\'' +
                ", CONSTRAINT_TYPE='" + CONSTRAINT_TYPE + '\'' +
                ", TABLE_NAME='" + TABLE_NAME + '\'' +
                ", SEARCH_CONDITION='" + SEARCH_CONDITION + '\'' +
                ", R_OWNER='" + R_OWNER + '\'' +
                ", R_CONSTRAINT_NAME='" + R_CONSTRAINT_NAME + '\'' +
                ", DELETE_RULE='" + DELETE_RULE + '\'' +
                ", STATUS='" + STATUS + '\'' +
                ", DEFERRABLE='" + DEFERRABLE + '\'' +
                ", DEFERRED='" + DEFERRED + '\'' +
                ", VALIDATED='" + VALIDATED + '\'' +
                ", GENERATED='" + GENERATED + '\'' +
                ", BAD='" + BAD + '\'' +
                ", RELY='" + RELY + '\'' +
                ", LAST_CHANGE='" + LAST_CHANGE + '\'' +
                ", INDEX_OWNER='" + INDEX_OWNER + '\'' +
                ", INDEX_NAME='" + INDEX_NAME + '\'' +
                ", INVALID='" + INVALID + '\'' +
                ", VIEW_RELATED='" + VIEW_RELATED + '\'' +
                '}';
    }
}
