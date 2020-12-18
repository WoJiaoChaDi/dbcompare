package com.chadi.dbcompare.utils;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @ClassName ExcelUtils
 * @Description TODO
 * @Author XuDong
 * @Date 2020/12/17 9:26
 * @Version 1.0
 **/
public class ExcelUtils {

    private static Log log = LogFactory.getLog(ExcelUtils.class);

    /**
     * @description: 填充Excel
     * @param sheetName
     * @param title
     * @param values
     * @return: org.apache.poi.hssf.usermodel.HSSFWorkbook
     * @author: XuDong
     * @time: 2020/12/17 17:20
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String[] title,String[][] values) {

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        // 创建一个居中格式
        style.setAlignment(HorizontalAlignment.CENTER);

        // 声明列对象
        HSSFCell cell = null;

        // 创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        // 创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < title.length; j++) {
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

    /**
     * @description: 将填充完数据的HSSFWorkbook导出到桌面
     * @param wb
* @param path
     * @return: void
     * @author: XuDong
     * @time: 2020/12/17 17:19
     */
    public static void exportExcelToDesk(HSSFWorkbook wb, String path) {
        try {
            OutputStream out = new FileOutputStream(path);
            wb.write(out);
            out.close();
        } catch (Exception e) {
            log.error("导出Excel异常！" + e.getMessage(), e);
        }
    }


    /**
     * @param sheetName
     * @param titles
     * @param values
     * @description: 填充Excel
     * @return: org.apache.poi.hssf.usermodel.HSSFWorkbook
     * @author: XuDong
     * @time: 2020/12/17 17:20
     */
    public static HSSFWorkbook getHSSFWorkbookForDb(HSSFWorkbook wb, String sheetName, String bigTitle, List<String> titles, List<Map> values, Integer rowCount) {

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.getSheet(sheetName);
        if (sheet == null) {
            sheet = wb.createSheet(sheetName);
        }
        //设置Sheet的单元格的默认宽度
        sheet.setDefaultColumnWidth(20);

        //如果没有传rowCount，则在已有的数据下拼接
        if (rowCount == null) {
            rowCount = sheet.getLastRowNum() + 1;
        }

        //准备创建单元格样式
        //大标题单元格样式
        HSSFCellStyle bigTitleCellStyle = wb.createCellStyle();
        bigTitleCellStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFFont fontRedBold = wb.createFont();
        fontRedBold.setColor((short) 4);////1-透明 2-红色 3-绿色 4-蓝色 5-亮黄 6-紫色 7-青蓝 8-黑色
        fontRedBold.setBold(true);
        bigTitleCellStyle.setFont(fontRedBold);

        //标题单元格样式
        HSSFCellStyle titleCellStyle = wb.createCellStyle();
        titleCellStyle.setAlignment(HorizontalAlignment.LEFT);
        HSSFFont font = wb.createFont();
        font.setColor(Font.COLOR_RED);
        font.setBold(true);
        titleCellStyle.setFont(font);

        //值单元格样式（突出）
        HSSFCellStyle valueCellStyle = wb.createCellStyle();
        HSSFFont valueFont = wb.createFont();
        valueFont.setColor((short) 4);
        valueFont.setBold(true);
        valueCellStyle.setFont(font);
        //设置背景色
        valueCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //valueCellStyle.setFillBackgroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        valueCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());


        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(rowCount);
        int bitTitleRow = rowCount;
        rowCount++;

        // 填写大标题
        HSSFCell cell = null;
        cell = row.createCell(0);
        cell.setCellValue(bigTitle);
        cell.setCellStyle(bigTitleCellStyle);
        // 单元格合并  四个参数分别是：起始行，起始列，结束行，结束列
        sheet.addMergedRegion(new CellRangeAddress(bitTitleRow, (bitTitleRow), 0, 5));


        // 创建标题
        row = sheet.createRow(rowCount);
        rowCount++;
        for (int i = 0; i < titles.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(titles.get(i));
            cell.setCellStyle(titleCellStyle);
        }


        //迭代填写数据
        for (int i = 0; i < values.size(); i++) {
            row = sheet.createRow(rowCount);
            rowCount++;
            for (int j = 0; j < titles.size(); j++) {
                //将内容按顺序赋给对应的列对象
                Map valueMap = values.get(i);
                String title = titles.get(j);

                HSSFCell valueCell = row.createCell(j);
                //去掉*号
                String key = CompareUtils.strToHumpAndNoStar(title);
                String value = (String) valueMap.get(key);


                //判断字段匹配失败，设置不同颜色 并对比写下错误的值
                if(CompareUtils.matchFlag_Yes_2.equals(valueMap.get(key + CompareUtils.keyMatchStatus))){
                    String diffValue = (String) valueMap.get(key + CompareUtils.diffValue);
                    valueCell.setCellValue(value + "|-diff-|" + diffValue);
                    valueCell.setCellStyle(valueCellStyle);
                }else{
                    valueCell.setCellValue(value);
                }
            }
        }

        return wb;
    }

