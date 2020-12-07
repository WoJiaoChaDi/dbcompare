package com.chadi.dbcompare.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbaTabCols {
    private String owner;
    private String tableName;
    private String columnName;
    private String dataType;
    private String dataTypeMod;
    private String dataTypeOwner;
    private String dataLength;
    private String dataPrecision;
    private String dataScale;
    private String nullable;
    private String columnId;
    private String defaultLength;
    private String dataDefault;
    private String numDistinct;
    private String lowValue;
    private String highValue;
    private String density;
    private String numNulls;
    private String numBuckets;
    private String lastAnalyzed;
    private String sampleSize;
    private String characterSetName;
    private String charColDeclLength;
    private String globalStats;
    private String userStats;
    private String avgColLen;
    private String charLength;
    private String charUsed;
    private String v80FmtImage;
    private String dataUpgraded;
    private String hiddenColumn;
    private String virtualColumn;
    private String segmentColumnId;
    private String internalColumnId;
    private String histogram;
    private String qualifiedColName;
}
