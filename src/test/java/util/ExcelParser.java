package util;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import page.ProductPage;
import pojo.ProductModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelParser {

    public Sheet getSheetFromFile(String filePath) {
        try (FileInputStream file = new FileInputStream(new File(filePath))) {
            Workbook workbook = new XSSFWorkbook(file);
            return workbook.getSheetAt(0);
        } catch (IOException e) {
            return null;
        }
    }

    public void initSheet(Sheet sheet){
        sheet.createRow(0).createCell(0).setCellValue("ARTICLE");
        sheet.getRow(0).createCell(1).setCellValue("NAME");
        sheet.getRow(0).createCell(2).setCellValue("MANUFACTURER");
        sheet.getRow(0).createCell(3).setCellValue("PRICE");
        sheet.getRow(0).createCell(4).setCellValue("WAREHOUSE");
    }

    public void writeProductsToSheet(Sheet sheet, List<ProductModel> products){
        int initialSize = sheet.getLastRowNum();
        for(int i=0; i<products.size();i++){
            sheet.createRow(initialSize+i+1).createCell(0).setCellValue(products.get(i).getArticle());
            sheet.getRow(initialSize+i+1).createCell(1).setCellValue(products.get(i).getName());
            sheet.getRow(initialSize+i+1).createCell(2).setCellValue(products.get(i).getManufacturer());
            sheet.getRow(initialSize+i+1).createCell(3).setCellValue(products.get(i).getPrice());
            sheet.getRow(initialSize+i+1).createCell(4).setCellValue(products.get(i).getWarehouse());
        }
    }

    public void saveToFile(Workbook workbook, String fileName){
        try(FileOutputStream outputStream = new FileOutputStream(fileName)){
            workbook.write(outputStream);
            workbook.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
