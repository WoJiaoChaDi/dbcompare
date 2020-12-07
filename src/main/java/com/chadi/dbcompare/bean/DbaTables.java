package com.chadi.dbcompare.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbaTables extends DbBaseObj{

    private String owner;
    private String tableName;
    private String tablespaceName;
    private String clusterName;
    private String iotName;
    private String status;
    private String pctFree;
    private String pctUsed;
    private String iniTrans;
    private String maxTrans;
    private String initialExtent;
    private String nextExtent;
    private String minExtents;
    private String maxExtents;
    private String pctIncrease;
    private String freelists;
    private String freelistGroups;
    private String logging;
    private String backedUp;
    private String numRows;
    private String blocks;
    private String emptyBlocks;
    private String avgSpace;
    private String chainCnt;
    private String avgRowLen;
    private String avgSpaceFreelistBlocks;
    private String numFreelistBlocks;
    private String degree;
    private String instances;
    private String cache;
    private String tableLock;
    private String sampleSize;
    private String lastAnalyzed;
    private String partitioned;
    private String iotType;
    private String temporary;
    private String secondary;
    private String nested;
    private String bufferPool;
    private String flashCache;
    private String cellFlashCache;
    private String rowMovement;
    private String globalStats;
    private String userStats;
    private String duration;
    private String skipCorrupt;
    private String monitoring;
    private String clusterOwner;
    private String dependencies;
    private String compression;
    private String compressFor;
    private String dropped;
    private String readOnly;
    private String segmentCreated;
    private String resultCache;
}
