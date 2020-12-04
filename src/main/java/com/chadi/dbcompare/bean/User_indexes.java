package com.chadi.dbcompare.bean;

public class User_indexes {

    private String INDEX_NAME;
    private String INDEX_TYPE;
    private String TABLE_OWNER;
    private String TABLE_NAME;
    private String TABLE_TYPE;
    private String UNIQUENESS;
    private String COMPRESSION;
    private String PREFIX_LENGTH;
    private String TABLESPACE_NAME;
    private String INI_TRANS;
    private String MAX_TRANS;
    private String INITIAL_EXTENT;
    private String NEXT_EXTENT;
    private String MIN_EXTENTS;
    private String MAX_EXTENTS;
    private String PCT_INCREASE;
    private String PCT_THRESHOLD;
    private String INCLUDE_COLUMN;
    private String FREELISTS;
    private String FREELIST_GROUPS;
    private String PCT_FREE;
    private String LOGGING;
    private String BLEVEL;
    private String LEAF_BLOCKS;
    private String DISTINCT_KEYS;
    private String AVG_LEAF_BLOCKS_PER_KEY;
    private String AVG_DATA_BLOCKS_PER_KEY;
    private String CLUSTERING_FACTOR;
    private String STATUS;
    private String NUM_ROWS;
    private String SAMPLE_SIZE;
    private String LAST_ANALYZED;
    private String DEGREE;
    private String INSTANCES;
    private String PARTITIONED;
    private String TEMPORARY;
    private String GENERATED;
    private String SECONDARY;
    private String BUFFER_POOL;
    private String FLASH_CACHE;
    private String CELL_FLASH_CACHE;
    private String USER_STATS;
    private String DURATION;
    private String PCT_DIRECT_ACCESS;
    private String ITYP_OWNER;
    private String ITYP_NAME;
    private String PARAMETERS;
    private String GLOBAL_STATS;
    private String DOMIDX_STATUS;
    private String DOMIDX_OPSTATUS;
    private String FUNCIDX_STATUS;
    private String JOIN_INDEX;
    private String IOT_REDUNDANT_PKEY_ELIM;
    private String DROPPED;
    private String VISIBILITY;
    private String DOMIDX_MANAGEMENT;
    private String SEGMENT_CREATED;

    public User_indexes() {
    }