    /**
     * @description: 填充Excel
     * @param sheetName
     * @param titles
     * @param values
     * @return: org.apache.poi.hssf.usermodel.HSSFWorkbook
     * @author: XuDong
     * @time: 2020/12/17 17:20
     */
    public static HSSFWorkbook getHSSFWorkbookForDb(String sheetName, List<String> titles, List<Map> values) {

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        // 创建一个居中格式
        style.setAlignment(HorizontalAlignment.CENTER);

        // 声明列对象
        HSSFCell cell = null;

        // 创建标题
        for (int i = 0; i < titles.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(titles.get(i));
            cell.setCellStyle(style);
        }

        // 创建内容
        for (int i = 0; i < values.size(); i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < titles.size(); j++) {
                //将内容按顺序赋给对应的列对象
                String key = CompareUtils.strToHumpAndNoStar(titles.get(j));
                row.createCell(j).setCellValue((String) values.get(i).get(key));
            }
        }

        return wb;
    }


    public static byte[] export(String sheetTitle, String[] title, List<?> list) {

        HSSFWorkbook wb = new HSSFWorkbook();//创建excel表
        HSSFSheet sheet = wb.createSheet(sheetTitle);
        sheet.setDefaultColumnWidth(20);//设置默认行宽

        //表头样式（加粗，水平居中，垂直居中）
        HSSFCellStyle cellStyle = wb.createCellStyle();
        //cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中   3.15版本后已过时
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        //设置边框样式
        //cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  3.15版本后已过时
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框

        HSSFFont fontStyle = wb.createFont();
        //fontStyle.setFontName();//设置头部字体
        fontStyle.setColor(Font.COLOR_RED);
        fontStyle.setBold(true);
        fontStyle.setItalic(true);
        fontStyle.setUnderline(Font.U_SINGLE);
        //fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 20);
        //f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        cellStyle.setFont(fontStyle);

        //标题样式（加粗，垂直居中）
        HSSFCellStyle cellStyle2 = wb.createCellStyle();
        cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        cellStyle2.setFont(fontStyle);

        //设置边框样式
        cellStyle2.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle2.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle2.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle2.setBorderRight(BorderStyle.THIN);//右边框

        //字段样式（垂直居中）
        HSSFCellStyle cellStyle3 = wb.createCellStyle();
        cellStyle3.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中

        //设置边框样式
        cellStyle3.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle3.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle3.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle3.setBorderRight(BorderStyle.THIN);//右边框

        //创建表头
        HSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(20);//行高

        HSSFCell cell = row.createCell(0);
        cell.setCellValue(sheetTitle);
        cell.setCellStyle(cellStyle);

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,(title.length-1)));

        //创建标题
        HSSFRow rowTitle = sheet.createRow(1);
        rowTitle.setHeightInPoints(20);

        HSSFCell hc;
        for (int i = 0; i < title.length; i++) {
            hc = rowTitle.createCell(i);
            hc.setCellValue(title[i]);
            hc.setCellStyle(cellStyle2);
        }

        byte result[] = null;

        ByteArrayOutputStream out = null;

        try {
            //创建表格数据
            Field[] fields;
            int i = 2;

            for (Object obj : list) {
                fields = obj.getClass().getDeclaredFields();

                HSSFRow rowBody = sheet.createRow(i);
                rowBody.setHeightInPoints(20);

                int j = 0;
                for (Field f : fields) {

                    f.setAccessible(true);

                    Object va = f.get(obj);
                    if (null == va) {
                        va = "";
                    }

                    hc = rowBody.createCell(j);
                    hc.setCellValue(va.toString());
                    hc.setCellStyle(cellStyle3);

                    j++;
                }

                i++;
            }

            out = new ByteArrayOutputStream();
            wb.write(out);
            result =  out.toByteArray();
        } catch (Exception ex) {
            Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(null != out){
                    out.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    wb.close();
                } catch (IOException ex) {
                    Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return result;
    }

    public static void exportExcelToDesk(String sheetTitle, String[] title, List<Object> list) {
        byte[] bytes = export(sheetTitle, title, list);

        try (OutputStream out = new FileOutputStream("d:\\e.xlsx")) {
            InputStream is = new ByteArrayInputStream(bytes);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = is.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            is.close();
            out.close();
        } catch (Exception e) {

        }
    }




}