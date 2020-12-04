package com.chadi.dbcompare.bean;

public class Dba_tab_cols {
    private String OWNER;
    private String TABLE_NAME;
    private String COLUMN_NAME;
    private String DATA_TYPE;
    private String DATA_TYPE_MOD;
    private String DATA_TYPE_OWNER;
    private String DATA_LENGTH;
    private String DATA_PRECISION;
    private String DATA_SCALE;
    private String NULLABLE;
    private String COLUMN_ID;
    private String DEFAULT_LENGTH;
    private String DATA_DEFAULT;
    private String NUM_DISTINCT;
    private String LOW_VALUE;
    private String HIGH_VALUE;
    private String DENSITY;
    private String NUM_NULLS;
    private String NUM_BUCKETS;
    private String LAST_ANALYZED;
    private String SAMPLE_SIZE;
    private String CHARACTER_SET_NAME;
    private String CHAR_COL_DECL_LENGTH;
    private String GLOBAL_STATS;
    private String USER_STATS;
    private String AVG_COL_LEN;
    private String CHAR_LENGTH;
    private String CHAR_USED;
    private String V80_FMT_IMAGE;
    private String DATA_UPGRADED;
    private String HIDDEN_COLUMN;
    private String VIRTUAL_COLUMN;
    private String SEGMENT_COLUMN_ID;
    private String INTERNAL_COLUMN_ID;
    private String HISTOGRAM;
    private String QUALIFIED_COL_NAME;

    public Dba_tab_cols() {
    }

    public Dba_tab_cols(String OWNER, String TABLE_NAME, String COLUMN_NAME, String DATA_TYPE, String DATA_TYPE_MOD, String DATA_TYPE_OWNER, String DATA_LENGTH, String DATA_PRECISION, String DATA_SCALE, String NULLABLE, String COLUMN_ID, String DEFAULT_LENGTH, String DATA_DEFAULT, String NUM_DISTINCT, String LOW_VALUE, String HIGH_VALUE, String DENSITY, String NUM_NULLS, String NUM_BUCKETS, String LAST_ANALYZED, String SAMPLE_SIZE, String CHARACTER_SET_NAME, String CHAR_COL_DECL_LENGTH, String GLOBAL_STATS, String USER_STATS, String AVG_COL_LEN, String CHAR_LENGTH, String CHAR_USED, String v80_FMT_IMAGE, String DATA_UPGRADED, String HIDDEN_COLUMN, String VIRTUAL_COLUMN, String SEGMENT_COLUMN_ID, String INTERNAL_COLUMN_ID, String HISTOGRAM, String QUALIFIED_COL_NAME) {
        this.OWNER = OWNER;
        this.TABLE_NAME = TABLE_NAME;
        this.COLUMN_NAME = COLUMN_NAME;
        this.DATA_TYPE = DATA_TYPE;
        this.DATA_TYPE_MOD = DATA_TYPE_MOD;
        this.DATA_TYPE_OWNER = DATA_TYPE_OWNER;
        this.DATA_LENGTH = DATA_LENGTH;
        this.DATA_PRECISION = DATA_PRECISION;
        this.DATA_SCALE = DATA_SCALE;
        this.NULLABLE = NULLABLE;
        this.COLUMN_ID = COLUMN_ID;
        this.DEFAULT_LENGTH = DEFAULT_LENGTH;
        this.DATA_DEFAULT = DATA_DEFAULT;
        this.NUM_DISTINCT = NUM_DISTINCT;
        this.LOW_VALUE = LOW_VALUE;
        this.HIGH_VALUE = HIGH_VALUE;
        this.DENSITY = DENSITY;
        this.NUM_NULLS = NUM_NULLS;
        this.NUM_BUCKETS = NUM_BUCKETS;
        this.LAST_ANALYZED = LAST_ANALYZED;
        this.SAMPLE_SIZE = SAMPLE_SIZE;
        this.CHARACTER_SET_NAME = CHARACTER_SET_NAME;
        this.CHAR_COL_DECL_LENGTH = CHAR_COL_DECL_LENGTH;
        this.GLOBAL_STATS = GLOBAL_STATS;
        this.USER_STATS = USER_STATS;
        this.AVG_COL_LEN = AVG_COL_LEN;
        this.CHAR_LENGTH = CHAR_LENGTH;
        this.CHAR_USED = CHAR_USED;
        V80_FMT_IMAGE = v80_FMT_IMAGE;
        this.DATA_UPGRADED = DATA_UPGRADED;
        this.HIDDEN_COLUMN = HIDDEN_COLUMN;
        this.VIRTUAL_COLUMN = VIRTUAL_COLUMN;
        this.SEGMENT_COLUMN_ID = SEGMENT_COLUMN_ID;
        this.INTERNAL_COLUMN_ID = INTERNAL_COLUMN_ID;
        this.HISTOGRAM = HISTOGRAM;
        this.QUALIFIED_COL_NAME = QUALIFIED_COL_NAME;
    }

