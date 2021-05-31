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
    public Cell cell;

    public ExcelPrinter(String name) {
        workbook = new XSSFWorkbook();
        excelName = name;
    }

    public void add(Event evt, XSSFSheet sheet, int colNr) {

        int columnCount = 0;
        int rowCount = colNr;

        for (Users user : evt.users) {

            Row row = sheet.createRow(rowCount);
            cell = row.createCell(columnCount);
            cell.setCellValue(user.getUsername()); //Prints out the username in 1st column
            columnCount++;

            for (Object[] result : user.resultArray) {
                int n = 0;

                for (Object field : result) {

                    if (n != 0) {
                        if (field instanceof String) {
                            //System.out.println("discipline name skipped");
                            cell = row.createCell(columnCount);
                            cell.setCellValue((String) field);
                            columnCount++;
                        }
                    }

                    n++; //to skip the discipline name in outprint

                    if (field instanceof Integer) {
                        cell = row.createCell(columnCount);
                        cell.setCellValue((Integer) field);
                        columnCount++;

                    } else if (field instanceof Double) {
                        cell = row.createCell(columnCount);
                        cell.setCellValue((Double) field);
                        columnCount++;

                    }
                }
            }

            cell = row.createCell(columnCount);
            cell.setCellValue(user.score); //Adding the total score to last column.

            rowCount++; //Next user, next row
            columnCount = 0;
        }

        //columnCount = 0;
    }

    /*
    TODO: Handle the closing of document!
    *  */
    public void write() throws IOException {
        FileOutputStream out = new FileOutputStream("C:\\Users\\eva\\git\\Multithlon\\" + excelName + ".xlsx");
        workbook.write(out);
        //workbook.close();
    }

    public String getCellInfo(String excelName, int sheetNumber, int rowNumber, int colNumber) throws IOException {
        File excelfile = new File("C:\\Users\\eva\\git\\Multithlon\\" + excelName + ".xlsx");
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