    public User_indexes(String INDEX_NAME, String INDEX_TYPE, String TABLE_OWNER, String TABLE_NAME, String TABLE_TYPE, String UNIQUENESS, String COMPRESSION, String PREFIX_LENGTH, String TABLESPACE_NAME, String INI_TRANS, String MAX_TRANS, String INITIAL_EXTENT, String NEXT_EXTENT, String MIN_EXTENTS, String MAX_EXTENTS, String PCT_INCREASE, String PCT_THRESHOLD, String INCLUDE_COLUMN, String FREELISTS, String FREELIST_GROUPS, String PCT_FREE, String LOGGING, String BLEVEL, String LEAF_BLOCKS, String DISTINCT_KEYS, String AVG_LEAF_BLOCKS_PER_KEY, String AVG_DATA_BLOCKS_PER_KEY, String CLUSTERING_FACTOR, String STATUS, String NUM_ROWS, String SAMPLE_SIZE, String LAST_ANALYZED, String DEGREE, String INSTANCES, String PARTITIONED, String TEMPORARY, String GENERATED, String SECONDARY, String BUFFER_POOL, String FLASH_CACHE, String CELL_FLASH_CACHE, String USER_STATS, String DURATION, String PCT_DIRECT_ACCESS, String ITYP_OWNER, String ITYP_NAME, String PARAMETERS, String GLOBAL_STATS, String DOMIDX_STATUS, String DOMIDX_OPSTATUS, String FUNCIDX_STATUS, String JOIN_INDEX, String IOT_REDUNDANT_PKEY_ELIM, String DROPPED, String VISIBILITY, String DOMIDX_MANAGEMENT, String SEGMENT_CREATED) {
        this.INDEX_NAME = INDEX_NAME;
        this.INDEX_TYPE = INDEX_TYPE;
        this.TABLE_OWNER = TABLE_OWNER;
        this.TABLE_NAME = TABLE_NAME;
        this.TABLE_TYPE = TABLE_TYPE;
        this.UNIQUENESS = UNIQUENESS;
        this.COMPRESSION = COMPRESSION;
        this.PREFIX_LENGTH = PREFIX_LENGTH;
        this.TABLESPACE_NAME = TABLESPACE_NAME;
        this.INI_TRANS = INI_TRANS;
        this.MAX_TRANS = MAX_TRANS;
        this.INITIAL_EXTENT = INITIAL_EXTENT;
        this.NEXT_EXTENT = NEXT_EXTENT;
        this.MIN_EXTENTS = MIN_EXTENTS;
        this.MAX_EXTENTS = MAX_EXTENTS;
        this.PCT_INCREASE = PCT_INCREASE;
        this.PCT_THRESHOLD = PCT_THRESHOLD;
        this.INCLUDE_COLUMN = INCLUDE_COLUMN;
        this.FREELISTS = FREELISTS;
        this.FREELIST_GROUPS = FREELIST_GROUPS;
        this.PCT_FREE = PCT_FREE;
        this.LOGGING = LOGGING;
        this.BLEVEL = BLEVEL;
        this.LEAF_BLOCKS = LEAF_BLOCKS;
        this.DISTINCT_KEYS = DISTINCT_KEYS;
        this.AVG_LEAF_BLOCKS_PER_KEY = AVG_LEAF_BLOCKS_PER_KEY;
        this.AVG_DATA_BLOCKS_PER_KEY = AVG_DATA_BLOCKS_PER_KEY;
        this.CLUSTERING_FACTOR = CLUSTERING_FACTOR;
        this.STATUS = STATUS;
        this.NUM_ROWS = NUM_ROWS;
        this.SAMPLE_SIZE = SAMPLE_SIZE;
        this.LAST_ANALYZED = LAST_ANALYZED;
        this.DEGREE = DEGREE;
        this.INSTANCES = INSTANCES;
        this.PARTITIONED = PARTITIONED;
        this.TEMPORARY = TEMPORARY;
        this.GENERATED = GENERATED;
        this.SECONDARY = SECONDARY;
        this.BUFFER_POOL = BUFFER_POOL;
        this.FLASH_CACHE = FLASH_CACHE;
        this.CELL_FLASH_CACHE = CELL_FLASH_CACHE;
        this.USER_STATS = USER_STATS;
        this.DURATION = DURATION;
        this.PCT_DIRECT_ACCESS = PCT_DIRECT_ACCESS;
        this.ITYP_OWNER = ITYP_OWNER;
        this.ITYP_NAME = ITYP_NAME;
        this.PARAMETERS = PARAMETERS;
        this.GLOBAL_STATS = GLOBAL_STATS;
        this.DOMIDX_STATUS = DOMIDX_STATUS;
        this.DOMIDX_OPSTATUS = DOMIDX_OPSTATUS;
        this.FUNCIDX_STATUS = FUNCIDX_STATUS;
        this.JOIN_INDEX = JOIN_INDEX;
        this.IOT_REDUNDANT_PKEY_ELIM = IOT_REDUNDANT_PKEY_ELIM;
        this.DROPPED = DROPPED;
        this.VISIBILITY = VISIBILITY;
        this.DOMIDX_MANAGEMENT = DOMIDX_MANAGEMENT;
        this.SEGMENT_CREATED = SEGMENT_CREATED;
    }

    public String getINDEX_NAME() {
        return INDEX_NAME;
    }

    public void setINDEX_NAME(String INDEX_NAME) {
        this.INDEX_NAME = INDEX_NAME;
    }

    public String getINDEX_TYPE() {
        return INDEX_TYPE;
    }