    public String getOWNER() {
        return OWNER;
    }

    public void setOWNER(String OWNER) {
        this.OWNER = OWNER;
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

    public String getDATA_TYPE() {
        return DATA_TYPE;
    }

    public void setDATA_TYPE(String DATA_TYPE) {
        this.DATA_TYPE = DATA_TYPE;
    }

    public String getDATA_TYPE_MOD() {
        return DATA_TYPE_MOD;
    }

    public void setDATA_TYPE_MOD(String DATA_TYPE_MOD) {
        this.DATA_TYPE_MOD = DATA_TYPE_MOD;
    }

    public String getDATA_TYPE_OWNER() {
        return DATA_TYPE_OWNER;
    }

    public void setDATA_TYPE_OWNER(String DATA_TYPE_OWNER) {
        this.DATA_TYPE_OWNER = DATA_TYPE_OWNER;
    }

    public String getDATA_LENGTH() {
        return DATA_LENGTH;
    }

    public void setDATA_LENGTH(String DATA_LENGTH) {
        this.DATA_LENGTH = DATA_LENGTH;
    }

    public String getDATA_PRECISION() {
        return DATA_PRECISION;
    }

    public void setDATA_PRECISION(String DATA_PRECISION) {
        this.DATA_PRECISION = DATA_PRECISION;
    }

    public String getDATA_SCALE() {
        return DATA_SCALE;
    }

    public void setDATA_SCALE(String DATA_SCALE) {
        this.DATA_SCALE = DATA_SCALE;
    }

    public String getNULLABLE() {
        return NULLABLE;
    }

    public void setNULLABLE(String NULLABLE) {
        this.NULLABLE = NULLABLE;
    }

    public String getCOLUMN_ID() {
        return COLUMN_ID;
    }

    public void setCOLUMN_ID(String COLUMN_ID) {
        this.COLUMN_ID = COLUMN_ID;
    }

    public String getDEFAULT_LENGTH() {
        return DEFAULT_LENGTH;
    }

    public void setDEFAULT_LENGTH(String DEFAULT_LENGTH) {
        this.DEFAULT_LENGTH = DEFAULT_LENGTH;
    }

    public String getDATA_DEFAULT() {
        return DATA_DEFAULT;
    }

    public void setDATA_DEFAULT(String DATA_DEFAULT) {
        this.DATA_DEFAULT = DATA_DEFAULT;
    }

    public String getNUM_DISTINCT() {
        return NUM_DISTINCT;
    }

    public void setNUM_DISTINCT(String NUM_DISTINCT) {
        this.NUM_DISTINCT = NUM_DISTINCT;
    }

    public String getLOW_VALUE() {
        return LOW_VALUE;
    }

    public void setLOW_VALUE(String LOW_VALUE) {
        this.LOW_VALUE = LOW_VALUE;
    }

    public String getHIGH_VALUE() {
        return HIGH_VALUE;
    }

    public void setHIGH_VALUE(String HIGH_VALUE) {
        this.HIGH_VALUE = HIGH_VALUE;
    }

    public String getDENSITY() {
        return DENSITY;
    }

    public void setDENSITY(String DENSITY) {
        this.DENSITY = DENSITY;
    }

    public String getNUM_NULLS() {
        return NUM_NULLS;
    }

    public void setNUM_NULLS(String NUM_NULLS) {
        this.NUM_NULLS = NUM_NULLS;
    }

    public String getNUM_BUCKETS() {
        return NUM_BUCKETS;
    }

    public void setNUM_BUCKETS(String NUM_BUCKETS) {
        this.NUM_BUCKETS = NUM_BUCKETS;
    }

    public String getLAST_ANALYZED() {
        return LAST_ANALYZED;
    }

    public void setLAST_ANALYZED(String LAST_ANALYZED) {
        this.LAST_ANALYZED = LAST_ANALYZED;
    }

    public String getSAMPLE_SIZE() {
        return SAMPLE_SIZE;
    }

    public void setSAMPLE_SIZE(String SAMPLE_SIZE) {
        this.SAMPLE_SIZE = SAMPLE_SIZE;
    }

    public String getCHARACTER_SET_NAME() {
        return CHARACTER_SET_NAME;
    }

    public void setCHARACTER_SET_NAME(String CHARACTER_SET_NAME) {
        this.CHARACTER_SET_NAME = CHARACTER_SET_NAME;
    }

    public String getCHAR_COL_DECL_LENGTH() {
        return CHAR_COL_DECL_LENGTH;
    }

    public void setCHAR_COL_DECL_LENGTH(String CHAR_COL_DECL_LENGTH) {
        this.CHAR_COL_DECL_LENGTH = CHAR_COL_DECL_LENGTH;
    }

    public String getGLOBAL_STATS() {
        return GLOBAL_STATS;
    }

    public void setGLOBAL_STATS(String GLOBAL_STATS) {
        this.GLOBAL_STATS = GLOBAL_STATS;
    }

    public String getUSER_STATS() {
        return USER_STATS;
    }

    public void setUSER_STATS(String USER_STATS) {
        this.USER_STATS = USER_STATS;
    }

    public String getAVG_COL_LEN() {
        return AVG_COL_LEN;
    }

    public void setAVG_COL_LEN(String AVG_COL_LEN) {
        this.AVG_COL_LEN = AVG_COL_LEN;
    }

    public String getCHAR_LENGTH() {
        return CHAR_LENGTH;
    }

    public void setCHAR_LENGTH(String CHAR_LENGTH) {
        this.CHAR_LENGTH = CHAR_LENGTH;
    }

    public String getCHAR_USED() {
        return CHAR_USED;
    }

    public void setCHAR_USED(String CHAR_USED) {
        this.CHAR_USED = CHAR_USED;
    }

    public String getV80_FMT_IMAGE() {
        return V80_FMT_IMAGE;
    }

    public void setV80_FMT_IMAGE(String v80_FMT_IMAGE) {
        V80_FMT_IMAGE = v80_FMT_IMAGE;
    }

    public String getDATA_UPGRADED() {
        return DATA_UPGRADED;
    }

    public void setDATA_UPGRADED(String DATA_UPGRADED) {
        this.DATA_UPGRADED = DATA_UPGRADED;
    }

    public String getHIDDEN_COLUMN() {
        return HIDDEN_COLUMN;
    }

    public void setHIDDEN_COLUMN(String HIDDEN_COLUMN) {
        this.HIDDEN_COLUMN = HIDDEN_COLUMN;
    }

    public String getVIRTUAL_COLUMN() {
        return VIRTUAL_COLUMN;
    }

    public void setVIRTUAL_COLUMN(String VIRTUAL_COLUMN) {
        this.VIRTUAL_COLUMN = VIRTUAL_COLUMN;
    }

    public String getSEGMENT_COLUMN_ID() {
        return SEGMENT_COLUMN_ID;
    }

    public void setSEGMENT_COLUMN_ID(String SEGMENT_COLUMN_ID) {
        this.SEGMENT_COLUMN_ID = SEGMENT_COLUMN_ID;
    }

    public String getINTERNAL_COLUMN_ID() {
        return INTERNAL_COLUMN_ID;
    }

    public void setINTERNAL_COLUMN_ID(String INTERNAL_COLUMN_ID) {
        this.INTERNAL_COLUMN_ID = INTERNAL_COLUMN_ID;
    }

    public String getHISTOGRAM() {
        return HISTOGRAM;
    }

    public void setHISTOGRAM(String HISTOGRAM) {
        this.HISTOGRAM = HISTOGRAM;
    }

    public String getQUALIFIED_COL_NAME() {
        return QUALIFIED_COL_NAME;
    }

    public void setQUALIFIED_COL_NAME(String QUALIFIED_COL_NAME) {
        this.QUALIFIED_COL_NAME = QUALIFIED_COL_NAME;
    }

    @Override
    public String toString() {
        return "Dba_tab_cols{" +
                "OWNER='" + OWNER + '\'' +
                ", TABLE_NAME='" + TABLE_NAME + '\'' +
                ", COLUMN_NAME='" + COLUMN_NAME + '\'' +
                ", DATA_TYPE='" + DATA_TYPE + '\'' +
                ", DATA_TYPE_MOD='" + DATA_TYPE_MOD + '\'' +
                ", DATA_TYPE_OWNER='" + DATA_TYPE_OWNER + '\'' +
                ", DATA_LENGTH='" + DATA_LENGTH + '\'' +
                ", DATA_PRECISION='" + DATA_PRECISION + '\'' +
                ", DATA_SCALE='" + DATA_SCALE + '\'' +
                ", NULLABLE='" + NULLABLE + '\'' +
                ", COLUMN_ID='" + COLUMN_ID + '\'' +
                ", DEFAULT_LENGTH='" + DEFAULT_LENGTH + '\'' +
                ", DATA_DEFAULT='" + DATA_DEFAULT + '\'' +
                ", NUM_DISTINCT='" + NUM_DISTINCT + '\'' +
                ", LOW_VALUE='" + LOW_VALUE + '\'' +
                ", HIGH_VALUE='" + HIGH_VALUE + '\'' +
                ", DENSITY='" + DENSITY + '\'' +
                ", NUM_NULLS='" + NUM_NULLS + '\'' +
                ", NUM_BUCKETS='" + NUM_BUCKETS + '\'' +
                ", LAST_ANALYZED='" + LAST_ANALYZED + '\'' +
                ", SAMPLE_SIZE='" + SAMPLE_SIZE + '\'' +
                ", CHARACTER_SET_NAME='" + CHARACTER_SET_NAME + '\'' +
                ", CHAR_COL_DECL_LENGTH='" + CHAR_COL_DECL_LENGTH + '\'' +
                ", GLOBAL_STATS='" + GLOBAL_STATS + '\'' +
                ", USER_STATS='" + USER_STATS + '\'' +
                ", AVG_COL_LEN='" + AVG_COL_LEN + '\'' +
                ", CHAR_LENGTH='" + CHAR_LENGTH + '\'' +
                ", CHAR_USED='" + CHAR_USED + '\'' +
                ", V80_FMT_IMAGE='" + V80_FMT_IMAGE + '\'' +
                ", DATA_UPGRADED='" + DATA_UPGRADED + '\'' +
                ", HIDDEN_COLUMN='" + HIDDEN_COLUMN + '\'' +
                ", VIRTUAL_COLUMN='" + VIRTUAL_COLUMN + '\'' +
                ", SEGMENT_COLUMN_ID='" + SEGMENT_COLUMN_ID + '\'' +
                ", INTERNAL_COLUMN_ID='" + INTERNAL_COLUMN_ID + '\'' +
                ", HISTOGRAM='" + HISTOGRAM + '\'' +
                ", QUALIFIED_COL_NAME='" + QUALIFIED_COL_NAME + '\'' +
                '}';
    }
}
