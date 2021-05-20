package common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * A very simple program that writes some data to an Excel file using the Apache
 * POI library.
 *
 * @author www.codejava.net
 *
 */
public class ExcelPrinter {

    public XSSFWorkbook workbook;
    public String excelName;

    public ExcelPrinter(String name) {
        workbook = new XSSFWorkbook();
        excelName = name;
    }

    public void add(Object[][] data, String sheetName, int rowNr) {

        XSSFSheet sheet;
        sheet = workbook.createSheet(sheetName);

        int rowCount = rowNr;

        for (Object[] aBook : data) {
            Row row = sheet.createRow(rowCount);
            rowCount++;
            int columnCount = 0;

            for (Object field : aBook) {
                System.out.println("field: " + field);
                Cell cell = row.createCell(columnCount);
                columnCount++;

                if (field instanceof String) {
                    cell.setCellValue((String) field);

                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);

                } else if (field instanceof Double) {
                    cell.setCellValue((Double) field);

                }
            }
        }
    }

    public void write() throws IOException {
        FileOutputStream out = new FileOutputStream("/Users/magnusjohansson/Dokument/MVT20/Testtekniker/Multithlon" + excelName + ".xlsx");
        workbook.write(out);
        workbook.close();
    }

    public String getCellInfo(String excelName, int sheetNumber, int rowNumber, int colNumber) throws IOException {
        File excelfile = new File("/Users/magnusjohansson/Dokument/MVT20/Testtekniker/Multithlon" + excelName + ".xlsx");
        FileInputStream fis = new FileInputStream(excelfile);

        @SuppressWarnings("resource")
        XSSFWorkbook wb = new XSSFWorkbook(fis);

        Sheet sheet = wb.getSheetAt(sheetNumber);
        Row row = sheet.getRow(rowNumber);
        Cell cell = row.getCell(colNumber);

        DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.formatCellValue(cell);

    }
}
