package com.chadi.dbcompare.bean;

public class User_triggers {
    private String TRIGGER_NAME;
    private String TRIGGER_TYPE;
    private String TRIGGERING_EVENT;
    private String TABLE_OWNER;
    private String BASE_OBJECT_TYPE;
    private String TABLE_NAME;
    private String COLUMN_NAME;
    private String REFERENCING_NAMES;
    private String WHEN_CLAUSE;
    private String STATUS;
    private String DESCRIPTION;
    private String ACTION_TYPE;
    private String TRIGGER_BODY;
    private String CROSSEDITION;
    private String BEFORE_STATEMENT;
    private String BEFORE_ROW;
    private String AFTER_ROW;
    private String AFTER_STATEMENT;
    private String INSTEAD_OF_ROW;
    private String FIRE_ONCE;
    private String APPLY_SERVER_ONLY;

    public User_triggers() {
    }

    public User_triggers(String TRIGGER_NAME, String TRIGGER_TYPE, String TRIGGERING_EVENT, String TABLE_OWNER, String BASE_OBJECT_TYPE, String TABLE_NAME, String COLUMN_NAME, String REFERENCING_NAMES, String WHEN_CLAUSE, String STATUS, String DESCRIPTION, String ACTION_TYPE, String TRIGGER_BODY, String CROSSEDITION, String BEFORE_STATEMENT, String BEFORE_ROW, String AFTER_ROW, String AFTER_STATEMENT, String INSTEAD_OF_ROW, String FIRE_ONCE, String APPLY_SERVER_ONLY) {
        this.TRIGGER_NAME = TRIGGER_NAME;
        this.TRIGGER_TYPE = TRIGGER_TYPE;
        this.TRIGGERING_EVENT = TRIGGERING_EVENT;
        this.TABLE_OWNER = TABLE_OWNER;
        this.BASE_OBJECT_TYPE = BASE_OBJECT_TYPE;
        this.TABLE_NAME = TABLE_NAME;
        this.COLUMN_NAME = COLUMN_NAME;
        this.REFERENCING_NAMES = REFERENCING_NAMES;
        this.WHEN_CLAUSE = WHEN_CLAUSE;
        this.STATUS = STATUS;
        this.DESCRIPTION = DESCRIPTION;
        this.ACTION_TYPE = ACTION_TYPE;
        this.TRIGGER_BODY = TRIGGER_BODY;
        this.CROSSEDITION = CROSSEDITION;
        this.BEFORE_STATEMENT = BEFORE_STATEMENT;
        this.BEFORE_ROW = BEFORE_ROW;
        this.AFTER_ROW = AFTER_ROW;
        this.AFTER_STATEMENT = AFTER_STATEMENT;
        this.INSTEAD_OF_ROW = INSTEAD_OF_ROW;
        this.FIRE_ONCE = FIRE_ONCE;
        this.APPLY_SERVER_ONLY = APPLY_SERVER_ONLY;
    }

    public String getTRIGGER_NAME() {
        return TRIGGER_NAME;
    }

    public void setTRIGGER_NAME(String TRIGGER_NAME) {
        this.TRIGGER_NAME = TRIGGER_NAME;
    }

    public String getTRIGGER_TYPE() {
        return TRIGGER_TYPE;
    }

    public void setTRIGGER_TYPE(String TRIGGER_TYPE) {
        this.TRIGGER_TYPE = TRIGGER_TYPE;
    }

    public String getTRIGGERING_EVENT() {
        return TRIGGERING_EVENT;
    }

    public void setTRIGGERING_EVENT(String TRIGGERING_EVENT) {
        this.TRIGGERING_EVENT = TRIGGERING_EVENT;
    }

    public String getTABLE_OWNER() {
        return TABLE_OWNER;
    }

    public void setTABLE_OWNER(String TABLE_OWNER) {
        this.TABLE_OWNER = TABLE_OWNER;
    }

    public String getBASE_OBJECT_TYPE() {
        return BASE_OBJECT_TYPE;
    }

    public void setBASE_OBJECT_TYPE(String BASE_OBJECT_TYPE) {
        this.BASE_OBJECT_TYPE = BASE_OBJECT_TYPE;
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public void setTABLE_NAME(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    public String getCOLUMN_NAME() {
        return COLUMN_NAME;
    }

    public void setCOLUMN_NAME(String COLUMN_NAME) {
        this.COLUMN_NAME = COLUMN_NAME;
    }

    public String getREFERENCING_NAMES() {
        return REFERENCING_NAMES;
    }

    public void setREFERENCING_NAMES(String REFERENCING_NAMES) {
        this.REFERENCING_NAMES = REFERENCING_NAMES;
    }

    public String getWHEN_CLAUSE() {
        return WHEN_CLAUSE;
    }

    public void setWHEN_CLAUSE(String WHEN_CLAUSE) {
        this.WHEN_CLAUSE = WHEN_CLAUSE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getACTION_TYPE() {
        return ACTION_TYPE;
    }

    public void setACTION_TYPE(String ACTION_TYPE) {
        this.ACTION_TYPE = ACTION_TYPE;
    }

    public String getTRIGGER_BODY() {
        return TRIGGER_BODY;
    }

    public void setTRIGGER_BODY(String TRIGGER_BODY) {
        this.TRIGGER_BODY = TRIGGER_BODY;
    }

    public String getCROSSEDITION() {
        return CROSSEDITION;
    }

    public void setCROSSEDITION(String CROSSEDITION) {
        this.CROSSEDITION = CROSSEDITION;
    }

    public String getBEFORE_STATEMENT() {
        return BEFORE_STATEMENT;
    }

    public void setBEFORE_STATEMENT(String BEFORE_STATEMENT) {
        this.BEFORE_STATEMENT = BEFORE_STATEMENT;
    }

    public String getBEFORE_ROW() {
        return BEFORE_ROW;
    }

    public void setBEFORE_ROW(String BEFORE_ROW) {
        this.BEFORE_ROW = BEFORE_ROW;
    }

    public String getAFTER_ROW() {
        return AFTER_ROW;
    }

    public void setAFTER_ROW(String AFTER_ROW) {
        this.AFTER_ROW = AFTER_ROW;
    }

    public String getAFTER_STATEMENT() {
        return AFTER_STATEMENT;
    }

    public void setAFTER_STATEMENT(String AFTER_STATEMENT) {
        this.AFTER_STATEMENT = AFTER_STATEMENT;
    }

    public String getINSTEAD_OF_ROW() {
        return INSTEAD_OF_ROW;
    }

    public void setINSTEAD_OF_ROW(String INSTEAD_OF_ROW) {
        this.INSTEAD_OF_ROW = INSTEAD_OF_ROW;
    }

    public String getFIRE_ONCE() {
        return FIRE_ONCE;
    }

    public void setFIRE_ONCE(String FIRE_ONCE) {
        this.FIRE_ONCE = FIRE_ONCE;
    }

    public String getAPPLY_SERVER_ONLY() {
        return APPLY_SERVER_ONLY;
    }

    public void setAPPLY_SERVER_ONLY(String APPLY_SERVER_ONLY) {
        this.APPLY_SERVER_ONLY = APPLY_SERVER_ONLY;
    }
}
