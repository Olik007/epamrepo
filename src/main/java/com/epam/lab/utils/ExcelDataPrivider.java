package com.epam.lab.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelDataPrivider {
	private static final String filePath = System.getProperty("user.dir") + "/src/main/resources/excelData1User.xlsx";
	
	@DataProvider(name = "getUsersData", parallel = false)
	public Object[][] setGmailProperties() {
		Object [][] data = null;
        try (InputStream fileStream = new FileInputStream(filePath)) {

            @SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            data = new Object[sheet.getLastRowNum()][1];            
            Iterator<?> rowIterator = sheet.rowIterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                XSSFRow currentRow = (XSSFRow) rowIterator.next();
                Iterator<?> cellIterator = currentRow.cellIterator();                
                
                while (cellIterator.hasNext()) {
                    XSSFCell currentCell = (XSSFCell) cellIterator.next();
                    int rowIndex = currentCell.getRowIndex()-1;
                    int colIndex  = currentCell.getColumnIndex() -1;
                    data[rowIndex][colIndex] = currentCell.getStringCellValue();
                }               
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return data;
  }  
 	
}
