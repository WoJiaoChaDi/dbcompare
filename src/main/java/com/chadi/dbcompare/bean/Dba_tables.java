package com.chadi.dbcompare.bean;

public class Dba_tables {

    private String OWNER;
    private String TABLE_NAME;
    private String TABLESPACE_NAME;
    private String CLUSTER_NAME;
    private String IOT_NAME;
    private String STATUS;
    private String PCT_FREE;
    private String PCT_USED;
    private String INI_TRANS;
    private String MAX_TRANS;
    private String INITIAL_EXTENT;
    private String NEXT_EXTENT;
    private String MIN_EXTENTS;
    private String MAX_EXTENTS;
    private String PCT_INCREASE;
    private String FREELISTS;
    private String FREELIST_GROUPS;
    private String LOGGING;
    private String BACKED_UP;
    private String NUM_ROWS;
    private String BLOCKS;
    private String EMPTY_BLOCKS;
    private String AVG_SPACE;
    private String CHAIN_CNT;
    private String AVG_ROW_LEN;
    private String AVG_SPACE_FREELIST_BLOCKS;
    private String NUM_FREELIST_BLOCKS;
    private String DEGREE;
    private String INSTANCES;
    private String CACHE;
    private String TABLE_LOCK;
    private String SAMPLE_SIZE;
    private String LAST_ANALYZED;
    private String PARTITIONED;
    private String IOT_TYPE;
    private String TEMPORARY;
    private String SECONDARY;
    private String NESTED;
    private String BUFFER_POOL;
    private String FLASH_CACHE;
    private String CELL_FLASH_CACHE;
    private String ROW_MOVEMENT;
    private String GLOBAL_STATS;
    private String USER_STATS;
    private String DURATION;
    private String SKIP_CORRUPT;
    private String MONITORING;
    private String CLUSTER_OWNER;
    private String DEPENDENCIES;
    private String COMPRESSION;
    private String COMPRESS_FOR;
    private String DROPPED;
    private String READ_ONLY;
    private String SEGMENT_CREATED;
    private String RESULT_CACHE;

    public Dba_tables() {
    }

