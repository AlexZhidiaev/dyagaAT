package common;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pojo.ProductModel;
import step.SearchSteps;

import java.util.List;

import static util.ArticleFixer.checkArticle;

public class FilterTest extends BaseTest {

    private final SearchSteps searchSteps = new SearchSteps();
    Workbook workbook;
    String filePath = "src/test/resources/book1.xlsx";
    String outputFilepath = "src/test/resources/output/";
    Sheet outputSheet;

    @BeforeEach
    void precondition() {
        workbook = new XSSFWorkbook();
        outputSheet = workbook.createSheet("output");
    }

    @AfterEach
    void afterCondition() {

    }

    @Test
/*    @ParameterizedTest
    @MethodSource("xlsxIndices")*/
    void downloadAll() {
        Sheet sheet = excelParser.getSheetFromFile(filePath);
        excelParser.initSheet(outputSheet);
        for (Row row : sheet) {
            String article = switch (row.getCell(0).getCellType()) {
                case STRING -> row.getCell(0).getStringCellValue();
                case NUMERIC -> String.valueOf((long) row.getCell(0).getNumericCellValue());
                default -> null;
            };

            List<ProductModel> allProducts;
            if(checkArticle(article)){
                allProducts = searchSteps.getAllProducts(article);
            }
            else{
                allProducts = List.of(ProductModel.builder()
                        .article(article)
                        .name("ARTICLE SHOULD NOT BE NULL OR EMPTY")
                        .build());
            }
            excelParser.writeProductsToSheet(outputSheet, allProducts);
        }
        excelParser.saveToFile(workbook, outputFilepath + "output.xlsx");
    }
}
