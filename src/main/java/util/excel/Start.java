package util.excel;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import util.DbUtil;

import java.io.*;
import java.util.*;

public class Start {
    public static String path = "E:\\temp\\oa\\7.xlsx";

    @Test
    public void writeExcel() {
        WriteExcel.dao.writeExcel(path);
    }
}

class Sql {

    public static String[] columns = {"column_name" , "data_type" , "column_comment" , "table_name"};

    public static String sql = DbUtil.getSql();

    public static String[] columnsAnnotation = {"字段" , "数据类型" , "注释" , "表名"};

    public static String[] tabs = {
            "oa_finance_report" ,
            "oa_finance_report_data_error" ,
            "oa_finance_report_formula_value" ,
            "oa_finance_report_metadata" ,
            "oa_finance_report_prostpone" ,
            "oa_finance_report_relation"};
    static {
        try {
            DbUtil.init();
            DbUtil.setTabs(tabs);
            DbUtil.setColumns(columns);
            DbUtil.setColumnsAnnotation(columnsAnnotation);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

}

class WriteExcel {

    public static WriteExcel dao = new WriteExcel();

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public void writeExcel(String finalXlsxPath) {
        OutputStream out = null;
        try {
            File finalXlsxFile = new File(finalXlsxPath);

            if (!finalXlsxFile.exists()) {
                System.err.println(finalXlsxFile.getName()+" is not exist!");
                return;
            }
            List<Map> dataList = ExportTablesInfo.dao.getTbInfoByName(Sql.tabs);
            // 获取总列数
            int columnNumCount = Sql.columns.length;
            // 读取Excel文档

            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
            System.out.println("old row.length,except col:"+rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out = new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            int currentRow = 0;
            for (int j = 0; j < dataList.size(); j++) {
                // 得到要插入的每一条记录
                Map dataMap = dataList.get(j);
                if ("id".equals(dataMap.get(Sql.columns[0]))) {
                    currentRow++;
                    //合并单元格
                    Row row0 = sheet.createRow((j-2)+(currentRow * 4));
                    Cell first0 = row0.createCell(0);
                    first0.setCellValue(dataMap.get(Sql.columns[3]).toString());
                    //注释
                    Row row = sheet.createRow((j-1)+(currentRow * 4));
                    for (int k = 0; k <= columnNumCount; k++) {
                        for (int cl = 0; cl < (Sql.columnsAnnotation.length-1); cl++) {
                            Cell first = row.createCell(cl);
                            first.setCellValue(Sql.columnsAnnotation[cl]);
                            CellStyle cellStyle = first.getCellStyle();
                            Font font = workBook.createFont();
                            font.setColor(Font.COLOR_RED);
                            cellStyle.setFont(font);
                            first.setCellStyle(cellStyle);
                        }
                    }
                }
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j+(currentRow * 4));
                for (int k = 0; k <= columnNumCount; k++) {
                    // 在一行内循环
                    for (int cl = 0; cl < (Sql.columns.length-1); cl++) {
                        Cell first = row.createCell(cl);
                        first.setCellValue(dataMap.get(Sql.columns[cl]).toString());
                        CellStyle cellStyle = first.getCellStyle();
                        Font font = workBook.createFont();
                        font.setColor(Font.COLOR_NORMAL);
                        cellStyle.setFont(font);
                        first.setCellStyle(cellStyle);
                    }
                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out = new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ok");
    }

    /**
     * 判断Excel的版本,获取Workbook
     *
     * @return
     * @throws IOException
     */
    public Workbook getWorkbok(File file) throws IOException {
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if (file.getName().endsWith(EXCEL_XLS)) {     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}

class ExportTablesInfo {

    public static ExportTablesInfo dao = new ExportTablesInfo();

    public List<Map> getTbInfoByName(String[] tabls) {
        List<String> tabs = Arrays.asList(tabls);
        List<Map> list = new ArrayList<Map>();
        if (CollectionUtils.isNotEmpty(tabs)) {
            for (String tb : tabs) {
                if (StringUtils.isNotBlank(tb)) {
                    List<Record> records = Db.find(Sql.sql , tb);
                    for (Record record : records) {
                        Map<String, String> dataMap = new HashMap<String, String>();
                        for (String column : Sql.columns) {
                            dataMap.put(column , record.getStr(column));
                        }
                        list.add(dataMap);
                    }
                }
            }
        }
        return list;
    }

}