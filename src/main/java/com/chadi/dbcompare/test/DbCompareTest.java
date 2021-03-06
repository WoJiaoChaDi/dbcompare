package com.chadi.dbcompare.test;

import com.chadi.dbcompare.bean.DbaIndColumns;
import com.chadi.dbcompare.bean.DbaTabCols;
import com.chadi.dbcompare.bean.DbaTables;
import com.chadi.dbcompare.bean.UserConstraints;
import com.chadi.dbcompare.bean.UserIndexes;
import com.chadi.dbcompare.bean.UserProcedures;
import com.chadi.dbcompare.bean.UserSource;
import com.chadi.dbcompare.dao.DbaConsColumnsMapper;
import com.chadi.dbcompare.dao.DbaIndColumnsMapper;
import com.chadi.dbcompare.dao.DbaSourceMapper;
import com.chadi.dbcompare.dao.DbaTabColsMapper;
import com.chadi.dbcompare.dao.DbaTablesMapper;
import com.chadi.dbcompare.dao.Mapper;
import com.chadi.dbcompare.dao.UserConstraintsMapper;
import com.chadi.dbcompare.dao.UserIndexesMapper;
import com.chadi.dbcompare.dao.UserProceduresMapper;
import com.chadi.dbcompare.dao.UserSourceMapper;
import com.chadi.dbcompare.utils.CompareUtils;
import com.chadi.dbcompare.utils.ExcelUtils;
import com.chadi.dbcompare.utils.PropertyUtils;
import com.chadi.factory.DataSourceEnum;
import com.chadi.factory.DataSourceSqlSessionFactory;
import com.chadi.factory.MapperFactory;
import com.chadi.factory.SimpleDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DbCompareTest {

    final Logger logger = LoggerFactory.getLogger(DbCompareTest.class);


	@Test
	public void test() throws IOException {
        //String constantCol = PropertyUtils.getProperty("Dba_tables.ConsCols_1");
        //Map compareMap = PropertyUtils.getPropertyToMap("Dba_tables.ConsCols_2");
        //logger.info(compareMap.toString());
        //
		//String a = CommonUtils.strTrimLowlineAndRenameHump("TABLE_NAME_");
		//logger.info(a);


		//这种方式，每次使用完Mapper会关掉SqlSessionFactory连接
        //DbaTablesMapper dbaCols_Db1 = MapperFactory.createMapper(DbaTablesMapper.class, DataSourceEnum.d1);
		//Map dbaCols_BaseMap = PropertyUtils.getPropertyToMap("Dba_tables.ConsCols_1");
		//List<DbaTables> dbaCols_BaseList = dbaCols_Db1.getDba_tablesByPros(dbaCols_BaseMap);
        //logger.info("=============");
        //List<DbaTables> dbaCols_BaseList2 = dbaCols_Db1.getDba_tablesByOwner("HEAD");


        //这种方式保持了SqlSessionFactory连接
        //SqlSessionFactory sqlSessionFactory = DataSourceSqlSessionFactory.getSqlSessionFactory(DataSourceEnum.d1);
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //DbaTablesMapper dbaCols_Db1 = sqlSession.getMapper(DbaTablesMapper.class);
        //Map dbaCols_BaseMap = PropertyUtils.getPropertyToMap("Dba_tables.ConsCols_1");
        //List<DbaTables> dbaCols_BaseList = dbaCols_Db1.getDba_tablesByPros(dbaCols_BaseMap, null, null);
        //logger.info("=============");
        //List<DbaTables> dbaCols_BaseList2 = dbaCols_Db1.getDba_tablesByOwner("HEAD");

        String[] strs = {"sTile1", "sTile2"};
        List<DbaTables> list = new ArrayList<>();
        DbaTables dbaTables = new DbaTables();
        dbaTables.setTableLock("tabllllock");
        dbaTables.setTableName("tableNName");
        DbaTables dbaTables2 = new DbaTables();
        dbaTables2.setTableLock("tabllllock22");
        dbaTables2.setTableName("tableNName22");
        DbaTables dbaTables3 = new DbaTables();
        dbaTables3.setTableLock("tabllllock33");
        dbaTables3.setTableName("tableNName33");
        list.add(dbaTables);
        list.add(dbaTables2);
        list.add(dbaTables3);

        // excel的标题
        String[] title = {"账户名称","支付金额","支付去向","交易时间"};
        // 下载后文件的名称
        String fileName = "DbTestExcel.xls";
        //sheet名
        String sheetName = "sheet1";
        // 建一个二维数组，前面放行 （每行数据），后面放列 （每列标题）
        String[][] content = new String[list.size()][title.length];
        for (int i = 0; i < list.size(); i ++) {
            DbaTables record = list.get(i);
            content[i][0] = record.getTableName();
            content[i][1] = "1" + i;
            content[i][2] = "1" + i;
            content[i][3] = "1" + i;
        }


        // 建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook(sheetName, title, content);
        ExcelUtils.exportExcelToDesk(wb, "d:\\" +fileName+ ".xls");

    }

    /**
     * @description: 动态的DB左右对比，黄色标注不一样字段
     * @param
     * @return: void
     * @author: XuDong
     * @time: 2020/12/18 14:47
     */
    @Test
    public void test_DynamicUser() throws IOException {
        long startTime = System.currentTimeMillis();

        SimpleDataSource simDataSource1 = new SimpleDataSource();
        SimpleDataSource simDataSource2 = new SimpleDataSource();

        Properties dbconfig = PropertyUtils.getPropsMap().get("dbconfig");
        String d1 = "d1";
        String d2 = "d2";
        simDataSource1.setUsername(dbconfig.getProperty(d1+".orcl.username"));
        simDataSource1.setPassword(dbconfig.getProperty(d1+".orcl.password"));
        simDataSource1.setUrl(dbconfig.getProperty(d1+".orcl.url"));
        simDataSource1.setDriver(dbconfig.getProperty(d1+".orcl.driver"));
        simDataSource2.setUsername(dbconfig.getProperty(d2+".orcl.username"));
        simDataSource2.setPassword(dbconfig.getProperty(d2+".orcl.password"));
        simDataSource2.setUrl(dbconfig.getProperty(d2+".orcl.url"));
        simDataSource2.setDriver(dbconfig.getProperty(d2+".orcl.driver"));

        // 下载后文件的名称
        String fileName = "DbTestExcel_ALL_0012_Dynamic.xls";
        //DB名称
        List<String> dbInfoList = new ArrayList<>();
        dbInfoList.add("DB1");
        dbInfoList.add("DB2");

        //---------第一个Sheet页（表名对比）---------
        //获取数据源
        Mapper dbaTabMapper_db1 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d1, DbaTablesMapper.class, simDataSource1);
        Mapper dbaTabMapper_db2 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d2, DbaTablesMapper.class, simDataSource2);

        //获取库表的数据源
        Map<String, List> tabSourceMap = CompareUtils.getBsTgListAndCpCols(dbaTabMapper_db1, dbaTabMapper_db2, CompareUtils.Dba_tables, null);

        //sheet名
        String sheetName = CompareUtils.Sheet_Dba_tables;

        // 建HSSFWorkbook
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        Map<String, HSSFCellStyle> styleMap = ExcelUtils.getHSSFCellStyle(wb);

        // excel的标题
        List<String> titleTables = (List<String>) tabSourceMap.get(CompareUtils.compareCols);
        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetName, dbInfoList, titleTables, false, null, 0, null, styleMap);
        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetName, null, titleTables, true, null, null, null, styleMap);

        //输出比较数据
        Map<String, List> resultMap = CompareUtils.writeDataToWbByType(tabSourceMap, wb, sheetName, CompareUtils.dataType_01, styleMap);
        //CompareUtils.writeDataToWbByType(tabSourceMap, wb, sheetName, CompareUtils.dataType_02, styleMap);
        CompareUtils.writeDataToWbByType(tabSourceMap, wb, sheetName, CompareUtils.dataType_03, styleMap);
        CompareUtils.writeDataToWbByType(tabSourceMap, wb, sheetName, CompareUtils.dataType_04, styleMap);


        //---------第二个Sheet页（列名对比：基于前一个表的对比结果）---------
        //字段比较
        List<Map> baseTabMatchList = resultMap.get("baseMapMatchList");

        //获取数据源
        Mapper dbaTabColsMpr_db1 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d1, DbaTabColsMapper.class, simDataSource1);
        Mapper dbaTabColsMpr_db2 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d2, DbaTabColsMapper.class, simDataSource2);

        //每次循环一个表名 匹配列
        List<String> dbaCols_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "Dba_tab_cols.CompareCols");

        //先给页面添加列标题
        String sheetNameCols = CompareUtils.Sheet_Dba_tab_cols;
        List<String> titleCols = dbaCols_CompareCols;

        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameCols, dbInfoList, titleCols, false, null, 0, null, styleMap);
        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameCols, null, titleCols, true, null, null, null, styleMap);

        //循环匹配成功的表，比较列的匹配情况
        for (Map baseTabMap : baseTabMatchList) {

            String sameTableName = (String) baseTabMap.get("tableName");
            Map moreConsColsMap = new HashMap();
            moreConsColsMap.put("TABLE_NAME", sameTableName);

            //获取表列的数据源
            Map<String, List> colsSourceMap = CompareUtils.getBsTgListAndCpCols(dbaTabColsMpr_db1, dbaTabColsMpr_db2, CompareUtils.Dba_tab_cols, moreConsColsMap);

            //输出比较数据
            CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameCols, CompareUtils.dataType_01, styleMap);
            //CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameCols, CompareUtils.dataType_02, styleMap);
            CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameCols, CompareUtils.dataType_03, styleMap);
            CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameCols, CompareUtils.dataType_04, styleMap);
        }







        //---------第三个Sheet页（索引对比）---------
        //获取数据源
        Mapper userIdxMpr_db1 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d1, UserIndexesMapper.class, simDataSource1);
        Mapper userIdxMpr_db2 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d2, UserIndexesMapper.class, simDataSource2);

        //获取库表的数据源
        Map<String, List> urIdxSourceMap = CompareUtils.getBsTgListAndCpCols(userIdxMpr_db1, userIdxMpr_db2, CompareUtils.User_indexes, null);

        //sheet名
        String urIdxsheetName = CompareUtils.Sheet_User_indexes;

        // excel的标题
        List<String> titleUrIdx = (List<String>) urIdxSourceMap.get(CompareUtils.compareCols);
        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, urIdxsheetName, dbInfoList, titleUrIdx, false, null, 0, null, styleMap);

        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, urIdxsheetName, null, titleUrIdx, true, null, null, null, styleMap);

        //输出比较数据
        Map<String, List> urIdxResultMap = CompareUtils.writeDataToWbByType(urIdxSourceMap, wb, urIdxsheetName, CompareUtils.dataType_01, styleMap);
        //CompareUtils.writeDataToWbByType(urIdxSourceMap, wb, urIdxsheetName, CompareUtils.dataType_02, styleMap);
        CompareUtils.writeDataToWbByType(urIdxSourceMap, wb, urIdxsheetName, CompareUtils.dataType_03, styleMap);
        CompareUtils.writeDataToWbByType(urIdxSourceMap, wb, urIdxsheetName, CompareUtils.dataType_04, styleMap);


        //---------第四个Sheet页（索引详情对比:基于前一个索引的对比结果）---------
        //字段比较
        List<Map> baseIdxMatchList = urIdxResultMap.get("baseMapMatchList");

        //获取数据源
        Mapper dbaIndColsMpr_db1 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d1, DbaIndColumnsMapper.class, simDataSource1);
        Mapper dbaIndColsMpr_db2 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d2, DbaIndColumnsMapper.class, simDataSource2);

        //每次循环一个表名 匹配列
        List<String> dbaIndCol_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "Dba_ind_columns.CompareCols");

        //先给页面添加列标题
        String sheetNameDbaInd = CompareUtils.Sheet_Dba_ind_columns;
        List<String> titleDbaIndCols = dbaIndCol_CompareCols;

        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameDbaInd, dbInfoList, titleDbaIndCols, false, null, 0, null, styleMap);
        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameDbaInd, null, titleDbaIndCols, true, null, null, null, styleMap);

        //循环匹配成功的表，比较列的匹配情况
        for (Map baseIdxMap : baseIdxMatchList) {

            String sameName = (String) baseIdxMap.get("indexName");
            Map moreConsColsMap = new HashMap();
            moreConsColsMap.put("INDEX_NAME", sameName);

            //获取表列的数据源
            Map<String, List> colsSourceMap = CompareUtils.getBsTgListAndCpCols(dbaIndColsMpr_db1, dbaIndColsMpr_db2, CompareUtils.Dba_ind_columns, moreConsColsMap);

            //输出比较数据
            CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameDbaInd, CompareUtils.dataType_01, styleMap);
            //CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameDbaInd, CompareUtils.dataType_02, styleMap);
            CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameDbaInd, CompareUtils.dataType_03, styleMap);
            CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameDbaInd, CompareUtils.dataType_04, styleMap);
        }







        //---------第五个Sheet页（约束对比）---------
        //获取数据源
        Mapper userCostMpr_db1 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d1, UserConstraintsMapper.class, simDataSource1);
        Mapper userCostMpr_db2 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d2, UserConstraintsMapper.class, simDataSource2);

        //获取库表的数据源
        Map<String, List> urCostSourceMap = CompareUtils.getBsTgListAndCpCols(userCostMpr_db1, userCostMpr_db2, CompareUtils.User_Constraints, null);

        //sheet名
        String urCostSheetName = CompareUtils.Sheet_User_Constraints;

        // excel的标题
        List<String> titleUrCost = (List<String>) urCostSourceMap.get(CompareUtils.compareCols);
        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, urCostSheetName, dbInfoList, titleUrCost, false, null, 0, null, styleMap);

        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, urCostSheetName, null, titleUrCost, true, null, null, null, styleMap);

        //输出比较数据
        Map<String, List> urCostResultMap = CompareUtils.writeDataToWbByType(urCostSourceMap, wb, urCostSheetName, CompareUtils.dataType_01, styleMap);
        //CompareUtils.writeDataToWbByType(urIdxSourceMap, wb, urIdxsheetName, CompareUtils.dataType_02, styleMap);
        CompareUtils.writeDataToWbByType(urCostSourceMap, wb, urCostSheetName, CompareUtils.dataType_03, styleMap);
        CompareUtils.writeDataToWbByType(urCostSourceMap, wb, urCostSheetName, CompareUtils.dataType_04, styleMap);


        //---------第六个Sheet页（约束详情对比:基于前一个索引的对比结果）---------
        //字段比较
        List<Map> baseUrCostMatchList = urCostResultMap.get("baseMapMatchList");

        //获取数据源
        Mapper dbaConsColsMpr_db1 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d1, DbaConsColumnsMapper.class, simDataSource1);
        Mapper dbaConsColsMpr_db2 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d2, DbaConsColumnsMapper.class, simDataSource2);

        //每次循环一个表名 匹配列
        List<String> dbaConsCol_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "Dba_cons_columns.CompareCols");

        //先给页面添加列标题
        String sheetNameDbaCons = CompareUtils.Sheet_Dba_cons_columns;
        List<String> titleDbaConsCols = dbaConsCol_CompareCols;

        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameDbaCons, dbInfoList, titleDbaConsCols, false, null, 0, null, styleMap);
        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameDbaCons, null, titleDbaConsCols, true, null, null, null, styleMap);

        //循环匹配成功的表，比较列的匹配情况
        for (Map baseIdxMap : baseUrCostMatchList) {

            String sameName = (String) baseIdxMap.get("constraintName");
            Map moreConsColsMap = new HashMap();
            moreConsColsMap.put("CONSTRAINT_NAME", sameName);

            //获取表列的数据源
            Map<String, List> dbaConsColsSourceMap = CompareUtils.getBsTgListAndCpCols(dbaConsColsMpr_db1, dbaConsColsMpr_db2, CompareUtils.User_Constraints, moreConsColsMap);

            //输出比较数据
            CompareUtils.writeDataToWbByTypeLine(dbaConsColsSourceMap, wb, sheetNameDbaCons, CompareUtils.dataType_01, styleMap);
            //CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameDbaInd, CompareUtils.dataType_02, styleMap);
            CompareUtils.writeDataToWbByTypeLine(dbaConsColsSourceMap, wb, sheetNameDbaCons, CompareUtils.dataType_03, styleMap);
            CompareUtils.writeDataToWbByTypeLine(dbaConsColsSourceMap, wb, sheetNameDbaCons, CompareUtils.dataType_04, styleMap);
        }







        //---------第七个Sheet页（函数、存储过程、触发器名字对比）---------
        //获取数据源
        Mapper userProcMpr_db1 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d1, UserProceduresMapper.class, simDataSource1);
        Mapper userProcMpr_db2 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d2, UserProceduresMapper.class, simDataSource2);

        //获取库表的数据源
        Map<String, List> urProcSourceMap = CompareUtils.getBsTgListAndCpCols(userProcMpr_db1, userProcMpr_db2, CompareUtils.User_Procedures, null);

        //sheet名
        String urProcSheetName = CompareUtils.Sheet_User_Procedures;

        // excel的标题
        List<String> titleUrProc = (List<String>) urProcSourceMap.get(CompareUtils.compareCols);
        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, urProcSheetName, dbInfoList, titleUrProc, false, null, 0, null, styleMap);

        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, urProcSheetName, null, titleUrProc, true, null, null, null, styleMap);

        //输出比较数据
        Map<String, List> urProcResultMap = CompareUtils.writeDataToWbByType(urProcSourceMap, wb, urProcSheetName, CompareUtils.dataType_01, styleMap);
        //CompareUtils.writeDataToWbByType(urIdxSourceMap, wb, urIdxsheetName, CompareUtils.dataType_02, styleMap);
        CompareUtils.writeDataToWbByType(urProcSourceMap, wb, urProcSheetName, CompareUtils.dataType_03, styleMap);
        CompareUtils.writeDataToWbByType(urProcSourceMap, wb, urProcSheetName, CompareUtils.dataType_04, styleMap);


        //---------第八个Sheet页（函数、存储过程、触发器详情的对比结果）---------
        //字段比较
        List<Map> baseUrProcMatchList = urProcResultMap.get("baseMapMatchList");

        //获取数据源
        Mapper dbaSourceMpr_db1 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d1, DbaSourceMapper.class, simDataSource1);
        Mapper dbaSourceMpr_db2 = CompareUtils.getDbDynamicMapper(DataSourceEnum.d2, DbaSourceMapper.class, simDataSource2);

        //每次循环一个表名 匹配列
        List<String> dbaSource_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "Dba_Source.CompareCols");

        //先给页面添加列标题
        String sheetNameDbaSource = CompareUtils.Sheet_Dba_Source;
        List<String> titleDbaSourceCols = dbaSource_CompareCols;

        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameDbaSource, dbInfoList, titleDbaSourceCols, false, null, 0, null, styleMap);
        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameDbaSource, null, titleDbaSourceCols, true, null, null, null, styleMap);

        //循环匹配成功的表，比较列的匹配情况
        for (Map baseIdxMap : baseUrProcMatchList) {

            String sameName = (String) baseIdxMap.get("objectName");
            Map moreConsColsMap = new HashMap();
            moreConsColsMap.put("NAME", sameName);

            //获取表列的数据源
            Map<String, List> dbaConsColsSourceMap = CompareUtils.getBsTgListAndCpCols(dbaSourceMpr_db1, dbaSourceMpr_db2, CompareUtils.User_Procedures, moreConsColsMap);

            //输出比较数据
            CompareUtils.writeDataToWbByTypeLine(dbaConsColsSourceMap, wb, sheetNameDbaSource, CompareUtils.dataType_01, styleMap);
            //CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameDbaInd, CompareUtils.dataType_02, styleMap);
            CompareUtils.writeDataToWbByTypeLine(dbaConsColsSourceMap, wb, sheetNameDbaSource, CompareUtils.dataType_03, styleMap);
            CompareUtils.writeDataToWbByTypeLine(dbaConsColsSourceMap, wb, sheetNameDbaSource, CompareUtils.dataType_04, styleMap);
        }

        ExcelUtils.exportExcelToDesk(wb, "d:\\" +fileName);


        long endTime = System.currentTimeMillis();
        System.out.println("总计耗时：" + (endTime - startTime));
    }

    /**
     * @description: 双DB左右对比，黄色标注不一样字段
     * @param
     * @return: void
     * @author: XuDong
     * @time: 2020/12/18 14:47
     */
    @Test
    public void test_exportExcel_All_ShowTwoDB(){

        long startTime = System.currentTimeMillis();

        // 下载后文件的名称
        String fileName = "DbTestExcel_ALL_0005.xls";
        //DB名称
        List<String> dbInfoList = new ArrayList<>();
        dbInfoList.add("DB1");
        dbInfoList.add("DB2");

        //---------第一个Sheet页（表名对比）---------
        //获取数据源
        Mapper dbaTabMapper_db1 = CompareUtils.getDbMapper(DataSourceEnum.d1, DbaTablesMapper.class);
        Mapper dbaTabMapper_db2 = CompareUtils.getDbMapper(DataSourceEnum.d2, DbaTablesMapper.class);

        //获取库表的数据源
        Map<String, List> tabSourceMap = CompareUtils.getBsTgListAndCpCols(dbaTabMapper_db1, dbaTabMapper_db2, CompareUtils.Dba_tables, null);

        //sheet名
        String sheetName = CompareUtils.Sheet_Dba_tables;

        // 建HSSFWorkbook
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        Map<String, HSSFCellStyle> styleMap = ExcelUtils.getHSSFCellStyle(wb);

        // excel的标题
        List<String> titleTables = (List<String>) tabSourceMap.get(CompareUtils.compareCols);
        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetName, dbInfoList, titleTables, false, null, 0, null, styleMap);
        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetName, null, titleTables, true, null, null, null, styleMap);

        //输出比较数据
        Map<String, List> resultMap = CompareUtils.writeDataToWbByType(tabSourceMap, wb, sheetName, CompareUtils.dataType_01, styleMap);
        //CompareUtils.writeDataToWbByType(tabSourceMap, wb, sheetName, CompareUtils.dataType_02, styleMap);
        CompareUtils.writeDataToWbByType(tabSourceMap, wb, sheetName, CompareUtils.dataType_03, styleMap);
        CompareUtils.writeDataToWbByType(tabSourceMap, wb, sheetName, CompareUtils.dataType_04, styleMap);


        //---------第二个Sheet页（列名对比：基于前一个表的对比结果）---------
        //字段比较
        List<Map> baseTabMatchList = resultMap.get("baseMapMatchList");

        //获取数据源
        Mapper dbaTabColsMpr_db1 = CompareUtils.getDbMapper(DataSourceEnum.d1, DbaTabColsMapper.class);
        Mapper dbaTabColsMpr_db2 = CompareUtils.getDbMapper(DataSourceEnum.d2, DbaTabColsMapper.class);

        //每次循环一个表名 匹配列
        List<String> dbaCols_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "Dba_tab_cols.CompareCols");

        //先给页面添加列标题
        String sheetNameCols = CompareUtils.Sheet_Dba_tab_cols;
        List<String> titleCols = dbaCols_CompareCols;

        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameCols, dbInfoList, titleCols, false, null, 0, null, styleMap);
        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameCols, null, titleCols, true, null, null, null, styleMap);

        //循环匹配成功的表，比较列的匹配情况
        for (Map baseTabMap : baseTabMatchList) {

            String sameTableName = (String) baseTabMap.get("tableName");
            Map moreConsColsMap = new HashMap();
            moreConsColsMap.put("TABLE_NAME", sameTableName);

            //获取表列的数据源
            Map<String, List> colsSourceMap = CompareUtils.getBsTgListAndCpCols(dbaTabColsMpr_db1, dbaTabColsMpr_db2, CompareUtils.Dba_tab_cols, moreConsColsMap);

            //输出比较数据
            CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameCols, CompareUtils.dataType_01, styleMap);
            //CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameCols, CompareUtils.dataType_02, styleMap);
            CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameCols, CompareUtils.dataType_03, styleMap);
            CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameCols, CompareUtils.dataType_04, styleMap);
        }







        //---------第三个Sheet页（索引对比）---------
        //获取数据源
        Mapper userIdxMpr_db1 = CompareUtils.getDbMapper(DataSourceEnum.d1, UserIndexesMapper.class);
        Mapper userIdxMpr_db2 = CompareUtils.getDbMapper(DataSourceEnum.d2, UserIndexesMapper.class);

        //获取库表的数据源
        Map<String, List> urIdxSourceMap = CompareUtils.getBsTgListAndCpCols(userIdxMpr_db1, userIdxMpr_db2, CompareUtils.User_indexes, null);

        //sheet名
        String urIdxsheetName = CompareUtils.Sheet_User_indexes;

        // excel的标题
        List<String> titleUrIdx = (List<String>) urIdxSourceMap.get(CompareUtils.compareCols);
        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, urIdxsheetName, dbInfoList, titleUrIdx, false, null, 0, null, styleMap);

        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, urIdxsheetName, null, titleUrIdx, true, null, null, null, styleMap);

        //输出比较数据
        Map<String, List> urIdxResultMap = CompareUtils.writeDataToWbByType(urIdxSourceMap, wb, urIdxsheetName, CompareUtils.dataType_01, styleMap);
        //CompareUtils.writeDataToWbByType(urIdxSourceMap, wb, urIdxsheetName, CompareUtils.dataType_02, styleMap);
        CompareUtils.writeDataToWbByType(urIdxSourceMap, wb, urIdxsheetName, CompareUtils.dataType_03, styleMap);
        CompareUtils.writeDataToWbByType(urIdxSourceMap, wb, urIdxsheetName, CompareUtils.dataType_04, styleMap);


        //---------第四个Sheet页（索引详情对比:基于前一个索引的对比结果）---------
        //字段比较
        List<Map> baseIdxMatchList = urIdxResultMap.get("baseMapMatchList");

        //获取数据源
        Mapper dbaIndColsMpr_db1 = CompareUtils.getDbMapper(DataSourceEnum.d1, DbaIndColumnsMapper.class);
        Mapper dbaIndColsMpr_db2 = CompareUtils.getDbMapper(DataSourceEnum.d2, DbaIndColumnsMapper.class);

        //每次循环一个表名 匹配列
        List<String> dbaIndCol_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "Dba_ind_columns.CompareCols");

        //先给页面添加列标题
        String sheetNameDbaInd = CompareUtils.Sheet_Dba_ind_columns;
        List<String> titleDbaIndCols = dbaIndCol_CompareCols;

        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameDbaInd, dbInfoList, titleDbaIndCols, false, null, 0, null, styleMap);
        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameDbaInd, null, titleDbaIndCols, true, null, null, null, styleMap);

        //循环匹配成功的表，比较列的匹配情况
        for (Map baseIdxMap : baseIdxMatchList) {

            String sameName = (String) baseIdxMap.get("indexName");
            Map moreConsColsMap = new HashMap();
            moreConsColsMap.put("INDEX_NAME", sameName);

            //获取表列的数据源
            Map<String, List> colsSourceMap = CompareUtils.getBsTgListAndCpCols(dbaIndColsMpr_db1, dbaIndColsMpr_db2, CompareUtils.Dba_ind_columns, moreConsColsMap);

            //输出比较数据
            CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameDbaInd, CompareUtils.dataType_01, styleMap);
            //CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameDbaInd, CompareUtils.dataType_02, styleMap);
            CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameDbaInd, CompareUtils.dataType_03, styleMap);
            CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameDbaInd, CompareUtils.dataType_04, styleMap);
        }







        //---------第五个Sheet页（约束对比）---------
        //获取数据源
        Mapper userCostMpr_db1 = CompareUtils.getDbMapper(DataSourceEnum.d1, UserConstraintsMapper.class);
        Mapper userCostMpr_db2 = CompareUtils.getDbMapper(DataSourceEnum.d2, UserConstraintsMapper.class);

        //获取库表的数据源
        Map<String, List> urCostSourceMap = CompareUtils.getBsTgListAndCpCols(userCostMpr_db1, userCostMpr_db2, CompareUtils.User_Constraints, null);

        //sheet名
        String urCostSheetName = CompareUtils.Sheet_User_Constraints;

        // excel的标题
        List<String> titleUrCost = (List<String>) urCostSourceMap.get(CompareUtils.compareCols);
        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, urCostSheetName, dbInfoList, titleUrCost, false, null, 0, null, styleMap);

        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, urCostSheetName, null, titleUrCost, true, null, null, null, styleMap);

        //输出比较数据
        Map<String, List> urCostResultMap = CompareUtils.writeDataToWbByType(urCostSourceMap, wb, urCostSheetName, CompareUtils.dataType_01, styleMap);
        //CompareUtils.writeDataToWbByType(urIdxSourceMap, wb, urIdxsheetName, CompareUtils.dataType_02, styleMap);
        CompareUtils.writeDataToWbByType(urCostSourceMap, wb, urCostSheetName, CompareUtils.dataType_03, styleMap);
        CompareUtils.writeDataToWbByType(urCostSourceMap, wb, urCostSheetName, CompareUtils.dataType_04, styleMap);


        //---------第六个Sheet页（约束详情对比:基于前一个索引的对比结果）---------
        //字段比较
        List<Map> baseUrCostMatchList = urCostResultMap.get("baseMapMatchList");

        //获取数据源
        Mapper dbaConsColsMpr_db1 = CompareUtils.getDbMapper(DataSourceEnum.d1, DbaConsColumnsMapper.class);
        Mapper dbaConsColsMpr_db2 = CompareUtils.getDbMapper(DataSourceEnum.d2, DbaConsColumnsMapper.class);

        //每次循环一个表名 匹配列
        List<String> dbaConsCol_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "Dba_cons_columns.CompareCols");

        //先给页面添加列标题
        String sheetNameDbaCons = CompareUtils.Sheet_Dba_cons_columns;
        List<String> titleDbaConsCols = dbaConsCol_CompareCols;

        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameDbaCons, dbInfoList, titleDbaConsCols, false, null, 0, null, styleMap);
        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameDbaCons, null, titleDbaConsCols, true, null, null, null, styleMap);

        //循环匹配成功的表，比较列的匹配情况
        for (Map baseIdxMap : baseUrCostMatchList) {

            String sameName = (String) baseIdxMap.get("constraintName");
            Map moreConsColsMap = new HashMap();
            moreConsColsMap.put("CONSTRAINT_NAME", sameName);

            //获取表列的数据源
            Map<String, List> dbaConsColsSourceMap = CompareUtils.getBsTgListAndCpCols(dbaConsColsMpr_db1, dbaConsColsMpr_db2, CompareUtils.User_Constraints, moreConsColsMap);

            //输出比较数据
            CompareUtils.writeDataToWbByTypeLine(dbaConsColsSourceMap, wb, sheetNameDbaCons, CompareUtils.dataType_01, styleMap);
            //CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameDbaInd, CompareUtils.dataType_02, styleMap);
            CompareUtils.writeDataToWbByTypeLine(dbaConsColsSourceMap, wb, sheetNameDbaCons, CompareUtils.dataType_03, styleMap);
            CompareUtils.writeDataToWbByTypeLine(dbaConsColsSourceMap, wb, sheetNameDbaCons, CompareUtils.dataType_04, styleMap);
        }







        //---------第七个Sheet页（函数、存储过程、触发器名字对比）---------
        //获取数据源
        Mapper userProcMpr_db1 = CompareUtils.getDbMapper(DataSourceEnum.d1, UserProceduresMapper.class);
        Mapper userProcMpr_db2 = CompareUtils.getDbMapper(DataSourceEnum.d2, UserProceduresMapper.class);

        //获取库表的数据源
        Map<String, List> urProcSourceMap = CompareUtils.getBsTgListAndCpCols(userProcMpr_db1, userProcMpr_db2, CompareUtils.User_Procedures, null);

        //sheet名
        String urProcSheetName = CompareUtils.Sheet_User_Procedures;

        // excel的标题
        List<String> titleUrProc = (List<String>) urProcSourceMap.get(CompareUtils.compareCols);
        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, urProcSheetName, dbInfoList, titleUrProc, false, null, 0, null, styleMap);

        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, urProcSheetName, null, titleUrProc, true, null, null, null, styleMap);

        //输出比较数据
        Map<String, List> urProcResultMap = CompareUtils.writeDataToWbByType(urProcSourceMap, wb, urProcSheetName, CompareUtils.dataType_01, styleMap);
        //CompareUtils.writeDataToWbByType(urIdxSourceMap, wb, urIdxsheetName, CompareUtils.dataType_02, styleMap);
        CompareUtils.writeDataToWbByType(urProcSourceMap, wb, urProcSheetName, CompareUtils.dataType_03, styleMap);
        CompareUtils.writeDataToWbByType(urProcSourceMap, wb, urProcSheetName, CompareUtils.dataType_04, styleMap);


        //---------第八个Sheet页（函数、存储过程、触发器详情的对比结果）---------
        //字段比较
        List<Map> baseUrProcMatchList = urProcResultMap.get("baseMapMatchList");

        //获取数据源
        Mapper dbaSourceMpr_db1 = CompareUtils.getDbMapper(DataSourceEnum.d1, DbaSourceMapper.class);
        Mapper dbaSourceMpr_db2 = CompareUtils.getDbMapper(DataSourceEnum.d2, DbaSourceMapper.class);

        //每次循环一个表名 匹配列
        List<String> dbaSource_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "Dba_Source.CompareCols");

        //先给页面添加列标题
        String sheetNameDbaSource = CompareUtils.Sheet_Dba_Source;
        List<String> titleDbaSourceCols = dbaSource_CompareCols;

        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameDbaSource, dbInfoList, titleDbaSourceCols, false, null, 0, null, styleMap);
        //Excel列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightWithLeftStylemap(wb, sheetNameDbaSource, null, titleDbaSourceCols, true, null, null, null, styleMap);

        //循环匹配成功的表，比较列的匹配情况
        for (Map baseIdxMap : baseUrProcMatchList) {

            String sameName = (String) baseIdxMap.get("objectName");
            Map moreConsColsMap = new HashMap();
            moreConsColsMap.put("NAME", sameName);

            //获取表列的数据源
            Map<String, List> dbaConsColsSourceMap = CompareUtils.getBsTgListAndCpCols(dbaSourceMpr_db1, dbaSourceMpr_db2, CompareUtils.User_Procedures, moreConsColsMap);

            //输出比较数据
            CompareUtils.writeDataToWbByTypeLine(dbaConsColsSourceMap, wb, sheetNameDbaSource, CompareUtils.dataType_01, styleMap);
            //CompareUtils.writeDataToWbByTypeLine(colsSourceMap, wb, sheetNameDbaInd, CompareUtils.dataType_02, styleMap);
            CompareUtils.writeDataToWbByTypeLine(dbaConsColsSourceMap, wb, sheetNameDbaSource, CompareUtils.dataType_03, styleMap);
            CompareUtils.writeDataToWbByTypeLine(dbaConsColsSourceMap, wb, sheetNameDbaSource, CompareUtils.dataType_04, styleMap);
        }

        ExcelUtils.exportExcelToDesk(wb, "d:\\" +fileName);


        long endTime = System.currentTimeMillis();
        System.out.println("总计耗时：" + (endTime - startTime));
    }



    /**
     * @description: 双DB左右对比，黄色标注不一样字段
     * @param
     * @return: void
     * @author: XuDong
     * @time: 2020/12/18 14:47
     */
    @Test
    public void test_exportExcel_DbaTables_ShowTwoDB(){

        DbaTablesMapper dbaTables_db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, DbaTablesMapper.class);
        DbaTablesMapper dbaTables_db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d2, DbaTablesMapper.class);
        //DB名称
        List<String> dbInfoList = new ArrayList<>();
        dbInfoList.add("DB1");
        dbInfoList.add("DB2");

        Map dbaTables_BaseMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tables.ConsCols_1");
        Map dbaTables_CompareMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tables.ConsCols_2");
        Map dbaTables_NotLikeMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tables.NotLikeMap");
        List<String> dbaTables_AppendPlusList = PropertyUtils.getPropertyToList("dbcompare", "Dba_tables.AppendPlus");
        List<String> dbaTables_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "Dba_tables.CompareCols");


        List<DbaTables> dbaTables_BaseList = dbaTables_db1.getDba_tablesByPros(dbaTables_BaseMap, dbaTables_NotLikeMap, dbaTables_AppendPlusList);
        List<DbaTables> dbaTables_TargetList = dbaTables_db2.getDba_tablesByPros(dbaTables_CompareMap, dbaTables_NotLikeMap, dbaTables_AppendPlusList);

        //比较方法
        Map<String, List> resultMap = CompareUtils.compareList(dbaTables_BaseList, dbaTables_TargetList, dbaTables_CompareCols);

        // 下载后文件的名称
        String fileName = "DbTestExcel.xls";

        List<Map> baseMapNoMatchList = resultMap.get("baseMapNoMatchList");
        List<Map> baseMapAllMatchList = resultMap.get("baseMapAllMatchList");
        List<Map> baseMapPartMatchList = resultMap.get("baseMapPartMatchList");
        List<Map> targetMapNoMatchList = resultMap.get("targetMapNoMatchList");


        //sheet名
        String sheetName = "DbaTables";
        // excel的标题
        List<String> titleTables = dbaTables_CompareCols;
        // 建HSSFWorkbook
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();

        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightLeft(wb, sheetName, dbInfoList, titleTables, false, null, 0, null);
        //列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightLeft(wb, sheetName, null, titleTables, true, null, null, null);


        //填充数据
        wb = ExcelUtils.getHSSFWorkbookForDbRightLeft(wb, sheetName, null, titleTables, false, baseMapNoMatchList, null, CompareUtils.dataType_01);
        wb = ExcelUtils.getHSSFWorkbookForDbRightLeft(wb, sheetName, null, titleTables,false, baseMapAllMatchList, null, CompareUtils.dataType_02);
        wb = ExcelUtils.getHSSFWorkbookForDbRightLeft(wb, sheetName, null, titleTables,false, baseMapPartMatchList, null, CompareUtils.dataType_03);
        wb = ExcelUtils.getHSSFWorkbookForDbRightLeft(wb, sheetName, null, titleTables,false, targetMapNoMatchList, null, CompareUtils.dataType_04);

        //字段比较
        List<Map> baseMapMatchList = resultMap.get("baseMapMatchList");
        //库表字段
        DbaTabColsMapper dbaCols_Db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, DbaTabColsMapper.class);
        DbaTabColsMapper dbaCols_Db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d2, DbaTabColsMapper.class);

        Map dbaCols_BaseMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tab_cols.ConsCols_1");
        Map dbaCols_CompareMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tab_cols.ConsCols_2");
        Map dbaCols_NotLikeMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tab_cols.NotLikeMap");
        List<String> dbaCols_AppendPlusList = PropertyUtils.getPropertyToList("dbcompare", "Dba_tab_cols.AppendPlus");

        //每次循环一个表名 匹配列
        List<String> dbaCols_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "Dba_tab_cols.CompareCols");

        //先给页面添加列标题
        String sheetNameCols = "Dba_tab_cols";
        List<String> titleCols = dbaCols_CompareCols;

        //DB名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightLeft(wb, sheetNameCols, dbInfoList, titleCols, false, null, 0, null);
        //列名称
        wb = ExcelUtils.getHSSFWorkbookForDbRightLeft(wb, sheetNameCols, null, titleCols, true, null, null, null);

        for (Map baseMapMatch : baseMapMatchList) {

            String sameTableName = (String) baseMapMatch.get("tableName");
            dbaCols_BaseMap.put("TABLE_NAME", sameTableName);
            dbaCols_CompareMap.put("TABLE_NAME", sameTableName);

            List<DbaTabCols> dbaCols_BaseList = dbaCols_Db1.getDba_tab_colsByPros(dbaCols_BaseMap, dbaCols_NotLikeMap, dbaCols_AppendPlusList);
            List<DbaTabCols> dbaCols_TargetList = dbaCols_Db2.getDba_tab_colsByPros(dbaCols_CompareMap, dbaCols_NotLikeMap, dbaCols_AppendPlusList);

            //比较方法
            Map<String, List> resultColMap = CompareUtils.compareList(dbaCols_BaseList, dbaCols_TargetList, dbaCols_CompareCols);

            List<Map> colsBaseMapNoMatchList = resultColMap.get("baseMapNoMatchList");
            List<Map> colsBaseMapAllMatchList = resultColMap.get("baseMapAllMatchList");
            List<Map> colsBaseMapPartMatchList = resultColMap.get("baseMapPartMatchList");
            List<Map> colsTargetMapNoMatchList = resultColMap.get("targetMapNoMatchList");

            wb = ExcelUtils.getHSSFWorkbookForDbRightLeft(wb, sheetNameCols, null, titleCols, false, colsBaseMapNoMatchList, null, CompareUtils.dataType_01);
            wb = ExcelUtils.getHSSFWorkbookForDbRightLeft(wb, sheetNameCols, null, titleCols, false, colsBaseMapAllMatchList, null, CompareUtils.dataType_02);
            wb = ExcelUtils.getHSSFWorkbookForDbRightLeft(wb, sheetNameCols, null, titleCols, false, colsBaseMapPartMatchList, null, CompareUtils.dataType_03);
            wb = ExcelUtils.getHSSFWorkbookForDbRightLeft(wb, sheetNameCols, null, titleCols, false, colsTargetMapNoMatchList, null, CompareUtils.dataType_04);

            //输出结果
            logger.info("=============当前处理的表名是：" + baseMapMatch.get("tableName") + "=============");
            CompareUtils.soutResult(resultColMap, "columnName", dbaCols_CompareCols);
        }
        ExcelUtils.exportExcelToDesk(wb, "d:\\" +fileName+ "---1.xls");
    }

    /**
     * @description: 单DB内对比，黄色标注不一样字段  字段内左右区分DB1 DB2
     * @param
     * @return: void
     * @author: XuDong
     * @time: 2020/12/18 14:47
     */
    @Test
    public void test_exportExcel_DbaTables_ShowOneDB(){

        DbaTablesMapper dbaTables_db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, DbaTablesMapper.class);
        DbaTablesMapper dbaTables_db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d2, DbaTablesMapper.class);

        Map dbaTables_BaseMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tables.ConsCols_1");
        Map dbaTables_CompareMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tables.ConsCols_2");
        Map dbaTables_NotLikeMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tables.NotLikeMap");
        List<String> dbaTables_AppendPlusList = PropertyUtils.getPropertyToList("dbcompare", "Dba_tables.AppendPlus");
        List<String> dbaTables_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "Dba_tables.CompareCols");


        List<DbaTables> dbaTables_BaseList = dbaTables_db1.getDba_tablesByPros(dbaTables_BaseMap, dbaTables_NotLikeMap, dbaTables_AppendPlusList);
        List<DbaTables> dbaTables_TargetList = dbaTables_db2.getDba_tablesByPros(dbaTables_CompareMap, dbaTables_NotLikeMap, dbaTables_AppendPlusList);

        //比较方法
        Map<String, List> resultMap = CompareUtils.compareList(dbaTables_BaseList, dbaTables_TargetList, dbaTables_CompareCols);

        // 下载后文件的名称
        String fileName = "DbTestExcel.xls";

        String bigTitle1 = "DB1有，DB2中无";
        List<Map> baseMapNoMatchList = resultMap.get("baseMapNoMatchList");
        String bigTitle2 = "DB1有，DB2也有";
        List<Map> baseMapAllMatchList = resultMap.get("baseMapAllMatchList");
        String bigTitle3 = "DB1有，DB2也有，但是匹配字段不完全一致";
        List<Map> baseMapPartMatchList = resultMap.get("baseMapPartMatchList");
        String bigTitle4 = "DB1无，DB2有";
        List<Map> targetMapNoMatchList = resultMap.get("targetMapNoMatchList");


        // excel的标题
        List<String> titleTables = dbaTables_CompareCols;
        //sheet名
        String sheetName = "DbaTables";
        // 建一个二维数组，前面放行 （每行数据），后面放列 （每列标题）
        List<Map> resultList = new ArrayList<>();
        resultList.addAll(baseMapNoMatchList);

        // 建HSSFWorkbook
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        int rowCount = 0;
        //HSSFWorkbook wb, String sheetName, String bigTitle, List<String> titles, List<Map> values, int rowCount
        wb = ExcelUtils.getHSSFWorkbookForDb(wb, sheetName, bigTitle1, titleTables, baseMapNoMatchList, rowCount);
        wb = ExcelUtils.getHSSFWorkbookForDb(wb, sheetName, bigTitle2, titleTables, baseMapAllMatchList, null);
        wb = ExcelUtils.getHSSFWorkbookForDb(wb, sheetName, bigTitle3, titleTables, baseMapPartMatchList, null);
        wb = ExcelUtils.getHSSFWorkbookForDb(wb, sheetName, bigTitle4, titleTables, targetMapNoMatchList, null);

        //ExcelUtils.exportExcelToDesk(wb, "d:\\" +fileName+ ".xls");

        //字段比较
        List<Map> baseMapMatchList = resultMap.get("baseMapMatchList");
        //库表字段
        DbaTabColsMapper dbaCols_Db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, DbaTabColsMapper.class);
        DbaTabColsMapper dbaCols_Db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d2, DbaTabColsMapper.class);

        Map dbaCols_BaseMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tab_cols.ConsCols_1");
        Map dbaCols_CompareMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tab_cols.ConsCols_2");
        Map dbaCols_NotLikeMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tab_cols.NotLikeMap");
        List<String> dbaCols_AppendPlusList = PropertyUtils.getPropertyToList("dbcompare", "Dba_tab_cols.AppendPlus");

        //每次循环一个表名 匹配列
        List<String> dbaCols_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "Dba_tab_cols.CompareCols");
        for (Map baseMapMatch : baseMapMatchList) {

            String sameTableName = (String) baseMapMatch.get("tableName");
            dbaCols_BaseMap.put("TABLE_NAME", sameTableName);
            dbaCols_CompareMap.put("TABLE_NAME", sameTableName);

            List<DbaTabCols> dbaCols_BaseList = dbaCols_Db1.getDba_tab_colsByPros(dbaCols_BaseMap, dbaCols_NotLikeMap, dbaCols_AppendPlusList);
            List<DbaTabCols> dbaCols_TargetList = dbaCols_Db2.getDba_tab_colsByPros(dbaCols_CompareMap, dbaCols_NotLikeMap, dbaCols_AppendPlusList);

            //比较方法
            Map<String, List> resultColMap = CompareUtils.compareList(dbaCols_BaseList, dbaCols_TargetList, dbaCols_CompareCols);

            List<Map> colsBaseMapNoMatchList = resultColMap.get("baseMapNoMatchList");
            List<Map> colsBaseMapAllMatchList = resultColMap.get("baseMapAllMatchList");
            List<Map> colsBaseMapPartMatchList = resultColMap.get("baseMapPartMatchList");
            List<Map> colsTargetMapNoMatchList = resultColMap.get("targetMapNoMatchList");

            String sheetNameCols = "Dba_tab_cols";
            List<String> titleCols = dbaCols_CompareCols;

            wb = ExcelUtils.getHSSFWorkbookForDb(wb, sheetNameCols, bigTitle1, titleCols, colsBaseMapNoMatchList, null);
            wb = ExcelUtils.getHSSFWorkbookForDb(wb, sheetNameCols, bigTitle2, titleCols, colsBaseMapAllMatchList, null);
            wb = ExcelUtils.getHSSFWorkbookForDb(wb, sheetNameCols, bigTitle3, titleCols, colsBaseMapPartMatchList, null);
            wb = ExcelUtils.getHSSFWorkbookForDb(wb, sheetNameCols, bigTitle4, titleCols, colsTargetMapNoMatchList, null);

            ExcelUtils.exportExcelToDesk(wb, "d:\\" +fileName+ ".xls");

            //输出结果
            logger.info("=============当前处理的表名是：" + baseMapMatch.get("tableName") + "=============");
            CompareUtils.soutResult(resultColMap, "columnName", dbaCols_CompareCols);
        }
    }


    /**
     * @description: 比较器测试 - 存储过程/触发器
     * @param
     * @return: void
     * @author: XuDong
     * @time: 2020/12/8 10:32
     */
    @Test
    public void test_Procedure() throws IOException, IllegalAccessException {
        UserProceduresMapper uPrecedure_db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, UserProceduresMapper.class);
        UserProceduresMapper uPrecedure_db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d2, UserProceduresMapper.class);

        Map uPrecedure_BaseMap = PropertyUtils.getPropertyToMap("dbcompare", "User_Procedures.ConsCols_1");
        Map uPrecedure_CompareMap = PropertyUtils.getPropertyToMap("dbcompare", "User_Procedures.ConsCols_2");
        Map uPrecedure_NotLike = PropertyUtils.getPropertyToMap("dbcompare", "User_Procedures.NotLikeMap");
        List<String> uPrecedure_AppendPlusList = PropertyUtils.getPropertyToList("dbcompare", "User_Procedures.AppendPlus");
        List<String> uPrecedure_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "User_Procedures.CompareCols");

        List<UserProcedures> uPrecedure_BaseList = uPrecedure_db1.getUser_proceduresByPros(uPrecedure_BaseMap, uPrecedure_NotLike, uPrecedure_AppendPlusList);
        List<UserProcedures> uPrecedure_TargetList = uPrecedure_db2.getUser_proceduresByPros(uPrecedure_CompareMap, uPrecedure_NotLike, uPrecedure_AppendPlusList);

        //比较方法
        Map<String, List> resultMap = CompareUtils.compareList(uPrecedure_BaseList, uPrecedure_TargetList, uPrecedure_CompareCols);

        //输出结果
        CompareUtils.soutResult(resultMap, "uPrecedure", uPrecedure_CompareCols);


        //存储过程细节对比
        List<Map> baseMapMatchList = resultMap.get("baseMapMatchList");
        //库表字段
        UserSourceMapper uSource_Db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, UserSourceMapper.class);
        UserSourceMapper uSource_Db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d2, UserSourceMapper.class);

        //每次循环一个表名 匹配列
        for (Map baseMapMatch : baseMapMatchList) {

            List<String> uSource_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "User_Source.CompareCols");
            String objectName = (String) baseMapMatch.get("objectName");
            String objectType = (String) baseMapMatch.get("objectType");


            List<UserSource> uSource_BaseList = uSource_Db1.getUser_sourceByNameAndType(objectName, objectType);
            List<UserSource> uSource_TargetList = uSource_Db2.getUser_sourceByNameAndType(objectName, objectType);


            logger.info("=========================================================");
            //比较方法
            Map<String, List> resultColMap = CompareUtils.compareListByLine(uSource_BaseList, uSource_TargetList, uSource_CompareCols);

            //输出结果
            logger.info("=============当前处理的" + "类型是：" + objectType + "    存储过程名是：" +objectName + "=============");
            CompareUtils.soutResult(resultColMap, objectName, uSource_CompareCols);
        }
    }


    /**
     * @description: 比较器测试 - 约束
     * @param
     * @return: void
     * @author: XuDong
     * @time: 2020/12/8 10:32
     */
    @Test
    public void test_Constraints() throws IOException, IllegalAccessException {
        UserConstraintsMapper uConstr_db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, UserConstraintsMapper.class);
        UserConstraintsMapper uConstr_db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d2, UserConstraintsMapper.class);
        Map uConstr_BaseMap = PropertyUtils.getPropertyToMap("dbcompare", "User_Constraints.ConsCols_1");
        Map uConstr_CompareMap = PropertyUtils.getPropertyToMap("dbcompare", "User_Constraints.ConsCols_2");
        Map uConstr_NotLike = PropertyUtils.getPropertyToMap("dbcompare", "User_Constraints.NotLikeMap");
        List<String> uConstr_AppendPlusList = PropertyUtils.getPropertyToList("dbcompare", "User_Constraints.AppendPlus");
        List<String> uConstr_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "User_Constraints.CompareCols");

        List<UserConstraints> uConstr_BaseList = uConstr_db1.getUser_ConstraintsByPros(uConstr_BaseMap, uConstr_NotLike, uConstr_AppendPlusList);
        List<UserConstraints> uConstr_TargetList = uConstr_db2.getUser_ConstraintsByPros(uConstr_CompareMap, uConstr_NotLike, uConstr_AppendPlusList);

        //比较方法
        Map<String, List> resultMap = CompareUtils.compareList(uConstr_BaseList, uConstr_TargetList, uConstr_CompareCols);

        //输出结果
        CompareUtils.soutResult(resultMap, "uConstr", uConstr_CompareCols);
    }


    /**
     * @description: 比较器测试 - 索引
     * @param
     * @return: void
     * @author: XuDong
     * @time: 2020/12/8 10:32
     */
    @Test
    public void test_Indexs() throws IOException, IllegalAccessException {
        DbaIndColumnsMapper dbaIndex_db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, DbaIndColumnsMapper.class);
        DbaIndColumnsMapper dbaIndex_db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d2, DbaIndColumnsMapper.class);
        Map dbaIndex_BaseMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_ind_columns.ConsCols_1");
        Map dbaIndex_CompareMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_ind_columns.ConsCols_2");
        Map dbaIndex_NotLikeMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_ind_columns.NotLikeMap");
        List<String> dbaIndex_AppendPlusList = PropertyUtils.getPropertyToList("dbcompare", "Dba_ind_columns.AppendPlus");
        List<String> dbaIndex_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "Dba_ind_columns.CompareCols");

        List<DbaIndColumns> dbaIndex_BaseList = dbaIndex_db1.getDba_ind_columnsByPros(dbaIndex_BaseMap, dbaIndex_NotLikeMap, dbaIndex_AppendPlusList);
        List<DbaIndColumns> dbaIndex_TargetList = dbaIndex_db2.getDba_ind_columnsByPros(dbaIndex_CompareMap, dbaIndex_NotLikeMap, dbaIndex_AppendPlusList);

        //比较方法
        Map<String, List> resultMap = CompareUtils.compareList(dbaIndex_BaseList, dbaIndex_TargetList, dbaIndex_CompareCols);

        //输出结果
        CompareUtils.soutResult(resultMap, "dbaIndex", dbaIndex_CompareCols);


        logger.info("===================");

        UserIndexesMapper uIndex_db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, UserIndexesMapper.class);
        UserIndexesMapper uIndex_db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d2, UserIndexesMapper.class);
        Map uIndex_BaseMap = PropertyUtils.getPropertyToMap("dbcompare", "User_indexes.ConsCols_1");
        Map uIndex_CompareMap = PropertyUtils.getPropertyToMap("dbcompare", "User_indexes.ConsCols_2");
        Map uIndex_NotLikeMap = PropertyUtils.getPropertyToMap("dbcompare", "User_indexes.NotLikeMap");
        List<String> uIndex_AppendPlusList = PropertyUtils.getPropertyToList("dbcompare", "User_indexes.AppendPlus");

        List<String> uIndex_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "User_indexes.CompareCols");
        List<UserIndexes> uIndex_BaseList = uIndex_db1.getUser_indexesByPros(uIndex_BaseMap, uIndex_NotLikeMap, uIndex_AppendPlusList);
        List<UserIndexes> uIndex_TargetList = uIndex_db2.getUser_indexesByPros(uIndex_CompareMap, uIndex_NotLikeMap, uIndex_AppendPlusList);

        //比较方法
        Map<String, List> uIndex_resultMap = CompareUtils.compareList(uIndex_BaseList, uIndex_TargetList, uIndex_CompareCols);

        //输出结果
        CompareUtils.soutResult(uIndex_resultMap, "uIndex", uIndex_CompareCols);
    }


	/**
	 * @description: 比较器测试 - 表与列测试
	 * @param
	 * @return: void
	 * @author: XuDong
	 * @time: 2020/12/7 15:01
	 */
	@Test
	public void test_TableAndCols() throws IOException, IllegalAccessException {

        //库表
        //DbaTablesMapper dbaTables_db1 = MapperFactory.createMapper(DbaTablesMapper.class, DataSourceEnum.d1);
        //DbaTablesMapper dbaTables_db2 = MapperFactory.createMapper(DbaTablesMapper.class, DataSourceEnum.d2);

        DbaTablesMapper dbaTables_db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, DbaTablesMapper.class);
        DbaTablesMapper dbaTables_db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d2, DbaTablesMapper.class);

        Map dbaTables_BaseMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tables.ConsCols_1");
        Map dbaTables_CompareMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tables.ConsCols_2");
        Map dbaTables_NotLikeMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tables.NotLikeMap");
        List<String> dbaTables_AppendPlusList = PropertyUtils.getPropertyToList("dbcompare", "Dba_tables.AppendPlus");
        List<String> dbaTables_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "Dba_tables.CompareCols");


        List<DbaTables> dbaTables_BaseList = dbaTables_db1.getDba_tablesByPros(dbaTables_BaseMap, dbaTables_NotLikeMap, dbaTables_AppendPlusList);
        List<DbaTables> dbaTables_TargetList = dbaTables_db2.getDba_tablesByPros(dbaTables_CompareMap, dbaTables_NotLikeMap, dbaTables_AppendPlusList);

        //比较方法
        Map<String, List> resultMap = CompareUtils.compareList(dbaTables_BaseList, dbaTables_TargetList, dbaTables_CompareCols);

        //输出结果
        CompareUtils.soutResult(resultMap, "tableName", dbaTables_CompareCols);

        List<Map> baseMapNoMatchList = resultMap.get("baseMapNoMatchList");
        List<Map> baseMapMatchList = resultMap.get("baseMapMatchList");
        //库表字段
        DbaTabColsMapper dbaCols_Db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, DbaTabColsMapper.class);
        DbaTabColsMapper dbaCols_Db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d2, DbaTabColsMapper.class);

        Map dbaCols_BaseMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tab_cols.ConsCols_1");
        Map dbaCols_CompareMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tab_cols.ConsCols_2");
        Map dbaCols_NotLikeMap = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tab_cols.NotLikeMap");
        List<String> dbaCols_AppendPlusList = PropertyUtils.getPropertyToList("dbcompare", "Dba_tab_cols.AppendPlus");

        //每次循环一个表名 匹配列
        for (Map baseMapMatch : baseMapMatchList) {

            String sameTableName = (String) baseMapMatch.get("tableName");
            dbaCols_BaseMap.put("TABLE_NAME", sameTableName);
            dbaCols_CompareMap.put("TABLE_NAME", sameTableName);

            List<String> dbaCols_CompareCols = PropertyUtils.getPropertyToList("dbcompare", "Dba_tab_cols.CompareCols");

            List<DbaTabCols> dbaCols_BaseList = dbaCols_Db1.getDba_tab_colsByPros(dbaCols_BaseMap, dbaCols_NotLikeMap, dbaCols_AppendPlusList);
            List<DbaTabCols> dbaCols_TargetList = dbaCols_Db2.getDba_tab_colsByPros(dbaCols_CompareMap, dbaCols_NotLikeMap, dbaCols_AppendPlusList);

            //比较方法
            Map<String, List> resultColMap = CompareUtils.compareList(dbaCols_BaseList, dbaCols_TargetList, dbaCols_CompareCols);

            //输出结果
            logger.info("=============当前处理的表名是：" + baseMapMatch.get("tableName") + "=============");
            CompareUtils.soutResult(resultColMap, "columnName", dbaCols_CompareCols);
        }
	}

    //查询Dba_tab_cols
	@Test
	public void test_Dba_tab_clos_MapperFactory() throws IOException {

		//表
		//DbaTablesMapper mapper = MapperFactory.createMapper(DbaTablesMapper.class, DataSourceEnum.d1);
		//Map map = PropertyUtils.getPropertyToMap("DbaTables.constantCol");
		//List<DbaTables> resultList = mapper.getDba_tablesByPros(map);

		//表列
		//DbaTabColsMapper mapper = MapperFactory.createMapper(DbaTabColsMapper.class, DataSourceEnum.d1);
		//Map map = PropertyUtils.getPropertyToMap("DbaTabCols.constantCol");
		//List<DbaTabCols> resultList = mapper.getDba_tab_colsByPros(map);

		//索引
		//DbaIndColumnsMapper mapper = MapperFactory.createMapper(DbaIndColumnsMapper.class, DataSourceEnum.d1);
		//Map map = PropertyUtils.getPropertyToMap("DbaIndColumns.constantCol");
		//List<DbaIndColumns> resultList = mapper.getDba_ind_columnsByPros(map);

		//索引
		//UserIndexesMapper mapper = MapperFactory.createMapper(UserIndexesMapper.class, DataSourceEnum.d1);
		//Map map = PropertyUtils.getPropertyToMap("UserIndexes.constantCol");
		//List<UserIndexes> resultList = mapper.getUser_indexesByPros(map);

		//约束
		//UserConstraintsMapper mapper = MapperFactory.createMapper(UserConstraintsMapper.class, DataSourceEnum.d1);
		//Map map = PropertyUtils.getPropertyToMap("UserConstraints.constantCol");
		//List<UserConstraints> resultList = mapper.getUser_ConstraintsByPros(map);

		//存储过程与函数
		//UserProceduresMapper mapper = MapperFactory.createMapper(UserProceduresMapper.class, DataSourceEnum.d1);
		//Map map = PropertyUtils.getPropertyToMap("UserProcedures.constantCol");
		//List<UserProcedures> resultList = mapper.getUser_proceduresByPros(map);

		//触发器
		//UserTriggersMapper mapper = MapperFactory.createMapper(UserTriggersMapper.class, DataSourceEnum.d1);
		//Map map = PropertyUtils.getPropertyToMap("User_triggers.constantCol");
		//List<UserTriggers> resultList = mapper.getUser_triggersByPros(map);

		//logger.info(""+resultList.size());

		//for (int i = 0; i < 5; i++) {
		//	DbaTabCols result = dba_tablesList.get(i);
		//	System.out.println(result.toString());
		//}
	}


    //DbaTables
	@Test
	public void test_Dba_tables_MapperFactory() throws IOException {

		DbaTablesMapper mapper = MapperFactory.createMapper(DbaTablesMapper.class, DataSourceEnum.d1);

		//通过OWNER查询
		//List<DbaTables> dba_tablesList = mapper.getDba_tablesByOwner("HEAD");

		//通过配置查询
		Map map = PropertyUtils.getPropertyToMap("dbcompare", "Dba_tables.ConsCols_1");
		List<DbaTables> dba_tablesList = mapper.getDba_tablesByPros(map, null, null);

		logger.info(""+dba_tablesList.size());

		//for (int i = 0; i < 10; i++) {
		//	DbaTables result = dba_tablesList.get(i);
		//	System.out.println(result.toString());
		//}
	}


	@Test
	public void test_Dba_tables() throws IOException {
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = DataSourceSqlSessionFactory.getSqlSessionFactory(DataSourceEnum.d2);
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3、获取接口的实现类对象
			//会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
			DbaTablesMapper mapper = openSession.getMapper(DbaTablesMapper.class);
			List<DbaTables> dba_tablesList= mapper.getDba_tablesByOwner("HEAD");
			System.out.println(mapper.getClass());

			for (int i = 0; i < 10; i++) {
				DbaTables dba_tables = dba_tablesList.get(i);
				System.out.println(dba_tables.toString());
			}

		} finally {
			openSession.close();
		}
	}

}