    public void setINDEX_TYPE(String INDEX_TYPE) {
        this.INDEX_TYPE = INDEX_TYPE;
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

    public String getTABLE_TYPE() {
        return TABLE_TYPE;
    }

    public void setTABLE_TYPE(String TABLE_TYPE) {
        this.TABLE_TYPE = TABLE_TYPE;
    }

    public String getUNIQUENESS() {
        return UNIQUENESS;
    }

    public void setUNIQUENESS(String UNIQUENESS) {
        this.UNIQUENESS = UNIQUENESS;
    }

    public String getCOMPRESSION() {
        return COMPRESSION;
    }

    public void setCOMPRESSION(String COMPRESSION) {
        this.COMPRESSION = COMPRESSION;
    }

    public String getPREFIX_LENGTH() {
        return PREFIX_LENGTH;
    }

    public void setPREFIX_LENGTH(String PREFIX_LENGTH) {
        this.PREFIX_LENGTH = PREFIX_LENGTH;
    }

    public String getTABLESPACE_NAME() {
        return TABLESPACE_NAME;
    }

    public void setTABLESPACE_NAME(String TABLESPACE_NAME) {
        this.TABLESPACE_NAME = TABLESPACE_NAME;
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

    public String getPCT_THRESHOLD() {
        return PCT_THRESHOLD;
    }

    public void setPCT_THRESHOLD(String PCT_THRESHOLD) {
        this.PCT_THRESHOLD = PCT_THRESHOLD;
    }

    public String getINCLUDE_COLUMN() {
        return INCLUDE_COLUMN;
    }

    public void setINCLUDE_COLUMN(String INCLUDE_COLUMN) {
        this.INCLUDE_COLUMN = INCLUDE_COLUMN;
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

    public String getPCT_FREE() {
        return PCT_FREE;
    }

    public void setPCT_FREE(String PCT_FREE) {
        this.PCT_FREE = PCT_FREE;
    }

    public String getLOGGING() {
        return LOGGING;
    }

    public void setLOGGING(String LOGGING) {
        this.LOGGING = LOGGING;
    }

    public String getBLEVEL() {
        return BLEVEL;
    }

    public void setBLEVEL(String BLEVEL) {
        this.BLEVEL = BLEVEL;
    }

    public String getLEAF_BLOCKS() {
        return LEAF_BLOCKS;
    }

    public void setLEAF_BLOCKS(String LEAF_BLOCKS) {
        this.LEAF_BLOCKS = LEAF_BLOCKS;
    }

    public String getDISTINCT_KEYS() {
        return DISTINCT_KEYS;
    }

    public void setDISTINCT_KEYS(String DISTINCT_KEYS) {
        this.DISTINCT_KEYS = DISTINCT_KEYS;
    }

    public String getAVG_LEAF_BLOCKS_PER_KEY() {
        return AVG_LEAF_BLOCKS_PER_KEY;
    }

    public void setAVG_LEAF_BLOCKS_PER_KEY(String AVG_LEAF_BLOCKS_PER_KEY) {
        this.AVG_LEAF_BLOCKS_PER_KEY = AVG_LEAF_BLOCKS_PER_KEY;
    }

    public String getAVG_DATA_BLOCKS_PER_KEY() {
        return AVG_DATA_BLOCKS_PER_KEY;
    }

    public void setAVG_DATA_BLOCKS_PER_KEY(String AVG_DATA_BLOCKS_PER_KEY) {
        this.AVG_DATA_BLOCKS_PER_KEY = AVG_DATA_BLOCKS_PER_KEY;
    }

    public String getCLUSTERING_FACTOR() {
        return CLUSTERING_FACTOR;
    }

    public void setCLUSTERING_FACTOR(String CLUSTERING_FACTOR) {
        this.CLUSTERING_FACTOR = CLUSTERING_FACTOR;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getNUM_ROWS() {
        return NUM_ROWS;
    }

    public void setNUM_ROWS(String NUM_ROWS) {
        this.NUM_ROWS = NUM_ROWS;
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

    public String getPARTITIONED() {
        return PARTITIONED;
    }

    public void setPARTITIONED(String PARTITIONED) {
        this.PARTITIONED = PARTITIONED;
    }

    public String getTEMPORARY() {
        return TEMPORARY;
    }

    public void setTEMPORARY(String TEMPORARY) {
        this.TEMPORARY = TEMPORARY;
    }

    public String getGENERATED() {
        return GENERATED;
    }

    public void setGENERATED(String GENERATED) {
        this.GENERATED = GENERATED;
    }

    public String getSECONDARY() {
        return SECONDARY;
    }

    public void setSECONDARY(String SECONDARY) {
        this.SECONDARY = SECONDARY;
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

    public String getPCT_DIRECT_ACCESS() {
        return PCT_DIRECT_ACCESS;
    }

    public void setPCT_DIRECT_ACCESS(String PCT_DIRECT_ACCESS) {
        this.PCT_DIRECT_ACCESS = PCT_DIRECT_ACCESS;
    }

    public String getITYP_OWNER() {
        return ITYP_OWNER;
    }

    public void setITYP_OWNER(String ITYP_OWNER) {
        this.ITYP_OWNER = ITYP_OWNER;
    }

    public String getITYP_NAME() {
        return ITYP_NAME;
    }

    public void setITYP_NAME(String ITYP_NAME) {
        this.ITYP_NAME = ITYP_NAME;
    }

    public String getPARAMETERS() {
        return PARAMETERS;
    }

    public void setPARAMETERS(String PARAMETERS) {
        this.PARAMETERS = PARAMETERS;
    }

    public String getGLOBAL_STATS() {
        return GLOBAL_STATS;
    }

    public void setGLOBAL_STATS(String GLOBAL_STATS) {
        this.GLOBAL_STATS = GLOBAL_STATS;
    }

    public String getDOMIDX_STATUS() {
        return DOMIDX_STATUS;
    }

    public void setDOMIDX_STATUS(String DOMIDX_STATUS) {
        this.DOMIDX_STATUS = DOMIDX_STATUS;
    }

    public String getDOMIDX_OPSTATUS() {
        return DOMIDX_OPSTATUS;
    }

    public void setDOMIDX_OPSTATUS(String DOMIDX_OPSTATUS) {
        this.DOMIDX_OPSTATUS = DOMIDX_OPSTATUS;
    }

    public String getFUNCIDX_STATUS() {
        return FUNCIDX_STATUS;
    }

    public void setFUNCIDX_STATUS(String FUNCIDX_STATUS) {
        this.FUNCIDX_STATUS = FUNCIDX_STATUS;
    }

    public String getJOIN_INDEX() {
        return JOIN_INDEX;
    }

    public void setJOIN_INDEX(String JOIN_INDEX) {
        this.JOIN_INDEX = JOIN_INDEX;
    }

    public String getIOT_REDUNDANT_PKEY_ELIM() {
        return IOT_REDUNDANT_PKEY_ELIM;
    }

    public void setIOT_REDUNDANT_PKEY_ELIM(String IOT_REDUNDANT_PKEY_ELIM) {
        this.IOT_REDUNDANT_PKEY_ELIM = IOT_REDUNDANT_PKEY_ELIM;
    }

    public String getDROPPED() {
        return DROPPED;
    }

    public void setDROPPED(String DROPPED) {
        this.DROPPED = DROPPED;
    }

    public String getVISIBILITY() {
        return VISIBILITY;
    }

    public void setVISIBILITY(String VISIBILITY) {
        this.VISIBILITY = VISIBILITY;
    }

    public String getDOMIDX_MANAGEMENT() {
        return DOMIDX_MANAGEMENT;
    }

    public void setDOMIDX_MANAGEMENT(String DOMIDX_MANAGEMENT) {
        this.DOMIDX_MANAGEMENT = DOMIDX_MANAGEMENT;
    }

    public String getSEGMENT_CREATED() {
        return SEGMENT_CREATED;
    }

    public void setSEGMENT_CREATED(String SEGMENT_CREATED) {
        this.SEGMENT_CREATED = SEGMENT_CREATED;
    }

    @Override
    public String toString() {
        return "User_indexes{" +
                "INDEX_NAME='" + INDEX_NAME + '\'' +
                ", INDEX_TYPE='" + INDEX_TYPE + '\'' +
                ", TABLE_OWNER='" + TABLE_OWNER + '\'' +
                ", TABLE_NAME='" + TABLE_NAME + '\'' +
                ", TABLE_TYPE='" + TABLE_TYPE + '\'' +
                ", UNIQUENESS='" + UNIQUENESS + '\'' +
                ", COMPRESSION='" + COMPRESSION + '\'' +
                ", PREFIX_LENGTH='" + PREFIX_LENGTH + '\'' +
                ", TABLESPACE_NAME='" + TABLESPACE_NAME + '\'' +
                ", INI_TRANS='" + INI_TRANS + '\'' +
                ", MAX_TRANS='" + MAX_TRANS + '\'' +
                ", INITIAL_EXTENT='" + INITIAL_EXTENT + '\'' +
                ", NEXT_EXTENT='" + NEXT_EXTENT + '\'' +
                ", MIN_EXTENTS='" + MIN_EXTENTS + '\'' +
                ", MAX_EXTENTS='" + MAX_EXTENTS + '\'' +
                ", PCT_INCREASE='" + PCT_INCREASE + '\'' +
                ", PCT_THRESHOLD='" + PCT_THRESHOLD + '\'' +
                ", INCLUDE_COLUMN='" + INCLUDE_COLUMN + '\'' +
                ", FREELISTS='" + FREELISTS + '\'' +
                ", FREELIST_GROUPS='" + FREELIST_GROUPS + '\'' +
                ", PCT_FREE='" + PCT_FREE + '\'' +
                ", LOGGING='" + LOGGING + '\'' +
                ", BLEVEL='" + BLEVEL + '\'' +
                ", LEAF_BLOCKS='" + LEAF_BLOCKS + '\'' +
                ", DISTINCT_KEYS='" + DISTINCT_KEYS + '\'' +
                ", AVG_LEAF_BLOCKS_PER_KEY='" + AVG_LEAF_BLOCKS_PER_KEY + '\'' +
                ", AVG_DATA_BLOCKS_PER_KEY='" + AVG_DATA_BLOCKS_PER_KEY + '\'' +
                ", CLUSTERING_FACTOR='" + CLUSTERING_FACTOR + '\'' +
                ", STATUS='" + STATUS + '\'' +
                ", NUM_ROWS='" + NUM_ROWS + '\'' +
                ", SAMPLE_SIZE='" + SAMPLE_SIZE + '\'' +
                ", LAST_ANALYZED='" + LAST_ANALYZED + '\'' +
                ", DEGREE='" + DEGREE + '\'' +
                ", INSTANCES='" + INSTANCES + '\'' +
                ", PARTITIONED='" + PARTITIONED + '\'' +
                ", TEMPORARY='" + TEMPORARY + '\'' +
                ", GENERATED='" + GENERATED + '\'' +
                ", SECONDARY='" + SECONDARY + '\'' +
                ", BUFFER_POOL='" + BUFFER_POOL + '\'' +
                ", FLASH_CACHE='" + FLASH_CACHE + '\'' +
                ", CELL_FLASH_CACHE='" + CELL_FLASH_CACHE + '\'' +
                ", USER_STATS='" + USER_STATS + '\'' +
                ", DURATION='" + DURATION + '\'' +
                ", PCT_DIRECT_ACCESS='" + PCT_DIRECT_ACCESS + '\'' +
                ", ITYP_OWNER='" + ITYP_OWNER + '\'' +
                ", ITYP_NAME='" + ITYP_NAME + '\'' +
                ", PARAMETERS='" + PARAMETERS + '\'' +
                ", GLOBAL_STATS='" + GLOBAL_STATS + '\'' +
                ", DOMIDX_STATUS='" + DOMIDX_STATUS + '\'' +
                ", DOMIDX_OPSTATUS='" + DOMIDX_OPSTATUS + '\'' +
                ", FUNCIDX_STATUS='" + FUNCIDX_STATUS + '\'' +
                ", JOIN_INDEX='" + JOIN_INDEX + '\'' +
                ", IOT_REDUNDANT_PKEY_ELIM='" + IOT_REDUNDANT_PKEY_ELIM + '\'' +
                ", DROPPED='" + DROPPED + '\'' +
                ", VISIBILITY='" + VISIBILITY + '\'' +
                ", DOMIDX_MANAGEMENT='" + DOMIDX_MANAGEMENT + '\'' +
                ", SEGMENT_CREATED='" + SEGMENT_CREATED + '\'' +
                '}';
    }
}
