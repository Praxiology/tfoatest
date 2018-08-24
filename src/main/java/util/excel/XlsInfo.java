package util.excel;

import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.util.List;
import java.util.Map;

public class XlsInfo {

    public static void main(String[] args) throws Exception {
        getXlsInfo();
    }


    public static final String file = "E:\\temp\\export\\12_12_21.xls";

    public static void getXlsInfo() throws Exception {
        File finalXlsxFile = new File(file);
        if (!finalXlsxFile.exists()) {
            System.err.println(finalXlsxFile.getName()+" is not exist!");
            return;
        }
        HSSFWorkbook workBook = (HSSFWorkbook) ExcelUtil.getWorkbok(finalXlsxFile);
        //workBook.sheetIterator();
        HSSFSheet sheet = workBook.getSheetAt(0);
        List<CellRangeAddress> magirninfo = sheet.getMergedRegions();
        System.err.printf("%s\n" , JSON.toJSON(magirninfo));

        for (int i = 0; i <= 100; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row != null) {
                for (int j = 0; j <= 100; j++) {
                    HSSFCell cell = row.getCell(j);
                    if (cell != null) {
                        System.err.printf("value:%s,address:[%d,%d]\n" ,cell.toString(), cell.getRowIndex(),cell.getColumnIndex());
                    } else {

                    }
                }
            } else {
            }

        }

    }
}
