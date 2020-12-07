package com.chadi.dbcompare.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIndexes {

    private String indexName;
    private String indexType;
    private String tableOwner;
    private String tableName;
    private String tableType;
    private String uniqueness;
    private String compression;
    private String prefixLength;
    private String tablespaceName;
    private String iniTrans;
    private String maxTrans;
    private String initialExtent;
    private String nextExtent;
    private String minExtents;
    private String maxExtents;
    private String pctIncrease;
    private String pctThreshold;
    private String includeColumn;
    private String freelists;
    private String freelistGroups;
    private String pctFree;
    private String logging;
    private String blevel;
    private String leafBlocks;
    private String distinctKeys;
    private String avgLeafBlocksPerKey;
    private String avgDataBlocksPerKey;
    private String clusteringFactor;
    private String status;
    private String numRows;
    private String sampleSize;
    private String lastAnalyzed;
    private String degree;
    private String instances;
    private String partitioned;
    private String temporary;
    private String generated;
    private String secondary;
    private String bufferPool;
    private String flashCache;
    private String cellFlashCache;
    private String userStats;
    private String duration;
    private String pctDirectAccess;
    private String itypOwner;
    private String itypName;
    private String parameters;
    private String globalStats;
    private String domidxStatus;
    private String domidxOpstatus;
    private String funcidxStatus;
    private String joinIndex;
    private String iotRedundantPkeyElim;
    private String dropped;
    private String visibility;
    private String domidxManagement;
    private String segmentCreated;

}
