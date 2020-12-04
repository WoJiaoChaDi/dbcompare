package com.chadi.dbcompare.bean;

public class Dba_ind_columns {

    private String INDEX_OWNER;
    private String INDEX_NAME;
    private String TABLE_OWNER;
    private String TABLE_NAME;
    private String COLUMN_NAME;
    private String COLUMN_POSITION;
    private String COLUMN_LENGTH;
    private String CHAR_LENGTH;
    private String DESCEND;

    public Dba_ind_columns() {
    }

    public Dba_ind_columns(String INDEX_OWNER, String INDEX_NAME, String TABLE_OWNER, String TABLE_NAME, String COLUMN_NAME, String COLUMN_POSITION, String COLUMN_LENGTH, String CHAR_LENGTH, String DESCEND) {
        this.INDEX_OWNER = INDEX_OWNER;
        this.INDEX_NAME = INDEX_NAME;
        this.TABLE_OWNER = TABLE_OWNER;
        this.TABLE_NAME = TABLE_NAME;
        this.COLUMN_NAME = COLUMN_NAME;
        this.COLUMN_POSITION = COLUMN_POSITION;
        this.COLUMN_LENGTH = COLUMN_LENGTH;
        this.CHAR_LENGTH = CHAR_LENGTH;
        this.DESCEND = DESCEND;
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

    public String getTABLE_OWNER() {
        return TABLE_OWNER;
    }

    public void setTABLE_OWNER(String TABLE_OWNER) {
        this.TABLE_OWNER = TABLE_OWNER;
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

    public String getCOLUMN_POSITION() {
        return COLUMN_POSITION;
    }

    public void setCOLUMN_POSITION(String COLUMN_POSITION) {
        this.COLUMN_POSITION = COLUMN_POSITION;
    }

    public String getCOLUMN_LENGTH() {
        return COLUMN_LENGTH;
    }

    public void setCOLUMN_LENGTH(String COLUMN_LENGTH) {
        this.COLUMN_LENGTH = COLUMN_LENGTH;
    }

    public String getCHAR_LENGTH() {
        return CHAR_LENGTH;
    }

    public void setCHAR_LENGTH(String CHAR_LENGTH) {
        this.CHAR_LENGTH = CHAR_LENGTH;
    }

    public String getDESCEND() {
        return DESCEND;
    }

    public void setDESCEND(String DESCEND) {
        this.DESCEND = DESCEND;
    }

    @Override
    public String toString() {
        return "Dba_ind_columns{" +
                "INDEX_OWNER='" + INDEX_OWNER + '\'' +
                ", INDEX_NAME='" + INDEX_NAME + '\'' +
                ", TABLE_OWNER='" + TABLE_OWNER + '\'' +
                ", TABLE_NAME='" + TABLE_NAME + '\'' +
                ", COLUMN_NAME='" + COLUMN_NAME + '\'' +
                ", COLUMN_POSITION='" + COLUMN_POSITION + '\'' +
                ", COLUMN_LENGTH='" + COLUMN_LENGTH + '\'' +
                ", CHAR_LENGTH='" + CHAR_LENGTH + '\'' +
                ", DESCEND='" + DESCEND + '\'' +
                '}';
    }
}