    public Dba_tables(String OWNER, String TABLE_NAME, String TABLESPACE_NAME, String CLUSTER_NAME, String IOT_NAME, String STATUS, String PCT_FREE, String PCT_USED, String INI_TRANS, String MAX_TRANS, String INITIAL_EXTENT, String NEXT_EXTENT, String MIN_EXTENTS, String MAX_EXTENTS, String PCT_INCREASE, String FREELISTS, String FREELIST_GROUPS, String LOGGING, String BACKED_UP, String NUM_ROWS, String BLOCKS, String EMPTY_BLOCKS, String AVG_SPACE, String CHAIN_CNT, String AVG_ROW_LEN, String AVG_SPACE_FREELIST_BLOCKS, String NUM_FREELIST_BLOCKS, String DEGREE, String INSTANCES, String CACHE, String TABLE_LOCK, String SAMPLE_SIZE, String LAST_ANALYZED, String PARTITIONED, String IOT_TYPE, String TEMPORARY, String SECONDARY, String NESTED, String BUFFER_POOL, String FLASH_CACHE, String CELL_FLASH_CACHE, String ROW_MOVEMENT, String GLOBAL_STATS, String USER_STATS, String DURATION, String SKIP_CORRUPT, String MONITORING, String CLUSTER_OWNER, String DEPENDENCIES, String COMPRESSION, String COMPRESS_FOR, String DROPPED, String READ_ONLY, String SEGMENT_CREATED, String RESULT_CACHE) {
        this.OWNER = OWNER;
        this.TABLE_NAME = TABLE_NAME;
        this.TABLESPACE_NAME = TABLESPACE_NAME;
        this.CLUSTER_NAME = CLUSTER_NAME;
        this.IOT_NAME = IOT_NAME;
        this.STATUS = STATUS;
        this.PCT_FREE = PCT_FREE;
        this.PCT_USED = PCT_USED;
        this.INI_TRANS = INI_TRANS;
        this.MAX_TRANS = MAX_TRANS;
        this.INITIAL_EXTENT = INITIAL_EXTENT;
        this.NEXT_EXTENT = NEXT_EXTENT;
        this.MIN_EXTENTS = MIN_EXTENTS;
        this.MAX_EXTENTS = MAX_EXTENTS;
        this.PCT_INCREASE = PCT_INCREASE;
        this.FREELISTS = FREELISTS;
        this.FREELIST_GROUPS = FREELIST_GROUPS;
        this.LOGGING = LOGGING;
        this.BACKED_UP = BACKED_UP;
        this.NUM_ROWS = NUM_ROWS;
        this.BLOCKS = BLOCKS;
        this.EMPTY_BLOCKS = EMPTY_BLOCKS;
        this.AVG_SPACE = AVG_SPACE;
        this.CHAIN_CNT = CHAIN_CNT;
        this.AVG_ROW_LEN = AVG_ROW_LEN;
        this.AVG_SPACE_FREELIST_BLOCKS = AVG_SPACE_FREELIST_BLOCKS;
        this.NUM_FREELIST_BLOCKS = NUM_FREELIST_BLOCKS;
        this.DEGREE = DEGREE;
        this.INSTANCES = INSTANCES;
        this.CACHE = CACHE;
        this.TABLE_LOCK = TABLE_LOCK;
        this.SAMPLE_SIZE = SAMPLE_SIZE;
        this.LAST_ANALYZED = LAST_ANALYZED;
        this.PARTITIONED = PARTITIONED;
        this.IOT_TYPE = IOT_TYPE;
        this.TEMPORARY = TEMPORARY;
        this.SECONDARY = SECONDARY;
        this.NESTED = NESTED;
        this.BUFFER_POOL = BUFFER_POOL;
        this.FLASH_CACHE = FLASH_CACHE;
        this.CELL_FLASH_CACHE = CELL_FLASH_CACHE;
        this.ROW_MOVEMENT = ROW_MOVEMENT;
        this.GLOBAL_STATS = GLOBAL_STATS;
        this.USER_STATS = USER_STATS;
        this.DURATION = DURATION;
        this.SKIP_CORRUPT = SKIP_CORRUPT;
        this.MONITORING = MONITORING;
        this.CLUSTER_OWNER = CLUSTER_OWNER;
        this.DEPENDENCIES = DEPENDENCIES;
        this.COMPRESSION = COMPRESSION;
        this.COMPRESS_FOR = COMPRESS_FOR;
        this.DROPPED = DROPPED;
        this.READ_ONLY = READ_ONLY;
        this.SEGMENT_CREATED = SEGMENT_CREATED;
        this.RESULT_CACHE = RESULT_CACHE;
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

    public String getTABLESPACE_NAME() {
        return TABLESPACE_NAME;
    }

    public void setTABLESPACE_NAME(String TABLESPACE_NAME) {
        this.TABLESPACE_NAME = TABLESPACE_NAME;
    }

    public String getCLUSTER_NAME() {
        return CLUSTER_NAME;
    }

    public void setCLUSTER_NAME(String CLUSTER_NAME) {
        this.CLUSTER_NAME = CLUSTER_NAME;
    }

    public String getIOT_NAME() {
        return IOT_NAME;
    }

    public void setIOT_NAME(String IOT_NAME) {
        this.IOT_NAME = IOT_NAME;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getPCT_FREE() {
        return PCT_FREE;
    }

    public void setPCT_FREE(String PCT_FREE) {
        this.PCT_FREE = PCT_FREE;
    }

    public String getPCT_USED() {
        return PCT_USED;
    }

    public void setPCT_USED(String PCT_USED) {
        this.PCT_USED = PCT_USED;
    }

    public String getINI_TRANS() {
        return INI_TRANS;
    }

    public void setINI_TRANS(String INI_TRANS) {
        this.INI_TRANS = INI_TRANS;
    }

    public String getMAX_TRANS() {
        return MAX_TRANS;
    }

    public void setMAX_TRANS(String MAX_TRANS) {
        this.MAX_TRANS = MAX_TRANS;
    }

    public String getINITIAL_EXTENT() {
        return INITIAL_EXTENT;
    }

    public void setINITIAL_EXTENT(String INITIAL_EXTENT) {
        this.INITIAL_EXTENT = INITIAL_EXTENT;
    }

    public String getNEXT_EXTENT() {
        return NEXT_EXTENT;
    }

    public void setNEXT_EXTENT(String NEXT_EXTENT) {
        this.NEXT_EXTENT = NEXT_EXTENT;
    }

    public String getMIN_EXTENTS() {
        return MIN_EXTENTS;
    }

    public void setMIN_EXTENTS(String MIN_EXTENTS) {
        this.MIN_EXTENTS = MIN_EXTENTS;
    }

    public String getMAX_EXTENTS() {
        return MAX_EXTENTS;
    }

    public void setMAX_EXTENTS(String MAX_EXTENTS) {
        this.MAX_EXTENTS = MAX_EXTENTS;
    }

    public String getPCT_INCREASE() {
        return PCT_INCREASE;
    }

    public void setPCT_INCREASE(String PCT_INCREASE) {
        this.PCT_INCREASE = PCT_INCREASE;
    }

    public String getFREELISTS() {
        return FREELISTS;
    }

    public void setFREELISTS(String FREELISTS) {
        this.FREELISTS = FREELISTS;
    }

    public String getFREELIST_GROUPS() {
        return FREELIST_GROUPS;
    }

    public void setFREELIST_GROUPS(String FREELIST_GROUPS) {
        this.FREELIST_GROUPS = FREELIST_GROUPS;
    }

    public String getLOGGING() {
        return LOGGING;
    }

    public void setLOGGING(String LOGGING) {
        this.LOGGING = LOGGING;
    }

    public String getBACKED_UP() {
        return BACKED_UP;
    }

    public void setBACKED_UP(String BACKED_UP) {
        this.BACKED_UP = BACKED_UP;
    }

    public String getNUM_ROWS() {
        return NUM_ROWS;
    }

    public void setNUM_ROWS(String NUM_ROWS) {
        this.NUM_ROWS = NUM_ROWS;
    }

    public String getBLOCKS() {
        return BLOCKS;
    }

    public void setBLOCKS(String BLOCKS) {
        this.BLOCKS = BLOCKS;
    }

    public String getEMPTY_BLOCKS() {
        return EMPTY_BLOCKS;
    }

    public void setEMPTY_BLOCKS(String EMPTY_BLOCKS) {
        this.EMPTY_BLOCKS = EMPTY_BLOCKS;
    }

    public String getAVG_SPACE() {
        return AVG_SPACE;
    }

    public void setAVG_SPACE(String AVG_SPACE) {
        this.AVG_SPACE = AVG_SPACE;
    }

    public String getCHAIN_CNT() {
        return CHAIN_CNT;
    }

    public void setCHAIN_CNT(String CHAIN_CNT) {
        this.CHAIN_CNT = CHAIN_CNT;
    }

    public String getAVG_ROW_LEN() {
        return AVG_ROW_LEN;
    }

    public void setAVG_ROW_LEN(String AVG_ROW_LEN) {
        this.AVG_ROW_LEN = AVG_ROW_LEN;
    }

    public String getAVG_SPACE_FREELIST_BLOCKS() {
        return AVG_SPACE_FREELIST_BLOCKS;
    }

    public void setAVG_SPACE_FREELIST_BLOCKS(String AVG_SPACE_FREELIST_BLOCKS) {
        this.AVG_SPACE_FREELIST_BLOCKS = AVG_SPACE_FREELIST_BLOCKS;
    }

    public String getNUM_FREELIST_BLOCKS() {
        return NUM_FREELIST_BLOCKS;
    }

    public void setNUM_FREELIST_BLOCKS(String NUM_FREELIST_BLOCKS) {
        this.NUM_FREELIST_BLOCKS = NUM_FREELIST_BLOCKS;
    }

    public String getDEGREE() {
        return DEGREE;
    }

    public void setDEGREE(String DEGREE) {
        this.DEGREE = DEGREE;
    }

    public String getINSTANCES() {
        return INSTANCES;
    }

    public void setINSTANCES(String INSTANCES) {
        this.INSTANCES = INSTANCES;
    }

    public String getCACHE() {
        return CACHE;
    }

    public void setCACHE(String CACHE) {
        this.CACHE = CACHE;
    }

    public String getTABLE_LOCK() {
        return TABLE_LOCK;
    }

    public void setTABLE_LOCK(String TABLE_LOCK) {
        this.TABLE_LOCK = TABLE_LOCK;
    }

    public String getSAMPLE_SIZE() {
        return SAMPLE_SIZE;
    }

    public void setSAMPLE_SIZE(String SAMPLE_SIZE) {
        this.SAMPLE_SIZE = SAMPLE_SIZE;
    }

    public String getLAST_ANALYZED() {
        return LAST_ANALYZED;
    }

    public void setLAST_ANALYZED(String LAST_ANALYZED) {
        this.LAST_ANALYZED = LAST_ANALYZED;
    }

    public String getPARTITIONED() {
        return PARTITIONED;
    }

    public void setPARTITIONED(String PARTITIONED) {
        this.PARTITIONED = PARTITIONED;
    }

    public String getIOT_TYPE() {
        return IOT_TYPE;
    }

    public void setIOT_TYPE(String IOT_TYPE) {
        this.IOT_TYPE = IOT_TYPE;
    }

    public String getTEMPORARY() {
        return TEMPORARY;
    }

    public void setTEMPORARY(String TEMPORARY) {
        this.TEMPORARY = TEMPORARY;
    }

    public String getSECONDARY() {
        return SECONDARY;
    }

    public void setSECONDARY(String SECONDARY) {
        this.SECONDARY = SECONDARY;
    }

    public String getNESTED() {
        return NESTED;
    }

    public void setNESTED(String NESTED) {
        this.NESTED = NESTED;
    }

    public String getBUFFER_POOL() {
        return BUFFER_POOL;
    }

    public void setBUFFER_POOL(String BUFFER_POOL) {
        this.BUFFER_POOL = BUFFER_POOL;
    }

    public String getFLASH_CACHE() {
        return FLASH_CACHE;
    }

    public void setFLASH_CACHE(String FLASH_CACHE) {
        this.FLASH_CACHE = FLASH_CACHE;
    }

    public String getCELL_FLASH_CACHE() {
        return CELL_FLASH_CACHE;
    }

    public void setCELL_FLASH_CACHE(String CELL_FLASH_CACHE) {
        this.CELL_FLASH_CACHE = CELL_FLASH_CACHE;
    }

    public String getROW_MOVEMENT() {
        return ROW_MOVEMENT;
    }

    public void setROW_MOVEMENT(String ROW_MOVEMENT) {
        this.ROW_MOVEMENT = ROW_MOVEMENT;
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

    public String getDURATION() {
        return DURATION;
    }

    public void setDURATION(String DURATION) {
        this.DURATION = DURATION;
    }

    public String getSKIP_CORRUPT() {
        return SKIP_CORRUPT;
    }

    public void setSKIP_CORRUPT(String SKIP_CORRUPT) {
        this.SKIP_CORRUPT = SKIP_CORRUPT;
    }

    public String getMONITORING() {
        return MONITORING;
    }

    public void setMONITORING(String MONITORING) {
        this.MONITORING = MONITORING;
    }

    public String getCLUSTER_OWNER() {
        return CLUSTER_OWNER;
    }

    public void setCLUSTER_OWNER(String CLUSTER_OWNER) {
        this.CLUSTER_OWNER = CLUSTER_OWNER;
    }

    public String getDEPENDENCIES() {
        return DEPENDENCIES;
    }

    public void setDEPENDENCIES(String DEPENDENCIES) {
        this.DEPENDENCIES = DEPENDENCIES;
    }

    public String getCOMPRESSION() {
        return COMPRESSION;
    }

    public void setCOMPRESSION(String COMPRESSION) {
        this.COMPRESSION = COMPRESSION;
    }

    public String getCOMPRESS_FOR() {
        return COMPRESS_FOR;
    }

    public void setCOMPRESS_FOR(String COMPRESS_FOR) {
        this.COMPRESS_FOR = COMPRESS_FOR;
    }

    public String getDROPPED() {
        return DROPPED;
    }

    public void setDROPPED(String DROPPED) {
        this.DROPPED = DROPPED;
    }

    public String getREAD_ONLY() {
        return READ_ONLY;
    }

    public void setREAD_ONLY(String READ_ONLY) {
        this.READ_ONLY = READ_ONLY;
    }

    public String getSEGMENT_CREATED() {
        return SEGMENT_CREATED;
    }

    public void setSEGMENT_CREATED(String SEGMENT_CREATED) {
        this.SEGMENT_CREATED = SEGMENT_CREATED;
    }

    public String getRESULT_CACHE() {
        return RESULT_CACHE;
    }

    public void setRESULT_CACHE(String RESULT_CACHE) {
        this.RESULT_CACHE = RESULT_CACHE;
    }

    @Override
    public String toString() {
        return "Dba_tables{" +
                "OWNER='" + OWNER + '\'' +
                ", TABLE_NAME='" + TABLE_NAME + '\'' +
                ", TABLESPACE_NAME='" + TABLESPACE_NAME + '\'' +
                ", CLUSTER_NAME='" + CLUSTER_NAME + '\'' +
                ", IOT_NAME='" + IOT_NAME + '\'' +
                ", STATUS='" + STATUS + '\'' +
                ", PCT_FREE='" + PCT_FREE + '\'' +
                ", PCT_USED='" + PCT_USED + '\'' +
                ", INI_TRANS='" + INI_TRANS + '\'' +
                ", MAX_TRANS='" + MAX_TRANS + '\'' +
                ", INITIAL_EXTENT='" + INITIAL_EXTENT + '\'' +
                ", NEXT_EXTENT='" + NEXT_EXTENT + '\'' +
                ", MIN_EXTENTS='" + MIN_EXTENTS + '\'' +
                ", MAX_EXTENTS='" + MAX_EXTENTS + '\'' +
                ", PCT_INCREASE='" + PCT_INCREASE + '\'' +
                ", FREELISTS='" + FREELISTS + '\'' +
                ", FREELIST_GROUPS='" + FREELIST_GROUPS + '\'' +
                ", LOGGING='" + LOGGING + '\'' +
                ", BACKED_UP='" + BACKED_UP + '\'' +
                ", NUM_ROWS='" + NUM_ROWS + '\'' +
                ", BLOCKS='" + BLOCKS + '\'' +
                ", EMPTY_BLOCKS='" + EMPTY_BLOCKS + '\'' +
                ", AVG_SPACE='" + AVG_SPACE + '\'' +
                ", CHAIN_CNT='" + CHAIN_CNT + '\'' +
                ", AVG_ROW_LEN='" + AVG_ROW_LEN + '\'' +
                ", AVG_SPACE_FREELIST_BLOCKS='" + AVG_SPACE_FREELIST_BLOCKS + '\'' +
                ", NUM_FREELIST_BLOCKS='" + NUM_FREELIST_BLOCKS + '\'' +
                ", DEGREE='" + DEGREE + '\'' +
                ", INSTANCES='" + INSTANCES + '\'' +
                ", CACHE='" + CACHE + '\'' +
                ", TABLE_LOCK='" + TABLE_LOCK + '\'' +
                ", SAMPLE_SIZE='" + SAMPLE_SIZE + '\'' +
                ", LAST_ANALYZED='" + LAST_ANALYZED + '\'' +
                ", PARTITIONED='" + PARTITIONED + '\'' +
                ", IOT_TYPE='" + IOT_TYPE + '\'' +
                ", TEMPORARY='" + TEMPORARY + '\'' +
                ", SECONDARY='" + SECONDARY + '\'' +
                ", NESTED='" + NESTED + '\'' +
                ", BUFFER_POOL='" + BUFFER_POOL + '\'' +
                ", FLASH_CACHE='" + FLASH_CACHE + '\'' +
                ", CELL_FLASH_CACHE='" + CELL_FLASH_CACHE + '\'' +
                ", ROW_MOVEMENT='" + ROW_MOVEMENT + '\'' +
                ", GLOBAL_STATS='" + GLOBAL_STATS + '\'' +
                ", USER_STATS='" + USER_STATS + '\'' +
                ", DURATION='" + DURATION + '\'' +
                ", SKIP_CORRUPT='" + SKIP_CORRUPT + '\'' +
                ", MONITORING='" + MONITORING + '\'' +
                ", CLUSTER_OWNER='" + CLUSTER_OWNER + '\'' +
                ", DEPENDENCIES='" + DEPENDENCIES + '\'' +
                ", COMPRESSION='" + COMPRESSION + '\'' +
                ", COMPRESS_FOR='" + COMPRESS_FOR + '\'' +
                ", DROPPED='" + DROPPED + '\'' +
                ", READ_ONLY='" + READ_ONLY + '\'' +
                ", SEGMENT_CREATED='" + SEGMENT_CREATED + '\'' +
                ", RESULT_CACHE='" + RESULT_CACHE + '\'' +
                '}';
    }
}
