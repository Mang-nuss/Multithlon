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

    public void add(Object[][] data, XSSFSheet sheet, int colNr) {

        //Row row;
/*        XSSFSheet sheet;
        sheet = workbook.createSheet(sheetName);*/

/*
        int nr = 0;
        String[] cols;
        cols = new String[]{"name", "disc1", "disc2"};
        row = sheet.createRow(nr);
        int rowCount = 0;
        for(String s : cols) {
            Cell cell = row.createCell(nr++);
            cell.setCellValue(s);
        }
        //columnCount++;*/

        int columnCount = colNr;
        int rowCount = 1;
        Row row = sheet.createRow(rowCount);

        for (Object[] aBook : data) {
            //row = sheet.createRow(columnCount);
            //columnCount++;

            for (Object field : aBook) {
                //System.out.println("field: " + columnCount + " - " + field);

                //System.out.println("columnCount = " + columnCount);
                Cell cell = row.createCell(columnCount);
                //rowCount++;

                if (field instanceof String) {
                    cell.setCellValue((String) field);

                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);

                } else if (field instanceof Double) {
                    cell.setCellValue((Double) field);

                }

                //rowCount++;
            }

            columnCount++;
        }

        //columnCount = 0;
    }

    /*
    TODO: Handle the closing of document!
    *  */
    public void write() throws IOException {
        FileOutputStream out = new FileOutputStream("/Users/magnusjohansson/Dokument/MVT20/Testtekniker/Multithlon" + excelName + ".xlsx");
        workbook.write(out);
        //workbook.close();
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
