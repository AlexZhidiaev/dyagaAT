package step;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import page.ProductPage;
import page.SearchPage;
import pojo.ProductModel;

import java.util.ArrayList;
import java.util.List;

import static config.ConfigValue.WAREHOUSE;

public class SearchSteps {
    public final SearchPage searchPage = new SearchPage();
    public final ProductPage productPage = new ProductPage();
    public final ProductSteps productSteps = new ProductSteps();

    @Step("Вводим в поле поиска артикул {0}, нажимаем кнопку поиска")
    public void performSearch(String articleNum) {
        searchPage.searchInput.clear();
        searchPage.searchInput.sendKeys(articleNum);
        searchPage.searchSubmitButton.click();
    }

    @Step("Кликаем по элементу в списке найденных товаров")
    public void clickFoundItem(int foundProductIndex) {
        searchPage.foundItems.get(foundProductIndex).$x(".//td[@class='caseInfo']")
                .scrollIntoView(false)
                .click();
    }

    @Step("Получаем информацию обо всех продуктах в поиске")
    public List<ProductModel> getAllProducts(String articleNum) {///TODO:разделить на подметоды
        performSearch(articleNum);
        if (searchPage.searchAlert.isDisplayed()) {
            return List.of(ProductModel.builder().article(articleNum).price("INVALID ARTICLE").build());
        } else if (searchPage.singleResultTable.isDisplayed()) {
            SelenideElement item;
            if (searchPage.singleResultItems.size() > 1) {
                if (searchPage.singleResultItems.get(0).$x(".//td[contains(@class,'resultWarehouse')]").getText().equals(WAREHOUSE.getValue())) {
                    item = searchPage.singleResultItems.get(1);
                } else {
                    item = searchPage.singleResultItems.get(0);
                    if(item.$x(".//td[@class='resultAvailability noavail']").isDisplayed()){
                        return List.of(ProductModel.builder().article(articleNum).price("NOT AVAILABLE").build());
                    }
                }
                return List.of(ProductModel.builder().article(articleNum)
                        .price(item.$x(".//td[@class='resultPrice ']").getText())
                        .warehouse(item.$x(".//td[contains(@class,'resultWarehouse')]").getText())
                        .manufacturer(item.$x(".//td[@class='resultBrand resultInline ']").getText())
                        .name(item.$x(".//td[@class='resultDescription  verticalAlignCenter']").getText())
                        .build());
            } else {
                return List.of(ProductModel.builder().article(articleNum).price("NOT AVAILABLE").build());
            }
        } else {
            List<ProductModel> allProducts = new ArrayList<>();
            SelenideElement item;
            for (int i = 0; i < searchPage.foundItems.size(); i++) {
                item = searchPage.foundItems.get(i);
                ProductModel productModel = new ProductModel();
                if(item.$x(".//td[@class='noavail caseDescription']").exists()){
                    productModel.setName(item.$x(".//td[@class='noavail caseDescription']").getText());
                    productModel.setManufacturer(item.$x(".//td[@class='caseBrand noavail']").getText());
                }
                else{
                    productModel.setName(item.$x(".//td[@class=' caseDescription']").getText());
                    productModel.setManufacturer(item.$x(".//td[@class='caseBrand ']").getText());
                }
                productModel.setArticle(articleNum);
                clickFoundItem(i);

                Selenide.switchTo().window(1);
                ProductModel fullInfo = productSteps.getProduct();
                productModel.setWarehouse(fullInfo.getWarehouse());
                productModel.setPrice(fullInfo.getPrice());
                allProducts.add(productModel);
                Selenide.closeWindow();
                Selenide.switchTo().window(0);
            }
            return allProducts;
        }
    }

}
